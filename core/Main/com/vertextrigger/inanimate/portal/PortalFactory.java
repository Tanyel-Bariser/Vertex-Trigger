package com.vertextrigger.inanimate.portal;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.factory.bodyfactory.PortalBodyFactory;

public class PortalFactory {

	public Array<Portal> createPortalPair(final World world, final Vector2 portalPosition, final Vector2 pairedPortalPosition,
			final PortalTeleportation teleportMethod) {
		final PortalBodyFactory bodyFactory = new PortalBodyFactory();
		final Body body1 = bodyFactory.createPortalBody(world, portalPosition);
		final Body body2 = bodyFactory.createPortalBody(world, pairedPortalPosition);

		final Portal portal = new Portal(body1, null, teleportMethod);
		final Portal portal2 = new Portal(body2, null, teleportMethod);
		portal.setPairedPortal(portal2);
		portal2.setPairedPortal(portal);

		final Array<Portal> portals = new Array<>();
		portals.add(portal);
		portals.add(portal2);
		return portals;
	}
}