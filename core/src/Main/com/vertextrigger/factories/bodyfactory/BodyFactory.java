package com.vertextrigger.factories.bodyfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.*;

public abstract class BodyFactory {
	
	protected abstract FixtureDef createFixtureDefinition();
	
	protected abstract Shape createShape();	
	
	protected Body createBody(World world, Vector2 initialPosition, ContactBody contactBody, BodyType bodyType, FixtureDef fixtureDefinition) {
		BodyDef bodyDef = buildBodyDefinition(initialPosition, bodyType);
		Body body = world.createBody(bodyDef);
		buildFixture(body, contactBody, fixtureDefinition);
		return body;
	}
	
	protected BodyDef buildBodyDefinition(Vector2 initialPosition, BodyType bodyType) {
		BodyDef bodyDefinition = new BodyDef();
		bodyDefinition.type = bodyType;
		bodyDefinition.position.set(initialPosition);
		return bodyDefinition;
	}
		
	protected void buildFixture(Body body, ContactBody contactBody, FixtureDef fixtureDefinition) {
		assert(body != null);
		Fixture fixture = body.createFixture(fixtureDefinition);
		fixture.setUserData(contactBody);
	}
}