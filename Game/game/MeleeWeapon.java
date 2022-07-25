import java.util.ArrayList;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class MeleeWeapon implements Weapon {

	/**
	 * This superclass represents the meleeweapons in the game
	 */

    private float attack;
    private String name;
    private Vector2f position;
    private float rangeOfAttack;
    private Texture[][] animation;
    private boolean delete;
    public final Object origin;
    private boolean canMultiple;

	/**
	 * Constructor
	 * @param a the attack damage of the weapon
	 * @param n the name of the weapon
	 * @param ra the range of the weapons attack
	 * @param an the textures of the weapons animation
	 * @param pos the position of the weapon
	 * @param o @see Weapon#Weapon()
	 * @param m whether or not the weapon can hit more than one enemy per swing
	 */

    public MeleeWeapon(float a, String n, float ra, Texture[][] an, Vector2f pos, Object o, boolean m) {
        attack = a;
        name = n;
        animation = an;
        rangeOfAttack = ra;
        position = pos;
        origin = o;
        canMultiple = m;
    }

    public MeleeWeapon(float a, float ra, Vector2f pos, Object o, boolean m) {
        attack = a;
        name = null;
        animation = null;
        rangeOfAttack = ra;
        position = pos;
        origin = o;
        canMultiple = m;
    }

    @Override
    public void use(MainCharacter m) {
        m.setMainWeapon(this);
        m.setAttackAnimation(animation);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getAttack() {
        return attack;
    }

    @Override
    public Vector2f getPosition() {
        return position;
    }

    @Override
    public Vector2f getSize() {
        return new Vector2f(rangeOfAttack, 40);
    }

	/**
	 * This method checks if the block it is hitting is a chest
	 * @param b the list of blocks that the weapon is hitting
	 * @param s the screen the weapon is on
	 */
    public void checkForChest(ArrayList<Block> b, Screen s) {
        if (((Entity) origin).isRight()) {
            checkRight(b, s);
        } else {
            checkLeft(b, s);
        }
    }

	/**
	 * This method checks to the left of the character, to be called if the weapon is swung to the left of the character
	 * @param blocks the blocks to check
	 * @param s the screen to check on
	 */
    private void checkLeft(ArrayList<Block> blocks, Screen s) {
        for (Block b : blocks) {
            if (Math.abs(b.getPosition().x - position.x) < Math.abs(rangeOfAttack) + 10
                    && Math.abs(b.getPosition().y - position.y) < Math.abs(rangeOfAttack) + 10) {
                if (b.getPosition().y < position.y + rangeOfAttack && b.getPosition().y + b.getSize().y > position.y) {
                    if (b.getPosition().x + b.getSize().x > position.x
                            && b.getPosition().x + b.getSize().x < position.x + rangeOfAttack) {
                        if (b instanceof TreasureChest) {
                            ((TreasureChest) b).destroy(s);
                        }
                    }
                }
            }
        }
    }

	/**
	 * This method checks to the right of the character, to be called if the weapon is swung to the right of the character
	 * @param blocks the blocks to check
	 * @param s the screen to check on
	 */
    private void checkRight(ArrayList<Block> blocks, Screen s) {
        for (Block b : blocks) {
            if (Math.abs(b.getPosition().x - position.x) < Math.abs(rangeOfAttack) + 50
                    && Math.abs(b.getPosition().y - position.y) < Math.abs(rangeOfAttack) + 50) {
                if (b.getPosition().y < position.y + rangeOfAttack && b.getPosition().y + b.getSize().y > position.y) {
                    if (b.getPosition().x < position.x + rangeOfAttack && b.getPosition().x > position.x) {
                        if (b instanceof TreasureChest) {
                            ((TreasureChest) b).destroy(s);
                        }
                    }
                }
            }
        }
    }

    public boolean delete() {
        return delete;
    }

    public void delete(boolean b) {
        delete = b;
    }

	/**
	 * Accessor for if the weapon can hit multiple characters at once
	 * @return true if the weapon can hit multiple characters at once
	 */
    public boolean canMultiple() {
        return canMultiple;
    }
}
