import org.jsfml.system.Vector2f;

public class BloodSword extends MeleeWeapon {
	/**
	* This class is responsible for holding info about the bloodsword weapon
	*/

	/**
	* Constructor
	*
	* @see MeleeWeapon#MeleeWeapon()
	*/

	public BloodSword(Object o) {
		super(55f, "Blood Sword", 50f, Screen.TEXTURE_FACTORY.getAnimation("Blood Sword"), new Vector2f(0, 0),o, false);
	}
}
