package com.vertextrigger.entities.player;

import static org.junit.Assert.*;

import org.junit.*;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vertextrigger.entities.player.BulletBodyFactory;
import com.vertextrigger.util.UserData;

public class BulletBodyFactoryTest {
	private World world;
	private Body body;
	private Fixture fixture;
	
	@Before
	public void setUp() throws Exception {
		buildWorld();
		buildBulletBody();
	}
	
	private void buildWorld() {
		Vector2 gravity = new Vector2(0, -9.81f);
		boolean doNotSimulateInactiveBodies = true;
		world = new World(gravity, doNotSimulateInactiveBodies);
	}
	
	private void buildBulletBody() {
		body = BulletBodyFactory.getBulletBody(world);
		fixture = body.getFixtureList().first();
	}

	@Test
	public void whenBulletBodyIsCreateThenShouldBeDynamicType() {
		assertEquals(BodyType.DynamicBody, body.getType());
	}
	
	@Test
	public void whenBulletBodyIsCreatedThenShouldBeSetInitialPositionOutsideCameraView() {
		assertEquals(BulletBodyFactory.INITIAL_POSITION_OUT_OF_CAMERA_VIEW, body.getPosition());
	}
	
	@Test
	public void whenBulletBodyIsCreatedThenShouldBeSetAsBullet() {
		assertTrue(body.isBullet());
	}
	
	@Test
	public void whenBulletBodyIsCreatedThenShapeShapeBeCircle() {
		assertEquals(Shape.Type.Circle, fixture.getType());
	}
	
	@Test
	public void whenBulletShapeIsCreatedThenRadiusShouldBeSet() {
		Shape bulletShape = fixture.getShape();
		assertEquals((int) BulletBodyFactory.RADIUS, (int) bulletShape.getRadius());
	}
	
	@Test
	public void whenBulletFixtureDefinitionIsCreatedThenDensityShouldBeSet() {
		assertEquals((int) BulletBodyFactory.DENSITY, (int) fixture.getDensity());
	}
	
	@Test
	public void whenBulletFixtureDefinitionIsCreatedThenFrictionShouldBeSet() {
		assertEquals((int) BulletBodyFactory.FRICTION, (int) fixture.getFriction());
	}
	
	@Test
	public void whenBulletFixtureDefinitionIsCreatedThenRestitutionShouldBeSet() {
		assertEquals((int) BulletBodyFactory.BOUNCY, (int) fixture.getRestitution());
	}
	
	@Test
	public void whenBulletFixtureIsCreatedThenUserDataShouldBeSet() {
		assertEquals(UserData.BULLET, fixture.getUserData());
	}
	
	@Test
	public void whenChangePositionOfOneBulletBodyShouldNotAffectOtherBulletBodyPositions() {
		Body otherBody = BulletBodyFactory.getBulletBody(world);
		Vector2 position = new Vector2(10, 10);
		otherBody.setTransform(position, 0);
		assertEquals(BulletBodyFactory.INITIAL_POSITION_OUT_OF_CAMERA_VIEW, body.getPosition());
	}
}