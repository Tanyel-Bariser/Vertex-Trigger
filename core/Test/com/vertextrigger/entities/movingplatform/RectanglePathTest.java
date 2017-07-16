package com.vertextrigger.entities.movingplatform;

import com.badlogic.gdx.math.Vector2;
import org.junit.Test;

import java.lang.reflect.Field;

import static com.vertextrigger.entities.movingplatform.Path.DEFAULT_STEP_SIZE;
import static com.vertextrigger.entities.movingplatform.PathTestUtils.getBottom;
import static com.vertextrigger.entities.movingplatform.PathTestUtils.getLeft;
import static com.vertextrigger.entities.movingplatform.PathTestUtils.getRight;
import static com.vertextrigger.entities.movingplatform.PathTestUtils.getTop;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RectanglePathTest {

    final static private SimplePath EXPECTED_TOP_PATH = new SimplePath(new Vector2(0, 0), new Vector2(5, 0));
    final static private SimplePath EXPECTED_RIGHT_PATH = new SimplePath(new Vector2(5, 0), new Vector2(5, -5));
    final static private SimplePath EXPECTED_BOTTOM_PATH = new SimplePath(new Vector2(5, -5), new Vector2(0, -5));
    final static private SimplePath EXPECTED_LEFT_PATH = new SimplePath(new Vector2(0, -5), new Vector2(0, 0));

    final static private Vector2 EXPECTED_TOP_STEP = new Vector2(DEFAULT_STEP_SIZE, 0);
    final static private Vector2 EXPECTED_RIGHT_STEP = new Vector2(0, -DEFAULT_STEP_SIZE);
    final static private Vector2 EXPECTED_BOTTOM_STEP = new Vector2(-DEFAULT_STEP_SIZE, 0);
    final static private Vector2 EXPECTED_LEFT_STEP = new Vector2(0, DEFAULT_STEP_SIZE);

    private RectanglePath path = new RectanglePath(new Vector2(0, 0), new Vector2(5, -5), true);
    private Vector2 bodyPosition = new Vector2(0, 0);

    @Test
    public void test5x5Square() throws Exception {
        assertThat(getTop(path), is(EXPECTED_TOP_PATH));
        assertThat(getRight(path), is(EXPECTED_RIGHT_PATH));
        assertThat(getBottom(path), is(EXPECTED_BOTTOM_PATH));
        assertThat(getLeft(path), is(EXPECTED_LEFT_PATH));

        for (int i = 0; i < 7; i++) {
            assertStepAndAdvanceBody(EXPECTED_TOP_PATH, EXPECTED_TOP_STEP);
        }

        for (int i = 0; i < 7; i++) {
            assertStepAndAdvanceBody(EXPECTED_RIGHT_PATH, EXPECTED_RIGHT_STEP);
        }

        for (int i = 0; i < 7; i++) {
            assertStepAndAdvanceBody(EXPECTED_BOTTOM_PATH, EXPECTED_BOTTOM_STEP);
        }

        for (int i = 0; i < 7; i++) {
            assertStepAndAdvanceBody(EXPECTED_LEFT_PATH, EXPECTED_LEFT_STEP);
        }

        // and we're back to the top side!
        assertStepAndAdvanceBody(EXPECTED_TOP_PATH, EXPECTED_TOP_STEP);
    }

    private void assertStepAndAdvanceBody(final SimplePath expectedSide, final Vector2 expectedStep) throws Exception {
        final Vector2 step = path.getStep(bodyPosition);
        assertThat(getCurrentSide(path), is(expectedSide));
        assertThat(step, is(expectedStep));
        bodyPosition.add(step);
    }

    private SimplePath getCurrentSide(final RectanglePath rectanglePath) throws Exception {
        final Field currentSide = RectanglePath.class.getDeclaredField("currentSide");
        currentSide.setAccessible(true);
        return (SimplePath) currentSide.get(rectanglePath);
    }
}
