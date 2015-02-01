package com.vertextrigger.factories.bodyfactories;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BulletBodyFactory {
	private static BodyDef bulletBodyDef;
	private static Body bulletBody;
	private static FixtureDef bulletFixtureDef;
	private static Fixture bulletFixture;
	private static Shape bulletShape;
	static Vector2 INITIAL_POSITION_OUT_OF_CAMERA_VIEW = new Vector2(-50, -50);
	static float BULLET_RADIUS = 0.2f;
	static float BULLET_DENSITY = 3f;
	static float BULLET_FRICTION = 1f;
	static float BULLET_IS_VERY_BOUNCY = 1f;
	
	public static Body getBulletBody(World world) {
		buildBulletBodyDef();
		buildBulletBody(world);
		buildBulletShape();
		buildBulletFixtureDef();
		buildBulletFixture();
		return bulletBody;
	}
	
	private static void buildBulletBodyDef() {
		bulletBodyDef = new BodyDef();
		bulletBodyDef.type = BodyType.DynamicBody;
		bulletBodyDef.position.set(INITIAL_POSITION_OUT_OF_CAMERA_VIEW);
	}
	
	private static void buildBulletBody(World world) {
		bulletBody = world.createBody(bulletBodyDef);
		boolean continuousCollisionDetection = true;
		bulletBody.setBullet(continuousCollisionDetection);
	}
	
	private static void buildBulletShape() {
		bulletShape = new CircleShape();
		bulletShape.setRadius(BULLET_RADIUS);
	}
	
	private static void buildBulletFixtureDef() {
		bulletFixtureDef = new FixtureDef();
		bulletFixtureDef.shape = bulletShape;
		bulletFixtureDef.density = BULLET_DENSITY;
		bulletFixtureDef.friction = BULLET_FRICTION;
		bulletFixtureDef.restitution = BULLET_IS_VERY_BOUNCY;
	}
	
	private static void buildBulletFixture() {
		bulletFixture = bulletBody.createFixture(bulletFixtureDef);
		bulletShape.dispose();
		bulletFixture.setUserData(UserData.BULLET);
	}
}