package com.vertextrigger.entities.player;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.entities.*;

@RunWith(MockitoJUnitRunner.class)
public class AnimatorTest {
	Animator animator;
	@Mock Body player;
	@Mock AnimationSet animationSet;
	@Mock Animation animation;
	float movingLeft;
	float movingRight;
	@Mock Sprite sprite;
	float angle = 3f;
	float delta = 6.41f;
	Vector2 position = new Vector2(2,2);
	@Mock Entity entity;

	@Before
	public void setUp() throws Exception {
		setUpAnimationFactory();
		when(animation.getKeyFrame(anyFloat())).thenReturn(sprite);
		animator = new Animator(animationSet);
		when(entity.getOffsetX()).thenReturn(2.0f);
		when(entity.getOffsetY()).thenReturn(2.0f);
		animator.setEntity(entity);
		movingLeft = -0.31f;
		movingRight = .31f;
	}
	
	private void setUpAnimationFactory() {
		when(animationSet.getMoving()).thenReturn(animation);
		when(animationSet.getStanding()).thenReturn(animation);
		when(animationSet.getRising()).thenReturn(animation);
		when(animationSet.getFalling()).thenReturn(animation);
		when(animationSet.getDeath()).thenReturn(animation);
	}

	@Test
	public void givenSpriteFacingRightWhenPlayerMovingLeftThenSpriteShouldFlipLeft() {
		when(sprite.isFlipX()).thenReturn(false);
		animator.setHorizontalMovement(movingLeft);
		animator.getUpdatedSprite(delta, angle, position);
		verify(sprite).flip(true, false);
	}

	@Test
	public void givenSpriteFacingLeftWhenPlayerIsMovingLeftThenSpriteShouldNotFlipRight() {
		when(sprite.isFlipX()).thenReturn(true);
		animator.setHorizontalMovement(movingLeft);
		animator.getUpdatedSprite(delta, angle, position);
		verify(sprite, never()).flip(true, false);
	}
	
	@Test
	public void whenPlayerUpdatedDeltaIsAddedToFrame() {
		animator.getUpdatedSprite(delta, angle, position);
		verify(animation).getKeyFrame(delta);
	}
	
	@Test
	public void whenPlayerIsMovingMoreThanThresholdThenPlayerShouldMoveRight() {
		animator.setHorizontalMovement(movingRight);
		assertEquals(false, animator.isMovingLeft());
	}
	
	@Test
	public void whenPlayerIsMovingLessThanThresholdThenPlayerShouldMoveLeft() {
		animator.setHorizontalMovement(movingLeft);
		assertEquals(true, animator.isMovingLeft());
	}
	
	@Test
	public void whenPlayerIsNotMovingOutsideThresholdThenPlayerShouldNotChangeDirection() {
		boolean playerDirectionStaysSame = animator.isMovingLeft();
		animator.setHorizontalMovement(0f);
		assertEquals(playerDirectionStaysSame, animator.isMovingLeft());
		animator.setHorizontalMovement(0.3f);
		assertEquals(playerDirectionStaysSame, animator.isMovingLeft());
		animator.setHorizontalMovement(-0.3f);
		assertEquals(playerDirectionStaysSame, animator.isMovingLeft());
	}
}