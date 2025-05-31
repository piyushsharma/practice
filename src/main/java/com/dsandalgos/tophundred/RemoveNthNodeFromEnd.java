package com.dsandalgos.tophundred;

import com.dsandalgos.util.ListNode;
import com.dsandalgos.util.DataStructureUtility;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/* Given a linked list, remove the nth node from the end of list and return its head.

For example,
   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass. */

public class RemoveNthNodeFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        ListNode temp = head;
        int i = 0;
        while(cur != null) {
            ++i;
            // when i is greater than k + 1, start moving temp, it will point to the (TotalNodes - n +1)th node from
            // beginning, which is the node prev to the nth node from end
            if(i > n + 1) {
                temp = temp.next;
            }

            cur = cur.next;
        }

        /* This checks if length of list is < n, since n is given to be valid, redundant check */
        if(i < n ) return head; // nothing to delete

        /* Means we have to remove head, as i is incremented until we reach the end */
        if(i == n) {
            head = head.next;
        } else {
            /* We have the n+1th node from end in temp */
            temp.next = temp.next.next;
        }
        return head;
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEnd m = new RemoveNthNodeFromEnd();
        DataStructureUtility p = new DataStructureUtility();
        ListNode head = p.sampleSortedLinkedList(10);

        head = m.removeNthFromEnd(head, 1); // node removing the 1st node from end
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }










}
