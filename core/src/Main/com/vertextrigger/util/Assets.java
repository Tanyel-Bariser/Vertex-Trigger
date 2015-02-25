package com.vertextrigger.util;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Loads & stores assets, i.e. textures, bitmapfonts, sound effects, music, etc.
 */
public class Assets {
    final public static int LEVEL_ONE = 1;
    final public static int LEVEL_TWO = 2;
    // etc

    private static AssetManager assetManager;
    private static Map<Integer, AssetDescriptor<TextureAtlas>> levelTextures;
    private static List<AssetDescriptor<?>> music, sfx, hud, mainScreen, font;

	/**
	 * Initialise AssetManager before using this class, to be used for the rest of the game
	 * Initialise containers of assets to be loaded or unloaded for each screen/level
	 */
	public static void initialiseAssetManager() {
		// Create asset manager only if one does not already exist
        if (assetManager == null) {
            assetManager = new AssetManager();
        }

        levelTextures = new HashMap<>();
        //levelTextures.add(AssetFilenames.LEVEL_ONE_ATLAS);
        //levelTextures.add(AssetFilenames.LEVEL_TWO_ATLAS);

	}

	/**
	 * Loads assets for MainScreen
	 */
	public static void loadMainScreenAssets() {
		// for each main menu asset we load the asset to be stored in asset manager
		// for each asset not in main menu we unload asset from the asset manager
        for (AssetDescriptor<?> mainScreenAsset : mainScreen) {
            assetManager.load(mainScreenAsset);
        }
	}

    /**
     * Loads assets for a particular level
     *
     * @param level the number of the level assets we wish to load
     */
	public static void loadLevelAssets(int level) {
		// unload currently loaded assets
        assetManager.clear();

        assetManager.load(levelTextures.get(level));
	}

	/**
	 * Dispose of assets once they're no longer needed
	 */
	public static void dispose() {
		// release all assets and asset manager itself
        assetManager.dispose();
	}
}