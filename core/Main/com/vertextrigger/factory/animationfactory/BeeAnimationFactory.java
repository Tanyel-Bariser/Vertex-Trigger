package com.vertextrigger.factory.animationfactory;

import static com.vertextrigger.util.GameObjectSize.BEE_SIZE;

import com.badlogic.gdx.graphics.g2d.*;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.util.GameObjectSize;

public class BeeAnimationFactory extends AbstractAnimationFactory {
	final SpriteFactory spriteFactory;
	final GameObjectSize size;

	public BeeAnimationFactory() {
		spriteFactory = new SpriteFactory();
		size = BEE_SIZE;
	}

	@Override
	protected Animation getMoving() {
		final Sprite sprite = spriteFactory.createEnemySprite("bee_fly", size);
		sprite.flip(false, true);
		return new Animation(0, sprite);
	}

	/**
	 * Creates animation for the player's death from its composite sprites NORMAL plays the animation once in sequential order
	 *
	 * @return animation of the player's death
	 */
	@Override
	protected Animation getDeath() {
		final Sprite sprite = spriteFactory.createEnemySprite("bee_dead", size);
		sprite.flip(false, true);
		final Sprite[] deathSprites = new Sprite[] { sprite };

		final Animation deathAnimation = new Animation(.4f, deathSprites);
		deathAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		return deathAnimation;
	}

	@Override
	protected Animation getStanding() {
		final Sprite sprite = spriteFactory.createEnemySprite("bee_fly", size);
		sprite.flip(false, true);
		return new Animation(0, sprite);
	}
}