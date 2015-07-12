package com.vertextrigger.main;

import com.badlogic.gdx.*;
import com.vertextrigger.levelbuilders.PrototypeLevelBuilder;
import com.vertextrigger.screens.GameScreen;
import com.vertextrigger.util.Assets;

/**
 * First class called by the respective port's, i.e. Android, Desktop,
 * HTML, bootstrapping class.
 */
public class VertexTrigger extends Game {
	public static final Assets ASSETS = new Assets();
	private Screen nextScreen;

	@Override
	public void create() {
//		// Open main menu screen
		setScreen(new GameScreen(this, new PrototypeLevelBuilder()));
	}

	@Override
	public void render() {
		super.render();
		if (nextScreen != null) {
			setScreen(nextScreen);
			nextScreen = null;
		}
	}
	
	public void setNextScreen(Screen nextScreen) {
		this.nextScreen = nextScreen;
	}

	/**
	 * Called when exit game
	 * Releases all resources
	 */
	@Override
	public void dispose() {
		// Dispose of all resources including assets
	}
}