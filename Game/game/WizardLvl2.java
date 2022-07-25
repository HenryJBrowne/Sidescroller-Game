import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class WizardLvl2 extends Entity implements Npc {
	/**
	 * This class represents the wizard npc in the game
	 */
    private ConversationWindow conversation = new ConversationWindow();
    private boolean shouldDie = false;

	/**
	 * Constructor
	 * @see Npc#Npc()
	 */
    public WizardLvl2(Vector2f pos, Texture[][] idle, Texture[][] run, Texture[][] attack) {
        super(0, 0, 100, 0, pos, new Vector2f(20, 36), idle, run, attack, false);
        conversation.setConversation(getConversation("additionalFiles/Ozorath.txt"));
    }

	/**
	 * This method gets the conversations between the wizard and the player
	 * @param path the path to the conversation
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
        screen.getMainCharacter().canBlink(true); // enable blink ability
        conversation.show(screen);
        screen.getWorld().addAsset(new Asset(new Vector2f(this.getPosition().x + 100, this.getPosition().y - 50),
                new Vector2f(128, 64), Screen.TEXTURE_FACTORY.getIcon("Blink")));
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
