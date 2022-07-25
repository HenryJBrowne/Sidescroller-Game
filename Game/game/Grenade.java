import java.util.ArrayList;

import org.jsfml.system.Vector2f;

public class Grenade extends ThrowingWeapon {

	/**
	 * This class represents the grenade throwing weapon in the game
	 */

    private Vector2f velocity;
    private Vector2f positon;
    public static float areaOfDamage = 60;

	/**
	 * Constructor
	 * @param pos the position of the grenade
	 * @param dir where the grenade will be thrown
	 * @param o @see ThrowingWeapon#ThrowingWeapon
	 */

    public Grenade(Vector2f pos, Vector2f dir, Object o) {
        super(15, 50, new Vector2f(areaOfDamage, 10), pos, 0, dir, o, "Bomb",
                Screen.TEXTURE_FACTORY.getProjectiles("Bomb"));
        if ((dir.x - pos.x) > 0) {
            velocity = new Vector2f(10, -30);
        } else {
            velocity = new Vector2f(-10, -30);
        }
        positon = pos;
    }

    public Grenade(Object o) {
        super(15, 80, new Vector2f(0, 0), new Vector2f(0, 0), 0, new Vector2f(0, 0), o, "Bomb",
                Screen.TEXTURE_FACTORY.getProjectiles("Bomb"));
    }

    @Override
    public void update(ArrayList<Block> blocks, Screen s) {
        checkForBlocks(blocks, s);
        velocity = new Vector2f(velocity.x, velocity.y + 5f);
        move(velocity);
        positon = super.getPosition();
    }

    @Override
    public Vector2f getPosition() {
        return new Vector2f(positon.x - (areaOfDamage / 2), positon.y);
    }
}
