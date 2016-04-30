package com.vertextrigger.factory.bodyfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.GameObjectSize;

public class BulletBodyFactory extends AbstractBodyFactory {
	public static Vector2 INITIAL_POSITION_OUT_OF_CAMERA_VIEW = new Vector2(Float.MAX_VALUE, Float.MAX_VALUE);
	static final float DENSITY = 3f;
	static final float FRICTION = 1f;
	static final float BOUNCY = 1f;

	public Body createBulletBody(final World world) {
		final Body body = createBody(world, INITIAL_POSITION_OUT_OF_CAMERA_VIEW, BodyType.DynamicBody, createFixtureDefinition());
		body.setBullet(true);
		body.setGravityScale(0);
		body.setFixedRotation(true);
		return body;
	}

	@Override
	protected FixtureDef createFixtureDefinition() {
		final FixtureDef fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = createShape();
		fixtureDefinition.density = DENSITY;
		fixtureDefinition.friction = FRICTION;
		fixtureDefinition.restitution = BOUNCY;
		return fixtureDefinition;
	}

	@Override
	protected Shape createShape() {
		final CircleShape shape = new CircleShape();
		shape.setRadius(GameObjectSize.BULLET_SIZE.getPhysicalHeight() * GameObjectSize.OBJECT_SIZE);
		return shape;
	}
}