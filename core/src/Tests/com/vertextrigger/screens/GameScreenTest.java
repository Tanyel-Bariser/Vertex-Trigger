package com.vertextrigger.screens;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vertextrigger.levelbuilders.LevelBuilder;
import com.vertextrigger.main.VertexTrigger;

@RunWith(MockitoJUnitRunner.class)
public class GameScreenTest {
	GameScreen screen;
	@Mock VertexTrigger vertexTrigger;
	@Mock LevelBuilder levelBuilder;
	
	@Before
	public void setUp() throws Exception {
		screen = new GameScreen(vertexTrigger, levelBuilder);
	}

	@Test
	public void test() {
		
	}
}