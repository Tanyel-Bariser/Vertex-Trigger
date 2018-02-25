package com.vertextrigger.entities.movingplatform;

import com.badlogic.gdx.math.Vector2;
import com.vertextrigger.entities.enemy.Mouse;

/**
 * Implementation of {@link Path} which causes no movement at all (useful for entity death)
 *
 * @see Mouse#die()
 */
public class NullPath extends Path {

    public NullPath() {
        super(null, null, 0);
    }

    @Override
    public Vector2 getStep(final Vector2 bodyPosition) {
        return Vector2.Zero;
    }
}
