package com.vertextrigger.factory.entityfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.ai.*;
import com.vertextrigger.entities.*;
import com.vertextrigger.entities.player.*;
import com.vertextrigger.factory.animationfactory.PlayerAnimationFactory;
import com.vertextrigger.factory.bodyfactory.PlayerBodyFactory;
import com.vertextrigger.screen.AbstractGameScreen;

public class PlayerFactory {
	public static Player createPlayer(final World world, final Vector2 initialPosition, final AbstractGameScreen screen,
			final MagnetFlowField magnetFlowField) {
		final float maxAngularAcceleration = 10;
		final float maxAngularSpeed = 10;
		final float maxLinearAcceleration = 20;
		final float maxLinearSpeed = 100;
		final float zeroLinearSpeedThreshold = 0.1f;

		final Body playerBody = new PlayerBodyFactory().createPlayerBody(world, initialPosition);
		final Gun gun = createGun(world, screen, magnetFlowField);
		final Animator animator = new AnimatorImpl(new PlayerAnimationFactory().createAnimationSet());
		final SteerableBody steerableBody = new SteerableBody(playerBody, maxLinearAcceleration, maxLinearSpeed, maxAngularAcceleration,
				maxAngularSpeed, zeroLinearSpeedThreshold, 10, false);
		final MagnetBehaviour magnetBehaviour = new MagnetBehaviour(steerableBody, magnetFlowField);
		return new Player(initialPosition, playerBody, gun, animator, steerableBody, magnetBehaviour);
	}

	private static Gun createGun(final World world, final AbstractGameScreen screen, final MagnetFlowField magnetFlowField) {
		return new Gun(screen, new BulletFactory(world, magnetFlowField));
	}
}