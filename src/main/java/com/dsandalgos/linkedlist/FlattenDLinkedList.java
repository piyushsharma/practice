package com.dsandalgos.linkedlist;


import java.util.Stack;

/**
 * You are given a doubly linked list which in addition to the next and previous pointers,
 * it could have a child pointer, which may or may not point to a separate doubly linked list.
 *
 * These child lists may have one or more children of their own, and so on,
 * to produce a multilevel data structure, as shown in the example below.
 *
 * Flatten the list so that all the nodes appear in a single-level, doubly linked list.
 * You are given the head of the first level of the list.
 *
 * Example:
 *
 * Input:
 *  1---2---3---4---5---6--NULL
 *          |
 *          7---8---9---10--NULL
 *              |
 *              11--12--NULL
 *
 * Output:
 * 1-2-3-7-8-11-12-9-10-4-5-6-NULL
 *
 *
 * Explanation for the above example:
 *
 * Given the following multilevel doubly linked list:
 *
 * We should return the following flattened doubly linked list:
 */

public class FlattenDLinkedList {

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    };

    public Node flatten(Node head) {

        if(head == null) {
            return head;
        }

        Node psuedoHead = new Node(0, null, head, null);
        Node cur = psuedoHead;
        Node prev = psuedoHead;

        Stack<Node> stack = new Stack<>();
        stack.push(head);

        while(!stack.isEmpty()) {

            cur = stack.pop();
            prev.next = cur;
            cur.prev = prev;

            if(cur.next != null) {
                stack.push(cur.next);
            }
            if(cur.child != null) {
                stack.push(cur.child);
                cur.child = null;
            }
            prev = cur;
        }

        psuedoHead.next.prev = null;
        return psuedoHead.next;
    }


    public Node flattenV2(Node head) {

        if(head == null) {
            return head;
        }

        Node cur = head;
        Stack<Node> stack = new Stack<>();

        while(cur != null) {

            Node child = cur.child;
            if(child != null) {
                if(cur.next != null) {
                    stack.push(cur.next);
                }

                child.prev = cur;
                cur.next = child;
                cur.child = null;
                cur = child;

            } else {

                Node next = cur.next;
                if(next == null && !stack.isEmpty()) {
                    next = stack.pop();
                    cur.next = next;
                    next.prev = cur;
                }
                cur = next;
            }

        }

        return head;
    }


    public static void main(String[] args) {

/*
 * Example:
 *
 * Input:
 *  1---2---3---4---5---6--NULL
 *          |
 *          7---8---9---10--NULL
 *              |
 *              11--12--NULL
 *
 */

        Node head = new Node(1, null, null, null);
        Node cur = head;
        Node child1 = null;
        Node child2 = null;
        Node end1 = null;
        Node end2 = null;

        for(int i = 2; i < 13; ++i) {

            Node next = new Node(i, null, null, null);

            cur.next = next;
            next.prev = cur;
            if(i == 7) {
                child1 = next;
            }
            if(i == 11) {
                child2 = next;
            }
            if(i == 6) {
                end1 = next;
            }
            if(i == 10) {
                end2 = next;
            }

            cur = next;
        }

        cur = head;
        while(cur != null) {
            if(cur.val == 3) {
                cur.child = child1;
                child1.prev = null;
            }
            if(cur.val == 8) {
                cur.child = child2;
                child2.prev = null;
            }

            cur = cur.next;
        }
        end1.next = null;
        end2.next = null;


        Node updated = new FlattenDLinkedList().flatten(head);

        while(updated != null) {
            System.out.print(" " + updated.val + " ");
            updated = updated.next;
        }

        System.out.println("done");

    }






}
