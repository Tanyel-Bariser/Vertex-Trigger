package com.vertextrigger.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.*;

public class BodyBuilder {
	final World world;
	final Vector2 initialPosition;
	ContactBody contactBody;
	GameObjectSize size = GameObjectSize.createPlayerSize();
	Shape shape = defaultShape();
	BodyType bodyType = BodyType.DynamicBody;
	float density = 3f;
	
	public BodyBuilder(World world, Vector2 initialPosition, ContactBody contactBody) {
		this.world = world;
		this.initialPosition = initialPosition;
		this.contactBody = contactBody;
	}
	
	private Shape defaultShape() {
		shape = new PolygonShape();
		((PolygonShape) shape).setAsBox(size.getPhysicalWidth(), size.getPhysicalHeight());
		return shape;
	}
	
	public BodyBuilder setShape(Shape shape) {
		this.shape = shape;
		return this;
	}
	
	public BodyBuilder setObjectSize(GameObjectSize size) {
		this.size = size;
		return this;
	}
	
	public BodyBuilder setBodyDef(BodyType bodyType) {
		this.bodyType = bodyType;
		return this;
	}
	
	public BodyBuilder setDensity(float density) {
		this.density = density;
		return this;
	}
}
