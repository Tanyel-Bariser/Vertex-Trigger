package com.vertextrigger.controller;

import com.badlogic.gdx.*;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.util.State;

public class DesktopController extends Controller {

	protected DesktopController(final Player player, final Screen screen) {
		super(player, screen);
	}

	@Override
	public boolean keyDown(final int keycode) {
		// deal with pause separately as we want to disable all other input if the game is paused
		if (keycode == Input.Keys.P) {
			togglePause();
		}

		if (isControllable() == false) {
			return false;
		}

		// if game is paused then code doesn't reach this point, i.e. buttons do not work
		if (gameState == State.RUNNING) {
			switch (keycode) {
			case Input.Keys.LEFT:
				player.moveLeft();
				break;
			case Input.Keys.RIGHT:
				player.moveRight();
				break;
			case Input.Keys.SPACE:
				player.shoot();
				break;
			case Input.Keys.UP:
				player.jump();
				break;
			case Input.Keys.D:
				player.setDead();
				break;
			case Input.Keys.M:
				AudioManager.toggleMute();
				break;
			default:
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean keyUp(final int keycode) {
		if ((keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT) && gameState != State.PAUSED) {
			player.stopMoving();
			return true;
		}
		return false;
	}
}