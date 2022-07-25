import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public abstract class Enemy extends Entity implements Npc {

    /**
     * @param m      move speed of the entity
     * @param j      jump speed of the entity
     * @param pos    position of the entity
     * @param s      size of the enemy
     * @param idle   paths for idle assets
     * @param run    path for run assets
     * @param attack paths for attacking assets
     */
    public Enemy(float m, float j, int h, int a, Vector2f pos, Vector2f s, Texture[][] idle, Texture[][] run,
            Texture[][] attack, boolean c) {
        super(m, j, h, a, pos, s, idle, run, attack, c);
    }

    /**
     * Abstract method for behaviour of Enemy
     */
    public abstract void behave(MainCharacter eugene, Screen s);

    /**
     * Method for bouncing of a Entity
     */
    public abstract void bounce(Entity e);

}
