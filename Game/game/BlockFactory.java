import java.nio.file.Paths;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

/**
 * Factory for collidable blocks Indexes : 0 Grass, 1 Dirt, 2 Stone, 3 stone2, 4
 * Bricks, 5 Brick Left Corner, 6 Brick Right corner, 7 chest, 8 dark wood
 * plank, 9 real chest
 */

public class BlockFactory {

    private final int NUM_OF_BLOCK_TYPES = 18; // the number of blocks
    private final String[] paths = new String[NUM_OF_BLOCK_TYPES]; // paths for the Textures
    private final Texture[] textures = new Texture[NUM_OF_BLOCK_TYPES]; // textures for the Textures
    private final float[] frictCoefs = new float[NUM_OF_BLOCK_TYPES]; // friction coeficient of the blocks
    private Vector2f size = new Vector2f(16, 16);

    /**
     * Creates immutable database of blocks
     */
    public BlockFactory() {
        paths[0] = "assets/Tiles/Blocks/Grass.png";
        frictCoefs[0] = 0.5f;
        paths[1] = "assets/Tiles/Blocks/Dirt.png";
        frictCoefs[1] = 0.5f;
        paths[2] = "assets/Tiles/Blocks/Stone.png";
        frictCoefs[2] = 0.5f;
        paths[3] = "assets/Tiles/Blocks/Stone2.png";
        frictCoefs[3] = 0.5f;
        paths[4] = "assets/Tiles/Blocks/Bricks.png";
        frictCoefs[4] = 0.5f;
        paths[5] = "assets/Tiles/Blocks/Brick_corner_left.png";
        frictCoefs[5] = 0.5f;
        paths[6] = "assets/Tiles/Blocks/Brick_corner_right.png";
        frictCoefs[6] = 0.5f;
        paths[7] = "assets/Tiles/Blocks/NewChest.png";
        frictCoefs[7] = 0.5f;
        paths[8] = "assets/Tiles/Blocks/DarkWoodenPlank.png";
        frictCoefs[8] = 0.5f;
        paths[9] = "assets/Tiles/Blocks/NewChest.png";
        frictCoefs[9] = 0.5f;
        paths[10] = "assets/Tiles/Blocks/Dark_Bricks.png";
        frictCoefs[10] = 0.5f;
        paths[11] = "assets/Tiles/Blocks/DarkWoodenPlank2.png";
        frictCoefs[11] = 0.5f;
        paths[12] = "assets/Tiles/Blocks/Snow_Stone.png";
        frictCoefs[12] = 0.5f;
        paths[13] = "assets/Tiles/Blocks/Snow_Bricks.png";
        frictCoefs[13] = 0.5f;
        paths[14] = "assets/Tiles/Blocks/WoodenPlank.png";
        frictCoefs[14] = 0.5f;
        paths[15] = "assets/Tiles/Blocks/WoodenPlank.png";
        frictCoefs[15] = 0.5f;
        paths[16] = "assets/Tiles/Blocks/SnowWoodenPlank.png";
        frictCoefs[16] = 0.5f;
        paths[17] = "assets/Tiles/Blocks/Door.png";
        frictCoefs[17] = 0.5f;
    }

    /**
     * Method will create a block from its id in a level matrix
     * 
     * @param pos position of the block in a level
     * @param id  id from level matrix
     * @return if id is not in database: null, otherwise: new Block with appropriate
     *         attributes
     */
    public Block getBlock(Vector2f pos, int id) {
        if (id < 0 || id > NUM_OF_BLOCK_TYPES - 1) {
            return null;
        } else {
            if (textures[id] == null) {
                initializeTexture(id);
            }
            if (id == 9) {
                return new TreasureChest(pos, size, frictCoefs[id], textures[id]);
            }
            if (id== 17){
                return new Block(pos, new Vector2f(16, 48), frictCoefs[id], textures[id]);
            } else {
                return new Block(pos, size, frictCoefs[id], textures[id]);
            }
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
