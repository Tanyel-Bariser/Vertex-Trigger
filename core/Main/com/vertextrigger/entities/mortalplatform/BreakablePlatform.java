package com.vertextrigger.entities.mortalplatform;

import com.badlogic.gdx.physics.box2d.Body;
import com.vertextrigger.entities.Animator;
import com.vertextrigger.util.GameObjectSize;

/**
 * A {@link MortalPlatform} that breaks after being shot
 */
public class BreakablePlatform extends MortalPlatform {

    public BreakablePlatform(final Body body, final Animator animator, final GameObjectSize size) {
        super(body, animator, size);
    }

    // todo
}
