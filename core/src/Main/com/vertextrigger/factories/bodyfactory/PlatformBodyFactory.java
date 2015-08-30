package com.vertextrigger.factories.bodyfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.ContactBody;
import com.vertextrigger.util.GameObjectSize;

public class PlatformBodyFactory extends BodyFactory {

	private GameObjectSize size;
	
	public Body createPlatformBody(World world, Vector2 initialPosition, GameObjectSize size) {
		this.size = size;
		return createBody(world, initialPosition, ContactBody.GROUND, BodyType.StaticBody, createFixtureDefinition());
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
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.getPhysicalWidth(), size.getPhysicalHeight());
		return shape;
	}
}
