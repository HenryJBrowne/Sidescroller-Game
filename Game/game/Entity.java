import java.util.*;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

/**
 * Class for creating moveable entity in the RenderWindow
 */
public abstract class Entity implements Drawable {
    private float moveSpeed, jumpSpeed;
    private Vector2f velocity = new Vector2f(0, 0);
    private Vector2f position = new Vector2f(0, 0);
    private Vector2f size;
    private boolean grounded = false;
    private boolean isRight = true;
    private boolean isDead = false;
    private final boolean check;
    private float hp;
    private Vector2f spawn; // spawn position

    private Sprite rect;
    Texture texture = new Texture();
    private Vector2f center;

    private State state = State.IDLE;
    private final Animate animate;
    private int attack;

    /**
     * 
     * @param m      move speed
     * @param j      jump speed
     * @param h      health points
     * @param a      attack strength
     * @param pos    position
     * @param s      size
     * @param idle   paths to idle assets
     * @param run    paths to run assets
     * @param attack paths to attack assets
     */
    public Entity(float m, float j, float h, int a, Vector2f pos, Vector2f s, Texture[][] idle, Texture[][] run,
            Texture[][] attack, boolean c) {
        spawn = pos;
        moveSpeed = m;
        jumpSpeed = j;
        hp = h;
        check = c;
        this.attack = a;
        position = pos;
        size = s;
        rect = new Sprite(texture, new IntRect(0, 0, (int) size.x, (int) size.y));
        rect.setPosition(pos);
        center = new Vector2f(pos.x + s.x / 2, pos.y + size.y / 2);
        animate = new Animate(run, idle, attack, this);
    }

    /**
     * Method responsible for moving entity. Also provides checks for collisons with
     * blocks
     *
     * @param lvl level matrix
     */
    public void move(ArrayList<Block> blocks) {
        if (check) {
            Block b;
            if (velocity.x > 0) { // moving to the right
                b = findRightBlock(blocks);
                if (b != null) {
                    if (b.getPosition().x - position.x - size.x <= Math.abs(velocity.x)) {
                        // float move = b.getPosition().x - position.x - size.x - 2;
                        // rect.move(move, 0);
                        // position = rect.getPosition();
                        velocity = new Vector2f(0, velocity.y);
                    }
                }
            } else if (velocity.x < 0) { // moving to the left
                b = findLeftBlock(blocks);
                if (b != null) {
                    if (position.x - b.getPosition().x - b.getSize().x <= Math.abs(velocity.x)) {
                        // float move = position.x - b.getPosition().x - b.getSize().x - 2;
                        // rect.move(-move, 0);
                        // position = rect.getPosition();
                        velocity = new Vector2f(0, velocity.y);
                    }
                }
            }
            // jumping
            if (velocity.y < 0) {
                b = findTopBlock(blocks);
                if (b != null) {
                    if (position.y - b.getPosition().y - b.getSize().y < Math.abs(velocity.y)) {
                        float move = position.y - b.getPosition().y - b.getSize().y - 5;
                        rect.move(0, -move);
                        position = rect.getPosition();
                        velocity = new Vector2f(velocity.x, 0);
                    }
                }
            }
        }

        rect.move(velocity);
        position = rect.getPosition();
        center = new Vector2f(position.x + size.x / 2, position.y + size.y / 2);
        animate.update(state);
        if (!isRight && animate.getStateOfAnimation() == State.ATTACKING) {
            rect.setOrigin(rect.getTexture().getSize().x - size.x, 0);
        } else {
            rect.setOrigin(0, 0);
        }
    }

    /**
     * Method just animates entity
     */
    public void animate() {
        animate.update(state);
        if (!isRight && animate.getStateOfAnimation() == State.ATTACKING) {
            rect.setOrigin(rect.getTexture().getSize().x - size.x, 0);
        } else {
            rect.setOrigin(0, 0);
        }
    }

    public void setPosition(Vector2f position) {
        rect.setPosition(position);
        this.position = position;
        center = new Vector2f(position.x + size.x / 2, position.y + size.y / 2);
    }

    /**
     * Accessor for drawable Element of entity
     *
     * @return drawable of Entity
     */
    public Sprite getDrawable() {
        return rect;
    }

    /**
     * Accessor for the position of the Entity
     *
     * @return position
     */
    public Vector2f getPosition() {
        return position;
    }

    /**
     * Sets the position of the Entity
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setPosition(float x, float y) {
        rect.setPosition(x, y);
        position = new Vector2f(x, y);
        center = new Vector2f(position.x + size.x / 2, position.y + size.y / 2);
    }

    /**
     * Method will return center of a entity
     * 
     * @return center of a entity
     */
    public Vector2f getCenter() {
        return center;
    }

