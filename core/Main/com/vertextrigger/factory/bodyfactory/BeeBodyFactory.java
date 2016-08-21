package com.vertextrigger.factory.bodyfactory;

import static com.vertextrigger.util.GameObjectSize.BEE_SIZE;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.GameObjectSize;

public class BeeBodyFactory extends AbstractBodyFactory {
	private final GameObjectSize size = BEE_SIZE;

	public Body createBeeBody(final World world, final Vector2 initialPosition) {
		final Body body = createBody(world, initialPosition, BodyType.DynamicBody, createFixtureDefinition());
		body.setFixedRotation(false);
		body.setGravityScale(0);
		return body;
	}

	@Override
	protected FixtureDef createFixtureDefinition() {
		final FixtureDef fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = createShape();
		fixtureDefinition.density = 3f;
		fixtureDefinition.friction = 0f;
		return fixtureDefinition;
	}

	@Override
	protected Shape createShape() {
		final CircleShape shape = new CircleShape();
		shape.setRadius(size.getPhysicalWidth());
		return shape;
	}

}
