package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.entities.callback.Callback;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public abstract class AbstractEntity implements Entity {
	protected Body body;
	protected Animator animator;
	protected boolean isDead;
	protected Vector2 newPositionFromPortal;
	protected boolean isFacingLeft;
	protected boolean isDeathAnimationFinished;
	protected final InterpolatedPosition entityState;
	private boolean isTeleportable;
	private Collection<Callback> callbacks;

	public AbstractEntity(final Body body, final Animator animator) {
		this.body = body;
		isTeleportable = true;
		isDeathAnimationFinished = false;
		isDead = false;
		this.animator = animator;
		animator.setEntity(this);
		setUserData();
		entityState = new InterpolatedPosition(this.body);
		this.callbacks = new HashSet<>();
	}

	@Override
	public Sprite update(final float delta, final float alpha) {
		for (final Callback callback : callbacks) {
			if (callback.isRunnable()) {
				callback.run();
			}
		}

		handleTeleportation();
		return animator.getUpdatedSprite(delta, entityState.getNewPosition(alpha, body), entityState.getNewAngle(alpha, body));
	}

	public void addCallbacks(final Callback... callbacks) {
		this.callbacks.addAll(Arrays.asList(callbacks));
	}

	private void handleTeleportation() {
		if (newPositionFromPortal != null) {
			body.setTransform(newPositionFromPortal, 0);
			cachePosition();
			setNewPositionFromPortal(null);
		}
	}

	@Override
	public void setUserData() {
		body.setUserData(this);
		for (final Fixture fix : body.getFixtureList()) {
			fix.setUserData(this);
		}
	}

	@Override
	public void setNewPositionFromPortal(final Vector2 newPositionFromPortal) {
		this.newPositionFromPortal = newPositionFromPortal;
	}

	@Override
	public void setFacingLeft() {
		isFacingLeft = true;
	}

	@Override
	public void setFacingRight() {
		isFacingLeft = false;
	}

	@Override
	public boolean isTeleportable() {
		return isTeleportable;
	};

	@Override
	public void exitedPortal() {
		isTeleportable ^= true;
	}

	@Override
	public void cachePosition() {
		entityState.setState(body);
	}

	@Override
	public Vector2 getPosition() {
		return body.getPosition();
	}

	@Override
	public Body getBody() {
		return body;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead() {
		isDead = true;
	}

	public void setDeathAnimationFinished() {
		isDeathAnimationFinished = true;
	}

	public boolean isDeathAnimationFinished() {
		return isDeathAnimationFinished;
	}

	public boolean isVolitionallyMoving() {
		return false;
	}

	public boolean isFeetOnGround() {
		return true;
	}
}