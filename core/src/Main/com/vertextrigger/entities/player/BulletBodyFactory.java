package com.vertextrigger.entities.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.ContactBody;

final class BulletBodyFactory {
	public final static Vector2 INITIAL_POSITION_OUT_OF_CAMERA_VIEW = new Vector2(-50, -50);
	static final float RADIUS = 0.1f;
	static final float DENSITY = 3f;
	static final float FRICTION = 1f;
	static final float BOUNCY = 1f;
	private static BodyDef bodyDefinition;
	private static Body body;
	private static FixtureDef fixtureDefinition;
	private static Shape shape;
	
	static Body getBulletBody(World world) {
		buildBulletBodyDefinition();
		buildBulletBody(world);
		buildBulletShape();
		buildBulletFixtureDefinition();
		buildBulletFixture();
		return body;
	}
	
	private static void buildBulletBodyDefinition() {
		bodyDefinition = new BodyDef();
		bodyDefinition.type = BodyType.DynamicBody;
		bodyDefinition.position.set(INITIAL_POSITION_OUT_OF_CAMERA_VIEW);
	}
	
	private static void buildBulletBody(World world) {
		body = world.createBody(bodyDefinition);
		boolean continuousCollisionDetection = true;
		body.setBullet(continuousCollisionDetection);
		body.setGravityScale(0);
	}
	
	private static void buildBulletShape() {
		shape = new CircleShape();
		shape.setRadius(RADIUS);
	}
	
	private static void buildBulletFixtureDefinition() {
		fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = shape;
		fixtureDefinition.density = DENSITY;
		fixtureDefinition.friction = FRICTION;
		fixtureDefinition.restitution = BOUNCY;
	}
	
	private static void buildBulletFixture() {
		Fixture fixture = body.createFixture(fixtureDefinition);
		shape.dispose();
		fixture.setUserData(ContactBody.BULLET);
	}
}