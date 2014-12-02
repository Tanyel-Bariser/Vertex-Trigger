package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.Assets;
import com.vertextrigger.Coordinate;

public class Portal {

    private boolean facingLeft;
    private Coordinate coordinate;
    private World world;

    private Portal pairedPortal;

    /**
     * Initialises a single portal in the world and at a specific coordinate.
     *
     * @param world the portal will be in
     * @param coordinate the x & y coordinate at which the portal is located
     */
    private Portal(World world, Coordinate coordinate, boolean facingLeft) {
        this.world = world;
        this.coordinate = coordinate;
        this.facingLeft = facingLeft;
    }

    /**
     * Static factory method to simplify creation of a pair of portals.
     *
     * @param world the world the portal pair will be in
     * @param coordinates an array of coordinates for the individual portals
     * @param leftFacing an array of booleans specifying direction the individual portals will face
     * @return an array of Sprite objects for the calling code to use for rendering
     */
    public static Sprite[] createPortalPair(World world, Coordinate[] coordinates, Boolean[] leftFacing) {
        Portal portal1 = new Portal(world, coordinates[0], leftFacing[0]);
        Portal portal2 = new Portal(world, coordinates[1], leftFacing[1]);
        portal1.pairedPortal = portal2;
        portal2.pairedPortal = portal1;

        Sprite[] sprites = new Sprite[2];
        sprites[0] = null;      // TODO find portal sprite
        sprites[1] = null;
        return sprites;
    }

    /**
     * Places the body at the location of the exit portal at an appropriate angle and linear velocity.
     * Calculates new linear velocity and new angle for the body depending on if the portals face the same direction
     * If the two portals' directions differ then we must flip the angle and linear velocity x-axis of the body on exit
     *
     * @param entryPortal the portal through which the player entered the portal pair
     * @param body the body (player/enemy/bullet/etc) which has entered the portal pair
     */
    public void traverse(Portal entryPortal, Body body) {
        Vector2 currentVelocity = body.getLinearVelocity();
        float currentAngle = body.getAngle();
        Vector2 newVelocity;
        float newAngle;

        if (exitSameDirection(entryPortal)) {   // the simple case
            newVelocity = currentVelocity;
            newAngle = body.getAngle();
        }
        else {                                  // the complex case: we need to do some work!
            newVelocity = getOppositeVelocity(currentVelocity);
            newAngle = getOppositeAngle(currentAngle);
        }

        Portal exitPortal = entryPortal.pairedPortal;
        body.setTransform(exitPortal.coordinate.x, exitPortal.coordinate.y, newAngle);   // set body to the position of the exit portal with updated angle
        body.setLinearVelocity(newVelocity);                                             // set body's updated linear velocity
    }

    /**
     * Utility method to check whether both portals in a pair face the same direction.
     * If so the player does not need to be rotated nor have his linear velocity changed, only his position.
     * If not then we must calculate a new angle the player is facing and a new linear velocity.
     *
     * @param entryPortal the first portal of a linked pair
     * @return true if both portals face left OR both portals face right, otherwise false
     */
    private boolean exitSameDirection(Portal entryPortal) {
        return (entryPortal.facingLeft == entryPortal.pairedPortal.facingLeft);
    }

    /**
     * Gets the opposite angle in radians. Equivalent to a 180 degree flip.
     * Used for when the player must exit from an opposite-facing portal and his angle must be reversed.
     *
     * @param currentAngle the angle at which the player has entered the first portal
     * @return the flipped angle at which the player will exit the second portal
     */
    private float getOppositeAngle(float currentAngle) {
        // 360 degrees is equivalent to 2π and 180 degrees to π
        if (currentAngle >= Math.PI) {
            return currentAngle -= Math.PI;
        }
        else {
            return currentAngle += Math.PI;
        }
    }

    /**
     * Gets the opposite linear velocity, i.e. an entry velocity of (-20, 30) will become (20, 30).
     * We keep the current y-axis velocity but give the x-axis velocity the opposite sign, reversing it.
     *
     * @param currentVelocity the linear velocity at which the player has entered the first portal
     * @return the flipped linear velocity at which the player will exit the second portal
     */
    private Vector2 getOppositeVelocity(Vector2 currentVelocity) {
        // multiplying the float by -1 makes a positive number negative and vice-versa
        // i.e. -2.1f becomes 2.1f and 41.96f becomes -41.96f
        return new Vector2(currentVelocity.x *= -1, currentVelocity.y);
    }
}
