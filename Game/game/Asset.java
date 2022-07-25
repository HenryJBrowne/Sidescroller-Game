import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

/**
 * Class Asset represents assets in a background that are not possible to
 * collide with
 */
public class Asset implements Drawable {
    private RectangleShape rect;

    /**
	 * Constructor
     * 
     * @param pos  position of the asset
     * @param size size of the asset
     * @param path file name for Texture
     */
    public Asset(Vector2f pos, Vector2f size, Texture t) {
        rect = new RectangleShape(size);
        rect.setPosition(pos);
        rect.setTexture(t);
        rect.setTextureRect(new IntRect(0, 0, t.getSize().x, t.getSize().y));
    }

    /**
     * Accessor for the position of the asset
     * 
     * @return position of asset in the window
     */
    public Vector2f getPosition() {
        return rect.getPosition();
    }

    @Override
    public void draw(RenderTarget arg0, RenderStates arg1) {
        rect.draw(arg0, arg1);
    }

}
