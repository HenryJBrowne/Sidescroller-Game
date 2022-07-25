public class Armor implements InventoryItem {

	/**
	 * Class responsible for handling the stats of armor
	 */
	
    private float reduction;
    private String name;

	/**
	 * Constructor
	 * @param r the damage reduction coefficient
	 * @param n the name of the armor
	 */
    public Armor(float r, String n) {
        reduction = r;
        name = n;
    }

    @Override
    public void use(MainCharacter m) {
        m.setArmor(this);
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the damage reduction coeficient. The total damage taken =
     * attackStrength * damageReduction.
     * 
     * @return damage reduction coeficient
     */
    public float getDamageReduction() {
        return reduction;
    }

}
