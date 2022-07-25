import java.util.ArrayList;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

/**
 * Creates an inventory for the player
 */
public class Inventory {
    private RectangleShape bg = new RectangleShape();
    private RectangleShape bgEq = new RectangleShape();
    private final int OFFSET = 5;
    private float boxSize;
    private final int NUM_PER_ROW = 4;
    private int start = 0;
    private ArrayList<RectangleShape> itemBoxes;
    private ArrayList<InventoryItem> currentItems;
    private ArrayList<Text> titles;
    private int FONT_SIZE = 13;
    private RectangleShape scrollbar;

    /**
     * 
     * @param size size on x-axis of the inventory on the screen
     */
    public Inventory(float size) {
        bg.setFillColor(new Color(128, 128, 128));
        bg.setOutlineColor(Color.BLACK);
        bg.setOutlineThickness(1f);
        bgEq.setFillColor(new Color(128, 128, 128));
        bgEq.setOutlineColor(Color.BLACK);
        bgEq.setOutlineThickness(1f);
        scrollbar = new RectangleShape();
        scrollbar.setFillColor(Color.BLACK);
        scrollbar.setSize(new Vector2f(10, 40));

        boxSize = (size - scrollbar.getSize().x - (OFFSET * (NUM_PER_ROW + 2))) / (NUM_PER_ROW + 1);

        bg.setSize(new Vector2f(size - boxSize, (boxSize * 2) + (3 * OFFSET)));
        bgEq.setSize(new Vector2f(boxSize + 2 * OFFSET, (boxSize * 2) + (3 * OFFSET)));
    }

    /**
     * Method will show the inventory on the screen
     * 
     * @param window window to draw on
     * @param items  list of inventory items
     */
    public void show(RenderWindow window) {
        window.draw(bg);
        window.draw(bgEq);
        window.draw(scrollbar);
        for (int i = 0; i < titles.size(); i++) {
            window.draw(itemBoxes.get(i));
            window.draw(titles.get(i));
        }
    }

