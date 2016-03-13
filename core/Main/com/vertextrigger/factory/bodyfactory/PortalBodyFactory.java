package com.vertextrigger.factory.bodyfactory;

import static com.vertextrigger.util.GameObjectSize.*;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.GameObjectSize;

public class PortalBodyFactory extends AbstractBodyFactory {

	public Body createPortalBody(World world, Vector2 initialPosition) {
		return createBody(world, initialPosition, BodyType.StaticBody, createFixtureDefinition());
	}
	
	@Override
	protected FixtureDef createFixtureDefinition() {
		FixtureDef fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = createShape();
		fixtureDefinition.isSensor = true;
		return fixtureDefinition;
	}

	@Override
	protected Shape createShape() {
		GameObjectSize size = PORTAL_SIZE;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.getPhysicalWidth(), size.getPhysicalHeight());
		return shape;
	}
}