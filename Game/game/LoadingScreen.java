import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

/**
 * Method will display a loading screen until the level is loaded
 */
public class LoadingScreen {
    private RenderWindow window;
    RectangleShape rect = new RectangleShape();
    private int frame = 0;
    private int maxFrame = 5;
    private Color[] colors = new Color[] { new Color(220, 220, 220), new Color(211, 211, 211), new Color(192, 192, 192),
            new Color(169, 169, 169), new Color(128, 128, 128), };
    private int color = 1;
    private int colorInc = 1;
    Vector2f center;
    private final int size = 150;
    private Text text;

    public LoadingScreen(RenderWindow w) {
        window = w;
        center = window.getView().getCenter();
        rect.setSize(new Vector2f(size, size));
        rect.setTextureRect(new IntRect(0, 0, size, size));
        rect.setPosition(new Vector2f(center.x - size / 2, center.y - size / 2));
        text = new Text("Loading...", Screen.font, 50);
        text.setPosition(center.x - 70, center.y + (size / 2));
    }

    /**
     * Method updates the assets on a screen
     */
    public void update() {
        rect.setTexture(Screen.TEXTURE_FACTORY.getLoadingScreen(frame));
        text.setColor(colors[color]);
        window.clear();
        window.draw(rect);
        window.draw(text);
        window.display();
        if (frame < maxFrame - 1) {
            frame++;
        } else {
            frame = 0;
        }
        if (color > colors.length - 2 || color < 1) {
            colorInc *= -1;
        }
        color += colorInc;
        pause();
    }

    private void pause() {
        try {
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
