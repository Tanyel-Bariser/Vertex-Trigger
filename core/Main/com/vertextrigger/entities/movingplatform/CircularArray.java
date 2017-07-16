package com.vertextrigger.entities.movingplatform;

/**
 * Circular array used in {@link RectanglePath} implementation
 *
 * Array start and end are joined in an imaginary circle so elements can be retrieved forever, looping around clockwise and anticlockwise as needed
 * E.g. underlying array is [1, 2, 3, 4]. Five invocations of {@code next()} in a row return 1, 2, 3, 4, and then 1 again. Arrows represent {@code head}
 *
 *          ↓                                               ↓
 *          1          1            1            1          1
 *        4   2      4   2 ←      4   2      → 4   2      4   2
 *          3          3            3            3          3
 *                                  ↑
 */
class CircularArray<T> {

    final private T[] elements;
    final private int size;

    private int head = -1;

    CircularArray(final T... elements) {
        if (elements.length == 0) {
            throw new IllegalArgumentException("Array size must be greater than 0");
        }

        this.elements = elements;
        this.size = elements.length;
    }

    T next() {
        if (++head == size) {
            head = 0;
        }
        return elements[head];
    }

    T prev() {
        if (--head < 0) {
            head = size - 1;
        }
        return elements[head];
    }
}
