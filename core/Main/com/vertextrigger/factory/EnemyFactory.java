package com.vertextrigger.factory;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.ai.SteerableBody;
import com.vertextrigger.entities.*;
import com.vertextrigger.entities.enemy.*;
import com.vertextrigger.factory.animationfactory.*;
import com.vertextrigger.factory.bodyfactory.*;
import com.vertextrigger.factory.entityfactory.BulletFactory;
import com.vertextrigger.screen.AbstractGameScreen;

public class EnemyFactory {
	public static Poker createPokerEnemy(final World world, final Vector2 initialPosition) {
		final PokerBodyFactory factory = new PokerBodyFactory();
		final Body body = factory.createPokerBody(world, initialPosition);
		final AnimationSet anims = new PokerAnimationFactory().createAnimationSet();
		return new Poker(body, anims);
	}

	public static Bee createBeeEnemy(final World world, final Vector2 initialPosition, final Steerable<Vector2> target,
			final AbstractGameScreen screen, final MagnetFlowField magnetFlowField) {
		final float zeroLinearSpeedThreshold = 0.1f;
		final float maxLinearSpeed = 0.1f;
		final float maxLinearAcceleration = 1f;
		final float maxAngularSpeed = 30f;
		final float maxAngularAcceleration = 30f;

		final BeeBodyFactory factory = new BeeBodyFactory();
		final Body body = factory.createBeeBody(world, initialPosition);
		final AnimationSet anims = new BeeAnimationFactory().createAnimationSet();

		return new Bee(body, anims, target, new BulletFactory(world, magnetFlowField), screen, new SteerableBody(body, maxLinearAcceleration,
				maxLinearSpeed, maxAngularAcceleration, maxAngularSpeed, zeroLinearSpeedThreshold, 10, false));
	}
}