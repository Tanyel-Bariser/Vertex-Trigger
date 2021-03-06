package com.vertextrigger.collisiondetection;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.vertextrigger.ai.Magnet;
import com.vertextrigger.entities.AbstractEntity;
import com.vertextrigger.entities.bullet.BeeBullet;
import com.vertextrigger.entities.bullet.Bullet;
import com.vertextrigger.entities.bullet.EnemyBullet;
import com.vertextrigger.entities.bullet.PlayerBullet;
import com.vertextrigger.entities.enemy.Enemy;
import com.vertextrigger.entities.enemy.Mouse;
import com.vertextrigger.entities.enemy.Pit;
import com.vertextrigger.entities.enemy.PokerHead;
import com.vertextrigger.entities.enemy.spider.Spider;
import com.vertextrigger.entities.enemy.Spike;
import com.vertextrigger.entities.mortalplatform.FadingPlatform;
import com.vertextrigger.entities.player.Player;
import com.vertextrigger.entities.player.PlayerFeet;
import com.vertextrigger.entities.player.Shield;
import com.vertextrigger.inanimate.portal.Portal;
import com.vertextrigger.inanimate.portal.Teleportable;

public class CollisionDetection implements ContactListener {
	/**
	 * This method is called once when two game objects are first in contact with each other.
	 */
	@Override
	public void beginContact(final Contact contact) {
		final Fixture[] fixtures = getFixtures(contact);
		final Collidable[] collidableObjects = getUserData(fixtures);

		final Player player = (Player) getType(Player.class, collidableObjects);

		playerShieldContact(collidableObjects, player);
		shieldMagnetContact(collidableObjects);
		enemyBulletMagnetContact(collidableObjects);
		portalTransport(collidableObjects);

		final Bullet bullet = (Bullet) getType(Bullet.class, collidableObjects);
		final Portal portal = (Portal) getType(Portal.class, collidableObjects);
		if (bullet != null && portal == null) {
			bullet.incrementCollisions();
		}
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

	private void shieldMagnetContact(final Collidable[] collidableObjects) {
		final Magnet magnet = (Magnet) getType(Magnet.class, collidableObjects);
		final Shield shield = (Shield) getType(Shield.class, collidableObjects);

		if (shield != null && magnet != null && magnet.isActive()) {
			magnet.switchOff();
		}
	}

	private void enemyBulletMagnetContact(final Collidable[] collidableObjects) {
		final Magnet magnet = (Magnet) getType(Magnet.class, collidableObjects);
		final EnemyBullet enemyBullet = (EnemyBullet) getType(EnemyBullet.class, collidableObjects);

		if (enemyBullet != null && magnet != null && magnet.isActive()) {
			magnet.switchOff();
		}
	}

	private void playerShieldContact(final Collidable[] collidableObjects, final Player player) {
		final Shield shield = (Shield) getType(Shield.class, collidableObjects);
		if (player != null && shield != null) {
			player.setShield(shield);
		}
	}

	private void portalTransport(final Collidable[] collidableObject) {
		final Portal portal = (Portal) getType(Portal.class, collidableObject);
		final Teleportable teleportable = (Teleportable) getType(Teleportable.class, collidableObject);

		if (teleportable instanceof PokerHead) {
			return;
		}

		if ((portal != null) && (teleportable != null) && teleportable.isTeleportable()) {
			portal.teleport(teleportable.getBody());
		}
	}

	private void resetTeleportable(final Collidable[] collidableObject) {
		final Portal portal = (Portal) getType(Portal.class, collidableObject);
		final Teleportable teleportable = (Teleportable) getType(Teleportable.class, collidableObject);
		if ((portal != null) && (teleportable != null)) {
			teleportable.exitedPortal();
		}
	}

	// UNUSED METHOD FROM INTERFACE
	@Override
	public void preSolve(final Contact contact, final Manifold oldManifold) {
	}

	/**
	 * This method is called continuously during contact of two or more game objects.
	 */
	@Override
	public void postSolve(final Contact contact, final ContactImpulse impulse) {
		final Fixture[] fixtures = getFixtures(contact);
		final Collidable[] contactBodies = getUserData(fixtures);
		final PlayerFeet playerFeet = (PlayerFeet) getType(PlayerFeet.class, contactBodies);
		if (playerFeet != null) {
			playerFeet.setPlayerCanJump();
		}
		final Pit pit = (Pit) getType(Pit.class, contactBodies);
		if (playerFeet != null && pit != null) {
			playerFeet.setDead();
		}

		final Spike spike = (Spike) getType(Spike.class, contactBodies);
		if (playerFeet != null && spike != null) {
			playerFeet.setDead();
		}

		// once player has stepped on a fading platform, it is dead a begins fading out
		final FadingPlatform fadingPlatform = (FadingPlatform) getType(FadingPlatform.class, contactBodies);
		if (playerFeet != null && fadingPlatform != null) {
			fadingPlatform.setDead();
		}

		// allow jumping on mouse to kill it
		final Mouse mouse = (Mouse) getType(Mouse.class, contactBodies);
		if (playerFeet != null && mouse != null) {
			mouse.setDead();
		}

		// allow jumping on spider to kill it
		final Spider spider = (Spider) getType(Spider.class, contactBodies);
		if (playerFeet != null && spider != null) {
			spider.setDead();
		}

		/*
		 * boolean isPlayerContact = isPlayerContact(contactBodies); boolean isGroundContact = isGroundContact(contactBodies); boolean isBulletContact
		 * = isBulletContact(contactBodies); boolean isPlayerFeetContact = isPlayerFeetContact(contact, isPlayerContact);
		 */

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
		// If player's feet is in contact with conveyor belt/magnet/fan style
		// platform
		// Set players movements to that of conveyor belt platform behaviour

		final Player player = (Player) getType(Player.class, contactBodies);
		final Enemy enemy = (Enemy) getType(Enemy.class, contactBodies);

		// check if the enemy is dead - if it is we don't kill the player. this avoids annoyance of killing an enemy then bumping into its corpse during its death animation and dying unfairly
		boolean deadEnemy = enemy instanceof AbstractEntity && ((AbstractEntity) enemy).isDead();

		if (player != null && enemy != null && !deadEnemy && player.isShielded() == false) {
			player.setDead();
		}

		handlePlayerBulletCollision(contactBodies, player, enemy);
		handleEnemyBulletCollision(contactBodies, player, enemy);
	}

	private void handleEnemyBulletCollision(final Collidable[] contactBodies, final Player player, final Enemy enemy) {
		final EnemyBullet bullet = (EnemyBullet) getType(EnemyBullet.class, contactBodies);
		if ((enemy != null) && (bullet != null)) {
			bullet.destroyBullet();
		}

		if (player != null && bullet != null && player.isShielded() == false) {
			player.setDead();
		}

		if (bullet != null && bullet instanceof BeeBullet) {
			// bee bullets should not bounce as they are stings
			final Magnet magnet = (Magnet) getType(Magnet.class, contactBodies);
			if (magnet == null) {
				bullet.destroyBullet();
			} else {
				bullet.stopMagnetAttraction();
			}
		}
	}

	private void handlePlayerBulletCollision(final Collidable[] contactBodies, final Player player, final Enemy enemy) {
		final PlayerBullet bullet = (PlayerBullet) getType(PlayerBullet.class, contactBodies);
		final Shield shield = (Shield) getType(Shield.class, contactBodies);
		if ((player != null || shield != null) && bullet != null) {
			bullet.destroyBullet();
		}

		if (enemy != null && bullet != null) {
			enemy.setDead();
		}
	}

	private Fixture[] getFixtures(final Contact contact) {
		return new Fixture[] { contact.getFixtureA(), contact.getFixtureB() };
	}

	private Collidable[] getUserData(final Fixture[] fixtures) {
		return new Collidable[] { (Collidable) fixtures[0].getUserData(), (Collidable) fixtures[1].getUserData() };
	}

	boolean isContact(final Class<? extends Collidable> type, final Collidable... contactBodies) {
		for (final Collidable c : contactBodies) {
			if (type.isInstance(c)) {
				return true;
			}
		}
		return false;
	}

	Collidable getType(final Class<? extends Collidable> type, final Collidable[] contactBodies) {
		for (final Collidable c : contactBodies) {
			if (type.isInstance(c)) {
				return (c);
			}
		}
		return null;
	}

	/**
	 * This method is called once when two game objects are no longer in contact with each other.
	 */
	@Override
	public void endContact(final Contact contact) {
		final Fixture[] fixtures = getFixtures(contact);
		final Collidable[] contactBodies = getUserData(fixtures);

		resetTeleportable(contactBodies);

		final PlayerFeet playerFeet = (PlayerFeet) getType(PlayerFeet.class, contactBodies);
		if (playerFeet != null) {
			playerFeet.setPlayerCannotJump();
		}
		// If player's feet is not in contact with platform
		// FIRST WAIT FOR 0.2 SECONDS
		// Deny player the ability to jump
		// Restore player's angle to zero
		// Restore player's movements to normal, i.e. set player's xForce to
		// zero
		// Stop moving direction changing platforms
	}
}