package com.vertextrigger.factory.bodyfactory;

import static org.junit.Assert.*;

import org.junit.*;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.util.ContactBody;

public class PortalBodyFactoryTest {
	PortalBodyFactory factory;
	Body body;
	Fixture fixture;

	@Before
	public void setUp() throws Exception {
		World world = buildWorld();
		Vector2 initialPosition = new Vector2(0,0);
		factory = new PortalBodyFactory();
		body = factory.createPortalBody(world, initialPosition);
		fixture = body.getFixtureList().get(0);
	}

	private World buildWorld() {
		boolean doNotSimulateInactiveBodies = true;
		Vector2 gravity = new Vector2(0, -9.81f);
		return new World(gravity, doNotSimulateInactiveBodies);
	}

	@Test
	public void shouldBeStaticBodyType() {
		assertEquals(BodyType.StaticBody, body.getType());
	}

	@Test
	public void shouldBeSensor() {
		assertTrue(fixture.isSensor());
	}
	
	@Test
	public void shouldBePolygonShape() {
		assertTrue(fixture.getShape() instanceof PolygonShape);
	}
	
	@Test
	public void shouldBePolygonShapeType() {
		assertEquals(Shape.Type.Polygon, fixture.getType());
	}
	
	@Test
	public void bodyShouldBeContactBodyPortal() {
		assertEquals(ContactBody.PORTAL_ONE, body.getUserData());
	}
	
	@Test
	public void fixtureShouldBeContactBodyPortal() {
		assertEquals(ContactBody.PORTAL_ONE, fixture.getUserData());
	}
}