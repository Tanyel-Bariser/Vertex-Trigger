package com.vertextrigger.factories.bodyfactories;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class PlayerBodyFactory {
	private static BodyDef playerBodyDef;
	private static Body playerBody;
	private static PolygonShape playerShape;
	static float PLAYER_DENSITY = 3f;

	public static Body getPlayerBody(World world, Vector2 initialPosition) {
		buildPlayerBodyDef(initialPosition);
		buildPlayerBody(world);
		buildPlayerShape();
		buildPlayerFixture();
		return playerBody;
	}
	
	private static void buildPlayerBodyDef(Vector2 initialPosition) {
		playerBodyDef = new BodyDef();
		playerBodyDef.type = BodyType.DynamicBody;
		playerBodyDef.position.set(initialPosition);
	}
	
	private static void buildPlayerBody(World world) {
		playerBody = world.createBody(playerBodyDef);
	}
	
	private static void buildPlayerShape() {
		playerShape = new PolygonShape();
		float playerWidth = 0.5f;
		float playerHeight = 1.5f;
		playerShape.setAsBox(playerWidth, playerHeight);
	}
	
	private static void buildPlayerFixture() {
		Fixture playerFixture = playerBody.createFixture(playerShape, PLAYER_DENSITY);
		playerShape.dispose();
		playerFixture.setUserData(UserData.PLAYER);
	}
}