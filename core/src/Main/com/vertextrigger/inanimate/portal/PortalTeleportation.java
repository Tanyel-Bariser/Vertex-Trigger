package com.vertextrigger.inanimate.portal;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

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
		public void setLinearVelocity(Body body) { /* nothing */ };
	},
	
	MOVING_DIFFERENT_XY_AXIS_DIRECTION {
		@Override
		public void setLinearVelocity(Body body) {
			body.setLinearVelocity(body.getLinearVelocity().y, body.getLinearVelocity().x);
		}
	};
	
	public void teleport(Body body, Vector2 exitCoordinate) {
		body.setTransform(exitCoordinate, 0);
		setLinearVelocity(body);
	}
	
	abstract void setLinearVelocity(Body body);
}