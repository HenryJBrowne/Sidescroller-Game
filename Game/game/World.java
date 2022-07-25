import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

/**
 * Class responsible for physics and drawing
 */
public class World {
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> entitiesToCheck = new ArrayList<>();
    private ArrayList<Block> blocks = new ArrayList<>();
    private ArrayList<Block> blocksToCheck = new ArrayList<>();
    private ArrayList<Drawable> assets = new ArrayList<>();
    private ArrayList<ThrowingWeapon> projectiles = new ArrayList<>();
    private ArrayList<MeleeWeapon> meleeWeapon = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Trigger> triggers = new ArrayList<>();
    private boolean update;
    private final float gravity;
    private Background bg;
    private final Vector2f zeroV = new Vector2f(0, 0);
    private ArrayList<Block> doorBlocks = new ArrayList<>();
    private float airFriction = 0.9f;

    /**
     * Creates new instance with specified gravitational constant
     * 
     * @param g gravitational constant
     */

    public World(float g) {
        gravity = g;
        update = true;
    }

    /**
     * Updates every element on the screen
     * 
     * @param s screen to be updated
     */
    public void update(Screen s, MainCharacter eugene) {
        RenderWindow w = s.getWindow();
        ArrayList<Object> remove = new ArrayList<>();
        Vector2f posEugene = eugene.getPosition();

        for (Block b : blocks) {
            if (Math.abs(b.getPosition().x - posEugene.x) < 500 && Math.abs(b.getPosition().y - posEugene.y) < 500) {
                if (b instanceof TreasureChest) {
                    if (((TreasureChest) b).isOpen()) {
                        remove.add(b);
                        assets.add(b);
                        continue;
                    }
                }
                blocksToCheck.add(b);
            }
        }
        for (ThrowingWeapon t : projectiles) {
            if (t.delete()) {
                remove.add(t);
            } else {
                t.update(blocksToCheck, s);
            }
        }

        for (Item i : items) {
            if (Math.abs(i.getPosition().x - posEugene.x) < 320 && Math.abs(i.getPosition().y - posEugene.y) < 320) {
                i.pickUp(s.getMainCharacter());
                if (!i.isGrounded())
                    applyGravityToItem(i, i.getGround());
                if (i.pickedUp()) {
                    remove.add(i);
                }
            }
        }
        for (Object o : remove) {
            if (o instanceof ThrowingWeapon) {
                projectiles.remove((ThrowingWeapon) o);
            } else if (o instanceof Item) {
                items.remove((Item) o);
            } else if (o instanceof Block) {
                blocks.remove(o);
            }
        }
        for (Entity e : entities) {
            if (Math.abs(e.getPosition().x - posEugene.x) < 400 && Math.abs(e.getPosition().y - posEugene.y) < 400) {
                for (ThrowingWeapon t : projectiles) {
                    if (e.equals(t.origin) || !e.shouldCheck()) {
                        continue;
                    }
                    if (!t.delete()) {
                        if (e.isCollidingWith(t)) {
                            e.damage(t.getAttack());
                            t.delete(true);
                        }
                    }
                }
                for (MeleeWeapon mw : meleeWeapon) {
                    mw.checkForChest(blocksToCheck, s);
                    if (e.equals(mw.origin) || !e.shouldCheck()) {
                        continue;
                    }
                    if (!mw.delete()) {
                        if (e.isCollidingWith(mw)) {
                            e.damage(mw.getAttack());
                            if (!(mw.canMultiple())) {
                                mw.delete(true); // melee weapon can attack just one entity, only axe has a "swing"
                            }
                        }
                    }
                }
                applyPhysics(e, e.getGroundBlock(blocksToCheck), s);
                entitiesToCheck.add(e);
            }
        }
        drawCurrentScene(w, posEugene);
        if (triggers != null) {
            for (Trigger t : triggers) {
                t.checktotrigger(eugene, w);
            }
        }
        // pause(); set framerate in Screen class
    }

