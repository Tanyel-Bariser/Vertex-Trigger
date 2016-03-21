package com.vertextrigger.inanimate.portal;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.util.AudioManager;
import com.vertextrigger.util.GameObjectSize;

public enum PortalTeleportation {	
	MOVING_OPPOSITE_VERTICAL_DIRECTION {
		@Override
		public void setLinearVelocity(Body body) {
			body.setLinearVelocity(body.getLinearVelocity().x, -body.getLinearVelocity().y);
		}
	}, 
	
	MOVING_OPPOSITE_HORIZONTAL_DIRECTION {
		@Override
		public void setLinearVelocity(Body body) {
			body.setLinearVelocity(-body.getLinearVelocity().x, body.getLinearVelocity().y);
		}
	}, 
	
	MOVING_SAME_DIRECTION {
		@Override
		public void setLinearVelocity(Body body) { /* nothing */ }
	},
	
	MOVING_DIFFERENT_XY_AXIS_DIRECTION {
		@Override
		public void setLinearVelocity(Body body) {
			body.setLinearVelocity(body.getLinearVelocity().y, body.getLinearVelocity().x);
		}
	};
	
	public void teleport(Body body, Vector2 pairPortalPosition) {
		Vector2 exitPosition = getExitPosition(body, pairPortalPosition);
		((Teleportable)body.getUserData()).setNewPositionFromPortal(exitPosition);
		AudioManager.playPortalSound();
		setLinearVelocity(body);
	}

	abstract void setLinearVelocity(Body body);
	
	private Vector2 getExitPosition(Body body, Vector2 pairPortalPosition) {
		float displacement = 1.5f * GameObjectSize.OBJECT_SIZE;
		if (isMovingUpRight(body)) {
			return new Vector2(pairPortalPosition.x + displacement, pairPortalPosition.y);
		} else if (isMovingUpLeft(body)) {
			return new Vector2(pairPortalPosition.x - displacement, pairPortalPosition.y);  
		} else if (isMovingDownLeft(body)) {
			return new Vector2(pairPortalPosition.x - displacement, pairPortalPosition.y);
		} else if (isMovingDownRight(body)){
			return new Vector2(pairPortalPosition.x + displacement, pairPortalPosition.y);
		}
		throw new RuntimeException("Have I not covered all directions???!!!");
	}
	
	private boolean isMovingUpRight(Body body) {
		return 0 <= body.getLinearVelocity().y && 0 <= body.getLinearVelocity().x;
	}
	
	private boolean isMovingUpLeft(Body body) {
		return 0 <= body.getLinearVelocity().y && body.getLinearVelocity().x <= 0;
	}
	
	private boolean isMovingDownRight(Body body) {
		return body.getLinearVelocity().y <= 0 && 0 <= body.getLinearVelocity().x ;
	}
	
	private boolean isMovingDownLeft(Body body) {
		return body.getLinearVelocity().y <= 0 && body.getLinearVelocity().x <= 0;
	}
}