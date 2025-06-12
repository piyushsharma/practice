package com.tosort;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */
public class BSTMap<K,V> {

    private BSTNode<K,V> root;

    private Comparator<K> comparator;

    public BSTMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    // get value for key
    public V get(K key) {
        if(root == null)
            return null;
        // search bst for key
        return root.lookup(key, comparator);
    }


    // add or replace key,value pair:
    // returns previous value, if any
    public V set(K key, V value) {

        if (root == null) {
            root = new BSTNode<K, V>(key, value);
            return null;
        } else {
            return root.insert(key, value, comparator);
        }
    }

    // remove key (and its associated value)
    // returns the value associated with the deleted key,
    // or null if no such key exists
    public V remove(K key) {
        if (root == null) {
            // tree is empty
            return null;
        } else {
            V old = root.lookup(key, comparator);
            if (old == null) {
                // The key does not exist in the tree.
                return null;
            } else {
                root = root.remove(key, comparator);
                return old;
            }
        }
    }

}
