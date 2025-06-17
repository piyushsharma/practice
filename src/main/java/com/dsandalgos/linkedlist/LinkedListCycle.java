package com.dsandalgos.linkedlist;

/**
 * Created by Piyush Sharma on 2/27/15.
 */

/* Problem Statement:
    Given a linked list, determine if it has a cycle in it.
    Follow up: Can you solve it without using extra space?
*/

public class LinkedListCycle {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) {
                return true;
            }
        }
        return false;
    }
}
