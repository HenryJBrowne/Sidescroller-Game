import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.window.event.Event.Type;

/**
 * Class responsible for handling events that occur while playing a level
 */
public class LevelControls implements Controls {
    private boolean isSpacePressed = false;
    private boolean isSPressed = false;
    private boolean isAPressed = false;
    private boolean isDPressed = false;
    private boolean isLShiftPressed = false;
    private boolean disableInput = false;

    @Override
    public void checkEvents(Screen s) {
        MainCharacter ent = s.getMainCharacter();
        if (ent == null) {
            return;
        }
        if (disableInput) {
            return;
        }
        for (Event e : s.getWindow().pollEvents()) {
            if (e.type == Type.CLOSED) {
                s.getWindow().close();
            } else if (e.type == Type.KEY_PRESSED) {
                boolean menu = keyEventPressed(e.asKeyEvent(), s, ent);
                if (menu) {
                    break;
                }
            } else if (e.type == Type.KEY_RELEASED) {
                keyEventReleased(e.asKeyEvent(), s);
            } else if (e.type == Type.MOUSE_BUTTON_PRESSED) {
                mouseButtonEventPressed(e.asMouseButtonEvent(), ent, s);
            } else if (e.type == Type.MOUSE_WHEEL_MOVED) {
                mouseWheelEvent(e.asMouseWheelEvent(), s.getWindow(), ent);
            }
        }
        updateEntity(ent, s);
    }

    /**
     * Checks the mouse wheel events
     * 
     * @param asMouseWheelEvent event
     * @param ent               mainCharacter
     */
    private void mouseWheelEvent(MouseWheelEvent mwe, RenderWindow window, MainCharacter m) {
        if (m.showInvent()) {
            m.getInventory().move(mwe.delta);
            m.getInventory().update(window, m.getInventoryList(), m.getWeapon(), m.getArmor());
        }
    }

    /**
     * Updates the entity
     * 
     * @param ent entity to be updated
     * @param s   instance of the screen
     */
    private void updateEntity(Entity ent, Screen s) {
        if (isSpacePressed) {
            if (ent.isGrounded()) {
                ent.jump();
            }
        } else if (isSPressed) {
        }

        if (isAPressed) {
            ent.moveLeft();
        }
        if (isDPressed) {
            ent.moveRight();
        }
        if (isLShiftPressed) {
            if (((MainCharacter) ent).canBlink())
                ((MainCharacter) ent).isBlinking();
        }

    }

    /**
     * Checks the key pressed events
     * 
     * @param ke event
     * @param s  screen instance
     */
    private boolean keyEventPressed(KeyEvent ke, Screen s, MainCharacter m) {
        if (Keyboard.isKeyPressed(Keyboard.Key.ESCAPE)) {
            s.saveStateOfLevel();
            s.setLevel(new Level(-1)); // id of a menu
            s.isMenuShown(false);
            return true;
        }

        if (ke.key == Keyboard.Key.E) {
            if (!s.getLevel().getShop().isOpen()) {
                m.getInventory().update(s.getWindow(), m.getInventoryList(), m.getWeapon(), m.getArmor());
                m.showInvent(!m.showInvent());
            }
        }

        if (ke.key == Keyboard.Key.SPACE) {
            isSpacePressed = true;
        } else if (ke.key == Keyboard.Key.S) {
            isSPressed = true;
        } else if (ke.key == Keyboard.Key.A) {
            isAPressed = true;
        } else if (ke.key == Keyboard.Key.D) {
            isDPressed = true;
        } else if (ke.key == Keyboard.Key.LSHIFT) {
            isLShiftPressed = true;
        } else if (ke.key == Keyboard.Key.X) {
            if (m.canFrostbite())
                m.isFrostbiting();
        }
        return false;
    }

    /**
     * Checks the key released events
     * 
     * @param ke event
     * @param s  screen instance
     */
    private void keyEventReleased(KeyEvent ke, Screen s) {
        if (ke.key == Keyboard.Key.SPACE) {
            isSpacePressed = false;
        } else if (ke.key == Keyboard.Key.S) {
            isSPressed = false;
        } else if (ke.key == Keyboard.Key.A) {
            isAPressed = false;
        } else if (ke.key == Keyboard.Key.D) {
            isDPressed = false;
        } else if (ke.key == Keyboard.Key.LSHIFT) {
            isLShiftPressed = false;
        }

    }

    /**
     * Checks for mouse button events
     * 
     * @param me event
     * @param m  main character
     * @param s  screen instance
     */
    private void mouseButtonEventPressed(MouseButtonEvent me, MainCharacter m, Screen s) {
        if (me.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            if (me.button == Mouse.Button.LEFT) {
                boolean update = false;
                if (s.getLevel().getShop().isOpen()) {
                    update = s.getLevel().getShop().checkForInput(
                            s.getWindow().mapPixelToCoords(Mouse.getPosition(s.getWindow()), s.getWindow().getView()),
                            m);
                } else if (m.showInvent()) {
                    update = m.getInventory().checkForInput(
                            s.getWindow().mapPixelToCoords(Mouse.getPosition(s.getWindow()), s.getWindow().getView()),
                            m);
                } else {
                    m.attack(s);
                }
                if (update) {
                    s.getGUI().update(s.getWindow());
                }
            }
        }
    }

    public void disableInput() {
        disableInput = true;
        isSpacePressed = false;
        isSPressed = false;
        isAPressed = false;
        isDPressed = false;
        isLShiftPressed = false;
    }

    public void enableInput() {
        disableInput = false;
    }
}
