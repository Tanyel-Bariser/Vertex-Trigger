package com.vertextrigger.factory.bodyfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public abstract class AbstractBodyFactory {

	protected abstract FixtureDef createFixtureDefinition();

	protected abstract Shape createShape();

	protected Body createBody(final World world, final Vector2 initialPosition, final BodyType bodyType, final FixtureDef fixtureDefinition) {
		final BodyDef bodyDef = buildBodyDefinition(initialPosition, bodyType);
		final Body body = world.createBody(bodyDef);
		buildFixture(body, fixtureDefinition);
		return body;
	}

	protected BodyDef buildBodyDefinition(final Vector2 initialPosition, final BodyType bodyType) {
		final BodyDef bodyDefinition = new BodyDef();
		bodyDefinition.type = bodyType;
		bodyDefinition.position.set(initialPosition);
		return bodyDefinition;
	}

	protected void buildFixture(final Body body, final FixtureDef fixtureDefinition) {
		body.createFixture(fixtureDefinition);
	}
}