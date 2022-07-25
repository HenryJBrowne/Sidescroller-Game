import org.jsfml.system.Vector2f;

	/**
	* This class is responsible for holding info about the icesword weapon
	*/

	/**
	* Constructor
	*
	* @see MeleeWeapon#MeleeWeapon()
	*/

public class IceSword extends MeleeWeapon {
	public IceSword(Object o) {
		super(45f, "Ice Sword", 40f, Screen.TEXTURE_FACTORY.getAnimation("Ice Sword"), new Vector2f(0, 0), o,false);
	}
}
