import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Ogre extends Enemy {
	
	/**
	 * This class represents the ogre enemy in the game
	 */

    /**
	 * Constructor
     * @param pos position of the Ogre
	 * @param idle the textures of the idle animation of the ogre
	 * @param run the textures of the run animation of the ogre
	 * @param attack the textures of the attack animation of the ogre
     */
    public Ogre(Vector2f pos, Texture[][] idle, Texture[][] run, Texture[][] attack) {
        super(2f, 0f, 200, 40, pos, new Vector2f(20, 40), idle, run, attack, true);
    }

    @Override
    public void behave(MainCharacter eugene, Screen s) {
        if (this.getPosition().x > eugene.getPosition().x) {
            this.moveLeft();
        } else {
            this.moveRight();
        }

    }

    @Override
    public void bounce(Entity e) {
        e.damage(5);
        if (isRight()) {
            this.setVelocity(new Vector2f(-10, this.getVelocity().y));
        } else {
            this.setVelocity(new Vector2f(10, this.getVelocity().y));
        }
    }

	/**
	 * This method kills the ogre and removes it from the screen
	 * @param screen the screen for the ogre to die on
	 */
    public void die(Screen screen) {
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);

        screen.spawn(this.getPosition(), 302);

    }
}
