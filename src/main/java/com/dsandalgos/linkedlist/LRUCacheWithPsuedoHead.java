package com.dsandalgos.linkedlist;

import java.util.HashMap;
import java.util.Map;

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


public class LRUCacheWithPsuedoHead {

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

    Map<Integer, CacheNode> cache;
    CacheNode head;
    CacheNode tail;
    int capacity;

    public LRUCacheWithPsuedoHead(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();

        this.head = new CacheNode(-1, -1);
        this.tail = new CacheNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        CacheNode keyNode = cache.get(key);
        if(keyNode == null) {
            return -1;
        }
        delete(keyNode);
        insert(keyNode);
        return keyNode.val;
    }

    /* - Replace the old value with new value
       - Remove from cache if hits the capacity
       - Remember not to include capacity check when just replacing old value
     */
    public void put(int key, int value) {
        CacheNode node = cache.get(key);
        if(node != null) {
            delete(node);
        }

        CacheNode newNode = new CacheNode(key, value);
        cache.put(key, newNode);
        insert(newNode);

        if(cache.size() > capacity) {
            CacheNode nodeToDelete = head.next;
            delete(nodeToDelete);
            cache.remove(nodeToDelete.key);
        }
    }

    public void delete(CacheNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


    public void insert(CacheNode node) {
        CacheNode prevEnd = tail.prev;
        prevEnd.next = node;
        node.prev = prevEnd;
        node.next = tail;
        tail.prev = node;
    }

    public static void main(String[] args) {
//        LRUCache l = new LRUCache(1);
//        l.set(2, 1);
//        System.out.println(l.get(2));
//        l.set(3, 2);
//        System.out.println(l.get(2));
//        System.out.println(l.get(3));

        LRUCacheWithPsuedoHead l = new LRUCacheWithPsuedoHead(2);
        l.put(2, 1);
        l.put(1, 1);
        l.put(2, 3);
        l.put(4, 1);
        System.out.println(l.get(1));
        System.out.println(l.get(2));

//        2,[get(2),set(2,6),get(1),set(1,5),set(1,2),get(1),get(2)]
//        10,[set(10,13),set(3,17),set(6,11),set(10,5),set(9,10),get(13),set(2,19),get(2),get(3),set(5,25),get(8),set(9,22),set(5,5),set(1,30),get(11),set(9,12),get(7),get(5),get(8),get(9),set(4,30),set(9,3),get(9),get(10),get(10),set(6,14),set(3,1),get(3),set(10,11),get(8),set(2,14),get(1),get(5),get(4),set(11,4),set(12,24),set(5,18),get(13),set(7,23),get(8),get(12),set(3,27),set(2,12),get(5),set(2,9),set(13,4),set(8,18),set(1,7),get(6),set(9,29),set(8,21),get(5),set(6,30),set(1,12),get(10),set(4,15),set(7,22),set(11,26),set(8,17),set(9,29),get(5),set(3,4),set(11,30),get(12),set(4,29),get(3),get(9),get(6),set(3,4),get(1),get(10),set(3,29),set(10,28),set(1,20),set(11,13),get(3),set(3,12),set(3,8),set(10,9),set(3,26),get(8),get(7),get(5),set(13,17),set(2,27),set(11,15),get(12),set(9,19),set(2,15),set(3,16),get(1),set(12,17),set(9,1),set(6,19),get(4),get(5),get(5),set(8,1),set(11,7),set(5,2),set(9,28),get(1),set(2,2),set(7,4),set(4,22),set(7,24),set(9,26),set(13,28),set(11,26)]
    }
}