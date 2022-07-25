import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

public class PlayerGUI {

	/**
	 * This class is responsible for displaying and updating the player gui
	 */

    private RectangleShape hpBarBack;
    private RectangleShape hpBarFront;
    private RectangleShape mpBarBack;
    private RectangleShape mpBarFront;
    private final int WINDOW_OFFSET = 5;
    private final int HP_BAR_OFFSET = 1;
    private int xLength = 150;
    private int yLength = 7;
    private MainCharacter character;
    private final float ratio = ((xLength - 2 * HP_BAR_OFFSET) / MainCharacter.hp);
    private int MoneyOffset = 7;
    private int ManaOffset = 10;

    // display
    private Text MoneyDisplay;
    private Text blinkDisplay;
    private Text frostbiteDisplay;

	/**
	 * Constructor
	 * @param m the main character whos stats are to be displayed
	 */
    public PlayerGUI(MainCharacter m) {
        hpBarBack = new RectangleShape(new Vector2f(xLength, yLength));
        hpBarBack.setFillColor(Color.GREEN);
        hpBarFront = new RectangleShape(new Vector2f(xLength - 2 * HP_BAR_OFFSET, yLength - 2 * HP_BAR_OFFSET));
        hpBarFront.setFillColor(Color.RED);
        character = m;

        MoneyDisplay = new Text("0$", Screen.font, 16);
        MoneyDisplay.setColor(Color.YELLOW);
        blinkDisplay = new Text("Blink", Screen.font, 16);
        MoneyDisplay.setColor(Color.YELLOW);
        frostbiteDisplay = new Text("Frostbite", Screen.font, 16);
        MoneyDisplay.setColor(Color.YELLOW);

        mpBarBack = new RectangleShape(new Vector2f(xLength, yLength));
        mpBarBack.setFillColor(Color.GREEN);
        mpBarFront = new RectangleShape(new Vector2f(xLength - 2 * HP_BAR_OFFSET, yLength - 2 * HP_BAR_OFFSET));
        mpBarFront.setFillColor(Color.BLUE);

    }

	/**
	 * This method shows all of the information about the player on the screen
	 * @param window the window to display the information
	 */
    public void show(RenderWindow window) {
        window.draw(hpBarBack);
        float currentHp = character.getHp() * ratio;
        hpBarFront.setSize(new Vector2f(currentHp <= 0 ? 0 : currentHp, yLength - 2 * HP_BAR_OFFSET));
        window.draw(hpBarFront);
        window.draw(mpBarBack);
        float currentMana = character.getMana() * ratio;
        mpBarFront.setSize(new Vector2f(currentMana <= 0 ? 0 : currentMana, yLength - 2 * HP_BAR_OFFSET));
        window.draw(mpBarFront);
        window.draw(MoneyDisplay);
        if (character.canBlink()) {
            blinkDisplay.setColor(character.blinkReady() ? Color.GREEN : Color.RED);
            window.draw(blinkDisplay);
        }
        if (character.canFrostbite()) {
            frostbiteDisplay.setColor((character.frostbiteReady() ? Color.GREEN : Color.RED));
            window.draw(frostbiteDisplay);
        }
        if (character.showInvent()) {
            character.getInventory().show(window);
        }
    }

	/**
	 * This method updates the gui to show the current information about the player
	 * @param window the window to update the information on
	 */
    public void update(RenderWindow window) {
        View view = (View) window.getView();

        float xhpBar = view.getCenter().x - (view.getSize().x / 2) + WINDOW_OFFSET;
        float yhpBar = view.getCenter().y - (view.getSize().y / 2) + WINDOW_OFFSET;
        hpBarBack.setPosition(xhpBar, yhpBar);
        float currentHp = character.getHp() * ratio;
        hpBarFront.setSize(new Vector2f(currentHp <= 0 ? 0 : currentHp, yLength - 2 * HP_BAR_OFFSET));
        hpBarFront.setPosition(xhpBar + HP_BAR_OFFSET, yhpBar + HP_BAR_OFFSET);

        float ympBar = yhpBar + ManaOffset;
        mpBarBack.setPosition(xhpBar, ympBar);
        float currentMana = character.getMana() * ratio;
        mpBarFront.setSize(new Vector2f(currentMana <= 0 ? 0 : currentMana, yLength - 2 * HP_BAR_OFFSET));
        mpBarFront.setPosition(xhpBar + HP_BAR_OFFSET, ympBar + HP_BAR_OFFSET);

        if (character.showInvent()) {
            character.getInventory().update(window, character.getInventoryList(), character.getWeapon(),
                    character.getArmor());
        }
        MoneyDisplay.setPosition(new Vector2f(xhpBar, ympBar + MoneyOffset));
        MoneyDisplay.setString(character.getMoney() + "$");

        blinkDisplay.setPosition(new Vector2f(xhpBar, ympBar + 2 * MoneyOffset + 10));
        frostbiteDisplay.setPosition(new Vector2f(xhpBar, ympBar + 3 * MoneyOffset + 10 * 2));
    }

}
