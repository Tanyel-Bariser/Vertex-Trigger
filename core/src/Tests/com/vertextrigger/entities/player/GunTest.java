package com.vertextrigger.entities.player;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.screens.GameScreen;

@RunWith(MockitoJUnitRunner.class)
public class GunTest {
	Gun gun;
	World world;
	@Mock Array<Bullet> bullets;
	@Mock Body player;
	@Mock GameScreen gameScreen;
	@Mock BulletPool pool;
	@Mock Bullet bullet;

	@Before
	public void setUp() throws Exception {
		buildWorld();
		gun = new Gun(player, gameScreen, pool, bullets);
	}
	
	private void buildWorld() {
		Vector2 gravity = new Vector2(0, -9.81f);
		boolean doNotSimulateInactiveBodies = true;
		world = new World(gravity, doNotSimulateInactiveBodies);
	}

	@Test
	public void givenPlayerFacingLeftWhenGunShootThenBulletShouldBeShotLeft() {
		when(pool.obtain()).thenReturn(bullet);
		Vector2 playerFacingLeft = new Vector2(-1f, 0);
		when(player.getLinearVelocity()).thenReturn(playerFacingLeft);
		when(player.getPosition()).thenReturn(new Vector2());
		gun.shoot();
		boolean shootLeft = true;
		verify(bullet).shoot(shootLeft);
	}

	@Test
	public void givenPlayerFacingRightWhenGunShootThenBulletShouldBeShotRight() {
		when(pool.obtain()).thenReturn(bullet);
		Vector2 playerFacingRight = new Vector2(1f, 0);
		when(player.getLinearVelocity()).thenReturn(playerFacingRight);
		when(player.getPosition()).thenReturn(new Vector2());
		gun.shoot();
		boolean shootLeft = false;
		verify(bullet).shoot(shootLeft);
	}
}