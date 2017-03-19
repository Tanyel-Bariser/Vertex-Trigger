package com.vertextrigger.factory.bodyfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.GameObjectSize;

public class MagnetBodyFactory extends AbstractBodyFactory {

	private GameObjectSize size;

	public Body createMagnetBody(final World world, final Vector2 initialPosition, final GameObjectSize size) {
		this.size = size;
		return createBody(world, initialPosition, BodyType.StaticBody, createFixtureDefinition());
	}

	@Override
	protected FixtureDef createFixtureDefinition() {
		final FixtureDef fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = createShape();
		fixtureDefinition.density = 3f;
		fixtureDefinition.friction = 0.9f;
		return fixtureDefinition;
	}

	@Override
	protected Shape createShape() {
		final PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.getPhysicalWidth(), size.getPhysicalHeight());
		return shape;
	}
}