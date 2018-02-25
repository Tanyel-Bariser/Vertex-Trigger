package com.vertextrigger.entities.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.assets.AudioManager;
import com.vertextrigger.entities.AbstractEntity;
import com.vertextrigger.entities.AnimationSet;
import com.vertextrigger.entities.AnimatorImpl;
import com.vertextrigger.entities.Mortal;
import com.vertextrigger.entities.movingplatform.NullPath;
import com.vertextrigger.entities.movingplatform.Path;

import static com.vertextrigger.util.GameObjectSize.MOUSE_BODY_SIZE;

public class Mouse extends AbstractEntity implements Enemy, Mortal {

    private Path path;

    public Mouse(final Body body, final AnimationSet animationSet, final Path path) {
        super(body, new AnimatorImpl(animationSet));
        this.path = path;
    }

    @Override
    public float getOffsetX() {
        return MOUSE_BODY_SIZE.getOffsetX();
    }

    @Override
    public float getOffsetY() {
        return MOUSE_BODY_SIZE.getOffsetY();
    }

    @Override
    public void die() {
        AudioManager.playEnemyKilledSound();
        // setting path to NullPath means Mouse will stop moving
        this.path = new NullPath();
        animator.playDeathAnimation(this);
    }

    @Override
    public Sprite update(float delta, float alpha) {
        body.setLinearVelocity(path.getStep(body.getPosition()));
        animator.setHorizontalMovement(body.getLinearVelocity().x);
        return super.update(delta, alpha);
    }

    @Override
    public boolean isVolitionallyMoving() {
        return true;
    }
}
