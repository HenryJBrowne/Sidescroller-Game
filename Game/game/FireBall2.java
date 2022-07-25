import org.jsfml.system.Vector2f;

/**
 * This class represents the fireball2 projectile for use by enemies
 */

/**
 * Constructor
 * @see ThrowingWeapon#ThrowingWeapon()
 */
public class FireBall2 extends ThrowingWeapon {
    public FireBall2(Object o) {
        super(10, 80, new Vector2f(16, 16), new Vector2f(1, 1), 65, new Vector2f(0, 0), o, "Fireball",
                Screen.TEXTURE_FACTORY.getProjectiles("Fireball2"));
    }

    public FireBall2(Vector2f pos, Vector2f dir, Object o) {
        super(10, 80, new Vector2f(16, 16), pos, 65, dir, o, "Fireball",
                Screen.TEXTURE_FACTORY.getProjectiles("Fireball2"));
    }
}
