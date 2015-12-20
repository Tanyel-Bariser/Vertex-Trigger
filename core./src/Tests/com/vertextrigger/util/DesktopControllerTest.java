package com.vertextrigger.util;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.vertextrigger.entities.player.Player;

@RunWith(MockitoJUnitRunner.class)
public class DesktopControllerTest {
	
	Controller controller;
	@Mock Player player;
	@Mock Screen screen;
	@Mock Stage stage;
	@Mock ImageButton left;
	@Mock Drawable leftButton;
	@Mock ImageButton right;
	@Mock Drawable rightButton;
	@Mock ImageButton pause;
	@Mock Drawable pauseButton;
	@Mock ImageButton shoot;
	@Mock Drawable shootButton;
	@Mock ImageButton jump;
	@Mock Drawable jumpButton;

	@Before
	public void setUp() throws Exception {
		controller = new Controller(player, screen, stage) {
			@Override
			void setDeviceType() {
				isAndroidDevice = false;
			}
		};
	}

	@Test
	public void whenLaunchedOnDesktopRunningOnAndroidIsFalse() {
		assertFalse(controller.isAndroidDevice());
	}
	
	@Test
	public void whenLeftKeyPressedPlayerShouldMoveLeft() {
		controller.keyDown(Input.Keys.LEFT);
		verify(player).moveLeft();
	}
	
	@Test
	public void whenRightKeyPressedPlayerShouldMoveRight() {
		controller.keyDown(Input.Keys.RIGHT);
		verify(player).moveRight();
	}
	
	@Test
	public void whenUpKeyPressedPlayerShouldJump() {
		controller.keyDown(Input.Keys.UP);
		verify(player).jump();
	}
	
	@Test
	public void whenShootKeyPressedPlayerShouldShoot() {
		controller.keyDown(Input.Keys.SPACE);
		verify(player).shoot();
	}
	
	@Test
	public void whenPauseKeyPressedGameShouldPause() {
		controller.keyDown(Input.Keys.P);
		verify(screen).pause();
	}
	
	@Test
	public void whenLeftKeyUpPlayerShouldStopMovingHorizontally() {
		controller.keyUp(Input.Keys.LEFT);
		verify(player).stopMoving();
	}
	
	@Test
	public void whenRightKeyUpPlayerShouldStopMovingHorizontally() {
		controller.keyUp(Input.Keys.RIGHT);
		verify(player).stopMoving();
	}
}