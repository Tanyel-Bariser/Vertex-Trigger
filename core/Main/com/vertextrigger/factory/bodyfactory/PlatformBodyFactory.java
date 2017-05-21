package com.vertextrigger.factory.bodyfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.util.GameObjectSize;

public class PlatformBodyFactory extends AbstractBodyFactory {

	private GameObjectSize size;

	public enum Friction {
		NORMAL(1.5f),
		SNOW(0f),
		STICKY(50),
		VERY_STICKY(100);

		private final float value;

		Friction(final float value) {
			this.value = value;
		}
	}

	public Body createPlatformBody(final World world, final Vector2 initialPosition, final GameObjectSize size, final Friction friction) {
		return getBody(world, initialPosition, size, friction, BodyType.StaticBody);
	}

	public Body createMovingPlatformBody(final World world, final Vector2 initialPosition, final GameObjectSize size, final Friction friction) {
		return getBody(world, initialPosition, size, friction, BodyType.KinematicBody);
	}

	private Body getBody(World world, Vector2 initialPosition, GameObjectSize size, Friction friction, BodyType bodyType) {
		this.size = size;
		final FixtureDef fixtureDefinition = createFixtureDefinition();
		fixtureDefinition.friction = friction.value;
		return createBody(world, initialPosition, bodyType, fixtureDefinition);
	}

	@Override
	protected FixtureDef createFixtureDefinition() {
		final FixtureDef fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = createShape();
		fixtureDefinition.density = 3f;
		fixtureDefinition.friction = Friction.NORMAL.value;
		return fixtureDefinition;
	}

	@Override
	protected Shape createShape() {
		final PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.getPhysicalWidth(), size.getPhysicalHeight());
		return shape;
	}
}
