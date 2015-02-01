package com.vertextrigger.factories.bodyfactories;

import static org.junit.Assert.*;

import org.junit.*;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BulletBodyFactoryTest {
	private World world;
	private Body bulletBody;
	private Fixture bulletFixture;
	
	@Before
	public void setUp() throws Exception {
		buildWorld();
		buildBullet();
	}
	
	private void buildWorld() {
		Vector2 gravity = new Vector2(0, -9.81f);
		boolean doNotSimulateInactiveBodies = true;
		world = new World(gravity, doNotSimulateInactiveBodies);
	}
	
	private void buildBullet() {
		bulletBody = BulletBodyFactory.getBulletBody(world);
		bulletFixture = bulletBody.getFixtureList().first();
	}

	@Test
	public void whenBulletBodyIsCreateThenShouldBeDynamicType() {
		assertEquals(BodyType.DynamicBody, bulletBody.getType());
	}
	
	@Test
	public void whenBulletBodyIsCreatedThenShouldBeSetInitialPositionOutsideCameraView() {
		assertEquals(BulletBodyFactory.INITIAL_POSITION_OUT_OF_CAMERA_VIEW, bulletBody.getPosition());
	}
	
	@Test
	public void whenBulletBodyIsCreatedThenShouldBeSetAsBullet() {
		assertTrue(bulletBody.isBullet());
	}
	
	@Test
	public void whenBulletBodyIsCreatedThenShapeShapeBeCircle() {
		assertEquals(Shape.Type.Circle, bulletFixture.getType());
	}
	
	@Test
	public void whenBulletShapeIsCreatedThenRadiusShouldBeSet() {
		Shape bulletShape = bulletFixture.getShape();
		assertEquals((int) BulletBodyFactory.BULLET_RADIUS, (int) bulletShape.getRadius());
	}
	
	@Test
	public void whenBulletFixtureDefIsCreatedThenDensityShouldBeSet() {
		assertEquals((int) BulletBodyFactory.BULLET_DENSITY, (int) bulletFixture.getDensity());
	}
	
	@Test
	public void whenBulletFixtureDefIsCreatedThenFrictionShouldBeSet() {
		assertEquals((int) BulletBodyFactory.BULLET_FRICTION, (int) bulletFixture.getFriction());
	}
	
	@Test
	public void whenBulletFixtureDefIsCreatedThenRestitutionShouldBeSet() {
		assertEquals((int) BulletBodyFactory.BULLET_IS_VERY_BOUNCY, (int) bulletFixture.getRestitution());
	}
	
	@Test
	public void whenBulletFixtureIsCreatedThenUserDataShouldBeSet() {
		assertEquals(UserData.BULLET, bulletFixture.getUserData());
	}
}