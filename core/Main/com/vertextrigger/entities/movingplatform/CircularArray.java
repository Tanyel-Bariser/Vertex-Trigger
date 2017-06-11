package com.vertextrigger.entities.movingplatform;

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
        head++;
        if (head == size) {
            head = 0;
        }
        return elements[head];
    }

    T prev() {
        head--;
        if (head < 0) {
            head = size - 1;
        }
        return elements[head];
    }
}
