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
import com.vertextrigger.factories.bodyfactories.BulletBodyFactory;
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
	

	@Test
	public void givenExpiredBulletWhenPromptedShouldReset() {
		BulletPool pool = new BulletPool(world);
		Array<Bullet> bullets = new Array<Bullet>();
		bullets.add(bullet);
		gun = new Gun(player, gameScreen, pool, bullets);
		
		when(bullet.isExistenceTimeExpired()).thenReturn(true);
		
		//Main Test
		gun.freeExpiredBullets();
		verify(bullet).reset();
	}
	
	@Test
	public void givenUnexpiredBulletWhenPromptedShouldNotReset() {
		BulletPool pool = new BulletPool(world);
		Array<Bullet> bullets = new Array<Bullet>();
		bullets.add(bullet);
		gun = new Gun(player, gameScreen, pool, bullets);
		
		when(bullet.isExistenceTimeExpired()).thenReturn(false);
		
		//Main Test
		gun.freeExpiredBullets();
		verify(bullet, never()).reset();
	}
	
	@Test
	public void givenExpiredBulletWhenPromptedShouldRemoveBulletFromGun() {
		//Setup without Bullet Mocking
		BulletPool pool = new BulletPool(world);
		Array<Bullet> bullets = new Array<Bullet>();
		Bullet bullet1 = pool.newObject();
		bullets.add(bullet1);
		Bullet bullet2 = pool.newObject();
		bullets.add(bullet2);
		gun = new Gun(player, gameScreen, pool, bullets);
		
		//Initialise Bullet and expire existence time
		bullet1.shoot(true);
		bullet2.shoot(true);
		float expire = 7;
		bullet1.update(expire);
		float doNotExpire = 1;
		bullet2.update(doNotExpire);
		
		//Main Test
		gun.freeExpiredBullets();
		assertEquals(1, bullets.size);
	}
	
	@Test
	public void givenExpiredBulletWhenPromptedShouldResetRemainingTime() {
		//Setup without Bullet Mocking
		BulletPool pool = new BulletPool(world);
		Array<Bullet> bullets = new Array<Bullet>();
		Bullet bullet1 = pool.newObject();
		bullets.add(bullet1);
		Bullet bullet2 = pool.newObject();
		bullets.add(bullet2);
		gun = new Gun(player, gameScreen, pool, bullets);
		
		//Initialise Bullet and expire existence time
		bullet1.shoot(true);
		bullet2.shoot(true);
		float expire = 7;
		bullet1.update(expire);
		float doNotExpire = 1;
		bullet2.update(doNotExpire);
		
		//Main Test
		gun.freeExpiredBullets();
		assertEquals((int) Bullet.TOTAL_EXISTENCE_TIME, (int) bullet1.getRemainingTime());
		assertEquals((int) (Bullet.TOTAL_EXISTENCE_TIME - doNotExpire), (int) bullet2.getRemainingTime());
	}
}