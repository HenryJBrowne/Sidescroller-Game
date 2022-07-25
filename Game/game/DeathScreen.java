import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;

/**
 * Class will create and display a death message
 */

public class DeathScreen {

    public DeathScreen(RenderWindow window) {

        try {
            window.setActive(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Text t = new Text("YOU DIED", Screen.font, 10);
        t.setPosition(window.getView().getCenter().x - (4 * 10 * 0.386f), window.getView().getCenter().y - 10);
        t.setColor(Color.RED);
        show(t, window);

    }

    /**
     * Method responsible for showing death message
     * 
     * @param t text
     * @param w render window to draw on
     */
    private void show(Text t, RenderWindow w) {
        while (t.getCharacterSize() < 120) {

            w.clear();
            w.draw(t);
            w.display();
            t.setPosition(w.getView().getCenter().x - (4 * t.getCharacterSize() * 0.386f),
                    w.getView().getCenter().y - t.getCharacterSize());
            t.setCharacterSize(t.getCharacterSize() + 2);

            pause();
        }
        try {
            w.setActive(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Pause method
     */
    private void pause() {
        try {
            Thread.sleep(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
