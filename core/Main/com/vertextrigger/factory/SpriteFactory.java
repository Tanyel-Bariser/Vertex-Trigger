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
}