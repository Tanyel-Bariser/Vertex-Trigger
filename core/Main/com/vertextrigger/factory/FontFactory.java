package com.vertextrigger.factory;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.vertextrigger.assets.FontPath;
import com.vertextrigger.main.VertexTrigger;

public class FontFactory {

   private static final AssetManager assetManager = VertexTrigger.ASSETS.getAssetManager();

   public static BitmapFont getMainScreenButtonFont() {
       return assetManager.get(FontPath.THIN.getPath(), BitmapFont.class);
   }

   public static BitmapFont getMainScreenTitleFont() {
       return assetManager.get(FontPath.REGULAR.getPath(), BitmapFont.class);
   }
}
