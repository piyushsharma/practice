package com.tosort;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */
public class BSTNode<K,V> {

    public K key;
    public V value;
    public BSTNode<K, V> left, right; // children

    BSTNode(K k, V v) {
        key = k;
        value = v;
    }


    public V lookup(K key, Comparator<K> keyComp) {
        int cmp = keyComp.compare(key, key);
        if (cmp == 0) {
            return value;
        } else if (cmp < 0 && left != null) {
            return left.lookup(key, keyComp);
        } else if (cmp > 0 && right != null) {
            return right.lookup(key, keyComp);
        } else {
            return null; // key not present in tree
        }
    }


    public V insert(K key, V value, Comparator<K> keyComp) {
        int cmp = keyComp.compare(key, this.key);
        if (cmp == 0) {
            // replace value
            V old = this.value;
            this.value = value;
            return old;
        } else if (cmp < 0) {
            if (left == null) {
                left = new BSTNode<K, V>(key, value);
                return null;
            } else {
                return left.insert(key, value, keyComp);
            }
        } else { // cmp > 0
            if (right == null) {
                right = new BSTNode<K, V>(key, value);
                return null;
            } else {
                return right.insert(key, value, keyComp);
            }
        }
    }

    public BSTNode<K, V> remove(K key, Comparator<K> keyComp) {
        int cmp = keyComp.compare(key, this.key);
        if (cmp == 0) {
            if (left == null) {
                // right child becomes root of this subtree
                return right;
            } else if (right == null) {
                // left child becomes root of this subtree
                return left;
            } else {
                // hard case: we have both left and right children

                // find node containing min key in right subtree
                BSTNode<K, V> minOfRightSubtree = right.findMinNode();

                // remove node containing min key in right subtree
                right = right.removeMinNode();

                // copy its key and value into this node
                this.key = minOfRightSubtree.key;
                this.value = minOfRightSubtree.value;

                // this node is still the root of this subtree
                return this;
            }
        } else if (cmp < 0) {
            // remove from left subtree
            left = left.remove(key, keyComp);
            return this;
        } else {
            // remove from right subtree
            right = right.remove(key, keyComp);
            return this;
        }
    }

    private BSTNode<K, V> findMinNode() {
        if (left == null) {
            return this;
        } else {
            return left.findMinNode();
        }
    }

    private BSTNode<K, V> removeMinNode() {
        if (left == null) {
            return right;
        } else {
            left = left.removeMinNode();
            return this;
        }
    }


//    BinarySearchTree<K, V>{
//        .......... // balanced binary search tree with K as key and V as the value
//    }
//
//    TimeStampHashMap<K, V>{
//        Map<K, BinarySearchTree<Float, V>> keyToBSTMap;
//
//        V get(K k, Float time){
//            return keyToBSTMap.get(k).binarySearch(time).getValue();
//        }
//
//    void put(K k , Float time, V value){
//        keyToBSTMap.get(k).insert(time, value);
//    }
//  }

}
