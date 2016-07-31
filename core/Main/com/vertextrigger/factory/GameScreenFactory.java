package com.vertextrigger.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.collisiondetection.CollisionDetection;
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
				final Vector2 initialPlayerPosition = new Vector2(0, 0);
				final Array<Portal> portals = new Array<>();
				final AbstractLevelBuilder prototypeLevelBuilder = new PrototypeLevelBuilder(world, this, 4, 3, initialPlayerPosition);
				world.setContactListener(new CollisionDetection());
				return prototypeLevelBuilder;
			}
		};
	}
}
