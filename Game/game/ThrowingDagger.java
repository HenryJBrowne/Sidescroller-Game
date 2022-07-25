import org.jsfml.system.Vector2f;

public class ThrowingDagger extends ThrowingWeapon {

	/**
	 * This class represents the throwing dagger weapon in the game
	 */

	/**
	 * Constructor
	 * @see ThrowingWeapon#ThrowingWeapon()
	 */
	public ThrowingDagger(Object o) {
		super(15, 10, new Vector2f(16, 9), new Vector2f(1, 1), 6, new Vector2f(0, 0), o, "Throwing Dagger", Screen.TEXTURE_FACTORY.getProjectiles("Dagger"));
	}

	public ThrowingDagger(Vector2f pos, Vector2f direction, Object o) {
		super(15, 20, new Vector2f(16, 9), pos, 6, direction, o, "Throwing Dagger",Screen.TEXTURE_FACTORY.getProjectiles("Dagger"));
	}
}
