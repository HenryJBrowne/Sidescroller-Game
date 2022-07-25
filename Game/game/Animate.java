import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Texture;
import java.util.Hashtable;

/**
 * Class responsible for animation of entities
 */
public class Animate {

    // Image frames used in animation
    private Hashtable<State, Texture[][]> frames = new Hashtable<>();
    private int currentFrame = 0;
    private State state = State.IDLE;
    private Entity entity;
    private int attackFrame = 1;

    /**
     * Constructor. Paths are set as [0][] if facing right, [1][] if facing left
     * 
     * @param run    paths to run assets
     * @param idle   paths to idle assets
     * @param attack paths to attack assets
     * @param e      entity
     */
    public Animate(Texture[][] run, Texture[][] idle, Texture[][] attack, Entity e) {
        if (run != null) {
            frames.put(State.RUNNING, run);
            frames.put(State.ATTACKING, attack);
            frames.put(State.IDLE, idle);
            frames.put(State.JUMPING, idle);
        }
        entity = e;
    }

    /**
     * Method will update the entity texture
     * 
     * @param s state to update animation to.
     */
    public void update(State s) {
        // if animation is attack this will ensure it
        // will not change until it is finished
        if (state == State.ATTACKING && attackFrame != frames.get(state)[0].length) {
            updateAnimation(frames.get(state));
            attackFrame++;
            return;
        } else if (attackFrame == frames.get(State.ATTACKING)[0].length && state == State.ATTACKING) {
            attackFrame = 1;
            currentFrame = 0;
            state = State.IDLE;
        }
        if (state != s) {
            currentFrame = 0;
            state = s;

        }
        updateAnimation(frames.get(s));
        if (currentFrame == frames.get(s)[0].length - 1) {
            currentFrame = 0;
        } else {
            currentFrame++;
        }
    }

    /**
     * Updates the texture of a entity
     * 
     * @param s array of paths to the assets
     */
    private void updateAnimation(Texture[][] s) {
        Texture t = checkDirection(s);
        entity.getDrawable().setTextureRect(new IntRect(0, 0, t.getSize().x, t.getSize().y));
        entity.getDrawable().setTexture(t);
    }

    /**
     * Checks direction of a entity and returns the correct texture
     * 
     * @param s paths to the assets
	 * @return returns the correct texture for its direction
     */
    private Texture checkDirection(Texture[][] s) {
        if (entity.isRight()) {
            return s[0][currentFrame];
        } else {
            return s[1][currentFrame];
        }
    }

	/**
	 * Sets the attack animation of an entity
	 *
	 * @param animation the path to the animation assets
	 */
    public void setAttackAnimation(Texture[][] animation) {
        frames.remove(State.ATTACKING);
        frames.put(State.ATTACKING, animation);
    }

	/**
	 * Getter for the current animation state
	 *
	 * @return the current animation state
	 */
    public State getStateOfAnimation() {
        return state;
    }
}
