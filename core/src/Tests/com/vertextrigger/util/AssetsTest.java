package com.vertextrigger.util;

import static org.junit.Assert.*;
import org.junit.*;
import com.badlogic.gdx.assets.AssetManager;

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
	public void returnsAssetManager(){
		Object manager = assets.getAssetManager();
		assertTrue(manager instanceof AssetManager);
	}
}