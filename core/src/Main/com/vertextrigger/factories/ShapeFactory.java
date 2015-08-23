package com.vertextrigger.factories;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class ShapeFactory {

	public static CircleShape createCircleShape(float radius) {
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		return shape;
	}
	
	public static PolygonShape createPolygonShape(float width, float height) {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width, height);
		return shape;
	}
}
