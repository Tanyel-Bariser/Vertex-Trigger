package com.vertextrigger.factory.animationfactory;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.util.GameObjectSize;

public class PokerAnimationFactory extends AbstractAnimationFactory {
	final SpriteFactory spriteFactory;
	final GameObjectSize size;
	
	public PokerAnimationFactory() {
        spriteFactory = new SpriteFactory();
        size = GameObjectSize.getPokerSize();
    }
	
	@Override
	protected Animation getMoving() {
		return new Animation(0, spriteFactory.createLevelSprite("pokerMad", size));
	}
	
	/**
     * Creates animation for the player's death from its composite sprites
     * NORMAL plays the animation once in sequential order
     *
     * @return animation of the player's death
     */
	@Override
	protected Animation getDeath() {
        Sprite[] deathSprites = new Sprite[] {
        		spriteFactory.createLevelSprite("snakeLava_ani", size),
        		spriteFactory.createLevelSprite("snakeLava", size),
        		spriteFactory.createLevelSprite("snakeLava_ani", size),
        		spriteFactory.createLevelSprite("snakeLava_dead", size),
        };

        Animation deathAnimation = new Animation(.9f, deathSprites);
        deathAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        return deathAnimation;
    }
	
	@Override
	protected Animation getStanding() {
		return new Animation(0, spriteFactory.createLevelSprite("pokerMad", size));
	}
}
