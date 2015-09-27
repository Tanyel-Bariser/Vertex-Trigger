package com.vertextrigger.factory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.*;
import com.vertextrigger.entities.enemy.AbstractEnemy;
import com.vertextrigger.entities.enemy.Poker;
import com.vertextrigger.factory.bodyfactory.PokerBodyFactory;
import com.vertextrigger.util.*;

public class EnemyFactory {
	public static AbstractEnemy createPokerEnemy(World world, Vector2 initialPosition) {
		GameObjectSize enemySize = GameObjectSize.createPokerSize();
		PokerBodyFactory factory = new PokerBodyFactory();
		Body body = factory.createPokerBody(world, initialPosition);
		Sprite sprite = new SpriteFactory().createEnemy("pokerMad", enemySize.getSpriteWidth() , enemySize.getSpriteHeight());
		sprite.setPosition(initialPosition.x - sprite.getWidth() / 2, 
				initialPosition.y - sprite.getHeight() / 2);
		Array<Vector2> coordinates = new Array<>();
		coordinates.add(initialPosition);
		return new Poker(coordinates, body, sprite);
	}
	
	
}