package com.vertextrigger.util;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
	
	Controller controllerDesktop, controllerAndroid;
	@Mock Screen screen;
	@Mock Stage stage;

	@Before
	public void setUp() throws Exception {
		controllerDesktop = new Controller(screen, stage) {
			@Override
			void setDeviceType() {
				isAndroidDevice = false;
			}
		};
		
		controllerAndroid = new Controller(screen, stage) {
			@Override
			void setDeviceType() {
				isAndroidDevice = true;
			}
		};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void whenLaunchedOnDesktopRunningOnAndroidIsFalse() {
		assertFalse(controllerDesktop.isAndroidDevice());
	}

	@Test
	public void whenLaunchedOnAndroidRunningOnAndroidIsTrue() {
		assertTrue(controllerAndroid.isAndroidDevice());
	}

}