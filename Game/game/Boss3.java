import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

/**
 * Class for the boss in level 2
 */
public class Boss3 extends Enemy {
    int RangedCd = 30; // cooldown for ranged attacks, measured in frames/ticks
    int RangedTimer = 75; // adds variation to range attack

    /**
     * @param pos position of the Boss
     */
    public Boss3(Vector2f pos, Texture[][] idle, Texture[][] attack, Texture[][] run) {
        super(8f, 4f, 500, 50, pos, new Vector2f(32, 64), idle, run, attack, true);
    }

    /**
     * Method for activating, moving and attacking of the Boss
     * 
     * @param eugene main character
     * @param s      instance of the screen class
     */

    @Override
    public void behave(MainCharacter eugene, Screen s) {
        // TLDR : if within 100 blocks, the boss will chase and melee player,
        // if between 100 and 400, the boss will move away from player and use ranged
        // attacks.
        float dist = Math.abs(this.getPosition().x - eugene.getPosition().x);
        if (dist < 300) {
            if (dist < 100) {
                if (dist < 15) {
                    // stop if too close
                    return;
                }
                if (this.getPosition().x > eugene.getPosition().x) {
                    this.moveLeft();
                } else {
                    this.moveRight();
                }
            } else {
                if (this.getPosition().x > eugene.getPosition().x) {
                    this.moveRight();
                } else {
                    this.moveLeft();
                }
                // if moving away and cooldown is complete, throw a fireball at eugene
                if (RangedCd >= 30) {
                    // face eugene before firing a fireball
                    isRight(this.getPosition().x - eugene.getPosition().x < 0);
                    RangedCd = 0;
                    int OFFSET;
                    if (isRight()) {
                        OFFSET = 20;
                    } else {
                        OFFSET = -20;
                    }
                    s.getWorld().addProjectile(new Iceball(new Vector2f(getPosition().x + OFFSET, getPosition().y + 24),
                            eugene.getPosition(), this));
                } else {
                    RangedCd++;
                }
            }
        }
    }

    /**
     * Method for bouncing of the entity. Will deal damage to the entity
     */
    @Override
    public void bounce(Entity e) {
        e.damage(80);
        if (isRight()) {
            this.setVelocity(new Vector2f(-10, this.getVelocity().y));
        } else {
            this.setVelocity(new Vector2f(10, this.getVelocity().y));
        }
    }

    @Override
    public void die(Screen screen) {
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 301);
        screen.spawn(this.getPosition(), 302);
        screen.spawn(this.getPosition(), 303);
        screen.spawn(this.getPosition(), 304);
        screen.spawn(this.getPosition(), 305);
    }
}
