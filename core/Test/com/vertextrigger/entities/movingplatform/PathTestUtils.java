package com.vertextrigger.entities.movingplatform;

import java.lang.reflect.Field;

public class PathTestUtils {

    static boolean getHorizontal(final Path path) throws Exception {
        final Field horizontal = SimplePath.class.getDeclaredField("horizontal");
        horizontal.setAccessible(true);
        return horizontal.getBoolean(path);
    }

    static float getStart(final Path path) throws Exception {
        final Field start = SimplePath.class.getDeclaredField("start");
        start.setAccessible(true);
        return start.getFloat(path);
    }

    static float getEnd(final Path path) throws Exception {
        final Field end = SimplePath.class.getDeclaredField("end");
        end.setAccessible(true);
        return end.getFloat(path);
    }

    static SimplePath getTop(final Path path) throws Exception {
        final Field top = RectanglePath.class.getDeclaredField("top");
        top.setAccessible(true);
        return (SimplePath) top.get(path);
    }

    static SimplePath getRight(final Path path) throws Exception {
        final Field right = RectanglePath.class.getDeclaredField("right");
        right.setAccessible(true);
        return (SimplePath) right.get(path);
    }

    static SimplePath getBottom(final Path path) throws Exception {
        final Field bottom = RectanglePath.class.getDeclaredField("bottom");
        bottom.setAccessible(true);
        return (SimplePath) bottom.get(path);
    }

    static SimplePath getLeft(final Path path) throws Exception {
        final Field left = RectanglePath.class.getDeclaredField("left");
        left.setAccessible(true);
        return (SimplePath) left.get(path);
    }
}
