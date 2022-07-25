import java.util.ArrayList;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

/**
 * Super-class for items that can be dropped by enemies
 * 
 */
public abstract class Item {
    private RectangleShape rect;
    private boolean pickedUp = false;
    private boolean isGrounded = false;
    private Block ground = null;
    private Vector2f size;

    /**
     * 
     * @param pos  position
     * @param s    size
     * @param path path to the asset
     */
    public Item(Vector2f pos, Vector2f s, ArrayList<Block> blocks, Texture t) {
        rect = new RectangleShape();
        rect.setSize(s);
        size = s;
        rect.setTexture(t);
        rect.setTextureRect(new IntRect(0, 0, (int) t.getSize().x, (int) t.getSize().y));
        rect.setPosition(pos.x + (float) (Math.random() * 7), pos.y + (float) (Math.random() * 7));
        if (blocks != null) {
            ground = getGroundBlock(blocks);
        }
    }

    public Item() {

    }

    /**
     * Abstract method for picking up the item.
     * 
     * @param mainCharacter
     */
    public void pickUp(MainCharacter mainCharacter) {
        if (mainCharacter.getPosition().x < getPosition().x + getSize().x
                && mainCharacter.getPosition().x + mainCharacter.getSize().x > getPosition().x
                && mainCharacter.getPosition().y < getPosition().y
                && mainCharacter.getPosition().y + mainCharacter.getSize().y > getPosition().y) {
            pickedUp(true);
            if (this instanceof InventoryItem)
                mainCharacter.addItemtoInventory((InventoryItem) this);
        }
    }

    /**
     * Accessor for pickedUp boolean
     * 
     * @return true if item was picked up, false otherwise
     */
    public boolean pickedUp() {
        return pickedUp;
    }

    /**
     * Mutator for pickedUp boolean
     * 
     * @param b new value
     */
    public void pickedUp(boolean b) {
        pickedUp = b;
    }

    /**
     * Accessor for drawable part of the item
     * 
     * @return drawable part of the item
     */
    public Drawable getDrawable() {
        return rect;
    }

    /**
     * Accessor for the position vector
     * 
     * @return position vector
     */
    public Vector2f getPosition() {
        return rect.getPosition();
    }

    /**
     * Accessor for the size vector
     * 
     * @return size vector
     */
    public Vector2f getSize() {
        return size;
    }

    /**
     * Mutator for the position of the item
     * 
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setPosition(float x, float y) {
        rect.setPosition(new Vector2f(x, y));
    }

    /**
     * <<<<<<< HEAD Method finds the closest block under the item ======= Method
     * finds the closest block below the item >>>>>>>
     * bb7d13250f4cc3757bdbb185c84a09ea13a9cf8d
     * 
     * @param blocks
     * @return closest block below the item
     */
    public Block getGroundBlock(ArrayList<Block> blocks) {
        ArrayList<Block> under = new ArrayList<>();
        for (Block b : blocks) {
            if (Math.abs(b.getPosition().x - getPosition().x) < 30) {
                if (b.getPosition().x < getPosition().x + getSize().x
                        && b.getPosition().x + b.getSize().x > getPosition().x) {
                    if (b.getPosition().y > getPosition().y) {
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
        return new Block(new Vector2f(0, 0), new Vector2f(0, 0), 0, null);
    }

    /**
     * Accessor for isGrounded boolean
     * 
     * @return true if grounded, false otherwise
     */
    public boolean isGrounded() {
        return isGrounded;
    }

    /**
     * Mutator for isGrounded
     * 
     * @param b new value
     */
    public void isGrounded(boolean b) {
        isGrounded = b;
    }

    /**
     * Accessor for the ground block
     * 
     * @return closest block below the item
     */
    public Block getGround() {
        return ground;
    }

}
