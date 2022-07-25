import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConstView;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Shop {
	
	/**
	 * This method represents the vendors in the game
	 */

    private InventoryItem[] items;
    private RectangleShape bg = new RectangleShape();
    private final int OFFSET = 5;
    private float boxSize;
    private final int NUM_PER_ROW = 4;
    private String[] descriptions;
    private RectangleShape[] itemBoxes = new RectangleShape[NUM_PER_ROW];
    private int FONT_SIZE = 13;
    private int[] prices;
    private boolean isOpen = false;
    private Text description = new Text();
    private Text price = new Text();
    private int currentDesShown;
    private Color outlineColor = new Color(220, 220, 220);

	/**
	 * Constructor
	 * @param i the inventory of the store (the stock to buy from)
	 * @param d the item descriptions of each respective item in the store
	 * @param p the prices of each item in the store
	 */
    public Shop(InventoryItem[] i, String[] d, int[] p) {
        if (i.length > NUM_PER_ROW) {
            System.out.println("Max items per shop exceeded. [max " + NUM_PER_ROW + "]");
            System.out.println("Items above index " + NUM_PER_ROW + " are ignored");
        }
        items = i;
        descriptions = d;
        prices = p;
        bg = new RectangleShape(new Vector2f(500, 240));
        boxSize = 119f;// (500 / NUM_PER_ROW)
        bg.setFillColor(new Color(128, 128, 128));
    }

	/**
	 * This method displays the store page
	 * @param w the window to display the store page on
	 */
    private void display(RenderWindow w) {
        w.draw(bg);
        for (RectangleShape r : itemBoxes) {
            w.draw(r);
        }
        w.draw(description);
        w.draw(price);
    }

	/**
	 * This method updates the store page
	 * @param view the view of the store
	 */
    private void update(ConstView view) {
        float x = view.getCenter().x - (bg.getSize().x / 2) + OFFSET;
        float y = view.getCenter().y - (bg.getSize().y / 2) + OFFSET;
        int posX = OFFSET;
        int posY = OFFSET;
        bg.setPosition(x, y);

        price.setColor(Color.YELLOW);
        description.setColor(Color.BLACK);
        description.setPosition(new Vector2f(x + OFFSET, y + boxSize + OFFSET));
        price.setPosition(new Vector2f(x + bg.getSize().x - OFFSET - price.getString().length() * FONT_SIZE,
                y + bg.getSize().y - OFFSET - FONT_SIZE));
        for (int i = 0; i < NUM_PER_ROW; i++) {
            RectangleShape r = new RectangleShape(new Vector2f(boxSize, boxSize));
            InventoryItem temp = items[i];
            Texture t = Screen.TEXTURE_FACTORY.getIcon(temp.getName());

            r.setTexture(t);
            r.setPosition(x + posX, y + posY);
            r.setOutlineColor(outlineColor);
            r.setOutlineThickness(1f);
            itemBoxes[i] = r;

            posX += boxSize + OFFSET;

        }

    }

    /**
     * Method checks if an item was clicked
     * 
     * @param position position of a mouse
     * @param m        main character
     */
    public boolean checkForInput(Vector2f position, MainCharacter m) {
        for (int i = 0; i < items.length; i++) {
            RectangleShape r = itemBoxes[i];
            if (position.x > r.getPosition().x && position.x < r.getPosition().x + boxSize) {
                if (position.y > r.getPosition().y && position.y < r.getPosition().y + boxSize) {
                    if (m.getMoney() - prices[i] >= 0) {
                        m.addItemtoInventory(items[i]);
                        m.modifyMoney(-prices[i]);
                        SoundEffectManager.playSound("soundeffects/MoneySound.wav");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void showDescription(Vector2f position) {
        for (int i = 0; i < items.length; i++) {
            RectangleShape r = itemBoxes[i];
            if (position.x > r.getPosition().x && position.x < r.getPosition().x + boxSize) {
                if (position.y > r.getPosition().y && position.y < r.getPosition().y + boxSize) {
                    if (currentDesShown != i) {
                        description = new Text(descriptions[i], Screen.font, FONT_SIZE);
                        price = new Text(Integer.toString(prices[i]) + "$", Screen.font, FONT_SIZE);
                        currentDesShown = i;
                    }
                }
            }
        }
    }

    public void show(RenderWindow window, Vector2f pos) {
        if (!isOpen) {
            isOpen = true;
        }
        update(window.getView());
        display(window);
        showDescription(pos);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void close() {
        isOpen = false;
    }

}
