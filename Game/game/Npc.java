public interface Npc {
	/**
	 * This interface represents the npcs in the game
	 */

	/**
	 * This method calculates how the npc will behave in respect to eugene
	 */
    public void behave(MainCharacter eugene, Screen s);

    public void bounce(Entity player);

}
