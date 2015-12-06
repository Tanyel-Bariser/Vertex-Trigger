package com.vertextrigger.inanimate.portal;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.collisiondetection.CollisionDetection;

public class Portal {
    public static enum Orientation {UP, RIGHT, DOWN, LEFT}
    private static int portalCounter;
    public static String IDENTIFIER = "PORTAL-";

    private Body body;
    private Sprite sprite;
    private PortalTeleportation teleportation;

    private Orientation orientation;
    private Portal pairedPortal;

    public Portal getPairedPortal() {
        return this.pairedPortal;
    }

    public Body getBody() {
        return this.body;
    }

    Portal(Body body, Sprite sprite, Vector2 position, PortalTeleportation teleportation) {
    	this.body = body;
    	this.sprite = sprite;
    	this.body.setTransform(position, 0);
    	this.teleportation = teleportation;
	}

	Vector2 getPosition() {
		return body.getPosition();
	}

	Vector2 getPairedPosition() {
		return pairedPortal.getPosition();
	}

	void setPairedPortal(Portal pairedPortal) {
		this.pairedPortal = pairedPortal;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public PortalTeleportation getTeleportation() {
		return teleportation;
	}
}
