package com.vertextrigger.factory.bodyfactory;

import static com.vertextrigger.util.GameObjectSize.*;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.entities.player.PlayerFeet;
import com.vertextrigger.util.GameObjectSize;

public class PlayerBodyFactory extends AbstractBodyFactory {
	private final GameObjectSize size = PLAYER_SIZE;

	public Body createPlayerBody(final World world, final Vector2 initialPosition) {
		final Body body = createBody(world, initialPosition, BodyType.DynamicBody, createFixtureDefinition());
		body.setFixedRotation(true);
		createFeet(body);
		return body;
	}

	private void createFeet(final Body body) {
		final FixtureDef fixtureDef = createFixtureDefinition();
		fixtureDef.shape = createFeet();
		fixtureDef.density = 3f;
		fixtureDef.friction = 1f;
		final Fixture fixture = body.createFixture(fixtureDef);
		fixture.setUserData(new PlayerFeet());
	}

	@Override
	protected FixtureDef createFixtureDefinition() {
		final FixtureDef fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = createShape();
		fixtureDefinition.density = 3f;
		fixtureDefinition.friction = 0f;
		return fixtureDefinition;
	}

	private PolygonShape createFeet() {
		final PolygonShape feet = new PolygonShape();
		final Vector2 center = new Vector2(0f, -((PLAYER_SIZE.getPhysicalHeight() * (OBJECT_SIZE + 0.02f))) * 6);
		final float width = size.getPhysicalWidth() * 0.9f;
		final float height = size.getPhysicalHeight() / 5;

		feet.setAsBox(width, height, center, 0);
		return feet;
	}

	@Override
	protected Shape createShape() {
		final PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.getPhysicalWidth(), size.getPhysicalHeight());
		return shape;
	}
}