package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.Coordinate;

public class PortalPair {

    Portal portalOne, portalTwo;

    /**
     * Initializes a linked pair of portals
     *
     * @param portalOne the (arbitrary) first portal in the pair
     * @param portalTwo the (arbitrary) second portal in the pair
     */
    public PortalPair(Portal portalOne, Portal portalTwo) {
        this.portalOne = portalOne;
        this.portalTwo = portalTwo;
    }

    /**
     * Entering one portal of a pair triggers the preTraverse method, passing in which portal has been entered and by what.
     * This allows calculation of the correct exit point, exit angle and exit velocity for the traversal method.
     *
     * @param entryPortal the portal through which the player entered the PortalPair
     * @param entity the entity (player/enemy/bullet/etc) which has entered the PortalPair
     */
    public void preTraverse(Portal entryPortal, Entity entity) {
        Portal exitPortal = (entryPortal.equals(portalOne)) ? portalTwo : portalOne;
        float entityAngle = entity.getAngle();
        float entityMovement = entity.getMovement();

        traverse(exitPortal, entityAngle, entityMovement);
    }

    public void traverse(Portal exitPortal, float currentAngle, float currentMovement) {
        // entity must reappear at exitPortal travelling outwards
        // entity's angle/momentum must be set appropriately given what went into the entry portal
    }

    static class Portal implements Entity {

        /**
         * Initialises the physical properties of the portal's physical body
         * Sets portal's sprite
         *
         * @param world the portal will be in
         * @param sprite the portal's image
         * @param coordinate is the x & y coordinate at which the portal is located
         */
        public Portal(World world, Sprite sprite, Coordinate coordinate) {

        }

        @Override
        public Sprite update(float delta) {
            return null;
        }
    }
}
