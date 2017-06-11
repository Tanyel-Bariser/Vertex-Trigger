package com.vertextrigger.entities.movingplatform;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.vertextrigger.entities.AbstractEntity;
import com.vertextrigger.entities.AnimatorImpl;
import com.vertextrigger.factory.animationfactory.PlatformAnimationFactory;
import com.vertextrigger.util.GameObjectSize;

public class MovingPlatform extends AbstractEntity {

    final private GameObjectSize size;
    final private Path path;

    private boolean moving;

    public MovingPlatform(final Body body, final GameObjectSize size, final String sprite, final Path path) {
        super(body, new AnimatorImpl(new PlatformAnimationFactory(size, sprite).createAnimationSet()));
        this.size = size;
        this.path = path;
        setUserData();
    }

    public void startMoving() {
        moving = true;
    }

    public void stopMoving() {
        moving = false;
    }

    @Override
    public Sprite update(final float delta, final float alpha) {
        final Vector2 step = moving ? path.getStep(body.getPosition()) : new Vector2(0, 0);
        body.setLinearVelocity(step);
        return super.update(delta, alpha);
    }

    @Override
    public void setUserData() {
        body.setUserData(this);
        for (final Fixture fix : body.getFixtureList()) {
            fix.setUserData(this);
        }
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
