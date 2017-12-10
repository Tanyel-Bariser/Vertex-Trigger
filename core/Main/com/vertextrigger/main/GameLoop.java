package com.vertextrigger.main;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.vertextrigger.entities.Entity;
import com.vertextrigger.level.Level;
import com.vertextrigger.screen.AbstractGameScreen;

public class GameLoop {

    private static final float MAX_DELTA = 0.05f;
    private static final float APPROX_FPS = 60.0f;
    private static final float TIMESTEP = 1.0f / APPROX_FPS;
    private static final int VELOCITY_ITERATIONS = 8; // Box2d manual recommends 8 & 3
    private static final int POSITION_ITERATIONS = 3; // for these iterations values

    private float acc = 0;
    private final AbstractGameScreen abstractGameScreen;

    public GameLoop(final AbstractGameScreen abstractGameScreen) {
        this.abstractGameScreen = abstractGameScreen;
    }

    public Array<Sprite> getSpritesToRender(final float oldDelta, final Level level) {
        final Array<Sprite> sprites = new Array<>();
        final float[] deltaAndAlpha = calculateDeltaAndAlpha(oldDelta, level);
        final float delta = deltaAndAlpha[0];
        final float alpha = deltaAndAlpha[1];

        final Array<Entity> entitiesForUpdate = level.getEntitiesForUpdate(this);
        for (final Entity entity : entitiesForUpdate) {
            sprites.add(entity.update(delta, alpha));
        }

        return sprites;
    }

    public boolean isUpdatable(final Entity entity) {
        return abstractGameScreen.isUpdatable(entity);
    }

    private float[] calculateDeltaAndAlpha(final float delta, final Level level) {
        acc += delta < MAX_DELTA ? delta : MAX_DELTA;

        while (acc >= TIMESTEP) {
            cachePreviousEntityPositions(level.getEntities());
            level.getWorld().step(TIMESTEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
            acc -= TIMESTEP;
        }

        final float alpha = acc / TIMESTEP;
        return new float[] { delta, alpha };
    }

    private void cachePreviousEntityPositions(final Array<Entity> entities) {
        for (final Entity entity : entities) {
            entity.cachePosition();
        }
    }
}
