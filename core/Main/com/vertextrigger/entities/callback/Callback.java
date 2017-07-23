package com.vertextrigger.entities.callback;

/**
 * {@code Callback}s allow the game to execute specified code on certain conditions specified by its subclasses
 */
public abstract class Callback {

    final private Runnable runnable;
    final private RunLimit runLimit;

    Callback(final Runnable runnable) {
        this(RunLimit.single(), runnable);
    }

    Callback(final RunLimit runLimit, final Runnable runnable) {
        this.runnable = runnable;
        this.runLimit = runLimit;
    }

    abstract public boolean isRunnable();

    final public void run() {
        if (isRunnable() && runLimit.canRun()) {
            runnable.run();
            runLimit.doRun();
        }
    }
}
