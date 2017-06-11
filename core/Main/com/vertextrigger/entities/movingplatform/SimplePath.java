package com.vertextrigger.entities.movingplatform;

import com.badlogic.gdx.math.Vector2;

/**
 * Implementation of {@link Path} which supports purely vertical or horizontal {@code Path}s
 */
public class SimplePath extends Path {

    final private boolean horizontal;
    private float start;
    private float end;

    // use one Vector2 to avoid excess object creation/GC
    final private Vector2 step = new Vector2();

    public SimplePath(final Vector2 pathStart, final Vector2 pathEnd) {
        this(pathStart, pathEnd, DEFAULT_STEP_SIZE);
    }

    public SimplePath(final Vector2 pathStart, final Vector2 pathEnd, final float stepSize) {
        super(pathStart, pathEnd, stepSize);
    }

    {
        final boolean horizontal = pathStart.y == pathEnd.y;
        final boolean vertical = pathStart.x == pathEnd.x;
        if ((horizontal && vertical) || (!horizontal && !vertical)) {
            throw new IllegalArgumentException("SimplePaths must be either vertical or horizontal!");
        }

        this.horizontal = horizontal;
        this.start = horizontal ? pathStart.x : pathStart.y;
        this.end = horizontal ? pathEnd.x : pathEnd.y;
    }

    @Override
    public Vector2 getStep(final Vector2 bodyPosition) {
        final float position = horizontal ? bodyPosition.x : bodyPosition.y;
        final boolean startBeforeEnd = end - start < 0;

        // needed for correct calculations if start is 'before' end
        // e.g. start is to the right of end or start is above end
        final float pathStart = startBeforeEnd ? end : start;
        final float pathEnd = startBeforeEnd ? start : end;

        if (position >= pathEnd) {
            step.set(horizontal ? new Vector2(-stepSize, 0) : new Vector2(0, -stepSize));
        }
        else if (position <= pathStart) {
            step.set(horizontal ? new Vector2(stepSize, 0) : new Vector2(0, stepSize));
        }
        return step;
    }
}
