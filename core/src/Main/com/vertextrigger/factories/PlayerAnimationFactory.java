package com.vertextrigger.factories;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerAnimationFactory {

    private final SpriteFactory spriteFactory;

    {
        spriteFactory = new SpriteFactory();
    }

    /**
     * Creates animation for the player's walk from its composite sprites
     * LOOP_NORMAL plays the animation looped in sequential order
     *
     * @return animation of the player's walk
     */
    public Animation getPlayerWalk() {
        Sprite[] walkSprites = new Sprite[] {
                spriteFactory.getPlayerSprite("walk/walk1", 2, 4),
                spriteFactory.getPlayerSprite("walk/walk2", 2, 4),
                spriteFactory.getPlayerSprite("walk/walk3", 2, 4),
                spriteFactory.getPlayerSprite("walk/walk4", 2, 4),
                spriteFactory.getPlayerSprite("walk/walk5", 2, 4),
                spriteFactory.getPlayerSprite("walk/walk6", 2, 4)
        };

        Animation walkAnimation = new Animation(0.1F, walkSprites);
        walkAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        return walkAnimation;
    }

    /**
     * Creates animation for the player's run from its composite sprites
     * LOOP_NORMAL plays the animation looped in sequential order
     *
     * @return animation of the player's run
     */
    public Animation getPlayerRun() {
        Sprite[] runSprites = new Sprite[] {
                spriteFactory.getPlayerSprite("run/run1", 2, 4),
                spriteFactory.getPlayerSprite("run/run2", 2, 4),
                spriteFactory.getPlayerSprite("run/run3", 2, 4),
                spriteFactory.getPlayerSprite("run/run4", 2, 4),
                spriteFactory.getPlayerSprite("run/run5", 2, 4),
                spriteFactory.getPlayerSprite("run/run6", 2, 4)
        };

        Animation runAnimation = new Animation(0.1F, runSprites);
        runAnimation.setPlayMode(Animation.PlayMode.LOOP);
        return runAnimation;
    }

    /**
     * Creates animation for the player's jump from its composite sprites
     * LOOP_PINGPONG plays the animation in reverse after reaching the end - used here to animate the jump landing
     *
     * @return animation of the player's jump
     */
    public Animation getPlayerJump() {
        Sprite[] jumpSprites = new Sprite[]{
                spriteFactory.getPlayerSprite("jump/jump1", 2, 4),
                spriteFactory.getPlayerSprite("jump/jump2", 2, 4),
                spriteFactory.getPlayerSprite("jump/jump3", 2, 4)
        };

        Animation jumpAnimation = new Animation(0.1f, jumpSprites);
        jumpAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        return jumpAnimation;
    }

    /**
     * Creates animation for the player's rise (first half of jump) from its composite sprites
     * NORMAL plays the animation once in sequential order
     *
     * @return animation of the player's jump
     */
    public Animation getPlayerRise() {
        Sprite[] jumpSprites = new Sprite[]{
                spriteFactory.getPlayerSprite("jump/jump1", 2, 4),
        };

        Animation jumpAnimation = new Animation(0.1f, jumpSprites);
        jumpAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        return jumpAnimation;
    }

    /**
     * Creates animation for the player's fall (second half of jump) from its composite sprites
     * NORMAL plays the animation once in sequential order
     *
     * @return animation of the player's jump
     */
    public Animation getPlayerFall() {
        Sprite[] jumpSprites = new Sprite[]{
                spriteFactory.getPlayerSprite("jump/jump3", 2, 4),
        };

        Animation jumpAnimation = new Animation(0.1f, jumpSprites);
        jumpAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        return jumpAnimation;
    }

    /**
     * Creates animation for the player standing still (middle of jump) from its composite sprites
     * NORMAL plays the animation once in sequential order
     *
     * @return animation of the player's jump
     */
    public Animation getPlayerStanding() {
        Sprite[] jumpSprites = new Sprite[]{
                spriteFactory.getPlayerSprite("jump/jump2", 2, 4),
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
    @Deprecated
    public Animation getPlayerShoot() {
        Sprite[] shootSprites = new Sprite[] {
                spriteFactory.getPlayerSprite("shoot/shoot1", 2, 4),
                spriteFactory.getPlayerSprite("shoot/shoot2", 2, 4),
                spriteFactory.getPlayerSprite("shoot/shoot3", 2, 4),
                spriteFactory.getPlayerSprite("shoot/shoot4", 2, 4),
                spriteFactory.getPlayerSprite("shoot/shoot5", 2, 4),
                spriteFactory.getPlayerSprite("shoot/shoot6", 2, 4),
                spriteFactory.getPlayerSprite("shoot/shoot7", 2, 4),
                spriteFactory.getPlayerSprite("shoot/shoot8", 2, 4)
        };

        Animation shootAnimation = new Animation(0.1F, shootSprites);
        shootAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        return shootAnimation;
    }

    /**
     * Creates animation for the player's crouched gunshot from its composite sprites
     * NORMAL plays the animation once in sequential order
     *
     * @return animation of the player's crouched gunshot
     */
    @Deprecated
    public Animation getPlayerCrouchedShoot() {
        Sprite[] crouchedSprites = new Sprite[] {
                spriteFactory.getPlayerSprite("crouch/crouch1", 2, 4),
                spriteFactory.getPlayerSprite("crouch/crouch2", 2, 4)
        };

        Animation crouchedShootAnimation = new Animation(0.1F, crouchedSprites);
        crouchedShootAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        return crouchedShootAnimation;
    }

    /**
     * Creates animation for the player's death from its composite sprites
     * NORMAL plays the animation once in sequential order
     *
     * @return animation of the player's death
     */
    public Animation getPlayerDeath() {
        Sprite[] deathSprites = new Sprite[] {
                spriteFactory.getPlayerSprite("death/death1", 2, 4),
                spriteFactory.getPlayerSprite("death/death2", 2, 4),
                spriteFactory.getPlayerSprite("death/death3", 2, 4),
                spriteFactory.getPlayerSprite("death/death4", 2, 4)
        };

        Animation deathAnimation = new Animation(0.1f, deathSprites);
        deathAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        return deathAnimation;
    }
}
