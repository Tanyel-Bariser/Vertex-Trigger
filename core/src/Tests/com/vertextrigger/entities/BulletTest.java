package com.vertextrigger.entities;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.physics.box2d.Body;

@RunWith(MockitoJUnitRunner.class)
public class BulletTest {
	@Mock Body body;
	Bullet bullet;

	@Before
	public void setUp() throws Exception {
		bullet = new Bullet(body);
	}

	@Test
	public void whenShootBulletThenExistenceTimeShouldBeInitialised() {
		bullet.shoot(false);
		assertEquals((int) Bullet.TOTAL_EXISTENCE_TIME, (int) bullet.existenceTime);
	}
	
	@Test
	public void whenShootBulletThenShouldApplyLinearImpulse() {
		bullet.shoot(true);
		verify(body).applyLinearImpulse(null, body.getPosition(), true);
	}
	
	
}