package com.vertextrigger.factory.bodyfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.ContactBody;

public class BulletBodyFactory extends AbstractBodyFactory {
	public static Vector2 INITIAL_POSITION_OUT_OF_CAMERA_VIEW = new Vector2(-50,-50);
	static final float DENSITY = 3;
	static final float FRICTION = 1f;
	static final float BOUNCY = 1f;
	static float RADIUS = 0.1f;
	
	public Body createBulletBody(World world) {
		Body body = createBody(world, INITIAL_POSITION_OUT_OF_CAMERA_VIEW,
				ContactBody.BULLET, BodyType.DynamicBody, createFixtureDefinition());
		body.setBullet(true);
		body.setGravityScale(0);
		return body;
	}
	
	@Override
	protected FixtureDef createFixtureDefinition() {
		FixtureDef fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = createShape();
		fixtureDefinition.density = DENSITY;
		fixtureDefinition.friction = FRICTION;
		fixtureDefinition.restitution = BOUNCY;
		return fixtureDefinition;
	}
	
	@Override
	protected Shape createShape() {
		CircleShape shape = new CircleShape();
		shape.setRadius(0.1f);
		return shape;
	}
}