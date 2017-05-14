package com.vertextrigger.factory.bodyfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.GameObjectSize;

public class PlatformBodyFactory extends AbstractBodyFactory {

	private GameObjectSize size;

	public enum Friction {
		NORMAL(0.9f),
		SNOW(0f),
		STICKY(1.5f);

		private final float value;

		Friction(final float value) {
			this.value = value;
		}
	}

	public Body createPlatformBody(final World world, final Vector2 initialPosition, final GameObjectSize size, final Friction friction) {
		this.size = size;
		final FixtureDef fixtureDefinition = createFixtureDefinition();
		fixtureDefinition.friction = friction.value;
		return createBody(world, initialPosition, BodyType.StaticBody, fixtureDefinition);
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