    /**
     * Pauses the world
     */
    public void pauseWorld() {
        update = false;
    }

    /**
     * Resumes the world from pause
     */
    public void resumeWorld() {
        update = true;
    }

    /**
     * Adds instance of entity so it will be updated
     * 
     * @param e entity to be updated
     */
    public void addEntity(Entity e) {
        entities.add(e);
    }

    public void addEntity(List<Entity> e) {
        entities.addAll(e);
    }

    /**
     * Removes instance of entity so it will not be updated
     * 
     * @param e entity to not be updated
     */
    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    public void removeProjectile(ThrowingWeapon t) {
        projectiles.remove(t);
    }

    public void addProjectile(ThrowingWeapon t) {
        projectiles.add(t);
    }

    public ArrayList<Entity> getEntitiesToCheck() {
        return entitiesToCheck;
    }

    public void applyGravityToItem(Item i, Block g) {
        if (i.getPosition().y + i.getSize().y + gravity < g.getPosition().y) {
            ((RectangleShape) i.getDrawable()).move(new Vector2f(0f, gravity));
        } else {
            i.setPosition(i.getPosition().x, g.getPosition().y - i.getSize().y);
            i.isGrounded(true);
        }
    }

    /**
     * Applies fricton and gravity to Entity
     * 
     * @param e      entity to be updated
     * @param ground ground block for specified entity
     */
    private void applyPhysics(Entity e, Block ground, Screen s) {
        if (!update) {
            return;
        }
        // debug(ground, s, e);
        // gravity
        if (ground == null) {
            if (e.getPosition().y > 2000) {// fell out of the map
                if (e instanceof Orvil) {
                    e.setPosition(s.getMainCharacter().getPosition()); // teleport orvil to eugene
                }
                e.setVelocity(zeroV);
                e.die(s);
                return;
            }
            e.setVelocity(new Vector2f(e.getVelocity().x, e.getVelocity().y + gravity));
            return;
        } else if (e.getPosition().y + e.getSize().y + e.getVelocity().y + gravity < ground.getPosition().y) {
            e.setVelocity(new Vector2f(e.getVelocity().x * airFriction, e.getVelocity().y + gravity));
            if (e.isGrounded()) { // if walked of the "cliff"
                e.isGrounded(false);
            }
        } else {
            // if the entity is still falling gound it
            if (!e.isGrounded() || e.getPosition().y + e.getSize().y + e.getVelocity().y < ground.getPosition().y) {
                e.setPosition(e.getPosition().x, ground.getPosition().y - e.getSize().y);
                e.setVelocity(new Vector2f(e.getVelocity().x, 0));
                e.isGrounded(true);
            }
            // friction - only when on the ground
            // stop the entity
            if (Math.abs(e.getVelocity().x) < 0.1 && !e.getVelocity().equals(zeroV)) {
                e.setVelocity(zeroV);
            } else if (!e.getVelocity().equals(zeroV)) {
                // apply friction
                e.setVelocity(new Vector2f(e.getVelocity().x * ground.getFrictionCoef(), e.getVelocity().y));
            }
        }

    }

    /**
     * Adds blocks to be drawn
     * 
     * @param blocks array of blocks
     */
    public void addBlocks(Block[] blocks) {
        if (blocks == null) {
            return;
        }

        for (Block b : blocks) {
            this.blocks.add(b);
        }
    }

    /**
     * Adds blocks to be drawn
     * 
     * @param blocks List of blocks
     */
    public void addBlocks(List<Drawable> blocks) {
        if (blocks == null) {
            return;
        }
        for (Drawable b : blocks) {
            if (b instanceof Block)
                this.blocks.add((Block) b);
        }

    }

