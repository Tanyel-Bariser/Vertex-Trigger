package com.vertextrigger.factory.bodyfactory;

import static com.vertextrigger.util.GameObjectSize.POKER_BODY_SIZE;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.GameObjectSize;

public class PokerBodyFactory extends AbstractBodyFactory {

	public Body createPokerBody(final World world, final Vector2 initialPosition) {
		final Body body = createBody(world, initialPosition, BodyType.DynamicBody, createFixtureDefinition());
		body.setFixedRotation(true);
		createHead(body);
		return body;
	}

	private void createHead(final Body body) {
		final CircleShape head = new CircleShape();
		head.setPosition(new Vector2(0f, 1.5f * GameObjectSize.OBJECT_SIZE));
		head.setRadius(0.6f * GameObjectSize.OBJECT_SIZE);
		final FixtureDef fixtureDef = createFixtureDefinition();
		fixtureDef.shape = head;
		body.createFixture(fixtureDef);
	}

	@Override
	protected FixtureDef createFixtureDefinition() {
		final FixtureDef fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = createShape();
		fixtureDefinition.density = 100f;
		fixtureDefinition.friction = 1f;
		return fixtureDefinition;
	}

	@Override
	protected PolygonShape createShape() {
		final GameObjectSize size = POKER_BODY_SIZE;
		final PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.getPhysicalWidth(), size.getPhysicalHeight());
		return shape;
	}
}