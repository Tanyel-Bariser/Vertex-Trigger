package com.vertextrigger.entities.player;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static com.vertextrigger.util.GameObjectSize.BULLET_SIZE;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.factory.bodyfactory.BulletBodyFactory;
import com.vertextrigger.util.GameObjectSize;

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
	public void whenBulletIsFreedThenShouldBeRepositionedOutOfCameraView() {
		bullet.reset();
		verify(body).setTransform(BulletBodyFactory.INITIAL_POSITION_OUT_OF_CAMERA_VIEW, 0);
	}
	
	@Test
	public void whenBulletUpdatedThenSpritePositionShouldBeSetToBodyPosition() {
		bullet.update(2.2f);
		verify(sprite).setPosition(position.x - BULLET_SIZE.getOffsetX(),
				position.y - BULLET_SIZE.getOffsetY());
	}
	
	@Test
	public void whenBulletUpdatedThenSpriteNotNullIsReturned() {
		assertEquals(bullet.update(2.2f), sprite);
	}
}