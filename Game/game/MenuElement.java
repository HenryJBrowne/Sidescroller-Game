import java.nio.file.Paths;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

/**
 * Class representing a menu button
 */
public class MenuElement extends Sprite {
    private Texture t;
    private int lvl = 1; // default

    public MenuElement(String path, int lvl) {
        t = new Texture();
        this.lvl = lvl;
        if (path != null) {
            try {
                t.loadFromFile(Paths.get(path));
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.setTexture(t);
            this.setTextureRect(new IntRect(0, 0, t.getSize().x, t.getSize().y));
        }
    }

    /**
     * Method is called when mouse was clicked on a menu element
     * 
     * @param s Screen where mouse was clicked
     */
    public void buttonClicked(Screen s) {
        if (lvl == -200) {
            s.resume();
            s.updateView();
            return;
        }
        if (lvl == -201) {
            s.toggleMusic(this);
            return;
        }
        if (lvl == -300) {
            if (s.getSavedLevel().getNumber() + 1 <= 4) {
                s.setLevel(new Level(s.getSavedLevel().getNumber() + 1));
                s.changeLevel(true);
            } else {
                s.setLevel(new Level(0));
            }
            return;
        }
        if (lvl <= 0) {
            s.isMenuShown(false);
        } else {
            s.changeLevel(true);
        }
        s.setLevel(new Level(lvl));
    }

}
