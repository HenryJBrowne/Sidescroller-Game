import org.jsfml.system.Vector2f;

/**
 * This class represents the iceball projectile for use by enemies
 */

/**
 * Constructor
 * @see ThrowingWeapon#ThrowingWeapon()
 */
public class Iceball extends ThrowingWeapon {
    public Iceball(Object o) {
        super(7, 25, new Vector2f(9, 9), new Vector2f(1, 1), 6, new Vector2f(0, 0), o, "Ice Ball",
                Screen.TEXTURE_FACTORY.getProjectiles("Iceball1"));
    }

    public Iceball(Vector2f pos, Vector2f dir, Object o) {
        super(7, 25, new Vector2f(9, 9), pos, 30, dir, o, "Ice Ball",
                Screen.TEXTURE_FACTORY.getProjectiles("Iceball1"));
    }
}
