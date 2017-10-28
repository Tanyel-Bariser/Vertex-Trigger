package com.vertextrigger.entities.enemy;

import com.vertextrigger.entities.Mortal;

public interface Enemy extends Hostile, Mortal {

	@Override
	void setDead();
}