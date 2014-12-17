package com.vertextrigger;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;

import java.util.List;
import java.util.Set;

/**
 * Loads & stores assets, i.e. textures, bitmapfonts, sound effects, music, etc.
 */
public class Assets {

    private static AssetManager assetManager;

    private static List<Set<AssetDescriptor<?>>> levelAssets;

	/**
	 * Initialise AssetManager before using this class, to be used for the rest
	 * of them game.
	 * Initialise containers of assets to be loaded or unloaded
	 * for each screen/level.
	 */
	public static void initialiseAssetManager() {
		// Create asset manager only if one does not already exist
        if (assetManager == null) {
            assetManager = new AssetManager();
        }
		// Create container of main menu assets to be loaded
		// Create container of all assets not in main menu to be unloaded
		// Create container of level one assets to be loaded
		// Create container of all assets not in level one to be unloaded
		// Create container of level two assets to be loaded
		// Create container of all assets not in level two to be unloaded
	}

	/**
	 * Loads assets for MainScreen
	 */
	public static void loadMainScreenAssets() {
		// For each main menu asset
				// Load asset to be stored in asset manager
		// For each asset not in main menu
				// Unload asset from asset manager
	}

	/**
	 * Loads assets for a level screen
	 */
	public static void loadLevelAssets(int level) {
		// for each level asset we load the asset to be stored in asset manager
		// for each asset not in level we unload the asset from the asset manager
        // the for loop iterates through levelAssets, a list of sets of level assets

        for (int i = 1; i <= levelAssets.size(); i++) {
            if (i == level) {
                for (AssetDescriptor<?> asset : levelAssets.get(i)) {
                    assetManager.load(asset);
                }
            }
            else {
                for (AssetDescriptor<?> asset : levelAssets.get(i)) {
                    assetManager.unload(asset.fileName);
                }
            }
        }
	}

    /**
     * Loads assets for the Heads-Up Display and the Controls for each level
     */
    private static void loadHudAndControls() {
    }

	/**
	 * Dispose of assets once they're no longer needed
	 */
	public static void dispose() {
		// Release all assets and asset manager itself
        assetManager.dispose();
	}
}