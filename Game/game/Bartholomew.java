import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Bartholomew extends Entity implements Npc {

	/**
	 * This class represent Bartholomew, a character in the game
	 */

    private ConversationWindow conversation = new ConversationWindow();
    private boolean shouldDie = false;

	/**
	 * Constructor
	 * @param pos the position of bartholomew
	 * @param idle the texture to bartholomew's idle animation
	 * @param run the texture to bartholomew's run animation
	 * @param attack the texture to bartholomew's attack animation
	 */

    public Bartholomew(Vector2f pos, Texture[][] idle, Texture[][] run, Texture[][] attack) {
        super(0, 0, 100, 0, pos, new Vector2f(20, 36), idle, run, attack, false);
        conversation.setConversation(getConversation("additionalFiles/Bartholomew.txt"));
    }

	/**
	 * Getter for dialogue with Bartholomew
	 * @param path the path to the conversation
	 * @return the conversation in a String ArrayList form
	 */

    private ArrayList<String> getConversation(String path) {
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void die(Screen screen) {
        screen.getMainCharacter().canFrostbite(true); // enable frostbite ability
        screen.getMainCharacter().addItemtoInventory(new ManaPotion());
        screen.getMainCharacter().addItemtoInventory(new ManaPotion());
        screen.getMainCharacter().addItemtoInventory(new ManaPotion());
        screen.getMainCharacter().addItemtoInventory(new ManaPotion());
        screen.getMainCharacter().addItemtoInventory(new ManaPotion());
        screen.getMainCharacter().addItemtoInventory(new ManaPotion());
        conversation.show(screen);
        screen.getWorld().addAsset(new Asset(new Vector2f(this.getPosition().x + 100, this.getPosition().y - 50),
                new Vector2f(128, 64), Screen.TEXTURE_FACTORY.getIcon("Frostbite")));
    }

    @Override
    public void behave(MainCharacter eugene, Screen s) {
        if (shouldDie) {
            setHp(0);
        }
    }

    @Override
    public void bounce(Entity player) {
        shouldDie = true;
    }

}
