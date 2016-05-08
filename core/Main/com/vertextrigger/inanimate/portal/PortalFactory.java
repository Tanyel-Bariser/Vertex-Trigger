package com.vertextrigger.inanimate.portal;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.factory.bodyfactory.PortalBodyFactory;
import com.vertextrigger.util.GameObjectSize;

public class PortalFactory {

	public Array<Portal> createPortalPair(final World world, final Vector2 portalPosition, final Vector2 pairedPortalPosition,
			final PortalTeleportation teleportMethod) {
		final PortalBodyFactory bodyFactory = new PortalBodyFactory();
		final Body body1 = bodyFactory.createPortalBody(world, portalPosition);
		final Body body2 = bodyFactory.createPortalBody(world, pairedPortalPosition);

		final Sprite sprite1 = new SpriteFactory().createPortalSprite("portal_blue", GameObjectSize.PORTAL_SIZE);
		final Sprite sprite2 = new SpriteFactory().createPortalSprite("portal_blue", GameObjectSize.PORTAL_SIZE);

		final Portal portal = new Portal(body1, sprite1, teleportMethod);
		final Portal portal2 = new Portal(body2, sprite2, teleportMethod);
		portal.setPairedPortal(portal2);
		portal2.setPairedPortal(portal);

		final Array<Portal> portals = new Array<>();
		portals.add(portal);
		portals.add(portal2);
		return portals;
	}
}