package com.vertextrigger.entities.player;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.factories.bodyfactories.PlayerBodyFactory;
import com.vertextrigger.screens.GameScreen;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {
	Player player;
	World world;
	Body body;
	Vector2 initialPosition;
	@Mock GameScreen gameScreen;

	@Before
	public void setUp() throws Exception {
		buildWorld();
		initialPosition = new Vector2(-5, 8);
		body = PlayerBodyFactory.getPlayerBody(world, initialPosition);
		player = new Player(world, initialPosition, gameScreen, body);
	}
	
	private void buildWorld() {
		Vector2 gravity = new Vector2(0, -9.81f);
		boolean doNotSimulateInactiveBodies = true;
		world = new World(gravity, doNotSimulateInactiveBodies);
	}

	@Test
	public void givenPlayerNotInInitialPositionWhenPlayerDiedThenResetsToInitialPosition() {
		setPlayerPositionToNonInitialPosition();
		player.died();
		assertEquals(initialPosition, body.getPosition());
	}
	
	private void setPlayerPositionToNonInitialPosition() {
		Vector2 anotherPosition = new Vector2(4, 2);
		body.setTransform(anotherPosition, 0);
		assertNotEquals(initialPosition, body.getPosition());
	}
	
	
}