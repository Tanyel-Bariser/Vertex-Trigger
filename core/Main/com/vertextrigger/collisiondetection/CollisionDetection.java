package com.vertextrigger.collisiondetection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.vertextrigger.entities.Entity;
import com.vertextrigger.entities.enemy.AbstractEnemy;
import com.vertextrigger.entities.player.Bullet;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.inanimate.portal.Portal;

public class CollisionDetection implements ContactListener {
	/**
	 * This method is called once when two game objects
	 * are first in contact with each other.
	 */
	@Override
	public void beginContact(Contact contact) {        
        portalTransport(contact);

		// If player is in contact with an item
				// Play rewarding pick up sound effect
				// Notify player that he has an item
		// If bullet is in contact with an enemy
				// Play kill enemy sound effect
				// Remove that enemy from the screen
		// If player is in contact with enemy or dangerous object
				// Play player killed sound effect
				// Notify player to move to the
				// initial position of the level
				// Reset whole layout of the level
		// If player is in contact with a killing edge of
		// the ground/wall of the level, i.e. bottom less pit
				// Play player killed sound effect
				// Notify player to move to the
				// initial position of the level
				// Reset whole layout of the level
		// If player is in contact with a portal
				// Change position of player & the direction, but not magnitude,
				// of movement to be perpendicular to the other portal
		// If player is in contact with level exit
				// Save level completion in persistent memory
				// Return user back to main menu screen
	}

	private void portalTransport(Contact contact) {
        Fixture[] fixtures = getFixtures(contact);
        Collidable[] entities = getUserData(fixtures);
        if (isContact(Portal.class, entities)) {
        	Portal portal = (Portal) getType(Portal.class, entities);
        	Entity e = null;

        	if (isContact(Portal.class, entities) && isContact(Bullet.class, entities)) {
        		e = (Bullet) getType(Bullet.class, entities);
        	}
        	else if (isContact(Portal.class, entities) && isContact(Player.class, entities)) {
        		e = (Player) getType(Bullet.class, entities);
        	}
        		
        	Gdx.app.log("", "contact!");
        	if (portal != null && e != null) {
        		portal.teleport(e.getBody());
        	}
        }
	}

	// UNUSED METHOD FROM INTERFACE
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		//portalTransport(contact);
	}
	
	/**
	 * This method is called continuously during
	 * contact of two or more game objects.
	 */
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		//portalTransport(contact);
        Fixture[] fixtures = getFixtures(contact);
        Collidable[] contactBodies = getUserData(fixtures);
        /*
        boolean isPlayerContact = isPlayerContact(contactBodies);
        boolean isGroundContact = isGroundContact(contactBodies);
        boolean isBulletContact = isBulletContact(contactBodies);
        boolean isPlayerFeetContact = isPlayerFeetContact(contact, isPlayerContact);*/
        
    	
    	if (isContact(Player.class, contactBodies)) {
    		Player player = (Player) getType(Player.class, contactBodies);
    		// see if feet contact
    		if (contact.getWorldManifold().getPoints()[0].y < player.getPosition().y) {
    			if (player != null) {
    				player.setCanJump();
    			}
    		}
    	}
        
		// If player's feet is in contact with a "normal" platform
				// Set player's angle to that of the platform
				// Allow player the ability to jump
		// If player's feet is in contact with a slippery platform
				// Set player's movements to that of slippery platform behaviour
		// If player's feet is in contact with falling platform
				// Cause platform to fall
		// If player's feet is in contact with crumbling platform
				// Cause platform to crumble
		// If player's feet is in contact with moving platform that
		// changes direction each time it's jumped on
				// Cause platform to change direction
		// If player's feet is in contact with sticky platform
				// Keep player's feet in contact with platform regardless
				// of gravity, until the player jumps
		// If player's feet is in contact with conveyor belt/magnet/fan style platform
				// Set players movements to that of conveyor belt platform behaviour
        	
    	
    	if (isContact(Player.class, contactBodies) && isContact(AbstractEnemy.class, contactBodies)) {
    		Player player = (Player) getType(Player.class, contactBodies);
    		if (player != null) {
    			player.die();
    		}
    	}
    	
    	if (isContact(Player.class, contactBodies) && isContact(Bullet.class, contactBodies)) {
    		Bullet bullet = (Bullet) getType(Bullet.class, contactBodies);
    		if (bullet != null) {
    			bullet.setHitPlayer();
    		}
    	}
    		
    	if (isContact(Bullet.class, contactBodies) && isContact(AbstractEnemy.class, contactBodies)) {
    		AbstractEnemy enemy = (AbstractEnemy) getType(AbstractEnemy.class, contactBodies);
    		if (enemy != null) {
    			enemy.die();
    		}
    	}
	}

	private Fixture[] getFixtures(Contact contact) {
		return new Fixture[] {contact.getFixtureA(), contact.getFixtureB()};
	}

	private Collidable[] getUserData(Fixture[] fixtures) {
		return new Collidable[]{ (Collidable) fixtures[0].getUserData(), (Collidable) fixtures[1].getUserData() };
	}
	
	boolean isContact(Class<? extends Collidable> type, Collidable... contactBodies) {
		for (Collidable c : contactBodies) {
			if (c.getClass().equals(type)) {
				return true;
			}
		}
		return false;
	}
	
	Collidable getType(Class<? extends Collidable> type, Collidable[] contactBodies) {
		for (Collidable c : contactBodies) {
			if (c.getClass().equals(type)) {
				return (c);
			}
		}
		return null;
	}
	
	private Vector2 getPosition(Fixture fixture) {
		return fixture.getBody().getPosition();
	}
	
	private float getAngle(Fixture fixture) {
		return fixture.getBody().getAngle();
	}
	

	/**
	 * This method is called once when two game objects
	 * are no longer in contact with each other.
	 */
	@Override
	public void endContact(Contact contact) {
		portalTransport(contact);
		
		Fixture[] fixtures = getFixtures(contact);
		Collidable[] contactBodies = getUserData(fixtures);
        
        if (isContact(Player.class, contactBodies)) {
    		Player player = (Player) getType(Player.class, contactBodies);
    		if (player != null) {
    			player.setCannotJump();;
    		}
    	}
		// If player's feet is not in contact with platform
				// FIRST WAIT FOR 0.2 SECONDS
				// Deny player the ability to jump
				// Restore player's angle to zero
				// Restore player's movements to normal, i.e. set player's xForce to zero
				// Stop moving direction changing platforms
	}
}