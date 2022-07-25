import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import org.jsfml.graphics.Drawable;
import org.jsfml.system.Vector2f;

/**
 * Class responsible for parsing level data from csv files
 */
public class LevelParser implements Runnable {
    private int id;
    private Level level;
    private boolean ready = false;
    private MainCharacter mainChar;
    private ArrayList<Trigger> triggers;

    public LevelParser(int i, Level l, MainCharacter m, Screen s) {
        triggers = Screen.TRIGGER_DB.getTriggers(i, s);
        id = i;
        level = l;
        mainChar = m;
    }

    /**
     * Method will find the appropriate level file and parse it to an instance of a
     * Level class
     * 
     * @param id id of a level -> level file must be refered to as "Level" + id
     *           ".csv"
     * @return instance of Level with appropriate attributes
     */
    private void parseLevel(int id) {
        String file = "levels/Level" + String.valueOf(id) + ".csv";// "levels/TestLVL.csv";
        ArrayList<Drawable> assets = new ArrayList<>();
        ArrayList<Drawable> blocks = new ArrayList<>();
        ArrayList<Drawable> doorBlocks = new ArrayList<>();
        ArrayList<Entity> entities = new ArrayList<>();
        MainCharacter m = null;
        Vector2f pos = new Vector2f(0, 0);
        float length = -1;
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNext()) {
                String s = scanner.next();
                for (String str : s.split(",")) {
                    int temp = Integer.valueOf(str);
                    // find what list/var to use
                    if (temp < -1) {
                        assets.add(Screen.BACKGROUND_FACTORY.getAsset(pos, temp));
                    } else if (temp < 101) {
                        Block b = null;
                        if (temp == 17) {
                            b = Screen.BLOCK_FACTORY.getBlock(pos, temp);
                            doorBlocks.add(b);
                        }
                        if (b == null)
                            blocks.add(Screen.BLOCK_FACTORY.getBlock(pos, temp));
                        else
                            blocks.add(b);
                    } else if (temp == 101) {
                        if (mainChar != null) {
                            mainChar.setPosition(pos);
                            mainChar.setHp(MainCharacter.hp);
                            mainChar.setSpawn(pos);
                            mainChar.setVelocity(new Vector2f(0, 0));
                            mainChar.resetAbilities();
                            m = mainChar;
                        } else {
                            m = (MainCharacter) Screen.ENTITY_FACTORY.getEntity(pos, 101);
                        }
                    } else {
                        Entity e = Screen.ENTITY_FACTORY.getEntity(pos, temp);
                        if (e != null) {
                            entities.add(e);
                        }
                        if (temp == 155 || temp == 207 || temp == 211 || temp == 212) {
                            level.setBoss((Enemy) e);
                        }
                    }
                    pos = new Vector2f(pos.x + 16, pos.y);
                }
                length = pos.x;
                pos = new Vector2f(0, pos.y + 16);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        level.setElements(assets);
        level.setBlocks(blocks);
        level.setNpcs(entities);
        level.setMainCharacter(m);
        level.setNumber(id);
        level.setBackground(Screen.BACKGROUND_FACTORY.getBackground(id, length));
        level.setTriggers(triggers);
        level.setShop(new ShopFactory(m).getShop(id));
        level.setStoryIntro(Screen.BACKGROUND_FACTORY.getIntro(id));
        level.setDoorBlocks(doorBlocks.isEmpty() ? null : doorBlocks);
        ready = true;
    }

    @Override
    public void run() {
        parseLevel(id);
    }

    /**
     * Accessor for isReady boolean
     * 
     * @return true if level is loaded, false otherwise
     */
    public boolean isReady() {
        return ready;
    }
}
