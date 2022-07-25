import java.util.ArrayList;
import org.jsfml.graphics.Drawable;

/**
 * Class that stores all data about the level
 */
public class Level {
    private ArrayList<Drawable> elements;
    private ArrayList<Drawable> blocks;
    private int number;
    private ArrayList<Entity> npcs;
    private MainCharacter mainCharacter;
    private Background bg;
    private Shop shop;
    private ArrayList<Trigger> triggers;
    private String storyIntro;
    private Enemy boss;
    private ArrayList<Drawable> doorBlocks;

    public Level(int n) {
        number = n;
    }

    /**
     * Accessor for the list of elements that implement Drawable interface
     * 
     * @return list of elements
     */
    public ArrayList<Drawable> getElements() {
        return elements;
    }

    /**
     * Accessor for the level id
     * 
     * @return level id
     */
    public int getNumber() {
        return number;
    }

    /**
     * Accessor for the list of NPCs
     * 
     * @return list of elements
     */
    public ArrayList<Entity> getNpcs() {
        return npcs;
    }

    /**
     * Accessor for the main character
     * 
     * @return main character
     */
    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }

    /**
     * Accessor for the list of blocks
     * 
     * @return list of blocks
     */
    public ArrayList<Drawable> getBlocks() {
        return blocks;
    }

    /**
     * Accessor for the background instance
     * 
     * @return background instance
     */
    public Background getBackground() {
        return bg;
    }

    /**
     * Accessor for a instance of a shop
     * 
     * @return background instance
     */
    public Shop getShop() {
        return shop;
    }

    public ArrayList<Trigger> getTriggers() {
        return triggers;
    }

    /**
     * Mutator for list of elements which implements Drawable interface
     * 
     * @param e list of elements
     */
    public void setElements(ArrayList<Drawable> e) {
        elements = e;
    }

    /**
     * Mutator for the level id
     * 
     * @param n level id
     */
    public void setNumber(int n) {
        number = n;
    }

    /**
     * Mutator for the list of NPCs that extend Entity class
     * 
     * @param n list of elements
     */
    public void setNpcs(ArrayList<Entity> n) {
        npcs = n;
    }

    /**
     * Mutator for the main character
     * 
     * @param m main character
     */
    public void setMainCharacter(MainCharacter m) {
        mainCharacter = m;
    }

    /**
     * Mutator for the list of blocks
     * 
     * @param b list of blocks
     */
    public void setBlocks(ArrayList<Drawable> b) {
        blocks = b;
    }

    /**
     * Set the background for the level
     * 
     * @param bg
     */
    public void setBackground(Background bg) {
        this.bg = bg;
    }

    /**
     * Mutator for the instance of the shop
     * 
     * @param s shop instance
     */
    public void setShop(Shop s) {
        shop = s;
    }

    public void setTriggers(ArrayList<Trigger> t) {
        triggers = t;
    }

    public String getStoryIntro() {
        return storyIntro;
    }

    public void setStoryIntro(String s) {
        storyIntro = s;
    }

    public Enemy getBoss() {
        return boss;
    }

    public void setBoss(Enemy b) {
        boss = b;
    }

    public void setDoorBlocks(ArrayList<Drawable> d) {
        doorBlocks = d;
    }

    public ArrayList<Drawable> getDoorBlocks() {
        return doorBlocks;
    }
}
