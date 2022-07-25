import java.util.ArrayList;

import org.jsfml.system.Vector2f;

public class Key extends Item implements InventoryItem {

	/**
	 * This class is responsible for the keys in the game
	 */

	/**
	 * Constructor
	 * @param pos the position of the key
	 * @param blocks the blocks for the dropped key to fall on
	 */
    public Key(Vector2f pos, ArrayList<Block> blocks) {
        super(pos, new Vector2f(20, 20), blocks, Screen.TEXTURE_FACTORY.getIcon("Key"));
    }

    public Key() {
        super();
    }

    @Override
    public void use(MainCharacter m) {
        // nothing, player cant use it
    }

    @Override
    public String getName() {
        return "Key";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Key;
    }

}
