import org.jsfml.system.Vector2f;

public class Bow extends ThrowingWeapon {
	
	/**
	 * This class represents the bow weapon in the game
	 */

	/**
	 * Constructor
	 * @param pos the position of the bow
	 * @param direction where the bow is facing
	 * @param o @see ThrowingWeapon#ThrowingWeapon
	 */

	public Bow(Vector2f pos, Vector2f direction, Object o) {
			super(10, 10, new Vector2f(10, 4), pos, 15, direction, o, "Bow",
							Screen.TEXTURE_FACTORY.getProjectiles("Arrow"));
	}

	public Bow(Object o) {
			super(10, 10, new Vector2f(10, 4), new Vector2f(0, 0), 15, new Vector2f(0, 0), o, "Bow",
							Screen.TEXTURE_FACTORY.getProjectiles("Arrow"));
	}

	@Override
	public void use(MainCharacter m) {
			m.setMainWeapon(this);
			m.setAttackAnimation(Screen.TEXTURE_FACTORY.getAnimation("Bow"));
	}

}
