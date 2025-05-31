package com.dsandalgos.design;

import java.util.Iterator;

/**
 * Created by Piyush Sharma
 */

/*

Given an Iterator class interface with methods: next() and hasNext(),
design and implement a PeekingIterator that support the peek() operation --
it essentially peek() at the element that will be returned by the next call to next().

Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].

Call next() gets you 1, the first element in the list.
Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.

*/

public class PeekingIterator implements Iterator<Integer> {

    // Cache the next value
    private Iterator<Integer> curIt;
    private Integer nextElement;

    public PeekingIterator(Iterator<Integer> iterator) {
        curIt = iterator;
        if(iterator.hasNext()) {
            nextElement = iterator.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return nextElement;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        int oldNextElement = nextElement;
        // update next
        if(curIt.hasNext()) {
            nextElement = curIt.next();
        } else {
            // note you cannot throw exception here, since oldNextElement is present
            // you also need it to decide hasNext()
            nextElement = null;
        }
        return oldNextElement;
    }

    @Override
    public boolean hasNext() {
        return nextElement != null;
    }

    // Another Version: Using Queue

//    public Queue<Integer> qIt;
//
//    public PeekingIterator(Iterator<Integer> iterator) {
//        this.qIt = new LinkedList<>();
//        while(iterator.hasNext()) {
//            qIt.add(iterator.next());
//        }
//    }
//
//    // Returns the next element in the iteration without advancing the iterator.
//    public Integer peek() {
//        return qIt.peek();
//    }
//
//    // hasNext() and next() should behave the same as in the Iterator interface.
//    // Override them if needed.
//    @Override
//    public Integer next() {
//        if(qIt.isEmpty()) throw new NoSuchElementException();
//        return qIt.poll();
//    }
//
//    @Override
//    public boolean hasNext() {
//        return !qIt.isEmpty();
//    }

}
