package com.vertextrigger.factory.bodyfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.ContactBody;
import com.vertextrigger.util.GameObjectSize;

public class PortalBodyFactory extends AbstractBodyFactory {

	private GameObjectSize size;
	private final static float RADIUS = 0.1f;
	
	public Body createPortalBody(World world, Vector2 initialPosition, GameObjectSize size) {
		this.size = size;
		return createBody(world, initialPosition, ContactBody.PORTAL, BodyType.StaticBody, createFixtureDefinition());
	}
	
	@Override
	protected FixtureDef createFixtureDefinition() {
		FixtureDef fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = createShape();
		fixtureDefinition.density = 3f;
		fixtureDefinition.friction = 0.9f;
		fixtureDefinition.isSensor = true;
		return fixtureDefinition;
	}

	@Override
	protected Shape createShape() {
		CircleShape shape = new CircleShape();
		shape.setRadius(RADIUS);
		return shape;
	}
}