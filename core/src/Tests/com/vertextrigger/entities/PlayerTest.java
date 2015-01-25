package com.vertextrigger.entities;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.entities.Player;
import com.vertextrigger.screens.GameScreen;
import com.vertextrigger.util.Coordinate;

public class PlayerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPlayerPositionInitialisation() {
		World mockWorld = mock(World.class);
		when(mockWorld.createBody(any(BodyDef.class))).thenReturn(any(Body.class));
		Coordinate initialPosition = new Coordinate(-5, 2);
		GameScreen mockGameScreen = mock(GameScreen.class);
		Player player = new Player(mockWorld, initialPosition, mockGameScreen);
		ArgumentCaptor<BodyDef> arg = ArgumentCaptor.forClass(BodyDef.class);
		verify(mockWorld, atLeastOnce()).createBody(arg.capture());
		BodyDef bodyDef = arg.getValue();
		assertEquals(new Vector2(initialPosition.x, initialPosition.y), bodyDef.position);
	}
}