    /**
     * Sets the velocity of the Entity
     *
     * @param v new velocity
     */
    public void setVelocity(Vector2f v) {
        velocity = v;
    }

    /**
     * Accessor for the velocity of the Entity
     *
     * @return
     */
    public Vector2f getVelocity() {
        return velocity;
    }

    /**
     * Mutator for grounded boolean
     *
     * @param b new value of grounded
     */
    public void isGrounded(boolean b) {
        grounded = b;
    }

    /**
     * Accessor for grounded boolean
     *
     * @return grounded
     */
    public boolean isGrounded() {
        return grounded;
    }

    /**
     * Accessor for move speed
     *
     * @return move speed of Entity
     */
    public float getMoveSpeed() {
        return moveSpeed;
    }

    /**
     * Accessor for jump speed
     *
     * @return jump speed of Entity
     */
    public float getJumpSpeed() {
        return jumpSpeed;
    }

    /**
     * Accessor for size
     *
     * @return size of Entity
     */
    public Vector2f getSize() {
        return size;
    }

    /**
     * Accesor for is dead boolean
     * 
     * @return true if hp <= 0, false otherwise
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Mutator for is dead boolean
     * 
     * @param b new value
     */
    public void isDead(boolean b) {
        isDead = b;
    }

    /**
     * Method will find the closest block to the right of the entity
     * 
     * @param blocks list of blocks nearby
     * @return closest block to the right, can be null
     */
    public Block findRightBlock(ArrayList<Block> blocks) {
        ArrayList<Block> right = new ArrayList<>();
        for (Block b : blocks) {
            if (Math.abs(b.getPosition().x - position.x) < velocity.x + 150
                    && Math.abs(b.getPosition().y - position.y) < velocity.y + 100) {
                if (b.getPosition().y < position.y + size.y && b.getPosition().y + b.getSize().y > position.y) {
                    if (b.getPosition().x > position.x) {
                        right.add(b);
                    }
                }
            }
        }

        if (right.size() != 0) {
            Block closest = right.get(0);
            for (int i = 1; i < right.size(); i++) {
                Block b = right.get(i);
                if (b.getPosition().x < closest.getPosition().x) {
                    closest = b;
                }
            }
            return closest;
        }
        return null;

    }

    /**
     * Method will find closest block to the left of the entity
     * 
     * @param blocks list of blocks nearby
     * @return closest block to the left, can be null
     */
    public Block findLeftBlock(ArrayList<Block> blocks) {
        ArrayList<Block> left = new ArrayList<>();
        for (Block b : blocks) {
            if (Math.abs(b.getPosition().x - position.x) < Math.abs(velocity.x) + 150
                    && Math.abs(b.getPosition().y - position.y) < Math.abs(velocity.y) + 100) {
                if (b.getPosition().y < position.y + size.y && b.getPosition().y + b.getSize().y > position.y) {
                    if (b.getPosition().x < position.x) {
                        left.add(b);
                    }
                }
            }
        }
        if (left.size() != 0) {
            Block closest = left.get(0);
            for (int i = 1; i < left.size(); i++) {
                Block b = left.get(i);
                if (b.getPosition().x > closest.getPosition().x) {
                    closest = b;
                }
            }
            return closest;
        }
        return null;
    }

    /**
     * Method will find closest block above the entity
     * 
     * @param blocks list of blocks nearby
     * @return closest block above, can be null
     */
    public Block findTopBlock(ArrayList<Block> blocks) {
        ArrayList<Block> above = new ArrayList<>();
        for (Block b : blocks) {
            if (Math.abs(b.getPosition().x - position.x) < 30) {
                if (b.getPosition().x < position.x + size.x && b.getPosition().x + b.getSize().x > position.x) {
                    if (b.getPosition().y + b.getSize().y < position.y)
                        above.add(b);
                }
            }
        }
        if (above.size() != 0) {
            Block closest = above.get(0);
            for (int i = 1; i < above.size(); i++) {
                Block b = above.get(i);
                if (b.getPosition().y > closest.getPosition().y) {
                    closest = b;
                }
            }
            return closest;
        }
        return null;

    }

