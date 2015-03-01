package com.vertextrigger.entities.player;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
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
	
	@Test
	public void whenSetPlayerAngleWith5ThenPlayerAngleShouldBe5() {
		player.setAngle(5);
		assertEquals((int) 5, (int) body.getAngle());
	}
	
	@Test
	public void whenSetPlayerAngleThenPositionShouldNotChange() {
		Vector2 expected = body.getPosition();
		player.setAngle(3);
		Vector2 actual = body.getPosition();
		assertEquals(expected, actual);
	}
	
	@Test
	public void whenPlayerJumpThenShouldApplyUpwardImpulseByJUMP_POWER() {
		Body body = mock(Body.class);
		player = new Player(world, initialPosition, gameScreen, body);
		player.setJumpAbility(true);
		player.jump();
		ArgumentCaptor<Vector2> jump = ArgumentCaptor.forClass(Vector2.class);
		verify(body).applyLinearImpulse(jump.capture(), any(Vector2.class), eq(true));
		assertEquals(new Vector2(0, Player.JUMP_POWER), jump.getValue());
	}
	
	@Test
	public void givenPlayerCannotJumpThenShouldNotApplyUpwardImpulse() {
		Body body = mock(Body.class);
		player = new Player(world, initialPosition, gameScreen, body);
		player.setJumpAbility(false);
		player.jump();
		verify(body, never()).applyLinearImpulse(any(Vector2.class), any(Vector2.class), eq(true));
	}
	
	@Test
	public void givenPlayerMovingRightWhenUpdatedThenShouldMovePlayerRightAtMOVEMENT_SPEEDByDelta() {
		Body body = mock(Body.class);
		float verticalSpeed = 12f;
		when(body.getLinearVelocity()).thenReturn(new Vector2(0, verticalSpeed));
		player = new Player(world, initialPosition, gameScreen, body);
		
		player.moveRight();
		
		float delta = 0.1f;
		player.update(delta);
		verify(body).setLinearVelocity(Player.MOVEMENT_SPEED * delta, verticalSpeed * delta);
	}
}