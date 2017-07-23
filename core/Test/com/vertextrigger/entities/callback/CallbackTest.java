package com.vertextrigger.entities.callback;

import com.badlogic.gdx.math.Vector2;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CallbackTest {

    @Test
    public void testCallbackSingleRun() {
        final AtomicInteger runCounter = new AtomicInteger(0);
        final Callback callback = new PositionCallback(RunLimit.single(), new Runnable() {
            @Override
            public void run() {
                runCounter.incrementAndGet();
            }
        }, new Vector2(), new Vector2());

        for (int i = 0; i < 100; i++) {
            callback.run();
        }

        assertThat(runCounter.get(), is(1));
    }

    @Test
    public void testCallbackUnlimitedRun() {
        final AtomicInteger runCounter = new AtomicInteger(0);
        final Callback callback = new PositionCallback(RunLimit.unlimited(), new Runnable() {
            @Override
            public void run() {
                runCounter.incrementAndGet();
            }
        }, new Vector2(), new Vector2());

        for (int i = 0; i < 100; i++) {
            callback.run();
        }

        assertThat(runCounter.get(), is(100));
    }

    @Test
    public void testCallbackSpecifiedLimitRun() {
        final AtomicInteger runCounter = new AtomicInteger(0);
        final Callback callback = new PositionCallback(RunLimit.of(5), new Runnable() {
            @Override
            public void run() {
                runCounter.incrementAndGet();
            }
        }, new Vector2(), new Vector2());

        for (int i = 0; i < 100; i++) {
            callback.run();
        }

        assertThat(runCounter.get(), is(5));
    }
}
