package com.vertextrigger.entities.player;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.entities.BodyBuilder;
import com.vertextrigger.entities.BodyFactory;
import com.vertextrigger.screens.GameScreen;
import com.vertextrigger.util.ContactBody;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {
	Player player;
	World world;
	Body body;
	Vector2 initialPosition;
	@Mock GameScreen gameScreen;
	@Mock PlayerAnimator animator;
	@Mock Sprite sprite;
	@Mock Gun gun;

	@Before
	public void setUp() throws Exception {
		buildWorld();
		initialPosition = new Vector2(-5, 8);
		BodyBuilder builder = new BodyBuilder(world, initialPosition, ContactBody.PLAYER);
		body = BodyFactory.getBody(builder);
		player = new Player(world, initialPosition, gameScreen, body, gun, animator);
		when(animator.getUpdatedSprite(anyFloat(), anyFloat(), 
				(Vector2) anyObject())).thenReturn(sprite);
		when(sprite.isFlipX()).thenReturn(true);
	}
	
	private void buildWorld() {
		Vector2 gravity = new Vector2(0, -9.81f);
		boolean doNotSimulateInactiveBodies = true;
		world = new World(gravity, doNotSimulateInactiveBodies);
	}

	@Test
	public void givenPlayerNotInInitialPositionWhenPlayerDiedThenResetsToInitialPosition() {
		setPlayerPositionToNonInitialPosition();
		player.diedResetPosition();
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
		when(body.getWorldCenter()).thenReturn(new Vector2(0,0));
		player = new Player(world, initialPosition, gameScreen, body, gun, animator);
		player.setCanJump();
		player.jump();
		ArgumentCaptor<Float> jump = ArgumentCaptor.forClass(Float.class);
		verify(body).applyLinearImpulse(anyFloat(), jump.capture(), anyFloat(), anyFloat(), eq(true));
		assertEquals((float)Player.JUMP_POWER, (float)jump.getValue(), 1f);
	}
	
	@Test
	public void givenPlayerCannotJumpThenShouldNotApplyUpwardImpulse() {
		Body body = mock(Body.class);
		player = new Player(world, initialPosition, gameScreen, body, gun, animator);
		player.setCannotJump();
		player.jump();
		verify(body, never()).applyLinearImpulse(any(Vector2.class), any(Vector2.class), eq(true));
	}
	
	@Test
	public void givenPlayerMovingRightWhenUpdatedThenShouldMovePlayerRightAtMOVEMENT_SPEEDByDelta() {
		Body body = mock(Body.class);
		when(body.getPosition()).thenReturn(new Vector2(0,0));
		float verticalSpeed = 12f;
		when(body.getLinearVelocity()).thenReturn(new Vector2(0, verticalSpeed));
		player = new Player(world, initialPosition, gameScreen, body, gun, animator);
		
		player.moveRight();
		
		float delta = 0.1f;
		player.update(delta);
		verify(body).setLinearVelocity(Player.MOVEMENT_SPEED * delta, verticalSpeed * delta);
	}
	
	@Test
	public void playerAnimatorIsSetUpCorrectlyDuringUpdate() {
		player.update(1);
		verify(gun).freeExpiredBullets();
		verify(animator).setHorizontalMovement(anyFloat());
	}
	
	@Test
	public void givenPlayerRisingThenShouldSetRisingAnimator() {
		body.applyLinearImpulse(new Vector2(0, 50), body.getPosition(), true);
		player.update(1);
		verify(animator).setAnimationRising();
	}
}