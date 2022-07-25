import java.util.ArrayList;

import org.jsfml.system.Vector2f;

public class Money extends Item {
	/**
	 * This class represents money drops to be picked up by the main character
	 */
    private int amount;

    /**
	 * Constructor
     * @param pos position of the money
     */
    public Money(Vector2f pos, ArrayList<Block> b) {
        super(pos, new Vector2f(9, 9), b, Screen.TEXTURE_FACTORY.getIcon("Money"));
        amount = 1 + (int) (Math.random() * 4);
    }

    @Override
    public void pickUp(MainCharacter m) {
        if (m.getPosition().y < this.getPosition().y && m.getPosition().y + m.getSize().y > this.getPosition().y) {
            if (m.getPosition().x < this.getPosition().x + this.getSize().x
                    && this.getPosition().x < m.getPosition().x + m.getSize().x) {
                pickedUp(true);
                m.modifyMoney(amount);
                SoundEffectManager.playSound("soundeffects/MoneySound.wav");
            }
        }
    }

}
