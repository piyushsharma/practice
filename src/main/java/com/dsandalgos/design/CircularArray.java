package com.dsandalgos.design;

import java.util.Iterator;

/**
 * Created by Piyush Sharma
 */

// Implement a circular array that supports an array like data structure which can be efficiently rotated
//    the class should use a generic type and should support iteration via the standard
//    for(Obj o : circularArray) notation

public class CircularArray<T> implements Iterator<T> {

    private int head = 0;
    public T[] items;


    /*
        - In java, we cannot create an array of generic type, we either create a List<T> or cast the array
        - negative % positive still gives a negative number, so we add items.length to index first

    -----------------
    |   |   |   |   |      - Head = 0,  set at index -2 => converted to -2 + 4 => 2, (0 + 2) % 4 => 2
    -----------------      - say we rotate the array by 2 places (shift right = 2)
      0   1   2   3             head will be converted to => (0 + 2) % 4 => 2 for future operations
                           - now set at index 1 => convertToRotatedIndex(1) => (2 + 1) % 4 => 3

    */

    public CircularArray(int capacity) {
        this.items = (T[]) new Object[capacity];
    }

    @Override
    public boolean hasNext() {
        return head > 0 && head < items.length - 1;
    }

    @Override
    public T next() {                
        if(head < 0 || head >= items.length) {
            throw new java.lang.IndexOutOfBoundsException("Circular array index out of bounds");
        }
        T answer = items[convertToRotatedIndex(head)];
        head++;
        return answer;
    }

    public int convertToRotatedIndex(int index) {
        if(index < 0) {
            index += items.length;
        }

        return (head + index) % items.length;
    }

    public void rotate(int shiftRight) {
        head = convertToRotatedIndex(shiftRight);
    }

    public T get(int index) {
        if(index < 0 || index >= items.length) {
            throw new java.lang.IndexOutOfBoundsException("Circular array index out of bounds");
        }

        return items[convertToRotatedIndex(index)];
    }

    public void set(int index, T t) {
        items[convertToRotatedIndex(index)] = t;
    }

    // @Override
    // public Iterator<T> iterator() {
    //     return new CircularArrayIterator<T>(this);
    // }

}
