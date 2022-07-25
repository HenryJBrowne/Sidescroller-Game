import java.util.ArrayList;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;

/**
 * Class for the main character, Eugene
 */
public class MainCharacter extends Entity {
        public static final float hp = 100;
        public static final int maxMana = 100;
        private boolean showInvent;
        private ArrayList<InventoryItem> inventoryList = new ArrayList<>();
        private Weapon mainWeapon = new IronSword(this);
        private Inventory inventory = new Inventory(500);
        private int money = 150;
        private int blinkcd = 50;
        private int invulnerabilitycd = 20;
        private int timestopcd = 5000;
        private int regencd = 300;
        private int manacd = 500;
        private Text spawnMessage = new Text();
        private boolean isBlinking = false;
        private boolean isFrostbiting = false;

        private int mana = maxMana;
        private Armor armor = null;
        private boolean canBlink = false;
        private boolean canFrostbite = false;
        private int timeOfFrostbite = 0;

        /**
         * 
         * @param pos position of the character
         */
        public MainCharacter(Vector2f pos, Texture[][] idle, Texture[][] run, Texture[][] attack) {
                super(12, 40, hp, 0, pos, new Vector2f(16, 32), idle, run, attack, true);
                mainWeapon.use(this);
                spawnMessage.setString("Spawnpoint set");
                spawnMessage.setCharacterSize(60);
                spawnMessage.setColor(Color.WHITE);
                spawnMessage.setFont(Screen.font);
                addItemtoInventory(mainWeapon);
        }

        public MainCharacter() {
                super(0, 0, 0, 0, new Vector2f(0, 0), new Vector2f(0, 0), null, null, null, false);
        }

        /**
         * Adds item to the characters inventory list
         * 
         * @param i item to add
         */
        public void addItemtoInventory(InventoryItem i) {
                inventoryList.add(i);
        }

        /**
         * Removes item from the characters inventory list
         * 
         * @param i item to remove
         */
        public void removeItemFromInventory(InventoryItem i) {
                inventoryList.remove(i);
        }

        /**
         * Accessor for showInvent boolean
         * 
         * @return true if the inventory should be displayed, false otherwise
         */
        public boolean showInvent() {
                return showInvent;
        }

        /**
         * Accessor for inventory
         * 
         * @return inventory
         */
        public Inventory getInventory() {
                return inventory;
        }

        /**
         * Mutator for showInvent
         * 
         * @param b new value
         */
        public void showInvent(boolean b) {
                showInvent = b;
        }

        /**
         * Method will add an amount to the current balance
         * 
         * @param amount amount to change
         */
        public void modifyMoney(int amount) {
                if (money + amount < 0) {
                        money = 0;
                } else {
                        money += amount;
                }
        }

        /**
         * Returns the current balance
         * 
         * @return current balance
         */
        public int getMoney() {
                return money;
        }

        public void isBlinking() {
                isBlinking = true;
        }

        /**
         * Method responsible for attacking
         * 
         * @param s screen instance
         */
        public void attack(Screen s) {
                int OFFSET;
                if (isRight()) {
                        OFFSET = 10;
                } else {
                        OFFSET = -10;
                }
                if (mainWeapon instanceof ThrowingDagger) {
                        s.getWorld().addProjectile(
                                        new ThrowingDagger(new Vector2f(getPosition().x + OFFSET, getPosition().y),
                                                        s.getWindow().mapPixelToCoords(Mouse.getPosition(s.getWindow()),
                                                                        s.getWindow().getView()),
                                                        this));
                } else if (mainWeapon instanceof Grenade) {
                        s.getWorld().addProjectile(new Grenade(new Vector2f(getPosition().x + OFFSET, getPosition().y),
                                        s.getWindow().mapPixelToCoords(Mouse.getPosition(s.getWindow()),
                                                        s.getWindow().getView()),
                                        this));
                } else if (mainWeapon instanceof Bow) {
                        s.getWorld().addProjectile(new Bow(new Vector2f(getPosition().x + OFFSET, getPosition().y),
                                        s.getWindow().mapPixelToCoords(Mouse.getPosition(s.getWindow()),
                                                        s.getWindow().getView()),
                                        this));
                } else if (mainWeapon instanceof MeleeWeapon) {
                        Vector2f pos;
                        if (isRight()) {
                                pos = new Vector2f(getCenter().x, getPosition().y);
                        } else {
                                pos = new Vector2f(getCenter().x - ((MeleeWeapon) mainWeapon).getSize().x,
                                                getPosition().y);
                        }
                        s.getWorld().addMeele(
                                        new MeleeWeapon(mainWeapon.getAttack(), ((MeleeWeapon) mainWeapon).getSize().x,
                                                        pos, this, ((MeleeWeapon) mainWeapon).canMultiple()));
                }
                super.attack();
        }

