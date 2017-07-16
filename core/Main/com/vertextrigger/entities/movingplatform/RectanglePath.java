package com.vertextrigger.entities.movingplatform;

import com.badlogic.gdx.math.Vector2;

import static com.vertextrigger.entities.movingplatform.RectanglePath.StartPoint.TOP_LEFT;

/**
 * Implementation of {@link Path} which supports rectangular (including square) {@code Path}s
 */
public class RectanglePath extends Path {

    enum StartPoint {
        TOP_LEFT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        TOP_RIGHT
    }

    final private boolean clockwise;
    final private SimplePath top, bottom, left, right;
    final private CircularArray<SimplePath> sides;
    final private StartPoint startPoint;

    private SimplePath currentSide;

    public RectanglePath(final Vector2 topLeft, final Vector2 bottomRight, final boolean clockwise) {
        this(topLeft, bottomRight, clockwise, DEFAULT_STEP_SIZE);
    }

    public RectanglePath(final Vector2 topLeft, final Vector2 bottomRight, final boolean clockwise, final float stepSize) {
        this(topLeft, bottomRight, clockwise, stepSize, TOP_LEFT);
    }

    public RectanglePath(final Vector2 topLeft, final Vector2 bottomRight, final boolean clockwise, final float stepSize, final StartPoint startPoint) {
        super(topLeft, bottomRight, stepSize);
        this.clockwise = clockwise;
        this.startPoint = startPoint;

        final Vector2 bottomLeft = new Vector2(topLeft.x, bottomRight.y);
        final Vector2 topRight = new Vector2(bottomRight.x, topLeft.y);
        this.top = new SimplePath(topLeft, topRight);
        this.right = new SimplePath(topRight, bottomRight);
        this.bottom = new SimplePath(bottomRight, bottomLeft);
        this.left = new SimplePath(bottomLeft, topLeft);
        this.sides = new CircularArray<>(top, right, bottom, left);
        this.currentSide = sides.next();
    }

    @Override
    public Vector2 getStep(final Vector2 bodyPosition) {
        if (reachedEndOfSide(currentSide.getStep(bodyPosition))) {
            //System.out.println(which(currentSide));
            currentSide = clockwise ? sides.next() : sides.prev();
        }
        return currentSide.getStep(bodyPosition);
    }

    private boolean reachedEndOfSide(final Vector2 step) {
        return currentSide == top && (clockwise ? step.x < 0 : step.x > 0) ||
               currentSide == right && (clockwise ? step.y > 0 : step.y < 0) ||
               currentSide == bottom && (clockwise ? step.x > 0 : step.x < 0) ||
               currentSide == left && (clockwise ? step.y < 0 : step.y > 0);
    }

    private String which(final SimplePath currentSide) {
        if (currentSide == left) return "left";
        else if (currentSide == right) return "right";
        else if (currentSide == top) return "top";
        else if (currentSide == bottom) return "bottom";
        return "null";
    }
}