    /**
     * Method checks if an item was clicked
     * 
     * @param position position of the mouse
     * @param m        main character
     * @param v        current view in the window
     */
    public boolean checkForInput(Vector2f position, MainCharacter m) {
        for (int i = 0; i < currentItems.size(); i++) {
            RectangleShape r = itemBoxes.get(i);
            if (position.x > r.getPosition().x && position.x < r.getPosition().x + boxSize) {
                if (position.y > r.getPosition().y && position.y < r.getPosition().y + boxSize) {
                    currentItems.get(i).use(m);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method will move the inventory down by 1 row
     * 
     * @param delta how many wheel scrolls
     */
    public void move(int delta) {
        start += (-delta * NUM_PER_ROW);
        if (start < 0) {
            start = 0;
        }
    }

    /**
     * Method will update inventory on the screen
     * 
     * @param window window to draw on
     * @param items  list of inventory items
     */
    public void update(RenderWindow window, ArrayList<InventoryItem> items, Weapon w, Armor a) {
        itemBoxes = new ArrayList<>();
        currentItems = new ArrayList<>();
        titles = new ArrayList<>();
        float x = window.getView().getCenter().x - (bg.getSize().x / 2) + OFFSET + (boxSize / 2);
        float y = window.getView().getCenter().y - (bg.getSize().y / 2) + OFFSET;
        int posX = OFFSET;
        int posY = OFFSET;
        bg.setPosition(x, y);
        if (start + NUM_PER_ROW > items.size() - 1) {
            start = items.size() - 1 - ((items.size() - 1) % NUM_PER_ROW);
        }
        bgEq.setPosition(x - (OFFSET * 3) - boxSize, y);
        scrollbar.setSize(new Vector2f(scrollbar.getSize().x,
                (bg.getSize().y - 2 * OFFSET) / (int) Math.ceil(items.size() / (double) NUM_PER_ROW)));
        scrollbar.setPosition(x + bg.getSize().x - scrollbar.getSize().x - (OFFSET), bg.getPosition().y + OFFSET
                + scrollbar.getSize().y * (Math.round(start >= items.size() ? items.size() : start / NUM_PER_ROW)));
        for (int i = start; i < items.size(); i++) {
            RectangleShape r = new RectangleShape(new Vector2f(boxSize, boxSize));
            InventoryItem temp = items.get(i);
            Texture t = Screen.TEXTURE_FACTORY.getIcon(temp.getName());
            r.setTexture(t);
            r.setTextureRect(new IntRect(0, 0, t.getSize().x, t.getSize().y));
            r.setPosition(x + posX, y + posY);
            r.setOutlineColor(new Color(220, 220, 220));
            r.setOutlineThickness(1f);
            Text title = new Text(temp.getName(), Screen.font, FONT_SIZE);
            float textStartX = (boxSize - (temp.getName().length() * FONT_SIZE * 0.35f)) / 2;
            title.setPosition(x + posX + textStartX, y + posY);
            title.setColor(Color.BLACK);

            itemBoxes.add(r);
            currentItems.add(temp);
            titles.add(title);
            if (posX + boxSize + 3 * OFFSET + scrollbar.getSize().x > bg.getSize().x - OFFSET) {
                posX = OFFSET;
                posY += boxSize + OFFSET;
                if (bg.getSize().y <= posY + boxSize) {
                    break;
                }
            } else {
                posX += boxSize + OFFSET;
            }
        }

        // armor and weapon
        RectangleShape armorBox = new RectangleShape();
        Text armorT;
        armorBox.setSize(new Vector2f(boxSize, boxSize));
        armorBox.setOutlineColor(new Color(220, 220, 220));
        armorBox.setOutlineThickness(1f);
        armorBox.setPosition(bgEq.getPosition().x + OFFSET, bgEq.getPosition().y + OFFSET);
        if (a != null) {
            Texture t = Screen.TEXTURE_FACTORY.getIcon(a.getName());
            armorT = new Text(a.getName(), Screen.font, FONT_SIZE);
            armorBox.setTexture(t);
            armorBox.setTextureRect(new IntRect(0, 0, t.getSize().x, t.getSize().y));
        } else {
            armorT = new Text("No armor", Screen.font, FONT_SIZE);
            armorBox.setFillColor(new Color(128, 128, 128));
        }

        float textStartX = (boxSize - (armorT.getString().length() * FONT_SIZE * 0.35f)) / 2;
        armorT.setPosition(armorBox.getPosition().x + textStartX, armorBox.getPosition().y);
        itemBoxes.add(armorBox);
        armorT.setColor(Color.BLACK);
        titles.add(armorT);

        RectangleShape weaponBox = new RectangleShape();
        Text weaponT;
        weaponBox.setSize(new Vector2f(boxSize, boxSize));
        weaponBox.setOutlineColor(new Color(220, 220, 220));
        weaponBox.setOutlineThickness(1f);
        weaponBox.setPosition(armorBox.getPosition().x, armorBox.getPosition().y + boxSize + OFFSET);
        if (w != null) {
            Texture t = Screen.TEXTURE_FACTORY.getIcon(w.getName());
            weaponT = new Text(w.getName(), Screen.font, FONT_SIZE);
            weaponBox.setTexture(t);
            weaponBox.setTextureRect(new IntRect(0, 0, t.getSize().x, t.getSize().y));
        } else {
            weaponT = new Text("No weapon", Screen.font, FONT_SIZE);
            weaponBox.setFillColor(new Color(128, 128, 128));
        }

        textStartX = (boxSize - (weaponT.getString().length() * FONT_SIZE * 0.35f)) / 2;
        weaponT.setPosition(weaponBox.getPosition().x + textStartX, weaponBox.getPosition().y);
        itemBoxes.add(weaponBox);
        weaponT.setColor(Color.BLACK);
        titles.add(weaponT);

    }
}
