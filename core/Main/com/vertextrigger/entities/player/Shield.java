package com.vertextrigger.entities.player;

import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.collisiondetection.Collidable;
import com.vertextrigger.entities.*;
import com.vertextrigger.util.GameObjectSize;

public class Shield extends AbstractEntity implements Entity, Collidable {
	private final Fixture fixture;

	public Shield(final Body body, final Animator animator) {
		super(body, animator);
		this.body = body;
		fixture = body.getFixtureList().get(0);
		setUserData();
	}

	public FixtureDef getFixtureDef() {
		final FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = fixture.getShape();
		fixtureDef.density = fixture.getDensity();
		fixtureDef.friction = fixture.getFriction();
		return fixtureDef;
	}

	public void setPlayerBody(final Body playerBody) {
		body.destroyFixture(fixture);
		body = playerBody;
	}

	@Override
	public boolean isTeleportable() {
		return false;
	}

	@Override
	public float getOffsetX() {
		return GameObjectSize.SHIELD_SIZE.getOffsetX();
	}

	@Override
	public float getOffsetY() {
		return GameObjectSize.SHIELD_SIZE.getOffsetY();
	}
}