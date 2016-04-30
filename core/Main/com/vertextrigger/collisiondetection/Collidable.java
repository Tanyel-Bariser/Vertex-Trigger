package com.vertextrigger.collisiondetection;

import com.badlogic.gdx.physics.box2d.Body;

public interface Collidable {

	void setUserData(final Body body);
}