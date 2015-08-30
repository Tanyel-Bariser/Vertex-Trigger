package com.vertextrigger.factories.bodyfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.ContactBody;

public class BulletBodyFactory extends BodyFactory {
	public Body createBulletBody(World world, Vector2 initialPosition) {
		Body body = createBody(world, initialPosition, ContactBody.BULLET, BodyType.DynamicBody, createFixtureDefinition());
		body.setBullet(true);
		body.setGravityScale(0);
		return body;
	}
	
	@Override
	protected FixtureDef createFixtureDefinition() {
		FixtureDef fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = createShape();
		fixtureDefinition.density = 3f;
		fixtureDefinition.friction = 1f;
		fixtureDefinition.restitution = 1f;
		return fixtureDefinition;
	}
	
	@Override
	protected Shape createShape() {
		CircleShape shape = new CircleShape();
		shape.setRadius(0.1f);
		return shape;
	}
}