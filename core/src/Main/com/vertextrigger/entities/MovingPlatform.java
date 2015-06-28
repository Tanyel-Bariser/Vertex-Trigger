package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public abstract class MovingPlatform implements Entity {
		// Predefined path for platform to move
		protected Path path;

		/**
		 * Initialises the physical properties of the platform's physical body
		 * Sets platforms's sprite
		 * Sets the path of the platform to move
		 * 
		 * @param world the moving platform will reside in
		 * @param sprite platform's image
		 * @param coordinates is the path, series of x & y coordinates, the platform moves
		 */
		public MovingPlatform(World world, Sprite sprite, Array<Vector2> coordinates) {
			// Set sprite for platform
			// Create physical platform body
			// Set the coordinates of the predefined path
			// for the enemy to follow in a loop
		}
		
		/**
		 * Create moving platform's physical body & physical properties.
		 * Each platform type can set a specific identifier label to it's fixture
		 * 
		 * @param world the moving platform will reside in
		 * @param position of moving platform in game world
		 * @return physical body of moving platform
		 */
		protected abstract Body createPlatformBody(World world, Vector2 position);
		
		/**
		 * Create & set sprite & animation for moving platform
		 */
		protected abstract void spriteAnimationSetup();
		
		/**
		 * Moves the platform further along its predefined
		 * path with the distance moved dependent on the delta.
		 * Delta is needed for frame rate independent movement.
		 * Returns sprite after it's position has been updated.
		 * 
		 * @param delta time passed between previous & current frame
		 * @return updated sprite of this moving platform
		 */
		@Override
		public Sprite update(float delta) {
			// Move platform along it's predefined path based on delta
			// Set moving platform's sprite position & angle to match
			// the new position of the platform's physical body
			// Return sprite after it's position/angle has been updated
			return null;
		}
}