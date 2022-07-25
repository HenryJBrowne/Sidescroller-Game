import java.util.ArrayList;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.*;

public class ConversationWindow {

	/**
	 * This class is responsible for displaying dialogue on the screen
	 */

    private ArrayList<Text> conversation;
    private boolean isDone = false;

    public ConversationWindow() {

    }

	/**
	 * This method shows the dialogue on screen
	 * @param s the screen to show the dialogue on
	 */
    public void show(Screen s) {
        if (conversation != null && !isDone) {
            s.getLevelControls().disableInput();
            RenderWindow w = s.getWindow();
            Vector2f pos = w.getView().getCenter();
            int i = 0;
            while (i < conversation.size()) {
                w.clear();
                s.getWorld().drawCurrentScene(w, s.getMainCharacter().getCenter());
                for (int j = 0; j <= i; j++) {
                    conversation.get(j).setPosition(new Vector2f(pos.x - 250, pos.y - 150 + j * 15));
                    w.draw(conversation.get(j));
                }
                s.showSkipMessage(10);
                w.display();
                if (pause(w, s)) {
                    isDone = true;
                    s.getLevelControls().enableInput();
                    return;
                }
                i++;
            }
            isDone = true;
            s.getLevelControls().enableInput();
        }
    }

	/**
	 * This method pauses the dialogue progression
	 * @param w the window to pause on
	 * @param s the screen to pause on
	 */
    private boolean pause(RenderWindow w, Screen s) {
        int totalSleep = 0;
        while (totalSleep < 3000) {
            for (Event e : w.pollEvents()) {
                if (e.asKeyEvent() != null) {
                    if (e.asKeyEvent().key == Key.RETURN) {
                        return true;
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            totalSleep += 100;
        }
        return false;
    }

	/**
	 * This method sets the text to be displayed in conversation
	 * @param strings the list of strings to be displayed in conversation
	 */
    public void setConversation(ArrayList<String> strings) {
        conversation = new ArrayList<>();
        for (String s : strings) {
            conversation.add(new Text(s, Screen.font, 15));
        }
    }

	/**
	 * Getter for when the conversation is done
	 * @return true if the conversation is finished
	 */
    public boolean isDone() {
        return isDone;
    }
}
