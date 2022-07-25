import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

/**
 * Class Block represents blocks the level will be made of. These blocks are
 * collidable
 */
public class Block implements Drawable {
    private RectangleShape rect;
    private float frictCoef; // friction coeficient

    /**
     * 
     * @param pos   position of the block
     * @param size  size of the block
     * @param frict friction coeficient
     * @param path  file path for the Texture
     */
    public Block(Vector2f pos, Vector2f size, float frict, Texture t) {
        rect = new RectangleShape(size);
        rect.setPosition(pos);
        rect.setTexture(t);
        rect.setTextureRect(new IntRect(new Vector2i(0, 0), new Vector2i(size)));
        frictCoef = frict;
    }

    /**
     * Accessor for the friction coeficient
     * 
     * @return friction coeficient
     */
    public float getFrictionCoef() {
        return frictCoef;
    }

    /**
     * Accessor for position of the block
     * 
     * @return position of the block in a level
     */
    public Vector2f getPosition() {
        return rect.getPosition();
    }

    /**
     * Accessor for the size of the block
     * 
     * @return size of the block
     */
    public Vector2f getSize() {
        return rect.getSize();
    }

    @Override
    public void draw(RenderTarget arg0, RenderStates arg1) {
        rect.draw(arg0, arg1);
    }

    public RectangleShape getRect() {
        return rect;
    }

}