    /**
     * Method will find closest block below the entity
     * 
     * @param blocks list of blocks nearby
     * @return closest block below, can be null
     */
    public Block getGroundBlock(ArrayList<Block> blocks) {
        ArrayList<Block> under = new ArrayList<>();
        for (Block b : blocks) {
            if (Math.abs(b.getPosition().x - position.x) < 30) {
                if (b.getPosition().x < position.x + size.x && b.getPosition().x + b.getSize().x > position.x) {
                    if (b.getPosition().y > position.y) {
                        under.add(b);
                    }
                }
            }
        }
        if (under.size() != 0) {
            Block closest = under.get(0);
            for (int i = 1; i < under.size(); i++) {
                Block b = under.get(i);
                if (b.getPosition().y < closest.getPosition().y) {
                    closest = b;
                }
            }
            return closest;
        }
        return null;
    }

    /**
     * Sets the velocity so the Entity will go left and change state
     */
    public void moveLeft() {
        velocity = new Vector2f(-moveSpeed, velocity.y);
        if (isRight) {
            isRight = false;
        }
        if (state != State.RUNNING) {
            state = State.RUNNING;
        }
    }

    /**
     * Sets the velocity so the Entity will go right and change state
     */
    public void moveRight() {
        velocity = new Vector2f(moveSpeed, velocity.y);
        if (!isRight) {
            isRight = true;
        }
        if (state != State.RUNNING) {
            state = State.RUNNING;
        }
    }

    /**
     * Sets the velocity so the Entity will go up, will set grounded to false and
     * change state
     */
    public void jump() {
        velocity = new Vector2f(velocity.x, velocity.y - jumpSpeed);
        // SoundEffectManager.playSound("soundeffects\\JumpSound.wav"); //just annoying
        grounded = false;
        if (state != State.JUMPING) {
            state = State.JUMPING;
        }
    }

    /**
     * Accessor for isRight boolean
     * 
     * @return true if entity is facing right, false otherwise
     */
    public boolean isRight() {
        return isRight;
    }

    /**
     * Mutator for isRight boolean
     * 
     * @param b new value
     */
    public void isRight(boolean b) {
        isRight = b;
    }

    /**
     * Accessor for state of the entity
     * 
     * @return current state
     */
    public State getState() {
        return state;
    }

    /**
     * Updates state of a entity to attacking
     */
    public void attack() {
        animate.update(State.ATTACKING);
        if (!isRight) {
            rect.setOrigin(rect.getTexture().getSize().x - size.x, 0);
        }
    }

    /**
     * Mutator for state of an entity
     * 
     * @param s new value
     */
    public void setState(State s) {
        state = s;
        animate.update(state);
    }

    public void setAttackAnimation(Texture[][] animation) {
        animate.setAttackAnimation(animation);
    }

    @Override
    public void draw(RenderTarget arg0, RenderStates arg1) {
        rect.draw(arg0, arg1);
    }

    /**
     * Returns whether the entity is colliding with another entity
     *
     * @param entity : the entity to check collision with.
     * @return true if colliding, false if not.
     */
    public boolean isCollidingWith(Entity e) {
        if (position.x <= e.getPosition().x + e.getSize().x && position.x + size.x >= e.getPosition().x) {
            if (e.getPosition().y <= position.y + size.y && e.getPosition().y + e.getSize().y > position.y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether the entity is colliding with a throwing weapon
     *
     * @param entity : the entity to check collision with.
     * @return true if colliding, false if not.
     */
    public boolean isCollidingWith(Weapon t) {
        if (position.x < t.getPosition().x + t.getSize().x && position.x + size.x > t.getPosition().x) {
            if (t.getPosition().y < position.y + size.y && t.getPosition().y + t.getSize().y > position.y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Accessor for hp
     * 
     * @return current hp
     */
    public float getHp() {
        return hp;
    }

    /**
     * Mutator for hp
     * 
     * @param f new value
     */
    public void setHp(float f) {
        hp = f;
    }

    /**
     * Decreases the hp
     * 
     * @param damage value to be substract from hp, hp cant be 0
     */
    public void damage(float damage) {
        hp -= damage;
        if (damage < 0) {
            return;
        }
        if (hp <= 0) {
            isDead = true;
        } else {
            SoundEffectManager.playSound("soundeffects/Damage.wav");
        }
    }

    /**
     * Accessor for attack
     * 
     * @return attack strength
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Abtract method for entity death - every entity will behave differently
     * 
     * @param screen instance of a screen
     */
    public abstract void die(Screen screen);

    /**
     * Accessor for spawn location
     * 
     * @return spawn location
     */
    public Vector2f getSpawn() {
        return spawn;
    }

    public boolean shouldCheck() {
        return check;
    }

    public void setSpawn(Vector2f newspawn) {
        spawn = newspawn;
    }
}
