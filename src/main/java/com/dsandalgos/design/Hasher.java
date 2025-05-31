package com.dsandalgos.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piyush Sharma
 */

public class Hasher<K,V> {

    private static class LinkedListNode<K, V> {
        public LinkedListNode<K,V> next;
        public LinkedListNode<K,V> prev;

        public K key;
        public V value;

        public LinkedListNode(K k, V v) {
            key = k;
            value = v;
        }
    }

    private List<LinkedListNode<K,V>> data;

    public Hasher(int capacity) {
        data = new ArrayList<LinkedListNode<K,V>>();
        for(int i = 0; i < capacity; ++i) {
            data.add(null);
        }
    }

    public V put(K key, V value) {
        LinkedListNode<K,V> node = getNodeForKey(key);

        // key exists
        if(node != null) {
            V prevValue = node.value;
            node.value = value;
            return prevValue;
        }

        // key did not exist
        node = new LinkedListNode<K,V>(key, value);
        int index = getIndexForKey(key);
        // there might still be something in the array but the list may not contain this key
        // even though node is null, that does not mean that data.get(index) is null
        // in that case, insert at head of the doubly linked list
        if(data.get(index) != null) {
            LinkedListNode<K,V> curHead = data.get(index);
            node.next = curHead;
            curHead.prev = node;
        }
        data.set(index, node);

        return null;
    }

    public V get(K key) {
        LinkedListNode<K,V> node = getNodeForKey(key);
        return node == null ? null : node.value;
    }

    public boolean contains(K key) {
        return get(key) == null ? false : true;
    }


    public V remove(K key) {
        LinkedListNode<K,V> node = getNodeForKey(key);
        if(node == null) {
            return null;
        }
        V removedValue = node.value;

        // in case we find a node and it is at the head of the list
        if(node.prev == null) {
            if(node.next != null) {
                node.next.prev = null;
            }

            int index = getIndexForKey(key);
            data.set(index, node.next);

        } else { // remove from the middle of the list
            node.prev.next = node.next;

            if(node.next != null) {
                node.next.prev = node.prev;
            }
        }

        return removedValue;
    }


    private int getIndexForKey(K key) {
        return Math.abs(key.hashCode() % data.size());
    }

    private LinkedListNode<K, V> getNodeForKey(K key) {
        LinkedListNode<K,V> node = data.get(getIndexForKey(key));

        while(node != null && node.key != key) {
            node = node.next;
        }
        return node;
    }

}
