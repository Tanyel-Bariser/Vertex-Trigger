package com.vertextrigger.util;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
	protected boolean isAndroidDevice;

	Vector2 leftButtonPosition = new Vector2(-50, 0);
	Vector2 rightButtonPosition = new Vector2(-25, 0);
	Vector2 pauseButtonPosition = new Vector2(0, 0);
	Vector2 shootButtonPosition = new Vector2(25, 0);
	Vector2 jumpButtonPosition = new Vector2(50, 0);

	/**
	 * Create all virtual buttons for Android version
	 */
	Controller(Player player, Screen level, Stage stage) {
		this.level = level; 
		this.stage = stage;
		this.player = player;
		this.buttonLayer = new Table().debug();

		setDeviceType();
		if (isAndroidDevice) {
			createLeftButton(new ImageButton(getLeftButton()));
			createRightButton(new ImageButton(getRightButton()));
			createPauseButton(new ImageButton(getPauseButton()));
			createShootButton(new ImageButton(getShootButton()));
			createJumpButton(new ImageButton(getJumpButton()));

			buttonLayer.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			buttonLayer.setPosition(0, 0);
			buttonLayer.align(Align.center|Align.bottom);
			stage.addActor(buttonLayer);
		}
	}

	public Controller(Player player, Screen level) {
		this(player, level, new Stage());
	}

	public Stage getStage() {
		return stage;
	}

	/**
	 * Overriden by unit tests to set the device type for testing either Android or desktop
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
	
	Drawable getShootButton() {
		return VertexTrigger.ASSETS.getShootButton();
	}
	
	Drawable getJumpButton() {
		return VertexTrigger.ASSETS.getJumpButton();
	}
	
	/**
	 * Creates a virtual button for Android version
	 * which moves the player left when touched
	 */
	void createLeftButton(ImageButton left) {
		left.addListener(getLeftClickListener());
		left.setPosition(leftButtonPosition.x, leftButtonPosition.y);
		buttonLayer.add(left).width(200).height(200);
		//stage.addActor(left);
	}
	
	ClickListener getLeftClickListener() {
		return new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				player.moveLeft();
				return true;
			}

			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				player.stopMoving();
			}
		};
	}
	
	/**
	 * Creates a virtual button for Android version
	 * which moves the player right when touched
	 */
	void createRightButton(ImageButton right) {
		right.addListener(getRightClickListener());
		right.setPosition(rightButtonPosition.x, rightButtonPosition.y);
		buttonLayer.add(right).width(200).height(200);
		//stage.addActor(right);
	}
	
	ClickListener getRightClickListener() {
		return new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				player.moveRight();
				return true;
			}

			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				player.stopMoving();
			}
		};
	}

	/**
	 * Creates a virtual button for Android version
	 * which pauses the game when touched
	 */
	void createPauseButton(ImageButton pause) {
		pause.addListener(getPauseClickListener());
		pause.setPosition(pauseButtonPosition.x, pauseButtonPosition.y);
		buttonLayer.add(pause).width(200).height(200);
		//stage.addActor(pause);
	}

	boolean paused = false;
	ClickListener getPauseClickListener() {
		return new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				pause();
			}
		};
	}

	private void pause() {
		if (paused) {
			AudioManager.onResume();
			level.resume();
			paused = false;
		}
		else {
			AudioManager.onPause();
			level.pause();
			paused = true;
		}
	}

	/**
	 * Creates a virtual button for Android version
	 * which cause the player to shoot his gun when touched
	 */
	void createShootButton(ImageButton shoot) {
		shoot.addListener(getShootClickListener());
		shoot.setPosition(shootButtonPosition.x, shootButtonPosition.y);
		buttonLayer.add(shoot).width(200).height(200);
		//stage.addActor(shoot);
	}
	
	ClickListener getShootClickListener() {
		return new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				player.shoot();
			}
		};
	}
		
	/**
	 * Creates a virtual button for Android version
	 * which makes the player jump when touched
	 */
	void createJumpButton(ImageButton jump) {
		jump.addListener(getJumpClickListener());
		jump.setPosition(jumpButtonPosition.x, jumpButtonPosition.y);
		buttonLayer.add(jump).width(200).height(200);
		//stage.addActor(jump);
	}
	
	ClickListener getJumpClickListener() {
		return new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				player.jump();
				return super.touchDown(event, x, y, pointer, button);
			}
		};
	}
	
	/**
	 * Only for Desktop and HTML versions
	 */
	@Override
	public boolean keyDown(int keycode) {
		if (player.isDead()) {
			return false;
		}
		switch (keycode) {
			case Input.Keys.LEFT:
				player.moveLeft();
				break;
			case Input.Keys.RIGHT:
				player.moveRight();
				break;
			case Input.Keys.P:
				pause();
				break;
			case Input.Keys.SPACE:
				player.shoot();
				break;
			case Input.Keys.UP:
				player.jump();
				break;
			case Input.Keys.R:
				player.spinLikeCrazy();
				break;
			case Input.Keys.M:
				if (!paused) {
					AudioManager.toggleMute();
				}
				break;
			default: return false;
		}
		return true;
	}

	/**
	 *  Only for Desktop and HTML versions
	 */
	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT) {
			player.stopMoving();
			return true;
		}
		return false;
	}

	//UNUSED METHODS FROM INTERFACE
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}