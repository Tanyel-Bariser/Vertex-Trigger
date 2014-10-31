package com.vertextrigger;

/**
 * Loads & stores assets, i.e. textures, bitmapfonts, sound effects, music, etc.
 */
public class Assets {

	/**
	 * Initialise AssetManager before using this class, to be used for the rest
	 * of them game.
	 * Initialise containers of assets to be loaded or unloaded
	 * for each screen/level.
	 */
	public static void initialiseAssetManager() {
		// Create asset manager
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
	 * Loads assets for LevelOneScreen
	 */
	public static void loadLevelOneAssets() {
		// For each level one asset
				// Load asset to be stored in asset manager
		// For each asset not in level one
				// Unload asset from asset manager
	}

	/**
	 * Loads assets for LevelTwoScreen
	 */
	public static void loadLevelTwoAssets() {
		// For each level two asset
				// Load asset to be stored in asset manager
		// For each asset not in level two
				// Unload asset from asset manager
	}

	/**
	 * Dispose of assets once they're no longer needed
	 */
	public static void dispose() {
		// Release all assets and asset manager
	}
}