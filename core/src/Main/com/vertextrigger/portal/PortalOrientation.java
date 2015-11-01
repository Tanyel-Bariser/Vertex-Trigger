package com.vertextrigger.portal;

import com.badlogic.gdx.math.Vector2;

public enum PortalOrientation {	
	VERTICAL_SAME, 
	HORIZONTAL_SAME, 
	OPPOSITE, 
	VERTICAL_HORIZONTAL;
	
	Vector2 pairedPortalPosition;
	
	Vector2 getPairPosition() {
		return pairedPortalPosition;
	}
	
	void setPairPosition(Vector2 position) {
		pairedPortalPosition = position;
	}
}
