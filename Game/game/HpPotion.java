import java.util.ArrayList;

import org.jsfml.system.Vector2f;

/**
 * Potion that will regenerate health
 */
public class HpPotion extends Potion {

    /**
     * 
     * @param pos position to be dropped
     */
    public HpPotion(Vector2f pos, ArrayList<Block> b) {
        super(pos, new Vector2f(20, 20), b, Screen.TEXTURE_FACTORY.getIcon("Health Potion"));
    }

    public HpPotion() {
        super(new Vector2f(0, 0), new Vector2f(20, 20), null, Screen.TEXTURE_FACTORY.getIcon("Health Potion"));
    }

    /**
     * Effect of the potion
     */
    @Override
    public void effect(MainCharacter m) {
        if (m.getHp() + 35 <= MainCharacter.hp)
            m.setHp(m.getHp() + 35);
        else
            m.setHp(MainCharacter.hp);
    }

    /**
     * Method called when clicked in Inventory
     */
    @Override
    public void use(MainCharacter m) {
        effect(m);
        m.removeItemFromInventory(this);
    }

    @Override
    public String getName() {
        return "Health Potion";
    }
}
