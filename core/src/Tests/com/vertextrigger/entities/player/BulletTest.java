package com.vertextrigger.entities.player;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

@RunWith(MockitoJUnitRunner.class)
public class BulletTest {
	@Mock Body body;
	Bullet bullet;

	@Before
	public void setUp() throws Exception {
		bullet = new Bullet(body);
		boolean shootLeft = true;
		bullet.shoot(shootLeft);
	}

	@Test
	public void whenShootBulletThenExistenceTimeShouldBeInitialised() {
		assertEquals((int) Bullet.TOTAL_EXISTENCE_TIME, (int) bullet.getRemainingTime());
	}
	
	@Test
	public void whenShootBulletLeftThenShouldApplyLinearImpulseHorizontallyAtNegativeSHOT_POWER() {
		ArgumentCaptor<Vector2> velocity = ArgumentCaptor.forClass(Vector2.class);
		verify(body).applyLinearImpulse(velocity.capture(), any(Vector2.class), eq(true));
		assertEquals(new Vector2(-Bullet.SHOT_POWER, 0), velocity.getValue());
	}
	
	@Test
	public void whenShootBulletRightThenShouldApplyLinearImpulseHorizontallyAtPositiveSHOT_POWER() {
		Body body = mock(Body.class);
		bullet = new Bullet(body);
		boolean shootRight = false;
		bullet.shoot(shootRight);
		ArgumentCaptor<Vector2> velocity = ArgumentCaptor.forClass(Vector2.class);
		verify(body).applyLinearImpulse(velocity.capture(), any(Vector2.class), eq(true));
		assertEquals(new Vector2(Bullet.SHOT_POWER, 0), velocity.getValue());
	}
	
	@Test
	public void whenReduceBulletExistenceTimeThenShouldUpdateBulletExistenceTime() {
		int delta = 3;
		bullet.update(delta);
		int expected = (int) Bullet.TOTAL_EXISTENCE_TIME - delta;
		assertEquals(expected, (int) bullet.getRemainingTime());
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
		assertEquals((int) Bullet.TOTAL_EXISTENCE_TIME, (int) bullet.getRemainingTime());
	}
}