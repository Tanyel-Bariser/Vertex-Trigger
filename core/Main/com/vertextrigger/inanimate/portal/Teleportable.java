package com.vertextrigger.inanimate.portal;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.collisiondetection.Collidable;

public interface Teleportable extends Collidable {
	void setNewPositionFromPortal(final Vector2 newPositionFromPortal);

	Body getBody();

	boolean canTeleport();

	void setTeleportable(final boolean canTeleport);
}