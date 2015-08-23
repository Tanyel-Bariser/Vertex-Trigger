package com.vertextrigger.collisiondetection;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.vertextrigger.entities.Portal;
import com.vertextrigger.entities.player.Bullet;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.util.ContactBody;

public class CollisionDetection implements ContactListener {
	private final Player player; 
	private final Body playerBody;	
	
	public CollisionDetection(Player player) {
		this.player = player;
		this.playerBody = player.getBody();
	}

    // the index will be in sync with the portal number assigned in the Portal constructor
    private static ArrayList<Portal> portals;

    public static void addPortal(Portal portal) {
        portals.add(portal);
    }

	/**
	 * This method is called once when two game objects
	 * are first in contact with each other.
	 */
	@Override
	public void beginContact(Contact contact) {
        ContactBody fixture1 = (ContactBody) contact.getFixtureA().getUserData();
        ContactBody fixture2 = (ContactBody) contact.getFixtureB().getUserData();

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

	// UNUSED METHOD FROM INTERFACE
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}
	
	/**
	 * This method is called continuously during
	 * contact of two or more game objects.
	 */
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
        Fixture[] fixtures = getFixtures(contact);
        ContactBody[] contactBodies = getContactBodies(fixtures);
        boolean isPlayerContact = isPlayerContact(contactBodies);
        boolean isGroundContact = isGroundContact(contactBodies);
        boolean isBulletContact = isBulletContact(contactBodies);
        boolean isPlayerFeetContact = isPlayerFeetContact(contact, isPlayerContact);
        if (isPlayerFeetContact) {
        	if (isGroundContact) {
        		player.setCanJump();
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
        }
        if (isPlayerContact && isBulletContact) {
        	for (Fixture fix : fixtures) {
        		if(fix.getUserData().equals(ContactBody.BULLET)) {
        			fix.getBody().setAwake(false);
        		}
        	}
        }
	}
	
	private Fixture[] getFixtures(Contact contact) {
		return new Fixture[] {contact.getFixtureA(), contact.getFixtureB()};
	}

	private ContactBody[] getContactBodies(Fixture[] fixtures) {
		return new ContactBody[]{(ContactBody) fixtures[0].getUserData(), 
			(ContactBody) fixtures[1].getUserData()};
	}

	boolean isPlayerContact(ContactBody[] contactBodies) {
		return contactBodies[0] == (ContactBody.PLAYER) || 
		  	   contactBodies[1] == (ContactBody.PLAYER);
	}

	boolean isGroundContact(ContactBody[] contactBodies) {
		return contactBodies[0] == (ContactBody.GROUND) || 
			   contactBodies[1] == (ContactBody.GROUND);
	}

	boolean isBulletContact(ContactBody[] contactBodies) {
		return contactBodies[0] == (ContactBody.BULLET) || 
		  	   contactBodies[1] == (ContactBody.BULLET);
	}
	
	boolean isPlayerFeetContact(Contact contact, boolean isPlayerContact) {
		return isPlayerContact &&
			   contact.getWorldManifold().getPoints()[0].y < playerBody.getPosition().y;
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
		player.setCannotJump();
		// If player's feet is not in contact with platform
				// FIRST WAIT FOR 0.2 SECONDS
				// Deny player the ability to jump
				// Restore player's angle to zero
				// Restore player's movements to normal, i.e. set player's xForce to zero
				// Stop moving direction changing platforms
	}
}