package com.vertextrigger.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vertextrigger.util.Assets;

/**
 * First class called by the respective port's, i.e. Android, Desktop,
 * HTML, bootstrapping class.
 */
public class VertexTrigger extends ApplicationAdapter {//Game {
	
	public static final Assets ASSETS = new Assets();
	SpriteBatch batch;
		Texture img;
		
		@Override
		public void create () {
			batch = new SpriteBatch();
			img = new Texture("background/bg_castle.png");
		}
	
		@Override
		public void render () {
			Gdx.gl.glClearColor(1, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			batch.draw(img, 0, 0);
			batch.end();
	}
	
	
	/**
	 * First method called when Vertex Trigger is first created
	 * Sets up the game
	 */
//	@Override
//	public void create () {
//		// Initialise assets
//		// Initialise audio manager
//		// Initialise sprite factory
//		// Initialise animation factory
//		// Open main menu screen
//	}
//	
//	/**
//	 * Called when exit game
//	 * Releases all resources
//	 */
//	@Override
//	public void dispose() {
//		// Dispose of all resources including assets
//	}
}