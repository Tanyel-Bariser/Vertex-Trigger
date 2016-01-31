package com.vertextrigger.inanimate.portal;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.factory.bodyfactory.PortalBodyFactory;
import com.vertextrigger.util.ContactBody;

public class PortalFactory {

	public Array<Portal> createPortalPair(World world, Vector2 portalPosition, Vector2 pairedPortalPosition, PortalTeleportation teleportMethod ) {
		PortalBodyFactory bodyFactory = new PortalBodyFactory(); 
		Body body1 = bodyFactory.createPortalBody(world, portalPosition, ContactBody.PORTAL_ONE);
		Body body2 = bodyFactory.createPortalBody(world, pairedPortalPosition, ContactBody.PORTAL_TWO);
		
	
		
		final Portal portal = new Portal(body1, null, teleportMethod, pairedPortalPosition);
		final Portal portal2 = new Portal(body2, null, teleportMethod, portalPosition);
		
		return new Array<Portal>() {{
			add(portal);
			add(portal2);
		}};
	}
}