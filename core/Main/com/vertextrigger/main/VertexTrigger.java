package com.vertextrigger.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.vertextrigger.assets.Assets;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.level.HughLevel;
import com.vertextrigger.level.Level;

/**
 * First class called by the respective ports, i.e. Android, Desktop, HTML, bootstrapping class.
 */
public class VertexTrigger extends Game {
	public static final Assets ASSETS = new Assets();
	private Screen nextScreen;

	private Level level;

	@Override
	public void create() {
		// Open main menu screen
		System.out.println("VertexTrigger#create");
		setScreen(getLevel().getScreen(this));
	}

	public void resetLevel() {
		System.out.println("RESET LEVEL");
		setScreen(getLevel().getScreen(this));
	}

	private Level getLevel() {
		if (level == null) {
			level = new HughLevel();
		}
		return this.level;
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