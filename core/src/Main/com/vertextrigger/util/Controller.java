package com.vertextrigger.util;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.main.VertexTrigger;

/**
 * Manages the creation & use of the buttons that control the player & the game
 */
public class Controller implements InputProcessor {
	private final Screen level;
	private final Stage stage;
	private final Player player;
	protected boolean isAndroidDevice; 
	
	/**
	 * Overriden by unit tests to set the device type for testing either Android or desktop
	 */
	void setDeviceType() {
		isAndroidDevice = Gdx.app.getType() == ApplicationType.Android;
	}
	
	boolean isAndroidDevice() { 
		return isAndroidDevice;
	}

	/**
	 * Create all virtual buttons for Android version
	 */
	public Controller(Player player, Screen level, Stage stage) {
		this.level = level; 
		this.stage = stage;
		this.player = player;
		
		setDeviceType();
		if (isAndroidDevice) {
			createLeftButton(new ImageButton(getLeftButton()));
			createRightButton(new ImageButton(getRightButton()));
			createPauseButton(new ImageButton(getPauseButton()));
			createShootButton(new ImageButton(getShootButton()));
			createJumpButton(new ImageButton(getJumpButton()));			
		}
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
		stage.addActor(left);
	}
	
	ClickListener getLeftClickListener() {
		return new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				player.moveLeft();
			}
		};
	}
	
	/**
	 * Creates a virtual button for Android version
	 * which moves the player right when touched
	 */
	void createRightButton(ImageButton right) {
		right.addListener(getRightClickListener());
		stage.addActor(right);
	}
	
	ClickListener getRightClickListener() {
		return new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				player.moveRight();
			}
		};
	}

	/**
	 * Creates a virtual button for Android version
	 * which pauses the game when touched
	 */
	void createPauseButton(ImageButton pause) {
		pause.addListener(getPauseClickListener());
		stage.addActor(pause);
	}
	
	ClickListener getPauseClickListener() {
		return new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				level.pause();
			}
		};
	}

	/**
	 * Creates a virtual button for Android version
	 * which cause the player to shoot his gun when touched
	 */
	void createShootButton(ImageButton shoot) {
		shoot.addListener(getShootClickListener());
		stage.addActor(shoot);
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
		stage.addActor(jump);
	}
	
	ClickListener getJumpClickListener() {
		return new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				player.jump();
			}
		};
	}
	
	/**
	 * Only for Desktop and HTML versions
	 */
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
			case Input.Keys.LEFT:
				player.moveLeft();
				break;
			case Input.Keys.RIGHT:
				player.moveRight();
				break;
			case Input.Keys.P:
				level.pause();
				break;
			case Input.Keys.SPACE:
				player.shoot();
				break;
			case Input.Keys.UP:
				player.jump();
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