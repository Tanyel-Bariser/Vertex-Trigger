package com.vertextrigger.entities.player;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.factories.PlayerAnimationFactory;

@RunWith(MockitoJUnitRunner.class)
public class PlayerAnimatorTest {
	PlayerAnimator animator;
	@Mock Body player;
	@Mock PlayerAnimationFactory factory;
	@Mock Animation animation;
	float movingLeft;
	float movingRight;
	@Mock Sprite sprite;

	@Before
	public void setUp() throws Exception {
		setUpAnimationFactory();
		when(animation.getKeyFrame(anyFloat())).thenReturn(sprite);
		animator = new PlayerAnimator(factory);
		movingLeft = -10;
		movingRight = 10;
	}
	
	private void setUpAnimationFactory() {
		when(factory.getRun()).thenReturn(animation);
		when(factory.getStanding()).thenReturn(animation);
		when(factory.getRise()).thenReturn(animation);
		when(factory.getFall()).thenReturn(animation);
		when(factory.getDeath()).thenReturn(animation);
	}

	@Test
	public void givenSpriteFacingRightWhenPlayerMovingLeftThenSpriteShouldFlipLeft() {
		when(sprite.isFlipX()).thenReturn(false);
		animator.setHorizontalMovement(movingLeft);
		animator.getUpdatedSprite(0);
		verify(sprite).flip(true, false);
	}

	@Test
	public void givenSpriteFacingLeftWhenPlayerIsMovingLeftThenSpriteShouldNotFlipRight() {
		when(sprite.isFlipX()).thenReturn(true);
		animator.setHorizontalMovement(movingLeft);
		animator.getUpdatedSprite(0);
		verify(sprite, never()).flip(true, false);
	}
	
	@Test
	public void whenPlayerUpdatedDeltaIsAddedToFrame() {
		
		animator.getUpdatedSprite(3f);
	}	
}