import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Orvil extends Entity implements Npc {
	/**
	 * This class represents Orvil, a follower npc that assists you throughout the game
	 */

    private ConversationWindow[] conversations = new ConversationWindow[] { new ConversationWindow(),
            new ConversationWindow(), new ConversationWindow() };
    private boolean isActive = false;

	/**
	* Constructor
	* @param pos the position of Orvil
	* @param idle the textures of Orvil's idle animation
	* @param run the textures of Orvil's run animation
	* @param attack the textures of Orvil's attack animation
	*/
    public Orvil(Vector2f pos, Texture[][] idle, Texture[][] run, Texture[][] attack) {
        super(15, 40, 100, 0, pos, new Vector2f(16, 25), idle, run, attack, true);
    }

    @Override
    public void behave(MainCharacter eugene, Screen s) {
        if (!isActive) {
            return;
        }

        if (Math.abs(eugene.getPosition().x - this.getPosition().x) < 300) {
            followEugene(eugene, s.getWorld().getBlocksToCheck(), s);
        }
    }

	/**
	 * This method loads the dialogue between orvil and the player
	 * @param number the index of the conversation to load
	 */
    public void loadConversations(int number) {
        if (number == 2) {
            conversations[0].setConversation(getConversation("additionalFiles/Orvil1.txt"));
            conversations[1].setConversation(getConversation("additionalFiles/OrvilBoss2Before.txt"));
            conversations[2].setConversation(getConversation("additionalFiles/OrvilBoss2After.txt"));
        } else if (number == 3) {
            conversations[0].setConversation(getConversation("additionalfiles/OrvilLevel3.txt"));
            conversations[1].setConversation(getConversation("additionalFiles/OrvilBoss3Before.txt"));
            conversations[2].setConversation(getConversation("additionalFiles/OrvilBoss3After.txt"));
        } else {
            conversations[0].setConversation(getConversation("additionalfiles/OrvilLevel4.txt"));
            conversations[1].setConversation(getConversation("additionalFiles/OrvilBoss4Before.txt"));
            conversations[2].setConversation(getConversation("additionalFiles/OrvilBoss4After.txt"));
        }

    }

    private void followEugene(MainCharacter eugene, ArrayList<Block> blocks, Screen s) {
        // stop if too close
        if ((Math.abs(eugene.getPosition().x - this.getPosition().x) < 30)) {
            return;
        }

        if (this.getVelocity().x == 0 && this.isGrounded()) {
            this.jump();
        }

        // left
        if (eugene.getPosition().x < this.getPosition().x) {
            this.moveLeft();
        }
        // right
        if (eugene.getPosition().x > this.getPosition().x) {
            this.moveRight();
        }

    }

    @Override
    public void bounce(Entity player) {

    }

    @Override
    public void damage(float damage) {
        // cant die
    }

    @Override
    public void die(Screen screen) {
        // cant die
    }

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

	/**
	 * This method displays the conversation between Orvil and the player
	 * @param id the index of the conversation to show
	 * @param s the screen to show the conversation
	 */
    public void showConversation(int id, Screen s) {
        conversations[id].show(s); // use triggers to activate, id is based on number of conversation in current
                                   // level
    }

	/**
	 * Mutator to set if Orvil is active or not
	 * @param b the boolean to set
	 */
    public void isActive(boolean b) { // use trigger to activate
        isActive = b;
    }

	/**
	 * This method returns the conversation between Orvil and the player
	 * @param i the index of the conversation
	 * @return the conversation window specified
	 */
    public ConversationWindow getConversation(int i) {
        return conversations[i];
    }
}
