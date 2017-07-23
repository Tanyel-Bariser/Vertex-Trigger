package com.vertextrigger.entities.callback;

/**
 * Class that can be given as an optional constructor param to {@link Callback} and is used to determine how many times one can run
 */
public class RunLimit {

    private int times;

    private RunLimit(final int times) {
        this.times = times;
    }

    public static RunLimit unlimited() {
        return new RunLimit(Integer.MAX_VALUE);
    }

    public static RunLimit single() {
        return new RunLimit(1);
    }

    public static RunLimit of(final int times) {
        return new RunLimit(times);
    }

    boolean canRun() {
        return times > 0;
    }

    void doRun() {
        times--;
    }
}
