package com.vertextrigger.entities;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.entities.BodyBuilder;
import com.vertextrigger.entities.BodyFactory;
import com.vertextrigger.util.ContactBody;

public class PlayerBodyFactoryTest {
	private World world;
	private Vector2 initialPosition;
	private Body body;
	private Fixture fixture;
	private BodyBuilder builder;

	@Before
	public void setUp() throws Exception {
		buildWorld();
		buildInitialPosition();
		builder = new BodyBuilder(world, initialPosition, ContactBody.PLAYER);
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
		body = BodyFactory.getBody(builder);
		fixture = body.getFixtureList().first();
	}

	@Test
	public void whenPlayerBodyIsCreatedThenShouldBeDynamicType() {
		assertEquals(BodyType.DynamicBody, body.getType());
	}
	
	@Test
	public void whenPlayerBodyIsCreatedThenShouldBeInInitialPosition() {
		assertEquals(initialPosition, body.getPosition());
	}
	
	@Test
	public void whenPlayerShapeIsCreatedThenShouldHavePolygonShape() {
		assertEquals(Shape.Type.Polygon, fixture.getType());
	}
	
	@Test
	public void whenPlayerFixtureIsCreatedThenPlayerDensityShouldBeInitialised() {
		assertEquals((int) builder.density, (int) fixture.getDensity());
	}
	
	@Test
	public void whenPlayerFixtureIsCreatedThenUserDataShouldBeInitialised() {
		assertEquals(ContactBody.PLAYER, fixture.getUserData());
	}
}