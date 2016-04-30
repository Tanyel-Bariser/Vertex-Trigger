package com.vertextrigger.screens;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vertextrigger.levelbuilder.AbstractLevelBuilder;
import com.vertextrigger.main.VertexTrigger;
import com.vertextrigger.screen.AbstractGameScreen;

@RunWith(MockitoJUnitRunner.class)
public class GameScreenTest {
	AbstractGameScreen screen;
	@Mock
	VertexTrigger vertexTrigger;
	@Mock
	AbstractLevelBuilder levelBuilder;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
	}
}