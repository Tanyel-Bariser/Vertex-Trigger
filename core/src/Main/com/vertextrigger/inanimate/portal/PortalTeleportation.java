package com.vertextrigger.inanimate.portal;

import com.badlogic.gdx.math.Vector2;

public enum PortalTeleportation {	
	MOVING_OPPOSITE_VERTICAL_DIRECTION, 
	MOVING_OPPOSITE_HORIZONTAL_DIRECTION, 
	MOVING_SAME_DIRECTION, 
	MOVING_DIFFERENT_XY_AXIS_DIRECTION;
	
	Vector2 pairedPortalPosition;
	
	Vector2 getPairPosition() {
		return pairedPortalPosition;
	}
	
	void setPairPosition(Vector2 position) {
		pairedPortalPosition = position;
	}
}
