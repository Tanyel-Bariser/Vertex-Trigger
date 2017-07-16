package com.vertextrigger.entities.callback;

import com.vertextrigger.entities.Mortal;

public class DeathCallback extends Callback {

    final private Mortal mortal;

    public DeathCallback(final Runnable runnable, final Mortal mortal) {
        super(runnable);
        this.mortal = mortal;
    }

    @Override
    public boolean isRunnable() {
        return mortal.isDead() && !runAlready;
    }
}
