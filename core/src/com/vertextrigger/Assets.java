package com.vertextrigger;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Loads & stores assets, i.e. textures, bitmapfonts, sound effects, music, etc.
 */
public class Assets {

    private static AssetManager assetManager;

    private static HashSet<AssetDescriptor<?>> levelOneAssets, levelTwoAssets, menuScreenAssets, hudAndControlAssets;

    private static List<Set<AssetDescriptor<?>>> levelAssets;

	/**
	 * Initialise AssetManager before using this class, to be used for the rest of the game
	 * Initialise containers of assets to be loaded or unloaded for each screen/level
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

        levelAssets = new ArrayList<Set<AssetDescriptor<?>>>();

        levelOneAssets = new HashSet<AssetDescriptor<?>>();
        // add level one assets to hashset
        levelTwoAssets = new HashSet<AssetDescriptor<?>>();
        // add level two assets to hashset

        levelAssets.add(levelOneAssets);
        levelAssets.add(levelTwoAssets);
	}

	/**
	 * Loads assets for MainScreen
	 */
	public static void loadMainScreenAssets() {
		// for each main menu asset we load the asset to be stored in asset manager
		// for each asset not in main menu we unload asset from the asset manager
        dispose();

        for (AssetDescriptor<?> asset : menuScreenAssets) {
            assetManager.load(asset);
        }
	}

    /**
     * Loads assets for a particular level
     *
     * @param level the number of the level assets we wish to load
     */
	public static void loadLevelAssets(int level) {
		// for each level asset we load the assets to be stored in asset manager
		// for each asset not in level we unload the assets from the asset manager
        // the for loop iterates through levelAssets, a list of sets of level assets
        dispose();

        level = --level;   // subtract one from level to align with the 0-indexed arraylist collection
        for (int i = 0; i <= levelAssets.size(); i++) {
            if (i == level) {
                for (AssetDescriptor<?> asset : levelAssets.get(i)) {
                    assetManager.load(asset);
                }
            }
        }
	}

    /**
     * Loads assets for the Heads-Up Display and the Controls for each level
     */
    private static void loadHudAndControls() {
        // for each hud and control asset we load the assets to be stored in asset manager
        // we do not dispose of other assets as the hud/controls go on top of a level
        for (AssetDescriptor<?> asset : hudAndControlAssets) {
            assetManager.load(asset);
        }
    }

	/**
	 * Dispose of assets once they're no longer needed
	 */
	public static void dispose() {
		// release all assets and asset manager itself
        assetManager.dispose();
	}
}