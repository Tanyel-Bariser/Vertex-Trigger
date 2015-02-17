package com.vertextrigger.entities.player;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.entities.player.Bullet;
import com.vertextrigger.factories.bodyfactories.BulletBodyFactory;

@RunWith(MockitoJUnitRunner.class)
public class BulletTest {
	@Mock Body body;
	Bullet bullet;

	@Before
	public void setUp() throws Exception {
		bullet = new Bullet(body);
		bullet.shoot(true);
	}

	@Test
	public void whenShootBulletThenExistenceTimeShouldBeInitialised() {
		assertEquals((int) Bullet.TOTAL_EXISTENCE_TIME, (int) bullet.existenceTime);
	}
	
	@Test
	public void whenShootBulletThenShouldApplyLinearImpulse() {
		verify(body).applyLinearImpulse(null, body.getPosition(), true);
	}
	
	@Test
	public void whenReduceBulletExistenceTimeThenShouldUpdateBulletExistenceTime() {
		int delta = 3;
		bullet.update(delta);
		int expected = (int) Bullet.TOTAL_EXISTENCE_TIME - delta;
		assertEquals(expected, (int) bullet.existenceTime);
	}
	
	@Test
	public void whenBulletExistenceTimeIsAboveZeroThenShouldNotBeFreed() {
		float delta = Bullet.TOTAL_EXISTENCE_TIME - 1;
		bullet.update(delta);
		assertFalse(bullet.isExistenceTimeExpired());
	}
	
	@Test
	public void whenBulletExistenceTimeIsBelowZeroThenShouldBeFreed() {
		float delta = Bullet.TOTAL_EXISTENCE_TIME + 1;
		bullet.update(delta);
		assertTrue(bullet.isExistenceTimeExpired());
	}
	
	@Test
	public void whenBulletIsFreedThenShouldBeRepositionedOutOfCameraView() {
		bullet.reset();
		verify(body).setTransform(BulletBodyFactory.INITIAL_POSITION_OUT_OF_CAMERA_VIEW, 0);
	}
	
	@Test
	public void whenBulletIsFreedThenExistenceTimeShouldBeReset() {
		bullet.update(6);
		bullet.reset();
		assertEquals((int) Bullet.TOTAL_EXISTENCE_TIME, (int) bullet.existenceTime);
	}
}