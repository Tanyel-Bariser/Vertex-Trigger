package com.vertextrigger.portal;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.vertextrigger.collisiondetection.CollisionDetection;

public class Portal {

    public static enum Orientation {UP, RIGHT, DOWN, LEFT}
    private static int portalCounter;
    public static String IDENTIFIER = "PORTAL-";

    private Body body;
    private Vector2 coordinate;
    private Fixture fixture;

    private Orientation orientation;
    private Portal pairedPortal;

    public Portal getPairedPortal() {
        return this.pairedPortal;
    }

    public Fixture getFixture() {
        return this.fixture;
    }

    public Body getBody() {
        return this.body;
    }

    /**
     * Initialises a single portal in the world and at a specific coordinate.
     *
     * @param world the portal will be in
     * @param coordinate the x & y coordinate at which the portal is located
     * @param orientation the direction the portal faces
     */
    private Portal(World world, Vector2 coordinate, Orientation orientation) {
        this.coordinate = coordinate;
        this.orientation = orientation;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;      // a portal has no need for movement
        bodyDef.position.set(coordinate.x, coordinate.y);
        body = world.createBody(bodyDef);

        fixture = body.createFixture(
                new CircleShape(), 0.0f
        );
        // the first portal pair will therefore be PORTAL-0 and PORTAL-1
        fixture.setUserData(IDENTIFIER + (portalCounter++));
    }

    /**
     * Static factory method to simplify creation of a pair of portals.
     *
     * @param world the world the portal pair will be in
     * @param coordinates an array of coordinates for the individual portals
     * @param orientations an array of booleans specifying direction the individual portals will face
     * @return an array of Sprite objects for the calling code to use for rendering
     */
    public static Sprite[] createPortalPair(World world, Vector2[] coordinates, Orientation[] orientations) {
        // Construct portal objects and pair them with each other
        Portal portal1 = new Portal(world, coordinates[0], orientations[0]);
        Portal portal2 = new Portal(world, coordinates[1], orientations[1]);
        portal1.pairedPortal = portal2;
        portal2.pairedPortal = portal1;

        // register portals with the collision detector
        CollisionDetection.addPortal(portal1);
        CollisionDetection.addPortal(portal2);

        // Create portal sprites and return them in an array to the caller
        Sprite[] sprites = new Sprite[2];
        sprites[0] = null;      // TODO find portal sprite
        sprites[1] = null;
        return sprites;
    }

    /**
     * Places the body at the location of the exit portal at an appropriate angle and linear velocity.
     * Calculates new linear velocity and new angle for the body depending on orientation of the portals
     *
     * @param entryPortal the portal through which the player entered the portal pair
     * @param body the body (player/enemy/bullet/etc) which has entered the portal pair
     */
    public void traverse(Portal entryPortal, Body body) {
        Portal exitPortal = entryPortal.pairedPortal;       // derive the exit portal from the entry portal
        Vector2 currentVelocity = body.getLinearVelocity();
        float currentAngle = body.getAngle();

        // use the rotational offset of the portals to calculate updated player angle and velocity
        int clockwiseRotations = getClockwiseRotations(entryPortal, exitPortal);
        float newAngle = getNewAngle(currentAngle, clockwiseRotations);
        Vector2 newVelocity = getNewLinearVelocity(currentVelocity, clockwiseRotations);

        body.setTransform(exitPortal.coordinate.x, exitPortal.coordinate.y, newAngle);   // set body to the position of the exit portal with updated angle
        body.setLinearVelocity(newVelocity);                                             // set body's updated linear velocity
    }

    /**
     * Utility method to calculate new angle the player must face after exiting the portal pair
     *
     * @param currentAngle the current angle at which the player is facing
     * @param clockwiseRotations the offset of the portal direction in number of clockwise rotations of 90 degrees
     * @return the new angle the player is facing in
     */
    private float getNewAngle(float currentAngle, int clockwiseRotations) {
        // π radians is 190 degrees and so (π / 2) radians is 90
        float changeInRadians = (float) (Math.PI / 2) * clockwiseRotations;
        return currentAngle + changeInRadians;
    }

    /**
     * Utility method to calculate new linear velocity the player must travel at after exiting the portal pair
     *
     * @param currentVelocity the current x-axis and y-axis velocity at which the player is travelling
     * @param clockwiseRotations the offset of the portal direction in number of clockwise rotations of 90 degrees
     * @return the new linear velocity the player is travelling in
     */
    private Vector2 getNewLinearVelocity(Vector2 currentVelocity, int clockwiseRotations) {
        float currentX = currentVelocity.x;
        float currentY = currentVelocity.y;
        float newX, newY;

        switch (clockwiseRotations) {
            case 0:
                newX = currentX;
                newY = currentY * -1;
                break;
            case 1:
                newX = currentX * -1;
                newY = currentY * -1;
                break;
            case 2:
                newX = currentX * -1;
                newY = currentY;
                break;
            case 3:
                newX = currentX;
                newY = currentY;
                break;
            default:
                // TODO handle this error
                newX = 0.0f;
                newY = 0.0f;
                break;
        }
        return new Vector2(newX, newY);
    }

    /**
     * Method to get the angular offset of the exit portal from the entry portal
     *
     * @param entry portal through which the player entered the portal pair
     * @param exit portal through which the player will exit the portal pair
     * @return an integer in the range 0-3 representing the offset in amount of clockwise turns of 90 degrees
     */
    private int getClockwiseRotations(Portal entry, Portal exit) {
        // enums map to an array of integers with [UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3]
        // a difference of 1 in the integer value represents 1 rotation of 90 degrees
        // i.e. RIGHT is 1 and LEFT is 3, the difference is 2 and therefore 180 degrees (two 90 degree rotations)
        int rotations = entry.orientation.ordinal() - exit.orientation.ordinal();

        // a positive value of rotations indicates anticlockwise rotation and a negative difference clockwise
        // e.g. UP to LEFT == (0 - 3) == -3  == 3 * 90 degrees clockwise == 270 degrees clockwise
        if (rotations > 0) {
            // rotations is positive so we subtract 4 rotations to get the clockwise rotations
            // i.e. if rotations is 3 then it means 270 degrees anticlockwise. we subtract 4 to make it -1 which is 90 degrees clockwise
            // finally we remove the sign and return 1 to indicate 1 clockwise rotation of 90 degrees
            return Math.abs(rotations - 4);
        }
        else if (rotations < 0) {
            // rotations is negative so we just remove the sign and return it
            // i.e. if rotations is -3 then it means 270 degrees clockwise so we remove the sign to indicate 3 rotations of 90 degrees
            return Math.abs(rotations);
        }
        else {
            return 0;   // rotations is 0 so we simply return 0 to indicate 0 rotations of 90 degrees
        }
    }
}
