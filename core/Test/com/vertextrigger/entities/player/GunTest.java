// package com.vertextrigger.entities.player;
//
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
//
// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.Mock;
// import org.mockito.runners.MockitoJUnitRunner;
//
// import com.badlogic.gdx.math.Vector2;
// import com.badlogic.gdx.physics.box2d.Body;
// import com.badlogic.gdx.physics.box2d.World;
// import com.vertextrigger.screen.AbstractGameScreen;
//
// @RunWith(MockitoJUnitRunner.class)
// public class GunTest {
// Gun gun;
// World world;
// BulletPool pool;
// @Mock Body player;
// @Mock AbstractGameScreen gameScreen;
// @Mock BulletPool mockPool;
// @Mock Bullet bullet;
//
// @Before
// public void setUp() throws Exception {
// buildWorld();
// gun = new Gun(gameScreen, mockPool);
// when(bullet.getPosition()).thenReturn(new Vector2(0,0));
// pool = new BulletPool(world);
// }
//
// private void buildWorld() {
// Vector2 gravity = new Vector2(0, -9.81f);
// boolean doNotSimulateInactiveBodies = true;
// world = new World(gravity, doNotSimulateInactiveBodies);
// }
//
// @Test
// public void givenPlayerFacingLeftWhenGunShootThenBulletShouldBeShotLeft() {
// when(mockPool.obtain()).thenReturn(bullet);
// when(player.getPosition()).thenReturn(new Vector2());
// gun.shoot(player.getPosition(), true);
// boolean shootLeft = true;
// verify(bullet).shoot(shootLeft);
// }
//
// @Test
// public void givenPlayerFacingRightWhenGunShootThenBulletShouldBeShotRight() {
// when(mockPool.obtain()).thenReturn(bullet);
// when(player.getPosition()).thenReturn(new Vector2());
// gun.shoot(player.getPosition(), false);
// boolean shootLeft = false;
// verify(bullet).shoot(shootLeft);
// }
// }