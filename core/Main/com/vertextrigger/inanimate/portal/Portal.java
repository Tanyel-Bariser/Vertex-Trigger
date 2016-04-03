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
    private Portal pairedPortal;
    private boolean isDisabled;

    Portal(Body body, Sprite sprite, PortalTeleportation teleportation) {
    	this.body = body;
    	this.sprite = sprite;
    	this.teleportation = teleportation;
		setUserData(body);
	}

    /**
     * @param body that enters portal to be teleported to the paired portal
     */
	public void teleport(Body body) {
		if (isDisabled == false) {
			pairedPortal.disable();
			teleportation.teleport(body, getPairedPosition());
		}
	}

	private void disable() {
		isDisabled = true;
	}
	
	public void enable() {
		isDisabled = false;
	}

	Vector2 getPosition() {
		return body.getPosition();
	}

	Vector2 getPairedPosition() {
		return pairedPortal.getPosition();
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

	public void setPairedPortal(Portal pairedPortal) {
		this.pairedPortal = pairedPortal;
	}
}