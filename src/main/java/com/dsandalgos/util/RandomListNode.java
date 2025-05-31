package com.dsandalgos.util;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

// Definition for singly-linked list with a random pointer.
public class RandomListNode {
    public int label;
    public RandomListNode next, random;
    public RandomListNode(int x) {
        this.label = x;
        this.next = null;
        this.random = null;
    }
}
