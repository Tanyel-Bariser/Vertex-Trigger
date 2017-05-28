package com.vertextrigger.main;

import com.badlogic.gdx.*;
import com.vertextrigger.assets.*;
import com.vertextrigger.factory.GameScreenFactory;

/**
 * First class called by the respective ports, i.e. Android, Desktop, HTML, bootstrapping class.
 */
public class VertexTrigger extends Game {
	public static final Assets ASSETS = new Assets();
	private Screen nextScreen;

	@Override
	public void create() {
		// Open main menu screen
		// setScreen(GameScreenFactory.createPrototypeLevel(this));
		// setScreen(new MainScreen(this));
		setScreen(GameScreenFactory.createTanyelLevel(this));
	}

	@Override
	public void render() {
		super.render();
		if (nextScreen != null) {
			setScreen(nextScreen);
			nextScreen = null;
		}
	}

	public void setNextScreen(final Screen nextScreen) {
		this.nextScreen = nextScreen;
	}

	/**
	 * Called when exit game Releases all resources
	 */
	@Override
	public void dispose() {
		AudioManager.disposeAll();
		ASSETS.dispose();
	}
}