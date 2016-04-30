package com.vertextrigger.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.AnimationSet;
import com.vertextrigger.entities.enemy.*;
import com.vertextrigger.factory.animationfactory.PokerAnimationFactory;
import com.vertextrigger.factory.bodyfactory.PokerBodyFactory;

public class EnemyFactory {
	public static AbstractEnemy createPokerEnemy(final World world, final Vector2 initialPosition) {
		// GameObjectSize enemySize = GameObjectSize.createPokerSize();
		final PokerBodyFactory factory = new PokerBodyFactory();
		final Body body = factory.createPokerBody(world, initialPosition);
		final AnimationSet anims = new PokerAnimationFactory().createAnimationSet();
		/*
		 * sprite.setPosition(initialPosition.x - sprite.getWidth() / 2, initialPosition.y - sprite.getHeight() / 2);
		 */final Array<Vector2> coordinates = new Array<>();
		coordinates.add(initialPosition);
		return new Poker(coordinates, body, anims);

	}

}