package com.dsandalgos.linkedlist;

import com.dsandalgos.util.ListNode;
import com.dsandalgos.util.DataStructureUtility;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */

public class ReorderList {

    public void reorderList(ListNode head) {
        if(head == null || head.next == null)
            return;

        ListNode slow = head;
        ListNode fast = head;

        /* Get the middle of the node */
        while(fast.next != null) {
            fast = fast.next;
            // We do not go till null for fast, only till the last node of the list
            if(fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }

        ListNode midNode = slow.next;
        slow.next = null; // break the list
        midNode = reverseList(midNode);

        ListNode cur = head;

        while(cur != null && midNode != null) {
            ListNode one = cur.next;
            ListNode two = midNode.next;

            cur.next = midNode;
            midNode.next = one;

            cur = one;
            midNode = two;
        }
    }

    private ListNode reverseList(ListNode head) {
        ListNode next;
        ListNode prev = null;
        ListNode cur = head;

        while(cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }


    public static void main(String[] args) {
        ReorderList r = new ReorderList();

        DataStructureUtility p = new DataStructureUtility();
        ListNode l = new ListNode(1);
        l = p.insertAtEnd(l, new ListNode(2));
        l = p.insertAtEnd(l, new ListNode(3));
        l = p.insertAtEnd(l, new ListNode(4));
        l = p.insertAtEnd(l, new ListNode(5));
        l = p.insertAtEnd(l, new ListNode(6));

        r.reorderList(l);

        while (l != null) {
            System.out.printf("%d ", l.val);
            l = l.next;
        }
    }
}
