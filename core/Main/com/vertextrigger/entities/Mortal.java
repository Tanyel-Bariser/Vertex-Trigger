package com.vertextrigger.entities;


public interface Mortal extends Entity {
	void setDeathAnimationFinished();

	boolean isDeathAnimationFinished();

	void die();

	void setDead();

	boolean isDead();
}