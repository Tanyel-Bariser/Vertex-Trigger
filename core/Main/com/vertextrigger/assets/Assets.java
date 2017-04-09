package com.vertextrigger.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Loads & stores assets, i.e. textures, bitmapfonts, sound effects, music, etc.
 */
public class Assets {
	private Skin coreSkin;
	private Skin enemySkin;
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
		assetManager.load(AtlasPath.MAIN_SCREEN.getPath(), TextureAtlas.class);
		assetManager.load(BackgroundPath.MAIN_SCREEN.getPath(), Texture.class);
		assetManager.load(MusicPath.MAIN_SCREEN.getPath(), Music.class);
		assetManager.load(FontPath.REGULAR.getPath(), BitmapFont.class);
		assetManager.load(FontPath.THIN.getPath(), BitmapFont.class);
		assetManager.finishLoading();
		mainScreenSkin = new Skin(assetManager.get(AtlasPath.MAIN_SCREEN.getPath(), TextureAtlas.class));
	}

	public void unloadMainScreen() {
		assetManager.unload(AtlasPath.MAIN_SCREEN.getPath());
		assetManager.unload(BackgroundPath.MAIN_SCREEN.getPath());
		assetManager.unload(MusicPath.MAIN_SCREEN.getPath());
		assetManager.unload(FontPath.REGULAR.getPath());
		assetManager.unload(FontPath.THIN.getPath());
		mainScreenSkin = null;
	}

	public void loadPrototypeLevel() {
		loadLevelAssets(AtlasPath.PROTOTYPE, BackgroundPath.LEVEL_THREE, MusicPath.LEVEL_ONE);
	}

	public void unloadPrototypeLevel() {
		unloadLevelAssets(AtlasPath.PROTOTYPE, BackgroundPath.LEVEL_THREE, MusicPath.LEVEL_ONE);
	}

	private void loadLevelAssets(final AtlasPath atlas, final BackgroundPath background, final MusicPath music) {
		loadCoreLevelAssets();
		loadEnemies();
		assetManager.load(atlas.getPath(), TextureAtlas.class);
		assetManager.load(background.getPath(), Texture.class);
		assetManager.load(music.getPath(), Music.class);
		assetManager.load(AtlasPath.MAIN_SCREEN.getPath(), TextureAtlas.class);
		assetManager.finishLoading();

		coreSkin = new Skin(assetManager.get(AtlasPath.CORE.getPath(), TextureAtlas.class));
		levelSkin = new Skin(assetManager.get(atlas.getPath(), TextureAtlas.class));
		enemySkin = new Skin(assetManager.get(AtlasPath.ENEMY.getPath(), TextureAtlas.class));
	}

	private void unloadLevelAssets(final AtlasPath atlas, final BackgroundPath background, final MusicPath music) {
		unloadCoreLevelAssets();
		unloadEnemies();
		assetManager.unload(atlas.getPath());
		assetManager.unload(background.getPath());
		assetManager.unload(music.getPath());
		assetManager.unload(AtlasPath.MAIN_SCREEN.getPath());

		coreSkin = null;
		levelSkin = null;
		enemySkin = null;
	}

	private void loadCoreLevelAssets() {
		assetManager.load(AtlasPath.CORE.getPath(), TextureAtlas.class);
		for (final SoundFxPath soundFx : SoundFxPath.values()) {
			assetManager.load(soundFx.getPath(), Sound.class);
		}
		assetManager.load(FontPath.THIN.getPath(), BitmapFont.class);
	}

	private void unloadCoreLevelAssets() {
		assetManager.unload(AtlasPath.CORE.getPath());
		for (final SoundFxPath soundFx : SoundFxPath.values()) {
			assetManager.unload(soundFx.getPath());
		}
		assetManager.unload(FontPath.THIN.getPath());
	}

	private void loadEnemies() {
		assetManager.load(AtlasPath.ENEMY.getPath(), TextureAtlas.class);
	}

	private void unloadEnemies() {
		assetManager.unload(AtlasPath.ENEMY.getPath());
	}

	public void loadLevelOne() {
		loadLevelAssets(AtlasPath.LEVEL_ONE, BackgroundPath.LEVEL_ONE, MusicPath.LEVEL_ONE);
	}

	public void unloadLevelOne() {
		unloadLevelAssets(AtlasPath.LEVEL_ONE, BackgroundPath.LEVEL_ONE, MusicPath.LEVEL_ONE);
	}

	public void loadLevelTwo() {
		loadLevelAssets(AtlasPath.LEVEL_TWO, BackgroundPath.LEVEL_TWO, MusicPath.LEVEL_TWO);
	}

	public void unloadLevelTwo() {
		unloadLevelAssets(AtlasPath.LEVEL_TWO, BackgroundPath.LEVEL_TWO, MusicPath.LEVEL_TWO);
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

	public Drawable getResumeButton() {
		return coreSkin.getDrawable("resumeButton");
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

	public Skin getEnemySkin() {
		return enemySkin;
	}

	public Sprite getBackground() {
		return new Sprite(assetManager.get(BackgroundPath.LEVEL_THREE.getPath(), Texture.class));
	}

	public Sprite getMainScreenBackground() {
		return new Sprite(assetManager.get(BackgroundPath.MAIN_SCREEN.getPath(), Texture.class));
	}

	public BitmapFont getMainScreenButtonFont() {
		return assetManager.get(FontPath.THIN.getPath(), BitmapFont.class);
	}

	public BitmapFont getMainScreenTitleFont() {
		return assetManager.get(FontPath.REGULAR.getPath(), BitmapFont.class);
	}

	public Sprite getShieldSprite() {
		return new Sprite(assetManager.get("atlas/tmp/spr_shield.png", Texture.class));
	}
}