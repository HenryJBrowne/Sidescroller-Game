import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class TreasureChest extends Block {
	/**
	 * This class represents the lootable treasure chests in the game
	 */

    // more occuring items have better chance to be spawned
    private int items[] = { 301, 301, 301, 301, 301, 302, 303, 301, 301, 301, 301, 301, 301, 301, 301, 301, 301, 301,
            301, 301, 301, 301, 301, 301, 301, 301, 302, 301, 301, 301, 301, 301, 303, 301, 301, 304, 301, 301, 301,
            301, 301, 301, 301, 301, 301, 301, 301, 301, 301, 301, 301, 302, 301, 301, 301, 301, 301, 303, 301, 301,
            301, 301, 301, 301, 301, 301, 301, 301, 301, 301, 301, 301, 301, 301, 301, 302, 301, 301, 301, 301, 301,
            303, 301, 301 };
    private final int NUM_OF_ITEMS;
    private final int MAX_ITEMS = 10;
    private final int MIN_ITEMS = 5;
    private boolean open = false;

	/**
	 * Constructor
	 * @param pos the position of the chest
	 * @param size the size of the chest
	 * @param frict the friction coefficient of the chest
	 * @param t the texture of the chest
	 */
    public TreasureChest(Vector2f pos, Vector2f size, float frict, Texture t) {
        super(pos, size, frict, t);
        NUM_OF_ITEMS = (int) (Math.random() * (MAX_ITEMS - MIN_ITEMS)) + MIN_ITEMS;

    }

	/**
	 * This method drops the random items from the chest when destroyed
	 * @param s the screen to drop items on
	 */
    public void destroy(Screen s) {
        if (!open) {
            for (int i = 0; i < NUM_OF_ITEMS; i++) {
                s.spawn(getPosition(), items[(int) (Math.random() * (double) items.length)]);
            }
            open = true;
            Texture openTexture = Screen.TEXTURE_FACTORY.getIcon("Chest");
            this.getRect().setTextureRect(new IntRect(0, 0, openTexture.getSize().x, openTexture.getSize().y));
            this.getRect().setTexture(openTexture);
        }
    }

	/**
	 * This accessor checks if the chest is already opened
	 * @return true if opened
	 */
    public boolean isOpen() {
        return open;
    }

}
