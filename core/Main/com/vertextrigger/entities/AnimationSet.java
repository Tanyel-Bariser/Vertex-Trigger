package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationSet {
	private final Animation standing;
	private final Animation moving;
	private final Animation rising;
	private final Animation falling;
	private final Animation shooting;
	private final Animation attack;
	private final Animation death;

	public AnimationSet(final Animation standing, final Animation moving, final Animation rising, final Animation falling, final Animation shooting,
			final Animation attack, final Animation death) {
		this.standing = standing;
		this.moving = moving;
		this.rising = rising;
		this.falling = falling;
		this.shooting = shooting;
		this.attack = attack;
		this.death = death;
	}

	public Animation getStanding() throws NoSuchAnimation {
		complainIfNull(standing);
		return standing;
	}

	public Animation getMoving() throws NoSuchAnimation {
		complainIfNull(moving);
		return moving;
	}

	public Animation getRising() throws NoSuchAnimation {
		complainIfNull(rising);
		return rising;
	}

	public Animation getFalling() throws NoSuchAnimation {
		complainIfNull(falling);
		return falling;
	}

	public Animation getShooting() throws NoSuchAnimation {
		complainIfNull(shooting);
		return shooting;
	}

	public Animation getAttack() throws NoSuchAnimation {
		complainIfNull(attack);
		return attack;
	}

	public Animation getDeath() throws NoSuchAnimation {
		complainIfNull(death);
		return death;
	}

	private void complainIfNull(final Animation animation) throws NoSuchAnimation {
		if (animation == null) {
			throw new NoSuchAnimation();
		}
	}

	class NoSuchAnimation extends Error {

		/**
		 *
		 */
		private static final long serialVersionUID = 1328734799717606849L;

	}
}
