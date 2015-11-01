package com.vertextrigger.portal;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class PortalAlgorithm {
	public static void setTrajectory(Body body, PortalOrientation orientation) {
		setNewPosition(body, orientation.pairedPortalPosition);
		switch(orientation) {
			case HORIZONTAL_SAME:
				invertX(body);
				break;
			case VERTICAL_SAME:
				invertY(body);
				break;
			case OPPOSITE:
				// no op
				break;
			case VERTICAL_HORIZONTAL:
				swapXY(body);
				break;			
			default:
				throw new RuntimeException("PortalOrientation: "
							+ orientation.name() + " is not handled in PortalAlgorithm.setTrajectory()");
		}
	}
	
	private static void setNewPosition(Body body, Vector2 exitCoordinates) {
		// TODO may need to change angle calc later if we want things rotate on exit
		body.setTransform(exitCoordinates, body.getAngle());
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