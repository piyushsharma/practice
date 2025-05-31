package com.dsandalgos.tophundred;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Design and implement a data structure for Least Recently Used (LRU) cache.
It should support the following operations: get and set.

get(key) -  Get the value (will always be positive) of the key
            if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present.
            When the cache reached its capacity, it should invalidate the least recently
            used item before inserting a new item.
*/


public class LRUCache {

    HashMap<Integer, CacheNode> cache = new HashMap<Integer, CacheNode>();
    class CacheNode {
        int val;
        int key;
        CacheNode next = null;
        CacheNode prev = null;
        CacheNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    CacheNode head = null;
    CacheNode tail = null;
    private int capacity = 0;
    private int count = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        CacheNode keyNode = cache.get(key);
        if(keyNode == null) {
            return -1;
        }
        deleteNode(keyNode);
        insertAtHead(keyNode);
        return keyNode.val;
    }

    /* - Replace the old value with new value
       - Remove from cache if hits the capacity
       - Remember not to include capacity check when just replacing old value
     */
    public void set(int key, int value) {
        CacheNode node = cache.get(key);
        /* No need to reduce count, if we already have the key,
          we will not exceed capacity, just replace old value and put the node at head
         */
        if(node != null) {
            deleteNode(node);
            node.val = value;
            insertAtHead(node);
            return;
        }

        if(count >= capacity) {
            cache.remove(tail.key);
            deleteNode(tail);
            --count;
        }
        node = new CacheNode(key, value);
        insertAtHead(node);
        cache.put(key, node);
        ++count;
    }

    public void deleteNode(CacheNode node) {
        if(node == null)
            return;

        CacheNode prev = node.prev;
        CacheNode next = node.next;

        if(prev == null) {
            head = next;
        } else {
            prev.next = next;
        }

        if(next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }
    }


    public void insertAtHead(CacheNode node) {
        node.next = head;
        node.prev = null;
        if(head != null) {
            head.prev = node;
        }
        if(tail == null) {
            tail = node;
        }
        head = node;
    }

    public static void main(String[] args) {
//        LRUCache l = new LRUCache(1);
//        l.set(2, 1);
//        System.out.println(l.get(2));
//        l.set(3, 2);
//        System.out.println(l.get(2));
//        System.out.println(l.get(3));

        LRUCache l = new LRUCache(2);
        l.set(2, 1);
        l.set(1, 1);
        l.set(2, 3);
        l.set(4, 1);
        System.out.println(l.get(1));
        System.out.println(l.get(2));

//        2,[get(2),set(2,6),get(1),set(1,5),set(1,2),get(1),get(2)]
//        10,[set(10,13),set(3,17),set(6,11),set(10,5),set(9,10),get(13),set(2,19),get(2),get(3),set(5,25),get(8),set(9,22),set(5,5),set(1,30),get(11),set(9,12),get(7),get(5),get(8),get(9),set(4,30),set(9,3),get(9),get(10),get(10),set(6,14),set(3,1),get(3),set(10,11),get(8),set(2,14),get(1),get(5),get(4),set(11,4),set(12,24),set(5,18),get(13),set(7,23),get(8),get(12),set(3,27),set(2,12),get(5),set(2,9),set(13,4),set(8,18),set(1,7),get(6),set(9,29),set(8,21),get(5),set(6,30),set(1,12),get(10),set(4,15),set(7,22),set(11,26),set(8,17),set(9,29),get(5),set(3,4),set(11,30),get(12),set(4,29),get(3),get(9),get(6),set(3,4),get(1),get(10),set(3,29),set(10,28),set(1,20),set(11,13),get(3),set(3,12),set(3,8),set(10,9),set(3,26),get(8),get(7),get(5),set(13,17),set(2,27),set(11,15),get(12),set(9,19),set(2,15),set(3,16),get(1),set(12,17),set(9,1),set(6,19),get(4),get(5),get(5),set(8,1),set(11,7),set(5,2),set(9,28),get(1),set(2,2),set(7,4),set(4,22),set(7,24),set(9,26),set(13,28),set(11,26)]
    }
}