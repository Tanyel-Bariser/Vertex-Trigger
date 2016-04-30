package com.vertextrigger.factory.entityfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.entities.Animator;
import com.vertextrigger.entities.player.*;
import com.vertextrigger.factory.animationfactory.PlayerAnimationFactory;
import com.vertextrigger.factory.bodyfactory.PlayerBodyFactory;
import com.vertextrigger.screen.AbstractGameScreen;

public class PlayerFactory {
	public static Player createPlayer(final World world, final Vector2 initialPosition, final AbstractGameScreen screen) {
		final Body playerBody = new PlayerBodyFactory().createPlayerBody(world, initialPosition);
		final Gun gun = createGun(world, screen);
		final Animator animator = new Animator(new PlayerAnimationFactory().createAnimationSet());
		return new Player(initialPosition, playerBody, gun, animator);
	}

	private static Gun createGun(final World world, final AbstractGameScreen screen) {
		return new Gun(screen, createBulletFactory(world));
	}

	private static BulletFactory createBulletFactory(final World world) {
		return new BulletFactory(world);
	}
}