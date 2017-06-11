package com.vertextrigger.entities.movingplatform;

import com.badlogic.gdx.math.Vector2;
import org.junit.Test;

import static com.vertextrigger.entities.movingplatform.PathTestUtils.getEnd;
import static com.vertextrigger.entities.movingplatform.PathTestUtils.getHorizontal;
import static com.vertextrigger.entities.movingplatform.PathTestUtils.getStart;
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
}
