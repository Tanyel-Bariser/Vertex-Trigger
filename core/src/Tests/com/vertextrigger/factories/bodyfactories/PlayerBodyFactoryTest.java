package com.vertextrigger.factories.bodyfactories;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class PlayerBodyFactoryTest {
	private World world;
	private Vector2 initialPosition;
	private Body playerBody;
	private Fixture playerFixture;

	@Before
	public void setUp() throws Exception {
		buildWorld();
		buildInitialPosition();
		buildPlayer();
	}
	
	private void buildWorld() {
		boolean doNotSimulateInactiveBodies = true;
		Vector2 gravity = new Vector2(0, -9.81f);
		world = new World(gravity, doNotSimulateInactiveBodies);
	}
	
	private void buildInitialPosition() {
		float xPosition = 7;
		float yPosition = 2;
		initialPosition = new Vector2(xPosition, yPosition);
	}
	
	private void buildPlayer() {
		playerBody = PlayerBodyFactory.getPlayerBody(world, initialPosition);
		playerFixture = playerBody.getFixtureList().first();
	}

	@Test
	public void whenPlayerBodyIsCreatedThenShouldBeDynamicType() {
		assertEquals(BodyType.DynamicBody, playerBody.getType());
	}
	
	@Test
	public void whenPlayerBodyIsCreatedThenShouldBeInInitialPosition() {
		assertEquals(initialPosition, playerBody.getPosition());
	}
	
	@Test
	public void whenPlayerShapeIsCreatedThenShouldHavePolygonShape() {
		assertEquals(Shape.Type.Polygon, playerFixture.getType());
	}
	
	@Test
	public void whenPlayerFixtureIsCreatedThenPlayerDensityShouldBeInitialised() {
		assertEquals((int) PlayerBodyFactory.PLAYER_DENSITY, (int) playerFixture.getDensity());
	}
	
	@Test
	public void whenPlayerFixtureIsCreatedThenUserDataShouldBeInitialised() {
		assertEquals(PlayerBodyFactory.PLAYER_USER_DATA, playerFixture.getUserData());
	}
}