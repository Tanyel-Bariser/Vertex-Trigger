package com.vertextrigger.entities.player;

import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.collisiondetection.Collidable;

public class PlayerFeet implements Collidable {
	Player player;

	@Override
	public void setUserData(Body body) {
		throw new UnsupportedOperationException("Cannot PlayerFeet.setUserData(Body body)");
	}
	
	void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setPlayerCanJump() {
		player.setCanJump();
	}
	
	public void setPlayerCannotJump() {
		player.setCannotJump();
	}
}