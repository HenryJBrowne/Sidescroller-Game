import java.util.ArrayList;
import org.jsfml.system.Vector2f;

/**
 * Factory for items Indexes : 301 money, 302 hp potion, 303 mana potion, 304
 * invincibility potion
 */
public class ItemFactory {

    public static Item getItem(Vector2f pos, int id, ArrayList<Block> b) {
        if (id == 301) {
            return new Money(pos, b);
        }
        if (id == 302) {
            return new HpPotion(pos, b);
        }
        if (id == 303) {
            return new ManaPotion(pos, b);
        }
        if (id == 304) {
            return new InvincibilityPotion(pos, b);
        }
        if (id == 305) {
            return new Key(pos, b);
        }
        return null;
    }

}
