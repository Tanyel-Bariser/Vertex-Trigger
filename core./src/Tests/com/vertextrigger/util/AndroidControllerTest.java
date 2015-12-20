package com.vertextrigger.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.vertextrigger.entities.player.Player;

@RunWith(MockitoJUnitRunner.class)
public class AndroidControllerTest {
	
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
				isAndroidDevice = true;
			}
			@Override
			Drawable getLeftButton() {
				return leftButton;
			}
			@Override
			Drawable getRightButton() {
				return rightButton;
			}
			@Override
			Drawable getPauseButton() {
				return pauseButton;
			}
			@Override
			Drawable getShootButton() {
				return shootButton;
			}
			@Override
			Drawable getJumpButton() {
				return jumpButton;
			}
		};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void whenLaunchedOnAndroidRunningOnAndroidIsTrue() {
		assertTrue(controller.isAndroidDevice());
	}
	
	@Test
	public void whenCreateLeftButtonIsCalledThenShouldAddClickListener() {
		controller.createLeftButton(left);
		verify(left).addListener((EventListener) anyObject());
	}
	
	@Test
	public void whenCreateLeftButtonIsCalledThenShouldBeAddedToStage() {
		controller.createLeftButton(left);
		verify(stage).addActor(left);
	}

	@Test
	public void whenClickListenerClickIsCalledThenShouldMovePlayerLeft() {
		ClickListener listener = controller.getLeftClickListener();
		listener.clicked(new InputEvent(), 0,0);
		verify(player).moveLeft();
	}
	
	@Test
	public void whenCreateRightButtonIsCalledThenShouldAddClickListener() {
		controller.createRightButton(right);
		verify(right).addListener((EventListener) anyObject());
	}

	@Test
	public void whenCreateRightButtonIsCalledThenShouldBeAddedToStage() {
		controller.createRightButton(right);
		verify(stage).addActor(right);
	}

	@Test
	public void whenClickListenerClickIsCalledThenShouldMovePlayerRight() {
		ClickListener listener = controller.getRightClickListener();
		listener.clicked(new InputEvent(), 0, 0);
		verify(player).moveRight();
	}
	
	@Test
	public void whenCreateShootButtonIsCalledThenShouldAddClickListener() {
		controller.createShootButton(shoot);
		verify(shoot).addListener((EventListener) anyObject());
	}

	@Test
	public void whenCreateShootButtonIsCalledThenShouldBeAddedToStage() {
		controller.createShootButton(shoot);
		verify(stage).addActor(shoot);
	}

	@Test
	public void whenClickListenerClickIsCalledThenPlayerShouldShoot() {
		ClickListener listener = controller.getShootClickListener();
		listener.clicked(new InputEvent(), 0, 0);
		verify(player).shoot();
	}
	
	@Test
	public void whenCreateJumpButtonIsCalledThenShouldAddClickListener() {
		controller.createJumpButton(jump);
		verify(jump).addListener((EventListener) anyObject());
	}

	@Test
	public void whenCreateJumpButtonIsCalledThenShouldBeAddedToStage() {
		controller.createJumpButton(jump);
		verify(stage).addActor(jump);
	}

	@Test
	public void whenClickListenerClickIsCalledThenPlayerShouldJump() {
		ClickListener listener = controller.getJumpClickListener();
		listener.clicked(new InputEvent(), 0, 0);
		verify(player).jump();
	}
	
	@Test
	public void whenCreatePauseButtonIsCalledThenShouldAddClickListener() {
		controller.createPauseButton(pause);
		verify(pause).addListener((EventListener) anyObject());
	}

	@Test
	public void whenCreatePauseButtonIsCalledThenShouldBeAddedToStage() {
		controller.createPauseButton(pause);
		verify(stage).addActor(pause);
	}

	@Test
	public void whenClickListenerClickIsCalledThenShouldSetScreenToPause() {
		ClickListener listener = controller.getPauseClickListener();
		listener.clicked(new InputEvent(), 0, 0);
		verify(screen).pause();
	}
}