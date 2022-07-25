import java.util.ArrayList;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class ThrowingWeapon implements Weapon {

	/**
	 * This superclass represents the throwing weapons in the game
	 */

    private float moveSpeed;
    private Sprite rect = new Sprite();
    private int attack;
    private int range;
    private int movement = 0;
    private boolean delete = false;
    private Vector2f direction;
    private Vector2f position;
    private Vector2f size;
    private String name;
    private Texture[][] animation;

    public final Object origin;

	/**
	 * Constructor
	 * @param m the speed of the thrown weapon
	 * @param a the attack damage of the thrown weapon
	 * @param size the size of the thrown weapon
	 * @param pos the position of the weapon
	 * @param r the range of the thrown weapon
	 * @param dir where the weapon will be thrown
	 * @param o @see Weapon#Weapon()
	 * @param n the name of the throwing weapon
	 */
    public ThrowingWeapon(float m, int a, Vector2f size, Vector2f pos, int r, Vector2f dir, Object o, String n,
            Texture[] t) {
        moveSpeed = m;
        attack = a;
        range = r;
        origin = o;
        position = pos;
        this.size = size;
        name = n;
        animation = Screen.TEXTURE_FACTORY.getAnimation("Default");

        float x = (dir.x - pos.x);
        float y = (dir.y - pos.y);
        float magnitude = (float) Math.sqrt(x * x + y * y);
        direction = new Vector2f(x / magnitude, y / magnitude);
        rect.rotate((float) Math.atan(x / y));
        rect.setTexture(x > 0 ? t[0] : t[1]);
        rect.setTextureRect(new IntRect(0, 0, (int) t[0].getSize().x, (int) t[0].getSize().y));
        rect.setPosition(pos);

    }

	/**
	 * This method updates the weapon in the air as it is thrown
	 * @param blocks the blocks to check for collision
	 * @param s the screen to update on
	 */
    public void update(ArrayList<Block> blocks, Screen s) {
        checkForBlocks(blocks, s);
        if (movement <= range) {
            move(new Vector2f(direction.x * moveSpeed, direction.y * moveSpeed));
            position = rect.getPosition();
        } else {
            delete = true;
        }
        movement++;
    }

	/**
	 * This checks if the weapon has collided with the inputted blocks
	 * @param blocks the blocks to check for collision
	 * @param s the screen to check collision on
	 */
    public void checkForBlocks(ArrayList<Block> blocks, Screen s) {
        if (direction.x > 0) {
            checkRight(blocks, s);
        } else {
            checkLeft(blocks, s);
        }
    }

	/**
	 * This checks for anything to the left of the character, to be called when the weapon is thrown left
	 * @param blocks the blocks to the left of the character
	 * @param s the screen to check on
	 */
    private void checkLeft(ArrayList<Block> blocks, Screen s) {
        for (Block b : blocks) {
            if (Math.abs(b.getPosition().x - position.x) < Math.abs(direction.x * moveSpeed) + 50
                    && Math.abs(b.getPosition().y - position.y) < Math.abs(direction.y * moveSpeed) + 50) {
                if (b.getPosition().y < position.y + size.y && b.getPosition().y + b.getSize().y > position.y) {
                    if (b.getPosition().x + b.getSize().x > position.x
                            && b.getPosition().x + b.getSize().x < position.x + size.x) {
                        if (b instanceof TreasureChest) {
                            ((TreasureChest) b).destroy(s);
                        }
                        delete = true;
                        return;
                    }
                }
            }
        }
    }

	/**
	 * This checks for anything to the right of the character, to be called when the weapon is thrown right
	 * @param blocks the blocks to the right of the character
	 * @param s the screen to check on
	 */
    private void checkRight(ArrayList<Block> blocks, Screen s) {
        for (Block b : blocks) {
            if (Math.abs(b.getPosition().x - position.x) < Math.abs(direction.x * moveSpeed) + 50
                    && Math.abs(b.getPosition().y - position.y) < Math.abs(direction.y * moveSpeed) + 50) {
                if (b.getPosition().y < position.y + size.y && b.getPosition().y + b.getSize().y > position.y) {
                    if (b.getPosition().x < position.x + size.x && b.getPosition().x > position.x) {
                        if (b instanceof TreasureChest) {
                            ((TreasureChest) b).destroy(s);
                        }
                        delete = true;
                        return;
                    }
                }
            }
        }
    }

	/**
	 * this Accessor checks if the projectile is deleted
	 * @return true if deleted
	 */
    public boolean delete() {
        return delete;
    }

	/**
	 * this mutator sets the projectile as deleted
	 * @param b the boolean to set
	 */
    public void delete(boolean b) {
        delete = b;
    }

	/**
	 * This accessor returns the drawable sprite of the projectile
	 * @return the drawable projectile sprite
	 */
    public Sprite getDrawable() {
        return rect;
    }

	/**
	 * This accessor returns the attack damage of the weapon
	 * @return attack damage of the weapon
	 */
    public float getAttack() {
        return attack;
    }

    private IntRect getRect() {
        return rect.getTextureRect();
    }

	/**
	 * This accessor returns the position of the thrown projectile
	 * @return the position of the thrown projectile
	 */
    public Vector2f getPosition() {
        return position;
    }

	/**
	 * This accessor returns the size of the thrown projectile
	 * @return the size of the thrown projectile
	 */
    public Vector2f getSize() {
        return size;
    }

    @Override
    public void use(MainCharacter m) {
        m.setMainWeapon(this);
        m.setAttackAnimation(animation);
    }

    @Override
    public String getName() {
        return name;
    }

	/**
	 * This method moves the thrown projectile in the air
	 * @param v the vector to move by
	 */
    public void move(Vector2f v) {
        rect.move(v);
        position = rect.getPosition();
    }
}
