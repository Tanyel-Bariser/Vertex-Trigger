package com.vertextrigger.factory.animationfactory;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.util.GameObjectSize;

public class PlayerAnimationFactory extends AbstractAnimationFactory {

    private final SpriteFactory spriteFactory;
    private final GameObjectSize size; 

    public PlayerAnimationFactory() {
        spriteFactory = new SpriteFactory();
        size = GameObjectSize.createPlayerSize();
    }

    /**
     * Creates animation for the player's run from its composite sprites
     * LOOP_NORMAL plays the animation looped in sequential order
     *
     * @return animation of the player's run
     */
    public Animation getRun() {
        Sprite[] runSprites = new Sprite[] {
        		spriteFactory.createCoreSprite("run1", size),
        		spriteFactory.createCoreSprite("run2", size),
        		spriteFactory.createCoreSprite("run3", size),
        		spriteFactory.createCoreSprite("run4", size),
        		spriteFactory.createCoreSprite("run5", size),
        		spriteFactory.createCoreSprite("run6", size)
        };

        Animation runAnimation = new Animation(0.1F, runSprites);
        runAnimation.setPlayMode(Animation.PlayMode.LOOP);
        return runAnimation;
    }

    /**
     * Creates animation for the player's rise (first half of jump) from its composite sprites
     * NORMAL plays the animation once in sequential order
     *
     * @return animation of the player's jump
     */
    public Animation getRise() {
        Sprite[] jumpSprites = new Sprite[]{
        		spriteFactory.createCoreSprite("jump2", size),
        		spriteFactory.createCoreSprite("jump3", size)
        };

        Animation jumpAnimation = new Animation(.5f, jumpSprites);
        jumpAnimation.setPlayMode(Animation.PlayMode.LOOP);
        return jumpAnimation;
    }
    
    /**
     * Creates animation for the player's fall (second half of jump) from its composite sprites
     * NORMAL plays the animation once in sequential order
     *
     * @return animation of the player's jump
     */
    public Animation getFall() {
        Sprite[] jumpSprites = new Sprite[]{
        		spriteFactory.createCoreSprite("jump3", size),
        		spriteFactory.createCoreSprite("jump2", size)
        };

        Animation jumpAnimation = new Animation(.5f, jumpSprites);
        jumpAnimation.setPlayMode(Animation.PlayMode.LOOP);
        return jumpAnimation;
    }

    /**
     * Creates animation for the player standing still (middle of jump) from its composite sprites
     * NORMAL plays the animation once in sequential order
     *
     * @return animation of the player's jump
     */
	public Animation getStanding() {
        Sprite[] jumpSprites = new Sprite[]{
        		spriteFactory.createCoreSprite("shoot2", size),
        };

        Animation jumpAnimation = new Animation(0.1f, jumpSprites);
        jumpAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        return jumpAnimation;
    }

    /**
     * Creates animation for the player's gunshot from its composite sprites
     * NORMAL plays the animation once in sequential order
     *
     * @return animation of the player's gunshot
     */
    public Animation getShoot() {
        Sprite[] shootSprites = new Sprite[] {
        		spriteFactory.createCoreSprite("shoot1", size),
        		spriteFactory.createCoreSprite("shoot2", size),
        		spriteFactory.createCoreSprite("shoot3", size),
        		spriteFactory.createCoreSprite("shoot4", size),
        		spriteFactory.createCoreSprite("shoot5", size),
        		spriteFactory.createCoreSprite("shoot6", size),
        		spriteFactory.createCoreSprite("shoot7", size),
        		spriteFactory.createCoreSprite("shoot8", size)
        };

        Animation shootAnimation = new Animation(0.02F, shootSprites);
        shootAnimation.setPlayMode(Animation.PlayMode.LOOP);
        return shootAnimation;
    }

    /**
     * Creates animation for the player's death from its composite sprites
     * NORMAL plays the animation once in sequential order
     *
     * @return animation of the player's death
     */
    public Animation getDeath() {
        Sprite[] deathSprites = new Sprite[] {
        		spriteFactory.createCoreSprite("death1", size),
        		spriteFactory.createCoreSprite("death2", size),
        		spriteFactory.createCoreSprite("death3", size),
        		spriteFactory.createCoreSprite("death4", size),
        };

        Animation deathAnimation = new Animation(0.1f, deathSprites);
        deathAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        return deathAnimation;
    }
}