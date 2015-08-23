package com.vertextrigger.factories;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.*;
import com.vertextrigger.util.*;

public class EnemyFactory {
	public static Enemy createEnemy(World world) {
		Vector2 initialPosition = new Vector2(0, 0);
		GameObjectSize enemySize = GameObjectSize.createMadPokerSize();
		
		BodyBuilder enemyBuilder = new BodyBuilder(world, initialPosition, ContactBody.ENEMY)
				.setObjectSize(enemySize)
				.setBodyDef(BodyType.StaticBody)
				.setShape(ShapeFactory.createPolygonShape(enemySize.getPhysicalWidth(), enemySize.getPhysicalHeight()));
		Body body = BodyFactory.getBody(enemyBuilder);

		Sprite sprite = new SpriteFactory().createEnemy("pokerMad", enemySize.getSpriteWidth() , enemySize.getSpriteHeight());
		sprite.setPosition(initialPosition.x - sprite.getWidth() / 2, 
				initialPosition.y - sprite.getHeight() / 2);
		Array<Vector2> coordinates = new Array<>();
		coordinates.add(initialPosition);
		return new MadPoker(coordinates, body, sprite);
	}
}