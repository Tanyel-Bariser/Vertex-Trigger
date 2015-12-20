package com.vertextrigger.inanimate.portal;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.vertextrigger.factory.bodyfactory.PortalBodyFactory;

public class PortalFactory {

	public Portal createPortalPair(World world, Vector2 portalPosition, Vector2 pairedPortalPosition) {
		PortalBodyFactory bodyFactory = new PortalBodyFactory(); 
		Body body1 = bodyFactory.createPortalBody(world, portalPosition);
		Body body2 = bodyFactory.createPortalBody(world, portalPosition);
		Portal portal = new Portal(body1, null, portalPosition, null);
		Portal pairedPortal = new Portal(body2, null, pairedPortalPosition, null);
		portal.setPairedPortal(pairedPortal);
		pairedPortal.setPairedPortal(portal);
		return portal;	
	}
}