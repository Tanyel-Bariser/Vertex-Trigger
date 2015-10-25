package com.vertextrigger.factory.entityfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.entities.Animator;
import com.vertextrigger.entities.player.*;
import com.vertextrigger.factory.animationfactory.PlayerAnimationFactory;
import com.vertextrigger.factory.bodyfactory.PlayerBodyFactory;
import com.vertextrigger.screen.AbstractGameScreen;

public class PlayerFactory {
	public static Player createPlayer(World world, Vector2 initialPosition, AbstractGameScreen screen) {
		Body playerBody = new PlayerBodyFactory().createPlayerBody(world, initialPosition);
		Gun gun = new Gun(world, screen);
		Animator animator = new Animator(new PlayerAnimationFactory().createAnimationSet());
		return new Player(world, initialPosition, screen, playerBody, gun, animator);
	}
}
