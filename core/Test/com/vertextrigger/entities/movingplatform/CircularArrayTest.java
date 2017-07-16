package com.vertextrigger.entities.movingplatform;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CircularArrayTest {

    @Test
    public void testCircularArrayOfSizeThree() {
        final CircularArray<String> arr = new CircularArray<>("one", "two", "three");
        assertThat(arr.next(), is("one"));
        assertThat(arr.next(), is("two"));
        assertThat(arr.next(), is("three"));
        assertThat(arr.next(), is("one"));
        assertThat(arr.next(), is("two"));
        assertThat(arr.next(), is("three"));

        assertThat(arr.prev(), is("two"));
        assertThat(arr.prev(), is("one"));
        assertThat(arr.prev(), is("three"));
        assertThat(arr.prev(), is("two"));
        assertThat(arr.prev(), is("one"));
        assertThat(arr.prev(), is("three"));

        assertThat(arr.next(), is("one"));
        assertThat(arr.prev(), is("three"));
        assertThat(arr.next(), is("one"));
        assertThat(arr.next(), is("two"));
        assertThat(arr.prev(), is("one"));
        assertThat(arr.prev(), is("three"));
    }

    @Test
    public void testCircularArrayPrevFirst() {
        final CircularArray<String> arr = new CircularArray<>("one", "two", "three");
        assertThat(arr.prev(), is("three"));
        assertThat(arr.prev(), is("two"));
        assertThat(arr.next(), is("three"));
        assertThat(arr.prev(), is("two"));
        assertThat(arr.prev(), is("one"));
        assertThat(arr.next(), is("two"));
        assertThat(arr.prev(), is("one"));
        assertThat(arr.prev(), is("three"));
    }

    @Test
    public void testCircularArrayOfSizeOne() {
        final CircularArray<String> arr = new CircularArray<>("one");
        assertThat(arr.next(), is("one"));
        assertThat(arr.prev(), is("one"));
        assertThat(arr.next(), is("one"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCircularArrayOfSizeZero() {
        new CircularArray<String>();
    }
}
