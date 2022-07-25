import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

/**
 * Factory for asset blocks
 * 
 * This stores the indexes of the assets in the csv file: -2 trees, -3 dead
 * trees, -4 wall, -5 newBrickBot1, -6 newBrickBot2, -7 newBrickBot3, -8 flagUp,
 * -9 flagBottom, -10 cage, -11 chainstart, -12 chainmiddle, -13 pot, -14
 * redFlower, -15 yellowFlower, -16 potdestroyed, -17 chainEnd, -18 windowIron1,
 * -19 windowIron2, -20 windowIron3, -21 tent, -22 fire, -23 Stone , -24
 * RightArrowSign , -25 DangerSign
 * 
 */
public class BackgroundFactory {

    private final int NUM_OF_LEVELS = 4;
    private final int NUM_OF_BLOCK_TYPES = 41; // number of available assets
    private final String[] paths = new String[NUM_OF_BLOCK_TYPES]; // paths for the Textures
    private final Texture[] textures = new Texture[NUM_OF_BLOCK_TYPES]; // textures for the Textures
    private final Vector2f[] sizes = new Vector2f[NUM_OF_BLOCK_TYPES]; // sizes for the assets
    private final String[] backgrounds = new String[NUM_OF_LEVELS]; //number of levels & backgrounds to go with them
    private final Vector2f standartSize = new Vector2f(16, 16); //saved standard size for an asset
    private final Texture[] texturesBg = new Texture[NUM_OF_LEVELS]; //A list of each background (one for each level)

    public BackgroundFactory() {

        paths[0] = "assets/Tiles/BackgroundAssets/Tree.png";
        sizes[0] = new Vector2f(64, 64);
        paths[1] = "assets/Tiles/BackgroundAssets/Dead tree.png";
        sizes[1] = new Vector2f(64, 64);
        paths[2] = "assets/Tiles/BackgroundAssets/Wall.png";
        sizes[2] = new Vector2f(16, 64);
        paths[3] = "assets/Tiles/BackgroundAssets/NewBrick/NewBrickBottom1.png";
        sizes[3] = standartSize;
        paths[4] = "assets/Tiles/BackgroundAssets/NewBrick/NewBrickBottom2.png";
        sizes[4] = standartSize;
        paths[5] = "assets/Tiles/BackgroundAssets/NewBrick/NewBrickBottom3.png";
        sizes[5] = standartSize;
        paths[6] = "assets/Tiles/BackgroundAssets/FlagTop.png";
        sizes[6] = standartSize;
        paths[7] = "assets/Tiles/BackgroundAssets/FlagBottom.png";
        sizes[7] = standartSize;
        paths[8] = "assets/Tiles/BackgroundAssets/Cage.png";
        sizes[8] = new Vector2f(48, 48);
        paths[9] = "assets/Tiles/BackgroundAssets/Chain/Chain.png";
        sizes[9] = standartSize;
        paths[10] = "assets/Tiles/BackgroundAssets/Chain/ChainBottom.png";
        sizes[10] = standartSize;
        paths[11] = "assets/Tiles/BackgroundAssets/Pot.png";
        sizes[11] = standartSize;
        paths[12] = "assets/Tiles/BackgroundAssets/RedFlower.png";
        sizes[12] = new Vector2f(7, 16);
        paths[13] = "assets/Tiles/BackgroundAssets/YellowFlower.png";
        sizes[13] = new Vector2f(7, 16);
        paths[14] = "assets/Tiles/BackgroundAssets/PotDestroyed.png";
        sizes[14] = standartSize;
        paths[15] = "assets/Tiles/BackgroundAssets/Chain/ChainEnd.png";
        sizes[15] = standartSize;
        paths[16] = "assets/Tiles/BackgroundAssets/WindowFrameIron1.png";
        sizes[16] = standartSize;
        paths[17] = "assets/Tiles/BackgroundAssets/WindowFrameIron2.png";
        sizes[17] = standartSize;
        paths[18] = "assets/Tiles/BackgroundAssets/WindowFrameIron3.png";
        sizes[18] = standartSize;
        paths[19] = "assets/Tiles/BackgroundAssets/BanditTent.png";
        sizes[19] = new Vector2f(64, 32);
        paths[20] = "assets/Tiles/BackgroundAssets/CampFire.png";
        sizes[20] = standartSize;
        paths[21] = "assets/Tiles/Blocks/Stone2.png"; // Non-Collision stone tile for "secret" areas
        sizes[21] = standartSize;
        paths[22] = "assets/Tiles/BackgroundAssets/Right Arrow Sign.png";
        sizes[22] = standartSize;
        paths[23] = "assets/Tiles/BackgroundAssets/Danger Sign.png";
        sizes[23] = standartSize;
        paths[24] = "assets/Tiles/Blocks/Dark_Bricks.png"; // Non-Collision tile
        sizes[24] = standartSize;
        paths[25] = "assets/Tiles/BackgroundAssets/WindowFrameIron1.png";
        sizes[25] = standartSize;
        paths[26] = "assets/Tiles/BackgroundAssets/Up Arrow Sign.png";
        sizes[26] = standartSize;
        paths[27] = "assets/Tiles/new/Shop.png";
        sizes[27] = new Vector2f(64, 64);
        paths[28] = "assets/Tiles/new/SnowShop.png";
        sizes[28] = new Vector2f(64, 64);
        paths[29] = "assets/Tiles/BackgroundAssets/SnowTree.png";
        sizes[29] = new Vector2f(64, 64);
        paths[30] = "assets/Tiles/Tutorial/WASD.png";
        sizes[30] = new Vector2f(128, 64);
        paths[31] = "assets/Tiles/Tutorial/E.png";
        sizes[31] = new Vector2f(128, 64);
        paths[32] = "assets/Tiles/Tutorial/SPACE.png";
        sizes[32] = new Vector2f(128, 32);
        paths[33] = "assets/Tiles/Tutorial/LEFT_CLICK.png";
        sizes[33] = new Vector2f(128, 64);
        paths[34] = "assets/Tiles/Tutorial/LEFT_CLICK2.png";
        sizes[34] = new Vector2f(128, 64);
        paths[38] = "assets/Tiles/Tutorial/CHEST.png";
        sizes[38] = new Vector2f(128, 64);
        paths[39] = "assets/Tiles/Tutorial/SHOP.png";
        sizes[39] = new Vector2f(128, 64);
        paths[37] = "assets/NPC/Allies/Wizard.png";
        sizes[37] = new Vector2f(20, 36);
        paths[35] = "assets/Tiles/Blocks/Bricks.png";
        sizes[35] = standartSize;
        paths[36] = "assets/Tiles/new/FireyShop.png";
        sizes[36] = new Vector2f(64, 64);
        paths[37] = "assets/NPC/Allies/Reuben.png";
        sizes[37] = new Vector2f(16, 32);
        paths[40] = "assets/tiles/Tutorial/BOSS.png";
        sizes[40] = new Vector2f(128, 64);

        backgrounds[0] = "assets/Background/Level1 Background.png";
        backgrounds[1] = "assets/Background/Level2 Background.png";
        backgrounds[2] = "assets/Background/Level3 Background.png";
        backgrounds[3] = "assets/Background/Level4 Background.png";

    }
	
