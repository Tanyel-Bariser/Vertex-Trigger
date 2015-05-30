package com.vertextrigger.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
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

}