package com.vertextrigger.factories;

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

	/**
	 * @param name
	 *            of player sprite in texture atlas
	 * @param width
	 *            of player sprite
	 * @param height
	 *            of player sprite
	 * @return player sprite
	 */
	public Sprite createPlayer(String name, GameObjectSize size) {
		Sprite player = coreSkin.getSprite(name);
		player.setSize(size.getSpriteWidth(), size.getSpriteHeight());
		player.setOriginCenter();
		return player;
	}

	/**
	 * @param name
	 *            of enemy sprite in texture atlas
	 * @param width
	 *            of enemy sprite
	 * @param height
	 *            of enemy sprite
	 * @return enemy sprite
	 */
	public Sprite createEnemy(String name, float width, float height) {
		Sprite enemy = levelSkin.getSprite(name);
		enemy.setSize(width, height);
		enemy.setOriginCenter();
		return enemy;
	}

	/**
	 * @param name
	 *            of dangerous ball sprite in texture atlas
	 * @param radius
	 *            of dangerous ball sprite
	 * @return dangerous ball sprite
	 */
	public Sprite createDangerousBall(String name, float radius) {
		// create a specific dangerous ball sprite from assets by its name
		// Set dangerous ball sprite size
		// Set dangerous ball origin as middle of sprite
		// Return dangerous ball sprite
		return null;
	}

	/**
	 * @param name
	 *            of platform sprite in texture atlas
	 */
	public Sprite createPlatform(String name, float width, float height) {
		Sprite sprite = levelSkin.getSprite(name);
		sprite.setSize(width, height);
		return new Sprite(sprite);
	}
}