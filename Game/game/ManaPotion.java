import java.util.ArrayList;

import org.jsfml.system.Vector2f;

public class ManaPotion extends Potion {

	/**
	 * This class represents the usable mana potions
	 */

	/**
	 * Constructor
	 * @see Potion#Potion()
	 */
    public ManaPotion(Vector2f pos, ArrayList<Block> b) {
        super(pos, new Vector2f(20, 20), b, Screen.TEXTURE_FACTORY.getIcon("Mana Potion"));
    }

    public ManaPotion() {
        super(new Vector2f(0, 0), new Vector2f(20, 20), null, Screen.TEXTURE_FACTORY.getIcon("Mana Potion"));
    }

    @Override
    public void effect(MainCharacter m) {
        if (m.getMana() + 35 <= MainCharacter.maxMana)
            m.changeMana(35);
        else
            m.changeMana(MainCharacter.maxMana - m.getMana());
    }

    @Override
    public void use(MainCharacter m) {
        effect(m);
        m.removeItemFromInventory(this);
    }

    @Override
    public String getName() {
        return "Mana Potion";
    }
}
