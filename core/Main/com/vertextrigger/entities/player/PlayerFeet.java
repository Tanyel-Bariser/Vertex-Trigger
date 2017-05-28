package com.vertextrigger.entities.player;

import com.vertextrigger.collisiondetection.Collidable;

public class PlayerFeet implements Collidable {
	Player player;

	@Override
	public void setUserData() {
		throw new UnsupportedOperationException("Cannot PlayerFeet.setUserData(Body body)");
	}

	void setPlayer(final Player player) {
		this.player = player;
	}

	public void setPlayerCanJump() {
		player.setCanJump();
	}

	public void setPlayerCannotJump() {
		player.setCannotJump();
	}

	public void setDead() {
		player.setDead();
	}
}