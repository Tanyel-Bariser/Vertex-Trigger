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
import com.vertextrigger.factories.AnimationFactory;

@RunWith(MockitoJUnitRunner.class)
public class PlayerAnimatorTest {
	PlayerAnimator animator;
	@Mock Body player;
	@Mock AnimationFactory factory;
	@Mock Animation runAnimation;
	Vector2 movingLeft;
	Vector2 movingRight;
	@Mock Sprite sprite;

	@Before
	public void setUp() throws Exception {
		when(factory.getPlayerRun()).thenReturn(runAnimation);
		when(runAnimation.getKeyFrame(anyFloat())).thenReturn(sprite);
		animator = new PlayerAnimator(player, factory);
		movingLeft = new Vector2(-10, 0);
		movingRight = new Vector2(10, 0);
	}

	@Test
	public void givenSpriteFacingRightWhenPlayerMovingLeftThenSpriteShouldFlipLeft() {
		when(sprite.isFlipX()).thenReturn(false);
		when(player.getLinearVelocity()).thenReturn(movingLeft);
		animator.getUpdatedSprite(0);
		verify(sprite).flip(true, false);
	}

	@Test
	public void givenSpriteFacingLeftWhenPlayerIsMovingLeftThenSpriteShouldNotFlipRight() {
		when(sprite.isFlipX()).thenReturn(true);
		when(player.getLinearVelocity()).thenReturn(movingLeft);
		animator.getUpdatedSprite(0);
		verify(sprite, never()).flip(true, false);
	}
}