import java.util.ArrayList;

import org.jsfml.system.Vector2f;

/**
 * Potion will make the main character invincible
 */
public class InvincibilityPotion extends Potion {

    /**
     * 
     * @param pos position to be dropped
     */
    public InvincibilityPotion(Vector2f pos, ArrayList<Block> b) {
        super(pos, new Vector2f(20, 20), b, Screen.TEXTURE_FACTORY.getIcon("Invincibility Potion"));
    }

    public InvincibilityPotion() {
        super(new Vector2f(0, 0), new Vector2f(20, 20), null, Screen.TEXTURE_FACTORY.getIcon("Invincibility Potion"));
    }

    /**
     * Effect of the potion
     */
    @Override
    public void effect(MainCharacter m) {
        m.setInvulnCD(-550);
    }

    /**
     * Method called when clicked in the Inventory
     */
    @Override
    public void use(MainCharacter m) {
        effect(m);
        m.removeItemFromInventory(this);
    }

    @Override
    public String getName() {
        return "Invincibility Potion";
    }
}
