package com.vertextrigger.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.collisiondetection.CollisionDetection;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.factory.entityfactory.PlayerFactory;
import com.vertextrigger.inanimate.portal.Portal;
import com.vertextrigger.levelbuilder.*;
import com.vertextrigger.main.VertexTrigger;
import com.vertextrigger.screen.AbstractGameScreen;

public class GameScreenFactory {
	public static AbstractGameScreen createPrototypeLevel(final VertexTrigger vertex) {
		return new AbstractGameScreen(vertex) {
			@Override
			protected void initialiseAssets() {
				VertexTrigger.ASSETS.loadPrototypeLevel();
			}

			@Override
			protected AbstractLevelBuilder createLevelBuilder() {
				world = new World(GRAVITY, true);
				final Vector2 initialPosition = new Vector2(0, 0);
				final Player player = PlayerFactory.createPlayer(world, initialPosition, this);
				final Array<Portal> portals = new Array<>();
				final AbstractLevelBuilder prototypeLevelBuilder = new PrototypeLevelBuilder(world, this);
				prototypeLevelBuilder.setPlayer(player);
				world.setContactListener(new CollisionDetection());
				return prototypeLevelBuilder;
			}
		};
	}
}
