package com.vertextrigger.entities.enemy;

import com.vertextrigger.collisiondetection.Collidable;

public class PokerHead implements Collidable, Enemy {
	Poker poker;

	@Override
	public void setUserData() {
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