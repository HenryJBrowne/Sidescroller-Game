import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Goblin extends Enemy {
	/**
	 * This class represents the goblin enemy
	 */

	/**
	 * Constructor
	 * @param pos the position of the goblin
	 * @param idle the textures of the goblins idle animation
	 * @param run the textures of the goblins run animation
	 * @param attack the textures of the goblins attack animation
	 */

    public Goblin(Vector2f pos, Texture[][] idle, Texture[][] run, Texture[][] attack) {
        super(16f, 30f, 30, 10, pos, new Vector2f(20, 24), idle, run, attack, true);
    }

    @Override
    public void behave(MainCharacter eugene, Screen s) {

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

    @Override
    public void die(Screen screen) {
        setHp(0);

        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
    }

}
