package com.vertextrigger.entities;

import com.badlogic.gdx.physics.box2d.Body;

public interface Mortal extends Entity {
	void setDeathAnimationFinished();
	
	boolean isDeathAnimationFinished();
	
	void die();
	
	boolean isDead();

	Body getBody();
}
