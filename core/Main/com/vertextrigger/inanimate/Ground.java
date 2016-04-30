package com.vertextrigger.inanimate;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.collisiondetection.Collidable;

public class Ground implements Collidable {

	public Ground(final World world, final Vector2[] corners) {
		final BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(0, 0);

		final ChainShape worldContainerShape = new ChainShape();
		worldContainerShape.createChain(corners);

		final FixtureDef fixDef = new FixtureDef();
		fixDef.shape = worldContainerShape;
		fixDef.friction = 0.9f;
		fixDef.restitution = 0;

		final Body worldContainer = world.createBody(bodyDef);
		worldContainer.createFixture(fixDef).setUserData(this);

		setUserData(worldContainer);
		worldContainerShape.dispose();
	}

	@Override
	public void setUserData(final Body body) {
		body.setUserData(this);
		for (final Fixture fix : body.getFixtureList()) {
			fix.setUserData(this);
		}
	}
}