    private void debug(Block ground, Screen s, Entity e) {
        Block left = e.findLeftBlock(blocks);
        Block right = e.findRightBlock(blocks);
        Block top = e.findTopBlock(blocks);
        if (left != null) {
            left.getRect().setOutlineColor(Color.RED);
            left.getRect().setOutlineThickness(2f);
            s.getWindow().draw(left);
        }
        if (right != null) {
            right.getRect().setOutlineColor(Color.GREEN);
            right.getRect().setOutlineThickness(2f);
            s.getWindow().draw(right);
        }
        if (top != null) {
            top.getRect().setOutlineColor(Color.WHITE);
            top.getRect().setOutlineThickness(2f);
            s.getWindow().draw(top);
        }
        if (ground != null) {
            ground.getRect().setOutlineColor(Color.BLUE);
            ground.getRect().setOutlineThickness(2f);
            s.getWindow().draw(ground);
        }
        RectangleShape ent = new RectangleShape(new Vector2f(e.getDrawable().getTexture().getSize()));
        ent.setPosition(e.getPosition());
        ent.setOutlineColor(Color.GREEN);
        ent.setOutlineThickness(2f);
        s.getWindow().draw(ent);
    }

    public void addItem(Item i) {
        items.add(i);
    }

    public ArrayList<Block> getBlocksToCheck() {
        return blocksToCheck;
    }

    public void resetThingsToCheck() {
        blocksToCheck = new ArrayList<>();
        entitiesToCheck = new ArrayList<>();
        meleeWeapon = new ArrayList<>(); // delete all melee
    }

    public void addAssets(Collection<Drawable> elements) {
        for (Drawable d : elements) {
            assets.add((Asset) d);
        }
    }

    public void addAsset(Asset a) {
        assets.add(a);
    }

    public void setBackground(Background background) {
        this.bg = background;
    }

    public void addMeele(MeleeWeapon w) {
        meleeWeapon.add(w);
    }

    public void setTriggers(ArrayList<Trigger> t) {
        triggers = t;
    }

    public void reset() {
        entities.clear();
        entitiesToCheck.clear();
        blocks.clear();
        blocksToCheck.clear();
        assets.clear();
        projectiles.clear();
        meleeWeapon.clear();
        items.clear();
    }

    public void openDoor(Vector2f pos) {
        ArrayList<Block> removals = new ArrayList<>();
        if (doorBlocks != null) {
            for (Block b : doorBlocks) {
                if (Math.abs(b.getPosition().x - pos.x) < 50) {
                    removals.add(b);
                }
            }
            blocks.removeAll(removals);
            doorBlocks.removeAll(removals);
        }
    }

    public void setDoorBlocks(ArrayList<Drawable> door) {
        if (door.isEmpty()) {
            return;
        }
        for (Drawable b : door) {
            if (b instanceof Block)
                doorBlocks.add((Block) b);
        }
    }

    public Orvil getOrvil() {
        for (Entity e : entities) {
            if (e instanceof Orvil) {
                return (Orvil) e;
            }
        }
        return null;
    }

    public void drawCurrentScene(RenderWindow w, Vector2f posEugene) {
        bg.update(w);
        for (Block b : blocksToCheck) {
            w.draw(b);
        }
        for (Drawable a : assets) {
            if (a instanceof Asset)
                if (Math.abs(((Asset) a).getPosition().x - posEugene.x) < 500
                        && Math.abs(((Asset) a).getPosition().y - posEugene.y) < 500) {
                    w.draw(a);
                }
            if (a instanceof Block) {
                if (Math.abs(((Block) a).getPosition().x - posEugene.x) < 500
                        && Math.abs(((Block) a).getPosition().y - posEugene.y) < 500) {
                    w.draw(a);
                }
            }
        }
        for (ThrowingWeapon t : projectiles) {
            w.draw(t.getDrawable());
        }
        for (Item i : items) {
            if (Math.abs(i.getPosition().x - posEugene.x) < 320 && Math.abs(i.getPosition().y - posEugene.y) < 320) {
                w.draw(i.getDrawable());
            }
        }
        for (Entity e : entitiesToCheck) {
            w.draw(e);
        }
    }
}
