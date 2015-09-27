package com.vertextrigger.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.collisiondetection.CollisionDetection;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.factory.entityfactory.PlayerFactory;
import com.vertextrigger.levelbuilder.AbstractLevelBuilder;
import com.vertextrigger.levelbuilder.PrototypeLevelBuilder;
import com.vertextrigger.main.VertexTrigger;
import com.vertextrigger.screen.AbstractGameScreen;

public class GameScreenFactory {
	public static AbstractGameScreen createPrototypeLevel(VertexTrigger vertex) {
		return new AbstractGameScreen(vertex) {
			@Override
			protected void initialiseAssets() {
				VertexTrigger.ASSETS.loadPrototypeLevel();
			}
			
			@Override
			protected AbstractLevelBuilder createLevelBuilder() {
				world = new World(GRAVITY, true);
				Player player = PlayerFactory.createPlayer(world, new Vector2(0,0), this);
				world.setContactListener(new CollisionDetection(player));
				return new PrototypeLevelBuilder(world, this)
							.setPlayer(player);

			}
		};
	}
}
