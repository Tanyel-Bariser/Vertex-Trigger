package com.vertextrigger.inanimate.portal;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.entities.player.Bullet;
import com.vertextrigger.entities.player.Player;
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
	
	public void teleport(Body body, Vector2 exitCoordinate) {
		//body.setTransform(exitCoordinate, 0); should work like this!!!
		
		Vector2 pairPortalPosition = getPairPortalPosition(body, exitCoordinate);
		
		Object userData = body.getUserData();
		if (userData instanceof Player) {
			Player.setNewPositionFromPortal(pairPortalPosition);
		}
		else if (userData instanceof Bullet) {
			Bullet.setNewPositionFromPortal(pairPortalPosition);
		}
		
		setLinearVelocity(body);
	}

	abstract void setLinearVelocity(Body body);
	
	private Vector2 getPairPortalPosition(Body body, Vector2 exitCoordinate) {
		float displacement =0;
		displacement = 1.5f * GameObjectSize.OBJECT_SIZE;
		if (isMovingUpRight(body)) {
			return new Vector2(exitCoordinate.x + displacement, exitCoordinate.y);
		} else if (isMovingUpLeft(body)) {
			return new Vector2(exitCoordinate.x - displacement, exitCoordinate.y);  
		} else if (isMovingDownLeft(body)) {
			return new Vector2(exitCoordinate.x - displacement, exitCoordinate.y);
		} else if (isMovingDownRight(body)){
			return new Vector2(exitCoordinate.x + displacement, exitCoordinate.y);
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