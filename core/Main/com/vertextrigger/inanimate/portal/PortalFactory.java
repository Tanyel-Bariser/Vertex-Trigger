package com.vertextrigger.inanimate.portal;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.factory.SpriteFactory;
import com.vertextrigger.factory.bodyfactory.PortalBodyFactory;
import com.vertextrigger.util.GameObjectSize;

public class PortalFactory {

	private static Sprite[][] portalPairs;
	private static int lastPortalPair = 0;

	private static void loadSprites() {
		portalPairs = new Sprite[][] {
				{ new SpriteFactory().createPortalSprite("portal_blue", GameObjectSize.PORTAL_SIZE),
						new SpriteFactory().createPortalSprite("portal_blue", GameObjectSize.PORTAL_SIZE) },
				{ new SpriteFactory().createPortalSprite("portal_green", GameObjectSize.PORTAL_SIZE),
						new SpriteFactory().createPortalSprite("portal_green", GameObjectSize.PORTAL_SIZE) },
				{ new SpriteFactory().createPortalSprite("portal_red", GameObjectSize.PORTAL_SIZE),
						new SpriteFactory().createPortalSprite("portal_red", GameObjectSize.PORTAL_SIZE) } };
	}

	private static Sprite[] getPortalSprites() {
		return portalPairs[lastPortalPair++ % portalPairs.length];
	}

	public static Array<Portal> createPortalPair(final World world, final Vector2 portalPosition, final Vector2 pairedPortalPosition,
			final PortalTeleportation teleportMethod) {
		loadSprites();
		final PortalBodyFactory bodyFactory = new PortalBodyFactory();
		final Body body1 = bodyFactory.createPortalBody(world, portalPosition);
		final Body body2 = bodyFactory.createPortalBody(world, pairedPortalPosition);

		final Sprite[] portalsSprites = getPortalSprites();

		final Portal portal = new Portal(body1, portalsSprites[0], teleportMethod);
		final Portal portal2 = new Portal(body2, portalsSprites[1], teleportMethod);
		portal.setPairedPortal(portal2);
		portal2.setPairedPortal(portal);

		final Array<Portal> portals = new Array<>();
		portals.add(portal);
		portals.add(portal2);
		return portals;
	}
}