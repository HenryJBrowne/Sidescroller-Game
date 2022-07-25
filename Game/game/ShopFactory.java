public class ShopFactory {

	/**
	 * This factory stores the prices, descriptions and names of each buyable item in the game
	 */
        private int NUM_OF_LEVELS = 4;
        private int NUM_OF_ITEMS = 4;
        private InventoryItem[][] items = new InventoryItem[NUM_OF_LEVELS][NUM_OF_ITEMS];
        private String[][] description = new String[NUM_OF_LEVELS][NUM_OF_ITEMS];
        private int[][] price = new int[NUM_OF_LEVELS][NUM_OF_ITEMS];
        private final String hp = "This potion helps you regenerate your health. Very useful for when you are in a \nsticky situation as it can restore your hp when consumed.";
        private final String mana = "This potion is like the health potion except it restores your manna.\nThis is useful for if your magic stamina and restores your manna when consumed.";

        public ShopFactory(MainCharacter m) {

                items[0] = new InventoryItem[] { new Armor(0.8f, "Steel Armor"), new HpPotion(), new ThrowingDagger(m),
                                new Axe(m) };
                description[0] = new String[] {
                                "Steel armour is a classic grey armour made from the generic material steel meaning\nit is strong and versatile. The steel armour is very useful and has proven it with\nmost warriors during battles as it is the most common armour. All shops and\nblacksmiths can sell and create this armours it is not hard to make and its easy to\nget hold of the material used for the armour. This armour will provide good\nprotection against enemies, but it might not hold up in the heart of Hells Point.",
                                hp,
                                "This is a throwable and can be thrown at your enemies. It is similar to \nan arrow as it has a sharp point except it is fired from a bow its thrown.\n Useful for short to medium range.",
                                "Like the iron sword it is a generic weapon for a warrior but deals less damage\n however its swing can behead multiple enemies at once. Use wisely as it is\nheavy but effective." };
                price[0] = new int[] { 50, 15, 40, 60 };

                items[1] = new InventoryItem[] { new Armor(0.6f, "Animite Armor"), new HpPotion(), new Bow(m),
                                new Grenade(m) };
                description[1] = new String[] {
                                "Animite armour is very rare but very effective, light and strong this gives you an\nupper hand in any battle. This armour is very hard to obtain but only the greatest\nwarriors can wear this armour set as the animite in the armour was used during the\nancient times to define a regular troop and solider from a war hero or general.\nWhen wearing this armour, it can strike intimidation into your enemy as it\nexpresses your skill and courage. Made from the material, animite, it is\nan armour set that any great warrior would want in their collection.",
                                hp,
                                "This a generic ranged weapon and is very effective for firing arrow\nprojectiles at enemies and targets range.",
                                "This is another throwable. Like the bow it has range however it is not fired from\na ranged weapon it is thrown. On impact it gives off a big blast and causes a lot \nof damage to your enemies if they are within the blast range.\nBe careful not to blow yourself up." };
                price[1] = new int[] { 150, 15, 50, 100 };

                items[2] = new InventoryItem[] { new Armor(0.4f, "Malumite Armor"), new HpPotion(), new ManaPotion(),
                                new IceSword(m) };
                description[2] = new String[] {
                                "Malumite armour is legendary and well known as it was forged in the lavas of hell\nitself. This armour strikes fear into the soul of your enemy. This can show true\nstrength as it also takes a warrior's iron will to not go insane as it was forged\nin the depths of the underworld. Equipped with a legendary material called malumite\nwhich is only found in hell, the very name of this armour derives from the Latin\nword for evil. When equipped it can favour the user, however, do not succumb to\nits evil ways as you will be driven to insanity.",
                                hp, mana,
                                "The ice sword is strong and when it hits an enemy can slow them down. \nLike the iron sword it is sturdy and reliable but has chilling effect. " };
                price[2] = new int[] { 250, 15, 15, 200 };

                items[3] = new InventoryItem[] { new HpPotion(), new ManaPotion(), new InvincibilityPotion(),
                                new BloodSword(m) };
                description[3] = new String[] { hp, mana,
                                "This potion is like the others but grants you a short burst of invincibility\nmeaning you can get shot, hit or even set on fire and you will\n take no damage when consumed.",
                                "The Blood sword is dangerous to wield and dangerous to be on the end off. \nHas a red blade and can deal quite a lot of damage due to it being able to manifest\n the blood in an enemy." };
                price[3] = new int[] { 15, 15, 200, 300 };

        }

		/**
		 * This accessor gets the item from the factory
		 * @param id the index of the item in the factory
		 */
        public Shop getShop(int id) {
                if (id > 0 || id < NUM_OF_LEVELS) {
                        return new Shop(items[id - 1], description[id - 1], price[id - 1]);
                }
                return null;
        }

}
