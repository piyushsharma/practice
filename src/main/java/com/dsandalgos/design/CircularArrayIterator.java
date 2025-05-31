package com.dsandalgos.design;

import java.util.Iterator;

/**
 * Created by Piyush Sharma
 */
public class CircularArrayIterator<TI> implements Iterator<TI> {

    private CircularArray<TI> _arr;
    private TI[] _items;

    // current point to the offset from rotated head, not from actual start
    private int _current = -1;

    public CircularArrayIterator(CircularArray<TI> array) {
        _items = array.items;
        _arr = array;
    }

    @Override
    public boolean hasNext() {
        return _current < _items.length - 1;
    }

    @Override
    public TI next() {
        _current++;
        TI item = (TI) _items[_arr.convertToRotatedIndex(_current)];
        return item;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("");
    }
}