	/**
	 * Getter for the asset
	 * @param pos the position of the asset
	 * @param idNeg the id of the asset in the factory
	 */

    public Asset getAsset(Vector2f pos, int idNeg) {
        int id = Math.abs(idNeg) - 2;

        if (id < 0 || id > NUM_OF_BLOCK_TYPES - 1) {
            return null;
        } else {
            if (textures[id] == null) {
                initializeTexture(id);
            }
            return new Asset(pos, sizes[id], textures[id]);
        }
    }

	/** 
	 * Getter for the background
	 * @param id the id of the background in the factory
	 * @param l the length of the level
	 */
    public Background getBackground(int id, float l) {
        if (id < 0 || id > NUM_OF_LEVELS) {
            return null;
        } else {
            if (texturesBg[id - 1] == null) {
                initializeBgTexture(id - 1);
            }
            return new Background(texturesBg[id - 1], new Vector2f(600, 338), l);
        }
    }

	/**
	 * This method initialises a background texture by loading it from its path
	 * @param id the id of the background
	 */

    private void initializeBgTexture(int id) {
        texturesBg[id] = new Texture();
        try {
            texturesBg[id].loadFromFile(Paths.get(backgrounds[id]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	/**
	 * Getter for an intro for a level
	 * @param id the id of the intro in the factory
	 */

    public String getIntro(int id) {
        if (id < 0 || id > NUM_OF_LEVELS) {
            return null;
        } else {
            Scanner scanner;
            try {
                scanner = new Scanner(new File("additionalFiles/intro" + id + ".txt"));
                String intro = "";
                while (scanner.hasNextLine()) {
                    intro += scanner.nextLine() + "\n";
                }
                scanner.close();
                return intro;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * If texture is not initialized, initialize it. Prevents redundant variables in
     * the cache
     * 
     * @param id
     */
    private void initializeTexture(int id) {
        textures[id] = new Texture();
        try {
            textures[id].loadFromFile(Paths.get(paths[id]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
