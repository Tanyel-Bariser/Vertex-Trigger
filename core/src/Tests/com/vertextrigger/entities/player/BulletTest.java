package com.vertextrigger.entities.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

@RunWith(MockitoJUnitRunner.class)
public class BulletTest {
	@Mock Body body;
	@Mock Sprite sprite;
	Bullet bullet;
	Vector2 position = new Vector2(2,3);
	float angle = 4f;
	float spriteAngle = angle * MathUtils.radiansToDegrees;

	@Before
	public void setUp() throws Exception {
		when(body.getPosition()).thenReturn(position);
		when(body.getAngle()).thenReturn(angle);
		bullet = new Bullet(body, sprite);
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
		bullet = new Bullet(body, sprite);
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
	
	@Test 
	public void whenBulletUpdatedThenBulletAngleIsGot() {
		bullet.update(4.44f);
		verify(body, atLeastOnce()).getAngle();
	}
	
	@Test
	public void whenBulletUpdatedThenSpritePositionShouldBeSetToBodyPosition() {
		bullet.update(2.2f);
		verify(sprite).setPosition(position.x, position.y);
	}
	
	@Test
	public void whenBulletUpdatedThenSpriteAngleShouldBeSetToBodyAngle() {
		bullet.update(2.2f);
		verify(sprite).setRotation(spriteAngle);
	}
	
	@Test
	public void whenBulletUpdatedThenSpriteNotNullIsReturned() {
		assertEquals(bullet.update(2.2f), sprite);
	}
}