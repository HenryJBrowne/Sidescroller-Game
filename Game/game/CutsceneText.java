import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;

/**
 * Class CutsceneText will create text that will slowly scroll on a window
 */

public class CutsceneText {
    private RenderWindow window;
    private Vector2f pos;
    private float offset = 0;
    private boolean complete = false;
    private ArrayList<Text> cutsceneText = new ArrayList<>();
    private int height = 0;
    private Runnable runnable;

    /**
     * 
     * @param window instance of the RenderWindow
     * @param txt    text to be shown
     */
    public CutsceneText(RenderWindow window, String txt) {
        initialize(window, txt, Color.YELLOW);
    }

    /**
     *
     * @param window instance of the RenderWindow
     * @param txt    text to be shown
     * @param color  color of the text
     */
    public CutsceneText(RenderWindow window, String txt, Color color) {
        initialize(window, txt, color);
    }

    /**
     *
     * @param window   instance of the RenderWindow
     * @param txt      text to be shown
     * @param color    color of the text
     * @param runnable the function/runnable to execute when cutscene is completed.
     */
    public CutsceneText(RenderWindow window, String txt, Color color, Runnable runnable) {
        initialize(window, txt, color);
        this.runnable = runnable;
    }

    /**
     * Method will initialize text
     * 
     * @param window instance of the Renderwindow
     * @param txt    text to be shown
     * @param color  color of the text
     */
    private void initialize(RenderWindow window, String txt, Color color) {
        String[] lines = txt.split("\n");
        for (String line : lines) {
            Text temptxt = new Text(line, Screen.font, 40);
            temptxt.setColor(color);
            height += temptxt.getLocalBounds().height;
            cutsceneText.add(temptxt);
        }
        this.window = window;
    }

    /**
     * Method responsible for drawing the cutscene text
     */
    public void draw() {
        ConstView view = window.getView();
        if (offset < view.getSize().y + 300 + height) {
            for (int i = 0; i < cutsceneText.size(); i++) {
                pos = new Vector2f(view.getCenter().x - cutsceneText.get(i).getLocalBounds().width / 2,
                        view.getCenter().y + 40 * i + view.getSize().y / 2
                                + cutsceneText.get(i).getLocalBounds().height / 2 - offset);
                cutsceneText.get(i).setPosition(pos);
                window.draw(cutsceneText.get(i));
            }
            offset += 1;
        } else {
            complete = true;
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Accessor for the boolean is complete
     * 
     * @return true if text has rolled all the way up false otherwise
     */
    public boolean isComplete() {
        return complete;
    }
}
