import org.jsfml.graphics.Color;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.*;

public class StoryScreen {
    private CutsceneText text;

    public StoryScreen(String t, Screen s) {
        text = new CutsceneText(s.getWindow(), t, Color.WHITE);
        start(s);
    }

    private void start(Screen s) {
        while (!text.isComplete()) {
            s.getWindow().clear(Color.BLACK);
            text.draw();
            s.showSkipMessage(20);
            s.getWindow().display();
            pause();
            for (Event e : s.getWindow().pollEvents()) {
                if (e.asKeyEvent() != null) {
                    if (e.asKeyEvent().key == Key.RETURN) {
                        return;
                    }
                }
            }
        }
    }

    public void pause() {
        try {
            Thread.sleep(40);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
