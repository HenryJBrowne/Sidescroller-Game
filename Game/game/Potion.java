import java.util.ArrayList;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public abstract class Potion extends Item implements InventoryItem {

	/**
	 * This abstract class represents the potions in the game
	 */

	/**
	 * Constructor
	 * @param pos the position of the potion
	 * @param s the size of the potion item
	 * @param b the blocks for the dropped potion to land on
	 * @param t the texture of the potion
	 */
    public Potion(Vector2f pos, Vector2f s, ArrayList<Block> b, Texture t) {
        super(pos, s, b, t);
    }

	/**
	 * This abstract method holds the effect of the potion on the player when used
	 * @param m the Main character that used the potion
	 */
    public abstract void effect(MainCharacter m);
}
