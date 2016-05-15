package com.vertextrigger.factory.animationfactory;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.util.GameObjectSize;

import java.util.HashMap;
import java.util.Map;

import static com.vertextrigger.util.GameObjectSize.PLAYER_SIZE;

public class PlayerAnimationFactory extends AbstractAnimationFactory {

	private final SpriteFactory spriteFactory;
	private final GameObjectSize size;
	private final Map<String, Sprite[]> animationCache;

	public PlayerAnimationFactory() {
		spriteFactory = new SpriteFactory();
		size = PLAYER_SIZE;
		animationCache = new HashMap<>();
	}

	@Override
	protected Animation getMoving() {
		final Animation runAnimation = new Animation(0.1F, createSprites("run", 6));
		runAnimation.setPlayMode(Animation.PlayMode.LOOP);
		return runAnimation;
	}

	private Sprite[] createSprites(final String name, final int numOfSprites) {
		if (!animationCache.containsKey(name)) {
			final Sprite[] sprites = new Sprite[numOfSprites];
			for (int i = 0; i < numOfSprites; i++) {
				sprites[i] = spriteFactory.createCoreSprite(name + i, size);
			}
			animationCache.put(name, sprites);
		}
		return animationCache.get(name);
	}

	@Override
	protected Animation getRising() {
		final Animation jumpAnimation = new Animation(.5f, createSprites("jump", 2));
		jumpAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		return jumpAnimation;
	}

	@Override
	protected Animation getFalling() {
		final Animation jumpAnimation = new Animation(.5f, createSprites("jump", 2));
		jumpAnimation.setPlayMode(Animation.PlayMode.LOOP_REVERSED);
		return jumpAnimation;
	}

	@Override
	protected Animation getStanding() {
		final Sprite[] standingSprite = new Sprite[] {
				// shoot1 sprite is the best image for the player standing
				spriteFactory.createCoreSprite("shoot1", size), };

		final Animation jumpAnimation = new Animation(0.1f, standingSprite);
		jumpAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		return jumpAnimation;
	}

	@Override
	protected Animation getShooting() {
		final Animation shootAnimation = new Animation(0.02F, createSprites("shoot", 8));
		shootAnimation.setPlayMode(Animation.PlayMode.LOOP);
		return shootAnimation;
	}

	@Override
	protected Animation getDeath() {
		final Animation deathAnimation = new Animation(0.2f, createSprites("death", 4)); // showing 3 of 4 frames looks better
		deathAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		return deathAnimation;
	}
}