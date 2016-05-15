package com.vertextrigger.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Loads & stores assets, i.e. textures, bitmapfonts, sound effects, music, etc.
 */
public class Assets {
	private Skin coreSkin;
	private Skin enemySkin;
	private Skin portalSkin;
	private Skin mainScreenSkin;
	private Skin levelSkin;
	private final AssetManager assetManager;

	public Assets() {
		assetManager = new AssetManager();
	}

	Assets(final AssetManager assetManager) {
		this.assetManager = assetManager;
	}

	/**
	 * Ensure required assets are loaded before using asset manager
	 */
	public AssetManager getAssetManager() {
		return assetManager;
	}

	public void loadMainScreen() {
		assetManager.clear();
		assetManager.load(ATLASES.MAIN_SCREEN.getPath(), TextureAtlas.class);
		assetManager.load(BACKGROUND.MAIN_SCREEN.getPath(), Texture.class);
		assetManager.load(MUSIC.MAIN_SCREEN.getPath(), Music.class);
		assetManager.load(FONT.REGULAR.getPath(), BitmapFont.class);
		assetManager.finishLoading();

		mainScreenSkin = new Skin(assetManager.get(ATLASES.MAIN_SCREEN.getPath(), TextureAtlas.class));
	}

	public void loadPrototypeLevel() {
		loadLevelAssets(ATLASES.PROTOTYPE, BACKGROUND.LEVEL_ONE, MUSIC.LEVEL_ONE);
	}

	public void loadLevelOne() {
		loadLevelAssets(ATLASES.LEVEL_ONE, BACKGROUND.LEVEL_ONE, MUSIC.LEVEL_ONE);
	}

	private void loadLevelAssets(final ATLASES atlas, final BACKGROUND background, final MUSIC music) {
		assetManager.clear();
		loadCoreLevelAssets();
		assetManager.load(atlas.getPath(), TextureAtlas.class);
		assetManager.load(background.getPath(), Texture.class);
		assetManager.load(music.getPath(), Music.class);
		assetManager.load(ATLASES.PORTAL.getPath(), TextureAtlas.class);
		assetManager.finishLoading();

		final TextureAtlas coreAtlas = assetManager.get(ATLASES.CORE.getPath(), TextureAtlas.class);
		coreSkin = new Skin(coreAtlas);
		levelSkin = new Skin(assetManager.get(atlas.getPath(), TextureAtlas.class));

		final TextureAtlas portalAtlas = assetManager.get(ATLASES.PORTAL.getPath(), TextureAtlas.class);
		portalSkin = new Skin(portalAtlas);
	}

	private void loadCoreLevelAssets() {
		assetManager.load(ATLASES.CORE.getPath(), TextureAtlas.class);
		for (final SOUND_FX soundFx : SOUND_FX.values()) {
			assetManager.load(soundFx.getPath(), Sound.class);
		}
		assetManager.load(FONT.THIN.getPath(), BitmapFont.class);
	}

	public void loadLevelTwo() {
		loadLevelAssets(ATLASES.LEVEL_TWO, BACKGROUND.LEVEL_TWO, MUSIC.LEVEL_TWO);
	}

	/**
	 * Dispose of assets once they're no longer needed and asset manager itself
	 */
	public void dispose() {
		assetManager.dispose();
	}

	/**
	 * Make sure the level assets have been loaded, i.e. invoked method loadLevelXXX().
	 */
	public Drawable getLeftButton() {
		return coreSkin.getDrawable("leftButton");
	}

	public Drawable getRightButton() {
		return coreSkin.getDrawable("rightButton");
	}

	public Drawable getShootButton() {
		return coreSkin.getDrawable("shootButton");
	}

	public Drawable getJumpButton() {
		return coreSkin.getDrawable("jumpButton");
	}

	public Drawable getPauseButton() {
		return coreSkin.getDrawable("pauseButton");
	}

	public Skin getCoreSkin() {
		return coreSkin;
	}

	public Skin getMainScreenSkin() {
		return mainScreenSkin;
	}

	public Skin getLevelSkin() {
		return levelSkin;
	}

	public Skin getPortalSkin() {
		return portalSkin;
	}
}