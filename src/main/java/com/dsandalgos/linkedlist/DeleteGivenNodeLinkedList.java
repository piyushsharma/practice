package com.dsandalgos.linkedlist;

import com.dsandalgos.util.ListNode;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3,
the linked list should become 1 -> 2 -> 4 after calling your function.
 */

public class DeleteGivenNodeLinkedList {
    public void deleteNode(ListNode node) {
        // not tail, so node.next is not null
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
