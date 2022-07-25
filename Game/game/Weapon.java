import org.jsfml.system.Vector2f;

public interface Weapon extends InventoryItem {
	/**
	 * This interface represents the weapons in the game
	 */

	/**
	 * This accessor gets the weapons attack damage
	 * @return the weapons attack damage
	 */
    public float getAttack();

	/**
	 * This accessor gets the weapons position
	 * @return the weapons position
	 */
    public Vector2f getPosition();

	/**
	 * This accessor gets the weapons size
	 * @return the weapons size
	 */
    public Vector2f getSize();
}
