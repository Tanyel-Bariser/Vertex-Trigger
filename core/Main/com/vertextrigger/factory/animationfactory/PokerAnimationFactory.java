package com.vertextrigger.factory.animationfactory;

import static com.vertextrigger.util.GameObjectSize.POKER_BODY_SIZE;

import com.badlogic.gdx.graphics.g2d.*;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.util.GameObjectSize;

public class PokerAnimationFactory extends AbstractAnimationFactory {
	final SpriteFactory spriteFactory;
	final GameObjectSize size;

	public PokerAnimationFactory() {
		spriteFactory = new SpriteFactory();
		size = POKER_BODY_SIZE;
	}

	@Override
	protected Animation getMoving() {
		return new Animation(0, spriteFactory.createEnemySprite("pokerMad", size));
	}

	/**
	 * Creates animation for the player's death from its composite sprites NORMAL plays the animation once in sequential order
	 *
	 * @return animation of the player's death
	 */
	@Override
	protected Animation getDeath() {
		final Sprite[] deathSprites = new Sprite[] { spriteFactory.createEnemySprite("snakeLava_ani", size),
				spriteFactory.createEnemySprite("snakeLava", size), spriteFactory.createEnemySprite("snakeLava_ani", size),
				spriteFactory.createEnemySprite("snakeLava_dead", size), };

		final Animation deathAnimation = new Animation(.4f, deathSprites);
		deathAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		return deathAnimation;
	}

	@Override
	protected Animation getStanding() {
		return new Animation(0, spriteFactory.createEnemySprite("pokerMad", size));
	}
}
