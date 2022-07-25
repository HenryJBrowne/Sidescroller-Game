import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;


public class Trigger {
	
	/**
	* This class runs custom code whenver player moves over a certain block
	*/
    private Vector2f position;
    private Vector2f size;
    private final Runnable runnable;
    RectangleShape rect = new RectangleShape(); // debug

	/**
	 * Constructor
	 * @param position where the trigger is placed
	 * @param the size of the trigger area
	 * @param runnable the runnable to be executed when triggered
	 */
    public Trigger(Vector2f position, Vector2f size, Runnable runnable) {
        this.position = position;
        this.size = size;
        this.runnable = runnable;
        rect.setSize(size);
        rect.setPosition(position);
        rect.setOutlineColor(Color.RED);
        rect.setOutlineThickness(1f);
    }

	/**
	 * This method checks if there should be a trigger
	 *
	 * @param triggerer the entity to check is in the trigger
	 * @param w the window the trigger is on
	 */
    public void checktotrigger(Entity triggerer, RenderWindow w) {
        if (position.x < triggerer.getPosition().x + triggerer.getSize().x
                && position.x + size.x > triggerer.getPosition().x) {
            if (triggerer.getPosition().y < position.y + size.y
                    && triggerer.getPosition().y + triggerer.getSize().y > position.y) {
                runnable.run();
            }
        }
    }
}
