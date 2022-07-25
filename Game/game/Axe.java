import org.jsfml.system.Vector2f;

public class Axe extends MeleeWeapon {

	/**
	 * This class is responsible for holding info about the axe weapon
	 */

	/**
	 * Constructor
	 *
	 * @see MeleeWeapon#MeleeWeapon()
	 */
	public Axe(Object o) {
		super(20f, "Battle Axe", 30f, Screen.TEXTURE_FACTORY.getAnimation("Battle Axe"), new Vector2f(0, 0), o,true);
	}
}
