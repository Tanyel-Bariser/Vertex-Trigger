package com.vertextrigger.inanimate.portal;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.collisiondetection.Collidable;
import com.vertextrigger.util.GameObjectSize;

public class Portal implements Collidable {
	private final Body body;
	private final Sprite sprite;
	private final PortalTeleportation teleportation;
	private Portal pairedPortal;

	Portal(final Body body, final Sprite sprite, final PortalTeleportation teleportation) {
		this.body = body;
		this.sprite = sprite;
		this.teleportation = teleportation;
		setUserData();
		setSpritePosition();
	}

	/**
	 * @param body
	 *            that enters portal to be teleported to the paired portal
	 */
	public void teleport(final Body body) {
		teleportation.teleport(body, getPairedPosition());
	}

	public void setSpritePosition() {
		final GameObjectSize platformSize = GameObjectSize.PORTAL_SIZE;
		sprite.setPosition(body.getPosition().x - (sprite.getWidth() / platformSize.getOffsetX()), body.getPosition().y
				- (sprite.getHeight() / platformSize.getOffsetY()));
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
	public void setUserData() {
		body.setUserData(this);
		for (final Fixture fix : body.getFixtureList()) {
			fix.setUserData(this);
		}
	}

	public void setPairedPortal(final Portal pairedPortal) {
		this.pairedPortal = pairedPortal;
	}
}