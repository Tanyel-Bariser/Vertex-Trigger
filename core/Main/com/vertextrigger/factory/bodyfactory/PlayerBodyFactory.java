package com.vertextrigger.factory.bodyfactory;

import static com.vertextrigger.util.GameObjectSize.OBJECT_SIZE;
import static com.vertextrigger.util.GameObjectSize.PLAYER_SIZE;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.entities.player.PlayerFeet;
import com.vertextrigger.util.GameObjectSize;

public class PlayerBodyFactory extends AbstractBodyFactory {
	private final GameObjectSize size = PLAYER_SIZE;
	
	public Body createPlayerBody(World world, Vector2 initialPosition) {
		Body body = createBody(world, initialPosition, BodyType.DynamicBody, createFixtureDefinition());
		body.setFixedRotation(true);
		createFeet(body);		
		return body;
	}

	private void createFeet(Body body) {
		FixtureDef fixtureDef = createFixtureDefinition();
		fixtureDef.shape = createFeet();
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
	
	private PolygonShape createFeet() {
		PolygonShape feet = new PolygonShape();
		Vector2 center = new Vector2(0f, -((PLAYER_SIZE.getPhysicalHeight() * OBJECT_SIZE)) * 6);
		float width = size.getPhysicalWidth() * 0.9f;
		float height = size.getPhysicalHeight() / 10;
		
		feet.setAsBox(width, height, center, 0);
		return feet;
	}
	
	@Override
	protected Shape createShape() {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.getPhysicalWidth(), size.getPhysicalHeight());
		return shape;
	}
}