import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class SnowWarrior extends Enemy {

	/**
	 * This class represents the Snow Warrior enemy in the game
	 */

    /**
	 * Constructor
     * @param pos position of the Snow Warrior
	 * @param idle the textures of the Snow Warrior idle animation
	 * @param run the textures of the Snow Warrior run animation
	 * @param attack the textures of the Snow Warrior attack animation
     */
    public SnowWarrior(Vector2f pos, Texture[][] idle, Texture[][] attack, Texture[][] run) {
        // testing
        super(16f, 20f, 200, 0, pos, new Vector2f(24, 48), idle, run, attack, true);
    }

    @Override
    public void behave(MainCharacter eugene, Screen s) {
        if (Math.abs(this.getPosition().x - eugene.getPosition().x) < 300) {
            // stop if too close
            if ((Math.abs(eugene.getPosition().x - this.getPosition().x) < 15)) {
                return;
            }
            if (this.getVelocity().x == 0 && this.isGrounded()) {
                this.jump();
            }
            if (this.getPosition().x > eugene.getPosition().x) {
                this.moveLeft();
            } else {
                this.moveRight();
            }
        }

    }

    @Override
    public void bounce(Entity e) {
        e.damage(50);
        if (isRight()) {
            this.setVelocity(new Vector2f(-10, this.getVelocity().y));
        } else {
            this.setVelocity(new Vector2f(10, this.getVelocity().y));
        }
    }

	/**
	 * This method kills the snow warrior and removes it from the screen
	 * @param screen the screen for the dark warrior to die on
	 */
    public void die(Screen screen) {
        setHp(0);
        int dropchance = (int) (Math.random() * 100);

        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);

        if (dropchance <= 40) {
            screen.spawn(this.getPosition(), 302);
        } else if (dropchance <= 30) {
            screen.spawn(this.getPosition(), 303);
        }
    }
}
