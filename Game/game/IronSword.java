import org.jsfml.system.Vector2f;

public class IronSword extends MeleeWeapon {

	/**
	* This class is responsible for holding info about the icesword weapon
	*/

	/**
	* Constructor
	*
	* @see MeleeWeapon#MeleeWeapon()
	*/

	public IronSword(Object o) {
		super(30f, "Iron Sword", 40f, Screen.TEXTURE_FACTORY.getAnimation("Iron Sword"), new Vector2f(0, 0), o,false);
	}
}