        /**
         * This is the blink power of eugene. It teleports him wherever he is facing.
         * 
         * @param s : the screen of the game.
         */
        public void blink(ArrayList<Block> blocks, Screen s) {
                if (isBlinking) {
                        if (blinkcd >= 50 && mana >= 25) {
                                Block b;
                                if (isRight()) { // if eugene is facing the right,blink to the right
                                        b = findRightBlock(blocks);
                                        if (b != null) {
                                                if (b.getPosition().x - this.getPosition().x - this.getSize().x < 146) {
                                                        this.setPosition(new Vector2f(
                                                                        b.getPosition().x - 2 - this.getSize().x,
                                                                        this.getPosition().y));
                                                } else {
                                                        this.setPosition(new Vector2f(this.getPosition().x + 144,
                                                                        this.getPosition().y));
                                                }
                                        } else {
                                                this.setPosition(new Vector2f(this.getPosition().x + 144,
                                                                this.getPosition().y));

                                        }
                                } else { // eugene blinks to the left
                                        b = findLeftBlock(blocks);
                                        if (b != null) {
                                                if (this.getPosition().x - b.getPosition().x - b.getSize().x < 146) {
                                                        this.setPosition(new Vector2f(
                                                                        b.getPosition().x + b.getSize().x + 2,
                                                                        this.getPosition().y));
                                                } else {
                                                        this.setPosition(new Vector2f(this.getPosition().x - 144,
                                                                        this.getPosition().y));
                                                }
                                        } else {
                                                this.setPosition(new Vector2f(this.getPosition().x - 144,
                                                                this.getPosition().y));

                                        }
                                }

                                blinkcd = 0;
                                mana -= 25;
                                manacd = 0;
                                this.setVelocity(new Vector2f(0, 0));
                                s.updateView();
                        }
                        isBlinking = false;
                }
        }

        /**
         * Increases cooldown for abilities
         */
        public void tickCooldown() {
                if (blinkcd < 50)
                        blinkcd++;
                if (invulnerabilitycd < 20)
                        invulnerabilitycd++;
                if (timestopcd < 5000)
                        timestopcd++;
                if (regencd < 300) {
                        regencd++;
                } else if (getHp() + 5 <= hp) {
                        setHp(getHp() + 5);
                        regencd = 0;
                }
                if (manacd <= 500) {
                        manacd++;
                } else if (mana + 5 < 100) {
                        mana += 5;
                        manacd = 0;
                }
                if (isFrostbiting) {
                        if (timeOfFrostbite < 400) {
                                timeOfFrostbite++;
                        } else {
                                timeOfFrostbite = 0;
                                isFrostbiting = false;
                        }
                }
        }

        /**
         * Method is called when the main character dies
         * 
         * @param screen instance of a screen
         */
        public void die(Screen screen) {

                new DeathScreen(screen.getWindow()); // show deathscreen

                setVelocity(new Vector2f(0, 0));// reset most values
                this.setPosition(getSpawn().x, getSpawn().y);
                setHp(hp);
                mana = 100;
                modifyMoney((int) -((Math.random() * 10) + 5));
                isDead(false);
                // reset input
                screen.getLevelControls().disableInput();
                screen.getLevelControls().enableInput();
                resetAbilities();
                screen.updateView();
        }

        /**
         * Method changes the current mana points
         * 
         * @param change amount to change
         */
        public void changeMana(int change) {
                mana += change;
                manacd = 0;
        }

        /**
         * Accessor for mana
         * 
         * @return current amount of mana
         */
        public int getMana() {
                return mana;
        }

        @Override
        public void damage(float damage) {
                float damageReduction;
                if (armor == null) {
                        damageReduction = 1;
                } else {
                        damageReduction = armor.getDamageReduction();
                }
                if (invulnerabilitycd >= 20) {
                        super.damage(damage * damageReduction);
                        invulnerabilitycd = 0;
                        if (isRight())
                                setVelocity(new Vector2f(10, getVelocity().y));
                        else
                                setVelocity(new Vector2f(-10, getVelocity().y));
                }
                regencd = 0;
        }

        /**
         * 
         * @param cd
         */
        public void setInvulnCD(int cd) {
                invulnerabilitycd = cd;
        }

        /**
         * Accessor for inventory list
         * 
         * @return inventory list
         */
        public ArrayList<InventoryItem> getInventoryList() {
                return inventoryList;
        }

        /**
         * 
         */
        public boolean frostbite() {
                return isFrostbiting;
        }

        public void setMainWeapon(Weapon w) {
                mainWeapon = w;
        }

        public Weapon getWeapon() {
                return mainWeapon;
        }

        public Armor getArmor() {
                return armor;
        }

        public void setArmor(Armor a) {
                armor = a;
        }

        public void setSpawn(Vector2f newspawn, RenderWindow w) {
                spawnMessage.setPosition(new Vector2f(newspawn.x - 145, newspawn.y - 100));
                w.draw(spawnMessage);
                super.setSpawn(newspawn);
        }

        public boolean canFrostbite() {
                return canFrostbite;
        }

        public boolean canBlink() {
                return canBlink;
        }

        public void canFrostbite(boolean b) {
                canFrostbite = b;
        }

        public void canBlink(boolean b) {
                canBlink = b;
        }

        public boolean haskey() {
                return inventoryList.contains(new Key());
        }

        public void removeKey() {
                inventoryList.remove(new Key());
        }

        public void isFrostbiting() {
                if (mana >= 90 && timestopcd == 5000) {
                        mana = 0;
                        isFrostbiting = true;
                        timestopcd = 0;
                        manacd = 0;
                }
        }

        public boolean frostbiteReady() {
                return timestopcd == 5000;
        }

        public boolean blinkReady() {
                return blinkcd == 50;
        }

        public void resetAbilities() {
                blinkcd = 50;
                invulnerabilitycd = 20;
                timestopcd = 5000;
                regencd = 300;
                manacd = 500;
        }

}
