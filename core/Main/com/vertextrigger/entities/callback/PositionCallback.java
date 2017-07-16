package com.vertextrigger.entities.callback;

import com.badlogic.gdx.math.Vector2;

/**
 * Implementation of {@link Callback} that triggers when a body is near a certain position
 */
public class PositionCallback extends Callback {

    final private Vector2 runPosition;
    final private Vector2 bodyPosition;

    public PositionCallback(final Runnable runnable, final Vector2 runPosition, final Vector2 bodyPosition) {
        super(runnable);
        this.runPosition = runPosition;
        this.bodyPosition = bodyPosition;
    }

    @Override
    public boolean isRunnable() {
        return bodyPosition.x > runPosition.x - 0.5f &&
                bodyPosition.x < runPosition.x + 0.5f &&
                bodyPosition.y > runPosition.y - 0.5f &&
                bodyPosition.y < runPosition.y + 0.5f;
    }
}
