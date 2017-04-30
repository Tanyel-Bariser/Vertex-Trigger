package com.vertextrigger.factory.bodyfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.GameObjectSize;

public class ShieldBodyFactory extends AbstractBodyFactory {

	public Body createShieldBody(final World world, final Vector2 initialPosition) {
		return createBody(world, initialPosition, BodyType.StaticBody, createFixtureDefinition());
	}

	@Override
	protected FixtureDef createFixtureDefinition() {
		final FixtureDef fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = createShape();
		fixtureDefinition.density = .3f;
		fixtureDefinition.friction = 0f;
		fixtureDefinition.isSensor = true;
		return fixtureDefinition;
	}

	@Override
	protected Shape createShape() {
		final CircleShape shape = new CircleShape();
		shape.setPosition(new Vector2(0f, 0.4f * GameObjectSize.OBJECT_SIZE));
		shape.setRadius(GameObjectSize.SHIELD_SIZE.getPhysicalHeight());
		return shape;
	}
}