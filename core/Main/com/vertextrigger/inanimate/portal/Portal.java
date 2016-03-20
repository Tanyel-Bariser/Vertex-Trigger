package com.vertextrigger.inanimate.portal;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.vertextrigger.collisiondetection.Collidable;

public class Portal implements Collidable {
    private final Body body;
    private final Sprite sprite;
    private final PortalTeleportation teleportation;
    private final Vector2 pairedPortalPosition;

    Portal(Body body, Sprite sprite, PortalTeleportation teleportation, Vector2 pairedPortalPosition) {
    	this.body = body;
    	this.sprite = sprite;
    	this.teleportation = teleportation;
		this.pairedPortalPosition = pairedPortalPosition;
		setUserData(body);
	}

    /**
     * @param body that enters portal to be teleported to the paired portal
     */
	public void teleport(Body body) {
		teleportation.teleport(body, getPairedPosition());
	}

	Vector2 getPosition() {
		return body.getPosition();
	}

	Vector2 getPairedPosition() {
		return pairedPortalPosition;
	}

	public Sprite getSprite() {
		return sprite;
	}
	
	Body getBody() {
		return body;
	}

	PortalTeleportation getTeleportation() {
		return teleportation;
	}

	@Override
	public void setUserData(Body body) {
		body.setUserData(this);
		for (Fixture fix : body.getFixtureList()) {
			fix.setUserData(this);
		}
	}
}