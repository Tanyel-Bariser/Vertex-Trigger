package com.vertextrigger.util;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.Align;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.main.VertexTrigger;

/**
 * Manages the creation & use of the buttons that control the player & the game
 */
public class Controller implements InputProcessor {
	private final Screen level;
	private final Table buttonLayer;
	private final Stage stage;
	private final Player player;
	private State gameState;
	protected boolean isAndroidDevice;

	Vector2 leftButtonPosition = new Vector2(-50, 0);
	Vector2 rightButtonPosition = new Vector2(-25, 0);
	Vector2 pauseButtonPosition = new Vector2(0, 0);
	Vector2 shootButtonPosition = new Vector2(25, 0);
	Vector2 jumpButtonPosition = new Vector2(50, 0);

	/**
	 * Create all virtual buttons for Android version
	 */
	Controller(final Player player, final Screen level, final Stage stage, final State gameState) {
		this.level = level;
		this.stage = stage;
		this.player = player;
		buttonLayer = new Table().debug();
		this.gameState = gameState;

		setDeviceType();
		if (isAndroidDevice) {
			createLeftButton(new ImageButton(getLeftButton()));
			createRightButton(new ImageButton(getRightButton()));
			createPauseButton(new ImageButton(getPauseButton(), null, getResumeButton()));
			createShootButton(new ImageButton(getShootButton()));
			createJumpButton(new ImageButton(getJumpButton()));

			buttonLayer.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			buttonLayer.setPosition(0, 0);
			buttonLayer.align(Align.center | Align.bottom);
			stage.addActor(buttonLayer);
		}
	}

	public Controller(final Player player, final Screen level, final State gameState) {
		this(player, level, new Stage(), gameState);
	}

	public Stage getStage() {
		return stage;
	}

	/**
	 * Overridden by unit tests to set the device type for testing either Android or desktop
	 */
	void setDeviceType() {
		isAndroidDevice = Gdx.app.getType() == ApplicationType.Android;
	}

	boolean isAndroidDevice() {
		return isAndroidDevice;
	}

	Drawable getRightButton() {
		return VertexTrigger.ASSETS.getRightButton();
	}

	Drawable getLeftButton() {
		return VertexTrigger.ASSETS.getLeftButton();
	}

	Drawable getPauseButton() {
		return VertexTrigger.ASSETS.getPauseButton();
	}

	Drawable getResumeButton() {
		return VertexTrigger.ASSETS.getResumeButton();
	}

	Drawable getShootButton() {
		return VertexTrigger.ASSETS.getShootButton();
	}

	Drawable getJumpButton() {
		return VertexTrigger.ASSETS.getJumpButton();
	}

	/**
	 * Creates a virtual button for Android version which moves the player left when touched
	 */
	void createLeftButton(final ImageButton left) {
		left.addListener(getLeftClickListener());
		left.setPosition(leftButtonPosition.x, leftButtonPosition.y);
		buttonLayer.add(left).width(200).height(200);
	}

	ClickListener getLeftClickListener() {
		return new ClickListener() {
			@Override
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				if (isControllable()) {
					player.moveLeft();
				}
				return true;
			}

			@Override
			public void touchUp(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				player.stopMoving();
			}
		};
	}

	private boolean isControllable() {
		return (gameState != State.PAUSED) && (player.isDead() == false);
	}

	/**
	 * Creates a virtual button for Android version which moves the player right when touched
	 */
	void createRightButton(final ImageButton right) {
		right.addListener(getRightClickListener());
		right.setPosition(rightButtonPosition.x, rightButtonPosition.y);
		buttonLayer.add(right).width(200).height(200);
		// stage.addActor(right);
	}

	ClickListener getRightClickListener() {
		return new ClickListener() {
			@Override
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				if (isControllable()) {
					player.moveRight();
				}
				return true;
			}

			@Override
			public void touchUp(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				player.stopMoving();
			}
		};
	}

	/**
	 * Creates a virtual button for Android version which pauses the game when touched
	 */
	void createPauseButton(final ImageButton pause) {
		pause.addListener(getPauseClickListener());
		pause.setPosition(pauseButtonPosition.x, pauseButtonPosition.y);
		buttonLayer.add(pause).width(200).height(200);
		// stage.addActor(pause);
	}

	ClickListener getPauseClickListener() {
		return new ClickListener() {
			@Override
			public void clicked(final InputEvent event, final float x, final float y) {
				togglePause();
			}
		};
	}

	/**
	 * Creates a virtual button for Android version which cause the player to shoot his gun when touched
	 */
	void createShootButton(final ImageButton shoot) {
		shoot.addListener(getShootClickListener());
		shoot.setPosition(shootButtonPosition.x, shootButtonPosition.y);
		buttonLayer.add(shoot).width(200).height(200);
		// stage.addActor(shoot);
	}

	ClickListener getShootClickListener() {
		return new ClickListener() {
			@Override
			public void clicked(final InputEvent event, final float x, final float y) {
				if (isControllable()) {
					player.shoot();
				}
			}
		};
	}

	/**
	 * Creates a virtual button for Android version which makes the player jump when touched
	 */
	void createJumpButton(final ImageButton jump) {
		jump.addListener(getJumpClickListener());
		jump.setPosition(jumpButtonPosition.x, jumpButtonPosition.y);
		buttonLayer.add(jump).width(200).height(200);
		// stage.addActor(jump);
	}

	ClickListener getJumpClickListener() {
		return new ClickListener() {
			@Override
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				if (isControllable()) {
					player.jump();
				}
				return super.touchDown(event, x, y, pointer, button);
			}
		};
	}

	/**
	 * Only for Desktop and HTML versions
	 */
	@Override
	public boolean keyDown(final int keycode) {
		// deal with pause separately as we want to disable all other input if the game is paused
		if (keycode == Input.Keys.P) {
			togglePause();
		}

		if (isControllable() == false) {
			return false;
		}

		// if game is paused then buttons do not work
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

	private void togglePause() {
		if (gameState == State.PAUSED) {
			AudioManager.onResume();
			level.resume();
			gameState = State.RUNNING;
		} else {
			AudioManager.onPause();
			level.pause();
			gameState = State.PAUSED;
		}
	}

	/**
	 * Only for Desktop and HTML versions
	 */
	@Override
	public boolean keyUp(final int keycode) {
		if (((keycode == Input.Keys.LEFT) || (keycode == Input.Keys.RIGHT)) && (gameState != State.PAUSED)) {
			player.stopMoving();
			return true;
		}
		return false;
	}

	// UNUSED METHODS FROM INTERFACE
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
}