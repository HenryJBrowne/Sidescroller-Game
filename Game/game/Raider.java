import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Raider extends Enemy {

	/**
	 * This class represents the Raider enemy
	 */

    /**
	 * Constructor
     * @param pos position of the Raider
	 * @param idle the textures of the raiders idle animation
	 * @param run the textures of the raiders run animation
	 * @param attack the textures of the raiders attack animation
     */
    public Raider(Vector2f pos, Texture[][] idle, Texture[][] run, Texture[][] attack) {
        super(14f, 25f, 200, 20, pos, new Vector2f(16, 32), idle, run, attack, true);
    }

    @Override
    public void behave(MainCharacter eugene, Screen s) {
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

    @Override
    public void bounce(Entity e) {
        e.damage(15);
        if (isRight()) {
            this.setVelocity(new Vector2f(-10, this.getVelocity().y));
        } else {
            this.setVelocity(new Vector2f(10, this.getVelocity().y));
        }
    }

	/**
	 * This method kills the raider and removes him from the screen
	 * @param screen the screen to remove the raider from
	 */
    public void die(Screen screen) {
        setHp(0);
        int dropchance = (int) (Math.random() * 100);

        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);

        if (dropchance <= 70) {
            screen.spawn(this.getPosition(), 302);
        } else if (dropchance <= 50) {
            screen.spawn(this.getPosition(), 303);
        }
    }
}
