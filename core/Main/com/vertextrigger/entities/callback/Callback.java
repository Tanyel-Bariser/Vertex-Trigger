package com.vertextrigger.entities.callback;

public abstract class Callback {

    final private Runnable runnable;

    boolean runAlready;

    Callback(final Runnable runnable) {
        this.runnable = runnable;
    }

    abstract public boolean isRunnable();

    final public void run() {
        runnable.run();
        runAlready = true;
    }
}
