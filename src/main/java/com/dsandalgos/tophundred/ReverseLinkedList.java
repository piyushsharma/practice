package com.dsandalgos.tophundred;

/**
 * Created by Piyush Sharma on 6/27/15.
 */
public class ReverseLinkedList {


    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /* prev(initialized to null), cur, next
       - Move next to prev;
       - Move prev to cur;
       - Move cur to next;
     */
    private ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;


        while(cur != null) {
            final ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    /*
        1) Divide the list in two parts - first node and rest of the linked list.
        2) Call reverse for the rest of the linked list.
        3) Link rest to first.
        4) Fix head pointer
    */
    private ListNode reverseLinkedListRecursive(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode first = head;
        ListNode second = head.next;
        ListNode reversedPart = reverseLinkedListRecursive(second);
        second.next = first;
        first.next = null;
        return reversedPart;
    }


}
