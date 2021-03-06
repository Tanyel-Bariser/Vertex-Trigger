package com.vertextrigger.factory.animationfactory;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.vertextrigger.entities.AnimationSet;

public abstract class AbstractAnimationFactory {

	public AnimationSet createAnimationSet() {
		return new AnimationSet(getStanding(), getMoving(), getRising(), getFalling(), getShooting(), getAttack(), getDeath());
	}

	protected abstract Animation getStanding();

	protected Animation getMoving() {
		return getStanding();
	}

	protected Animation getRising() {
		return getStanding();
	}

	protected Animation getFalling() {
		return getStanding();
	}

	protected Animation getShooting() {
		return getStanding();
	}

	protected Animation getAttack() {
		return getStanding();
	}

	protected Animation getDeath() {
		return getStanding();
	}
}