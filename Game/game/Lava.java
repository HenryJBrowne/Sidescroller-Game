import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Lava extends Enemy {

	/**
	 * This class represents the lava block
	 */

	/**
	 * Constructor
	 * @see Enemy#Enemy()
	 */
	public Lava(Vector2f pos, Texture[][] idle, Texture[][] run, Texture[][] attack) {
		super(0f, 0f, 200, 0, pos, new Vector2f(16, 16), idle, run, attack, false);
	}

	@Override
	public void behave(MainCharacter eugene, Screen s) {

	}

	@Override
	public void bounce(Entity e) {
		e.damage(50);
	}

	@Override
	public void die(Screen screen) {

	}
}
