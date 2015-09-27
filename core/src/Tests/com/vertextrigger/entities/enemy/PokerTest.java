package com.vertextrigger.entities.enemy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.factory.bodyfactory.PokerBodyFactory;

public class PokerTest {
	Sprite sprite;
	Body body;
	Poker poker;
	World world;

	@Before
	public void setUp() throws Exception {
		world = new World(new Vector2(0, 0), false);
		body = new PokerBodyFactory().createPokerBody(world, new Vector2(0, 0));
		sprite = new SpriteFactory().createEnemy("pokerMad", 10, 10);
		poker = new Poker(createPath(), body, sprite);
	}
	
	private Array<Vector2> createPath() {
		Vector2 startCoords = new Vector2(0, 0);
		Vector2 endCoords = new Vector2(5, 5);
		Array<Vector2> path = new Array<>();
		path.add(startCoords);
		return path;
	}

	@Test
	public void whenPokerBodyMovesPokerSpriteIsMovedCorrectly() {
		body.setTransform(0, 0, 0f);
		float startX = sprite.getBoundingRectangle().x;
		float startY = sprite.getBoundingRectangle().y;
		
		body.setTransform(5, 5, 0f);
		float endX = sprite.getBoundingRectangle().x;
		float endY = sprite.getBoundingRectangle().y;
		
		assertSame(5, endX - startX);
	}

}
