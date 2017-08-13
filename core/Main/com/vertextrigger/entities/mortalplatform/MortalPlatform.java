package com.vertextrigger.entities.mortalplatform;

import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.entities.AbstractEntity;
import com.vertextrigger.entities.Animator;
import com.vertextrigger.entities.Mortal;
import com.vertextrigger.util.GameObjectSize;

/**
 * Abstract class for platforms that can die or disappear
 */
public abstract class MortalPlatform extends AbstractEntity implements Mortal {

    final private GameObjectSize size;

    public MortalPlatform(final Body body, final Animator animator, final GameObjectSize size) {
        super(body, animator);
        this.size = size;
    }

    @Override
    public void die() {
        // override die so subclasses can choose to ignore it (e.g. FadingPlatform does not need it)
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
