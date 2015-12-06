package com.vertextrigger.inanimate.portal;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class PortalAlgorithm {
	public static void setTrajectory(Body body, PortalTeleportation orientation) {
		setNewPosition(body, orientation.pairedPortalPosition);
		switch(orientation) {
			case MOVING_OPPOSITE_HORIZONTAL_DIRECTION:
				invertX(body);
				break;
			case MOVING_OPPOSITE_VERTICAL_DIRECTION:
				invertY(body);
				break;
			case MOVING_SAME_DIRECTION:
				// no op
				break;
			case MOVING_DIFFERENT_XY_AXIS_DIRECTION:
				swapXY(body);
				break;			
			default:
				throw new RuntimeException("PortalOrientation: "
							+ orientation.name() + " is not handled in PortalAlgorithm.setTrajectory()");
		}
	}
	
	private static void setNewPosition(Body body, Vector2 exitCoordinates) {
		// TODO may need to change angle calc later if we want things rotate on exit
		int doesNotRotate = 0;
		body.setTransform(exitCoordinates, doesNotRotate);
	}

	private static void invertX(Body body) {
		body.setLinearVelocity(
				-body.getLinearVelocity().x, 
				body.getLinearVelocity().y
		);
	}

	private static void invertY(Body body) {
		body.setLinearVelocity(
				body.getLinearVelocity().x, 
				-body.getLinearVelocity().y
		);		
	}

	private static void swapXY(Body body) {
		body.setLinearVelocity(
				body.getLinearVelocity().y, 
				body.getLinearVelocity().x
		);
	}
}