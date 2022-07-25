import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class RollingBall extends Enemy {

	/**
	 * This class represents the rolling balls - a dangerous hazard to avoid in the game
	 */

    private static final int hp = 80;
    private static final int attackI = 5;

    /**
	 * Constructor
     * @param pos position of the rolling ball
	 * @param idle the textures of the balls idle animation
	 * @param run the textures of the balls run animation
	 * @param attack the textures of the balls attack animation
     */
    public RollingBall(Vector2f pos, Texture[][] idle, Texture[][] run, Texture[][] attack) {
        super(8f, 0f, hp, attackI, pos, new Vector2f(32, 32), idle, run, attack, true);
    }

	/**
	 * This method makes the ball roll back and forth
	 * @param eugene the main character to check for collision
	 * @param s the screen to roll on
	 */
    public void behave(MainCharacter eugene, Screen s) {

        if (this.getVelocity().x == 0) {
            isRight(!isRight());
        }
        if (isRight()) {
            moveRight();
        } else {
            moveLeft();
        }
    }

    @Override
    public void bounce(Entity e) {
        isRight(!isRight());
        e.damage(20);
    }

    @Override
    public void die(Screen screen) {
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
    }
}
