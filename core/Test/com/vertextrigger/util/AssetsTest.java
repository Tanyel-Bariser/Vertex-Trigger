package com.vertextrigger.util;

import static org.junit.Assert.*;

import org.junit.*;

import com.badlogic.gdx.assets.AssetManager;
import com.vertextrigger.assets.Assets;

public class AssetsTest {
	Assets assets;

	@Before
	public void setUp() throws Exception {
		assets = new Assets();
	}

	@Test
	public void givenInitializationOfAssetsAssetMangerShouldBeNotNull() {
		assertNotNull(assets.getAssetManager());
	}

	@Test
	public void returnsAssetManager() {
		final Object manager = assets.getAssetManager();
		assertTrue(manager instanceof AssetManager);
	}
}