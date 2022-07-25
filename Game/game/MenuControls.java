import java.util.ArrayList;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.window.event.Event.Type;

/**
 * Class responsible for handling events that occur during game while in menu
 */
public class MenuControls implements Controls {

    public MenuControls() {
    }

    @Override
    public void checkEvents(Screen s) {
        for (Event e : s.getWindow().pollEvents()) {

            if (e.type == Type.CLOSED) {
                s.getWindow().close();
            }

            mouseEvent(e.asMouseButtonEvent(), s); // try mouse event
            keyEvent(e.asKeyEvent(), s); // try key event
        }

    }

    /**
     * Method checks if a menuElement was clicked
     * 
     * @param s screen where the mouse was clicked
     */
    public void mouseAction(Screen s) {
        ArrayList<Drawable> elements = (ArrayList<Drawable>) s.getElements();
        Vector2f pos = s.getWindow().mapPixelToCoords(Mouse.getPosition(s.getWindow()), s.getWindow().getView());

        for (int i = 1; i < elements.size(); i++) {// start at 1 since 0 is background
            Drawable d = elements.get(i);
            if (d instanceof MenuElement) {
                MenuElement me = (MenuElement) d;
                if (containsPos(me, pos)) {
                    me.buttonClicked(s);
                    return;
                }
            }

        }
        return;
    }

    /**
     * Checks if mouse was clicked on a MenuElement
     * 
     * @param s   Sprite to check
     * @param pos positon of Mouse to check
     * @return true if mouse is on the Sprite, false otherwise
     */
    private boolean containsPos(Sprite s, Vector2f pos) {
        if (pos.x > s.getPosition().x && pos.x < s.getPosition().x + s.getTexture().getSize().x) {
            if (pos.y > s.getPosition().y && pos.y < s.getPosition().y + s.getTexture().getSize().y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if event is a mouse event
     * 
     * @param me event to check, can be null
     * @param s  screen where event occured
     */
    private void mouseEvent(MouseButtonEvent me, Screen s) {
        if (me != null) {
            if (me.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                if (me.button == Mouse.Button.LEFT)
                    mouseAction(s);
            }
        }
    }

    /**
     * Checks if event is a key event
     * 
     * @param ke event to check, can be null
     * @param s  screen where event occured
     */
    private void keyEvent(KeyEvent ke, Screen s) {
        if (ke != null) {
            if (ke.key == Keyboard.Key.ESCAPE) {
                // s.getWindow().close();
            }
        }
    }

}
