package com.dsandalgos.linkedlist;

import com.dsandalgos.util.ListNode;
import com.dsandalgos.util.DataStructureUtility;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5
 */
public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode cur = head;
        ListNode prev = null;

        while(cur != null) {
            if(cur.val == val) {
                if(prev == null) head = cur.next;
                else prev.next = cur.next;
            } else { // note: we change prev only if there is no match
                prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        RemoveLinkedListElements p = new RemoveLinkedListElements();
        DataStructureUtility pds = new DataStructureUtility();
        ListNode l = new ListNode(1);
        l = pds.insertAtEnd(l, new ListNode(1));
        l = pds.insertAtEnd(l, new ListNode(6));
        l = pds.insertAtEnd(l, new ListNode(3));
        l = pds.insertAtEnd(l, new ListNode(4));
        l = pds.insertAtEnd(l, new ListNode(5));
        l = pds.insertAtEnd(l, new ListNode(6));

        ListNode result = p.removeElements(l, 6);
        while(result != null) {
            System.out.printf("%d ", result.val);
            result = result.next;
        }
        System.out.println();
    }
}
