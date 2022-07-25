import org.jsfml.system.Vector2f;

/**
 * This class represents the fireball projectile for use by enemies
 */

/**
 * Constructor
 * @see ThrowingWeapon#ThrowingWeapon()
 */
public class FireBall extends ThrowingWeapon {
    public FireBall(Object o) {
        super(7, 25, new Vector2f(9, 9), new Vector2f(1, 1), 6, new Vector2f(0, 0), o, "Fire Ball",
                Screen.TEXTURE_FACTORY.getProjectiles("Fireball1"));
    }

    public FireBall(Vector2f pos, Vector2f dir, Object o) {
        super(7, 25, new Vector2f(9, 9), pos, 30, dir, o, "Fire Ball",
                Screen.TEXTURE_FACTORY.getProjectiles("Fireball1"));
    }
}
