package com.vertextrigger.factory.bodyfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import com.vertextrigger.util.ContactBody;
import com.vertextrigger.util.GameObjectSize;
import static com.vertextrigger.util.GameObjectSize.*;

public class PokerBodyFactory extends AbstractBodyFactory {
	
	public Body createPokerBody(World world, Vector2 initialPosition) {
		Body body = createBody(world, initialPosition, ContactBody.ENEMY, 
				BodyType.DynamicBody, createFixtureDefinition());
		body.setFixedRotation(true);
		createHead(body);
		return body;
	}
	
	private void createHead(Body body) {
		CircleShape head = new CircleShape();
		head.setPosition(new Vector2(0f, 1.5f));
		head.setRadius(0.6f);
		FixtureDef fixtureDef = createFixtureDefinition();
		fixtureDef.shape = head;
		Fixture headFixture = body.createFixture(fixtureDef);
		headFixture.setUserData(ContactBody.ENEMY_HEAD);
	}
	
	@Override
	protected FixtureDef createFixtureDefinition() {
		FixtureDef fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = createShape();
		fixtureDefinition.density = 3f;
		fixtureDefinition.friction = 1f;
		return fixtureDefinition;
	}
	
	@Override
	protected PolygonShape createShape() {
		GameObjectSize size = POKER_SIZE;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.getPhysicalWidth(), size.getPhysicalHeight());
		return shape;
	}
}