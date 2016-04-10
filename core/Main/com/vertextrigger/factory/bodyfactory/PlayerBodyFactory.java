package com.vertextrigger.factory.bodyfactory;

import static com.vertextrigger.util.GameObjectSize.OBJECT_SIZE;
import static com.vertextrigger.util.GameObjectSize.PLAYER_SIZE;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.entities.player.PlayerFeet;
import com.vertextrigger.util.GameObjectSize;

public class PlayerBodyFactory extends AbstractBodyFactory {
	public Body createPlayerBody(World world, Vector2 initialPosition) {
		Body body = createBody(world, initialPosition, BodyType.DynamicBody, createFixtureDefinition());
		body.setFixedRotation(true);
		createFeet(body);		
		return body;
	}

	private void createFeet(Body body) {
		CircleShape feet = new CircleShape();
		Vector2 sensorPosition = new Vector2(0f, -((PLAYER_SIZE.getPhysicalHeight() * OBJECT_SIZE)) * 6);
		feet.setPosition(sensorPosition);
		feet.setRadius(0.5f * OBJECT_SIZE);
		
		FixtureDef fixtureDef = createFixtureDefinition();
		fixtureDef.shape = feet;
		fixtureDef.isSensor = true;
		fixtureDef.density = 0;
		Fixture fixture = body.createFixture(fixtureDef);
		fixture.setUserData(new PlayerFeet());
	}

	@Override
	protected FixtureDef createFixtureDefinition() {
		FixtureDef fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = createShape();
		fixtureDefinition.density = 3f;
		fixtureDefinition.friction = 0f;
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