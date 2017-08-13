package com.vertextrigger.controller;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.util.State;

public abstract class Controller implements InputProcessor {
	protected State gameState = State.RUNNING;
	protected final Screen screen;
	protected final Player player;

	public static Controller createDesktopController(final Player player, final Screen screen) {
		return new DesktopController(player, screen);
	}

	public static Controller createAndroidSliderController(final Player player, final Screen screen, final Stage stage) {
		return new AndroidSliderController(player, screen, stage);
	}

	public static Controller createAndroidButtonController(final Player player, final Screen screen, final Stage stage) {
		return new AndroidButtonController(player, screen, stage);
	}

	protected Controller(final Player player, final Screen screen) {
		this.player = player;
		this.screen = screen;
	}

	protected void togglePause() {
		if (gameState == State.PAUSED) {
			AudioManager.onResume();
			screen.resume();
			gameState = State.RUNNING;
		} else {
			AudioManager.onPause();
			screen.pause();
			gameState = State.PAUSED;
		}
	}

	protected boolean isControllable() {
		return (gameState != State.PAUSED) && (player.isDead() == false);
	}

	@Override
	public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
		return false;
	}

	@Override
	public boolean keyTyped(final char character) {
		return false;
	}

	@Override
	public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button) {
		return false;
	}

	@Override
	public boolean touchDragged(final int screenX, final int screenY, final int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(final int screenX, final int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(final int amount) {
		return false;
	}

	@Override
	public boolean keyDown(final int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(final int keycode) {
		return false;
	}
}