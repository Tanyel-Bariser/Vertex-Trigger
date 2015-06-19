package com.vertextrigger.util;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.vertextrigger.main.VertexTrigger;

/**
 * Manages the creation & use of the buttons that control the player & the game
 */
public class Controller implements InputProcessor {
	private Screen level;
	private Stage stage;
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
	public Controller(Screen level, Stage stage) {
		setDeviceType();
		if (isAndroidDevice) {
			createLeftButton();
			createRightButton();
			createPauseButton();
			createShootButton();
			createJumpButton();			
		}
	}
	
	/**
	 * Creates a virtual button for Android version
	 * which moves the player left when touched
	 */
	private void createLeftButton() {
		ImageButton left = new ImageButton(VertexTrigger.ASSETS.getLeftButton());
		// Create virtual button with left arrow image
		// Create & set a listener to notice when the virtual button is
		// touched & that causes the player to move left when it's touched
		// Add button to the stage
	}
	
	/**
	 * Creates a virtual button for Android version
	 * which moves the player right when touched
	 */
	private void createRightButton() {
		// Create virtual button with right arrow image
		// Create & set a listener to notice when the virtual button is
		// touched & that causes the player to move right when it's touched
		// Add button to the stage
	}
	
	/**
	 * Creates a virtual button for Android version
	 * which pauses the game when touched
	 */
	private void createPauseButton() {
		// Create virtual button with a standard pause image
		// Create & set a listener to notice when the virtual button is
		// touched & that causes the game to pause when it's touched
		// Add button to the stage
	}
	
	/**
	 * Creates a virtual button for Android version
	 * which unpauses the game play when touched
	 */
	private void createResumeButton() {
		// Create virtual resume game play button
		// Create & set a listener to notice when the virtual button is
		// touched & that causes the game play to be resumed
		// Add button to the stage
	}

	/**
	 * Creates a virtual button for Android version
	 * which cause the player to shoot his gun when touched
	 */
	private void createShootButton() {
		// Create virtual button with a shoot gun image
		// Create & set a listener to notice when the virtual button is
		// touched & that causes the player to shoot his gun when it's touched
		// Add button to the stage
	}
		
	/**
	 * Creates a virtual button for Android version
	 * which makes the player jump when touched
	 */
	private void createJumpButton() {
		// Create virtual button with up arrow image
		// Create & set a listener to notice when the virtual button is
		// touched & that causes the player to jump when it's touched
		// Add button to the stage
	}
	
	/**
	 * Only for Desktop and HTML versions
	 */
	@Override
	public boolean keyDown(int keycode) {
		// If the left key is pressed
				// Move player left
		// If the right key is pressed
				// Move player right
		// If the space bar is pressed
				// If player is on a platform
						// Make player jump
		return false;
	}

	/**
	 *  Only for Desktop and HTML versions
	 */
	@Override
	public boolean keyUp(int keycode) {
		// If left or right keys are no longer being pressed
				// Stop moving player left or right
		return false;
	}

	/**
	 * Only for Android version
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	
	
	
	//UNUSED METHODS FROM INTERFACE
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