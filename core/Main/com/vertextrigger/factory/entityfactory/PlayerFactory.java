package com.vertextrigger.factory.entityfactory;

import com.badlogic.gdx.ai.steer.behaviors.FollowFlowField.FlowField;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.ai.SteerableBody;
import com.vertextrigger.entities.*;
import com.vertextrigger.entities.player.*;
import com.vertextrigger.factory.animationfactory.PlayerAnimationFactory;
import com.vertextrigger.factory.bodyfactory.PlayerBodyFactory;
import com.vertextrigger.screen.AbstractGameScreen;

public class PlayerFactory {
	public static Player createPlayer(final World world, final Vector2 initialPosition, final AbstractGameScreen screen,
			final Array<FlowField<Vector2>> magnetFlowFields) {
		final float maxAngularAcceleration = 10;
		final float maxAngularSpeed = 10;
		final float maxLinearAcceleration = 5;
		final float maxLinearSpeed = 10;
		final float zeroLinearSpeedThreshold = 0.1f;

		final Body playerBody = new PlayerBodyFactory().createPlayerBody(world, initialPosition);
		final Gun gun = createGun(world, screen, magnetFlowFields);
		final Animator animator = new AnimatorImpl(new PlayerAnimationFactory().createAnimationSet());
		return new Player(initialPosition, playerBody, gun, animator, new SteerableBody(playerBody, maxLinearAcceleration, maxLinearSpeed,
				maxAngularAcceleration, maxAngularSpeed, zeroLinearSpeedThreshold, 10, false));
	}

	private static Gun createGun(final World world, final AbstractGameScreen screen, final Array<FlowField<Vector2>> magnetFlowFields) {
		return new Gun(screen, new BulletFactory(world, magnetFlowFields));
	}
}