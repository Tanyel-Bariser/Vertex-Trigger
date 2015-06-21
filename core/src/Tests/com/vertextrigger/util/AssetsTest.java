package com.vertextrigger.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

@RunWith(MockitoJUnitRunner.class)
public class AssetsTest {
	@Mock AssetManager manager;
	Assets assets;
	
	@Before
	public void setUp() throws Exception {
		assets = new Assets(manager);
	}

	@Test
	public void givenInitializationOfAssetsAssetMangerShouldBeNotNull() {
		assertNotNull(assets.getAssetManager());
	}
	
	@Test
	public void returnsAssetManager(){
		Object manager = assets.getAssetManager();
		assertTrue(manager instanceof AssetManager);
	}

	@Test
	public void loadMainScreenClearsAssetManager() {
		assets.loadMainScreen();
		verify(manager).clear();
	}
	
	@Test
	public void loadMainScreenLoadsMainScreenAtlas() {
		assets.loadMainScreen();
		verify(manager).load(ATLASES.MAIN_SCREEN.getPath(), TextureAtlas.class);
	}
	
	@Test
	public void loadMainScreenLoadsMainScreenBackground() {
		assets.loadMainScreen();
		verify(manager).load(BACKGROUND.MAIN_SCREEN.getPath(), Texture.class);
	}
	
	@Test
	public void loadMainScreenLoadsMainScreenMusic() {
		assets.loadMainScreen();
		verify(manager).load(MUSIC.MAIN_SCREEN.getPath(), Music.class);
	}
	
	@Test
	public void loadMainScreenLoadsMainScreenFont() {
		assets.loadMainScreen();
		verify(manager).load(FONT.REGULAR.getPath(), BitmapFont.class);
	}

	@Test
	public void loadLevelOneClearsAssets() {
		assets.loadLevelOne();
		verify(manager).clear();
	}
	
	@Test
	public void loadLevelOneLoadsCoreAtlas() {
		assets.loadLevelOne();
		verify(manager).load(ATLASES.CORE.getPath(), TextureAtlas.class);
	}
	
	@Test
	public void loadLevelOneLoadsAllSoundEffects() {
		assets.loadLevelOne();
		for (SOUND_FX soundFx : SOUND_FX.values()) {
			verify(manager).load(soundFx.getPath(), Sound.class);
		}
	}
	
	@Test
	public void loadLevelOneLoadsThinFont() {
		assets.loadLevelOne();
		verify(manager).load(FONT.THIN.getPath(), BitmapFont.class);
	}
	
	@Test
	public void loadLevelOneLoadsLevelOneAtlas() {
		assets.loadLevelOne();
		verify(manager).load(ATLASES.LEVEL_ONE.getPath(), TextureAtlas.class);
	}
	
	@Test
	public void loadLevelOneLoadsLevelOneBackground() {
		assets.loadLevelOne();
		verify(manager).load(BACKGROUND.LEVEL_ONE.getPath(), Texture.class);
	}
	
	@Test
	public void loadLevelOneLoadsLevelOneMusic() {
		assets.loadLevelOne();
		verify(manager).load(MUSIC.LEVEL_ONE.getPath(), Music.class);
	}

	@Test
	public void loadLevelTwoClearsAssets() {
		assets.loadLevelTwo();
		verify(manager).clear();
	}
	
	@Test
	public void loadLevelTwoLoadsCoreAtlas() {
		assets.loadLevelTwo();
		verify(manager).load(ATLASES.CORE.getPath(), TextureAtlas.class);
	}
	
	@Test
	public void loadLevelTwoLoadsAllSoundEffects() {
		assets.loadLevelTwo();
		for (SOUND_FX soundFx : SOUND_FX.values()) {
			verify(manager).load(soundFx.getPath(), Sound.class);
		}
	}
	
	@Test
	public void loadLevelTwoLoadsThinFont() {
		assets.loadLevelTwo();
		verify(manager).load(FONT.THIN.getPath(), BitmapFont.class);
	}
	
	@Test
	public void loadLevelTwoLoadsLevelTwoAtlas() {
		assets.loadLevelTwo();
		verify(manager).load(ATLASES.LEVEL_TWO.getPath(), TextureAtlas.class);
	}
	
	@Test
	public void loadLevelTwoLoadsLevelTwoBackground() {
		assets.loadLevelTwo();
		verify(manager).load(BACKGROUND.LEVEL_TWO.getPath(), Texture.class);
	}
	
	@Test
	public void loadLevelTwoLoadsLevelTwoMusic() {
		assets.loadLevelTwo();
		verify(manager).load(MUSIC.LEVEL_TWO.getPath(), Music.class);
	}

//	@Test
//	public void getLeftButtonReturnsLeftButtonDrawable() {
//		Drawable left = assets.getLeftButton();
//		assertNotNull(left);
//	}
}