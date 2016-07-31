package com.vertextrigger.factory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.vertextrigger.main.VertexTrigger;
import com.vertextrigger.util.GameObjectSize;

/**
 * Encapsulates the creation of sprites
 */
public class SpriteFactory {

	public Sprite createCoreSprite(final String name, final GameObjectSize size) {
		return createSprite(name, size, VertexTrigger.ASSETS.getCoreSkin());
	}

	public Sprite createLevelSprite(final String name, final GameObjectSize size) {
		return createSprite(name, size, VertexTrigger.ASSETS.getLevelSkin());
	}

	public Sprite createPortalSprite(final String name, final GameObjectSize size) {
		return createSprite(name, size, VertexTrigger.ASSETS.getCoreSkin());
	}

	public Sprite createEnemySprite(final String name, final GameObjectSize size) {
		return createSprite(name, size, VertexTrigger.ASSETS.getEnemySkin());
	}

	private Sprite createSprite(final String name, final GameObjectSize size, final Skin skin) {
		final Sprite sprite = skin.getSprite(name);
		sprite.setSize(size.getSpriteWidth(), size.getSpriteHeight());
		sprite.setOriginCenter();
		return new Sprite(sprite);
	}

	/**
	 * @return main menu background sprite
	 */
	public Sprite createMainMenuBackground() {
		// create main menu background image from assets & return it
		return null;
	}

	/**
	 * @return level one background sprite
	 */
	public Sprite createLevelOneBackground() {
		// create level one background image from assets & return it
		return null;
	}

	/**
	 * @return level two background sprite
	 */
	public Sprite createLevelTwoBackground() {
		// create level two background image from assets & return it
		return null;
	}

	/**
	 * @return title sprite
	 */
	public Sprite createTitle() {
		// create title image for main screen from assets
		// Set size & return it
		return null;
	}
}