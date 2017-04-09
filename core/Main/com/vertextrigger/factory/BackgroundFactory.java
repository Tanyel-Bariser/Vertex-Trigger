package com.vertextrigger.factory;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.vertextrigger.assets.BackgroundPath;
import com.vertextrigger.main.VertexTrigger;

public class BackgroundFactory {

    private static final AssetManager assetManager = VertexTrigger.ASSETS.getAssetManager();

    public static Sprite getPrototypeLevelBackground() {
        return new Sprite(assetManager.get(BackgroundPath.LEVEL_THREE.getPath(), Texture.class));
    }

    public static Sprite getMainScreenBackground() {
        return new Sprite(assetManager.get(BackgroundPath.MAIN_SCREEN.getPath(), Texture.class));
    }
}
