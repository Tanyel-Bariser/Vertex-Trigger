package com.vertextrigger.entities.callback;

import com.vertextrigger.entities.Mortal;

/**
 * Implementation of {@link Callback} that triggers on a {@link Mortal}'s death
 */
public class DeathCallback extends Callback {

    final private Mortal mortal;

    public DeathCallback(final Runnable runnable, final Mortal mortal) {
        super(runnable);
        this.mortal = mortal;
    }

    public DeathCallback(final RunLimit runLimit, final Runnable runnable, final Mortal mortal) {
        super(runLimit, runnable);
        this.mortal = mortal;
    }

    @Override
    public boolean isRunnable() {
        return mortal.isDead();
    }
}
