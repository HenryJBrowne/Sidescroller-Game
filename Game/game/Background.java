import org.jsfml.graphics.ConstView;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Background {

	/**
	 * This class is responsible for displaying the background images for each level
	 */
	
    private RectangleShape rect;
    private Texture texture;
    private float ratioX;

	/**
	 * Constructor
	 * @param t the background asset texture
	 * @param size the size vector of the background
	 * @param l the length of the level in px
	 * @param h the height of the level in px
	 */
    public Background(Texture t, Vector2f size, float l) {
        rect = new RectangleShape();
        rect.setTexture(t);
        texture = t;
        ratioX = texture.getSize().x / l;
    }

	/**
	 * This method updates the background of a level
	 * @param window the render window to update in
	 */
    public void update(RenderWindow window) {
        ConstView view = window.getView();

        rect.setPosition(
                new Vector2f(view.getCenter().x - (view.getSize().x / 2), view.getCenter().y - (view.getSize().y / 2)));

        rect.setTextureRect(new IntRect((int) ((rect.getPosition().x * 2) * ratioX), 0,
                (int) (((rect.getSize().x) * ratioX)), texture.getSize().y));
        rect.setSize(view.getSize());
        window.draw(rect);

    }

}
