package com.vertextrigger.entities.enemy;

import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.*;

/**
 * Enemies can kill the player if touched & follows a predefined path This class manages an enemy's physical body & its movements & sprite animation
 */
public abstract class AbstractEnemy extends AbstractEntity implements Enemy, Mortal {

	public AbstractEnemy(final Body body, final AnimationSet animationSet) {
		super(body, new AnimatorImpl(animationSet));
	}

	@Override
	public void die() {
		AudioManager.playEnemyKilledSound();
		animator.playDeathAnimation(this);
	}
}