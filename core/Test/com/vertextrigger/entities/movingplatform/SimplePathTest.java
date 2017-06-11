package com.vertextrigger.entities.movingplatform;

import com.badlogic.gdx.math.Vector2;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimplePathTest {

    @Test
    public void horizontalPathStartingAtLeft() throws Exception {
        final Path path = new SimplePath(new Vector2(0, 0), new Vector2(5, 0));
        assertThat(getHorizontal(path), is(true));
        assertThat(getStart(path), is(0f));
        assertThat(getEnd(path), is(5f));

        final Vector2 rightStep = path.getStep(new Vector2(0, 0));
        assertThat(rightStep, is(new Vector2(Path.DEFAULT_STEP_SIZE, 0)));
        final Vector2 leftStep = path.getStep(new Vector2(5, 0));
        assertThat(leftStep, is(new Vector2(-Path.DEFAULT_STEP_SIZE, 0)));
    }

    @Test
    public void horizontalPathStartingAtRight() throws Exception {
        final Path path = new SimplePath(new Vector2(-5, -5), new Vector2(-10, -5));
        assertThat(getHorizontal(path), is(true));
        assertThat(getStart(path), is(-5f));
        assertThat(getEnd(path), is(-10f));

        final Vector2 leftStep = path.getStep(new Vector2(-5, -5));
        assertThat(leftStep, is(new Vector2(-Path.DEFAULT_STEP_SIZE, 0)));
        final Vector2 rightStep = path.getStep(new Vector2(-10, -5));
        assertThat(rightStep, is(new Vector2(Path.DEFAULT_STEP_SIZE, 0)));
    }

    @Test
    public void verticalPathStartingAtBottom() throws Exception {
        final Path path = new SimplePath(new Vector2(0, 0), new Vector2(0, 5));
        assertThat(getHorizontal(path), is(false));
        assertThat(getStart(path), is(0f));
        assertThat(getEnd(path), is(5f));

        final Vector2 upStep = path.getStep(new Vector2(0, 0));
        assertThat(upStep, is(new Vector2(0, Path.DEFAULT_STEP_SIZE)));
        final Vector2 downStep = path.getStep(new Vector2(0, 5));
        assertThat(downStep, is(new Vector2(0, -Path.DEFAULT_STEP_SIZE)));
    }

    @Test
    public void verticalPathStartingAtTop() throws Exception {
        final Path path = new SimplePath(new Vector2(0, 0), new Vector2(0, -5));
        assertThat(getHorizontal(path), is(false));
        assertThat(getStart(path), is(0f));
        assertThat(getEnd(path), is(-5f));

        final Vector2 downStep = path.getStep(new Vector2(0, 0));
        assertThat(downStep, is(new Vector2(0, -Path.DEFAULT_STEP_SIZE)));
        final Vector2 upStep = path.getStep(new Vector2(0, -5));
        assertThat(upStep, is(new Vector2(0, Path.DEFAULT_STEP_SIZE)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void verticalAndHorizontalPath() throws Exception {
        new SimplePath(new Vector2(0, 5), new Vector2(0, 5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void notVerticalAndNotHorizontalPath() throws Exception {
        new SimplePath(new Vector2(0, 0), new Vector2(5, 5));
    }

    private boolean getHorizontal(final Path path) throws Exception {
        final Field horizontal = SimplePath.class.getDeclaredField("horizontal");
        horizontal.setAccessible(true);
        return horizontal.getBoolean(path);
    }

    private float getStart(final Path path) throws Exception {
        final Field start = SimplePath.class.getDeclaredField("start");
        start.setAccessible(true);
        return start.getFloat(path);
    }

    private float getEnd(final Path path) throws Exception {
        final Field end = SimplePath.class.getDeclaredField("end");
        end.setAccessible(true);
        return end.getFloat(path);
    }
}
