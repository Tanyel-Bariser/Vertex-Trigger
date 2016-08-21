package com.vertextrigger.entities.enemy;

import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.collisiondetection.Collidable;

public class PokerHead implements Collidable, Enemy {
	Poker poker;

	@Override
	public void setUserData(final Body body) {
		throw new UnsupportedOperationException("Cannot PokerHead.setUserData(Body body)");
	}

	void setPoker(final Poker poker) {
		this.poker = poker;
	}

	@Override
	public void setDead() {
		poker.setDead();
	}
}