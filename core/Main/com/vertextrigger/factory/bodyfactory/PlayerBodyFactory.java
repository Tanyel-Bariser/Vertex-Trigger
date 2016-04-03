package com.vertextrigger.factory.bodyfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.*;

import static com.vertextrigger.util.GameObjectSize.*;

public class PlayerBodyFactory extends AbstractBodyFactory {
	public Body createPlayerBody(World world, Vector2 initialPosition) {
		Body body = createBody(world, initialPosition, BodyType.DynamicBody, createFixtureDefinition());
		body.setFixedRotation(true);
		createFeet(body);		
		return body;
	}

	private void createFeet(Body body) {
		CircleShape feet = new CircleShape();
		Vector2 sensorPosition = new Vector2(0f, -((GameObjectSize.PLAYER_SIZE.getPhysicalHeight() * GameObjectSize.OBJECT_SIZE)) * 6);
		feet.setPosition(sensorPosition);
		feet.setRadius(0.3f * GameObjectSize.OBJECT_SIZE);
		
		FixtureDef fixtureDef = createFixtureDefinition();
		fixtureDef.shape = feet;
		fixtureDef.isSensor = true;
		body.createFixture(fixtureDef);
	}

	@Override
	protected FixtureDef createFixtureDefinition() {
		FixtureDef fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = createShape();
		fixtureDefinition.density = 3f;
		return fixtureDefinition;
	}
	
	@Override
	protected Shape createShape() {
		GameObjectSize size = PLAYER_SIZE;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.getPhysicalWidth(), size.getPhysicalHeight());
		return shape;
	}
}