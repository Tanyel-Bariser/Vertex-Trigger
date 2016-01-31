package com.vertextrigger.factory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.vertextrigger.main.VertexTrigger;
import com.vertextrigger.util.GameObjectSize;

/**
 * Encapsulates the creation of sprites
 */
public class SpriteFactory {
	private final Skin coreSkin;
	private final Skin levelSkin;

	/**
	 * Initialises storage of sprites that can be accessed by name
	 */
	public SpriteFactory() {
		coreSkin = VertexTrigger.ASSETS.getCoreSkin();
		levelSkin = VertexTrigger.ASSETS.getLevelSkin();
	}
	
	public Sprite createCoreSprite(String name, GameObjectSize size) {
		return createSprite(name, size, VertexTrigger.ASSETS.getCoreSkin());
	}
	
	public Sprite createLevelSprite(String name, GameObjectSize size) {
		return createSprite(name, size, VertexTrigger.ASSETS.getLevelSkin());
	}
	
	private Sprite createSprite(String name, GameObjectSize size, Skin skin) {
		Sprite sprite = skin.getSprite(name);
		sprite.setSize(size.getSpriteWidth(), size.getSpriteHeight());
		sprite.setOriginCenter();
		return new Sprite(sprite);
	}

	Sprite bullet = null;

	public Sprite createBullet() {
		if (bullet == null) {
			bullet = coreSkin.getSprite("bullet");
			float radius = 0.5f;
			bullet.setSize(radius, radius);
			return bullet;
		}
		return new Sprite(bullet);
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