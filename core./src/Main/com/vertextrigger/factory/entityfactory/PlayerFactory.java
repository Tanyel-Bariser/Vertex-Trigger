package com.vertextrigger.factory.entityfactory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.entities.Animator;
import com.vertextrigger.entities.player.BulletPool;
import com.vertextrigger.entities.player.Gun;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.factory.animationfactory.PlayerAnimationFactory;
import com.vertextrigger.factory.bodyfactory.PlayerBodyFactory;
import com.vertextrigger.screen.AbstractGameScreen;

public class PlayerFactory {
	public static Player createPlayer(World world, Vector2 initialPosition, AbstractGameScreen screen) {
		Body playerBody = new PlayerBodyFactory().createPlayerBody(world, initialPosition);
		Gun gun = createGun(world, screen);
		Animator animator = new Animator(new PlayerAnimationFactory().createAnimationSet());
		return new Player(initialPosition, playerBody, gun, animator);
	}
	
	private static Gun createGun(World world, AbstractGameScreen screen) {
		BulletPool pool = new BulletPool(world);
		return new Gun(screen, pool);
	}
}