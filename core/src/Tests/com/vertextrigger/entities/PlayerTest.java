package com.vertextrigger.entities;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.entities.Player;
import com.vertextrigger.screens.GameScreen;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {
	Player player;
	World world;
	@Mock GameScreen gameScreen;

	@Before
	public void setUp() throws Exception {
		buildWorld();
		player = new Player(world, new Vector2(), gameScreen);
	}
	
	private void buildWorld() {
		Vector2 gravity = new Vector2(0, -9.81f);
		boolean doNotSimulateInactiveBodies = true;
		world = new World(gravity, doNotSimulateInactiveBodies);
	}

	@Test
	public void test() {
		
	}
}