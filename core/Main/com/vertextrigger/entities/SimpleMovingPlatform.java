package com.vertextrigger.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.vertextrigger.factory.animationfactory.PlatformAnimationFactory;
import com.vertextrigger.util.GameObjectSize;

public class SimpleMovingPlatform extends AbstractEntity {
    // TODO as this increases, friction must be higher to stop player sliding. perhaps tie the two?
    private static final float MOVEMENT_SPEED = 5 * GameObjectSize.OBJECT_SIZE;
    private final GameObjectSize size;
    private final float pathStart;
    private final float pathEnd;
    private final boolean horizontal;
    private boolean moving;

    public SimpleMovingPlatform(final Body body, final GameObjectSize size, final String sprite, final float pathStart, final float pathEnd, final boolean horizontal) {
        super(body, new AnimatorImpl(new PlatformAnimationFactory(size, sprite).createAnimationSet()));
        this.size = size;
        this.pathStart = pathStart;
        this.pathEnd = pathEnd;
        this.horizontal = horizontal;
        setUserData();
    }

    @Override
    public void setUserData() {
        body.setUserData(this);
        for (final Fixture fix : body.getFixtureList()) {
            fix.setUserData(this);
        }
    }

    @Override
    public Sprite update(final float delta, final float alpha) {
        if (moving) {
            final float bodyPosition = horizontal ? body.getPosition().x : body.getPosition().y;
            if (bodyPosition >= pathEnd) {
                body.setLinearVelocity(horizontal ? new Vector2(-MOVEMENT_SPEED, 0) : new Vector2(0, -MOVEMENT_SPEED));
            }
            else if (bodyPosition <= pathStart) {
                body.setLinearVelocity(horizontal ? new Vector2(MOVEMENT_SPEED, 0) : new Vector2(0, MOVEMENT_SPEED));
            }
        }
        else {
            body.setLinearVelocity(new Vector2(0, 0));
        }

        return super.update(delta, alpha);
    }

    public void startMoving() {
        moving = true;
    }

    public void stopMoving() {
        moving = false;
    }

    @Override
    public float getOffsetX() {
        return size.getOffsetX();
    }

    @Override
    public float getOffsetY() {
        return size.getOffsetY();
    }
}
