package com.dsandalgos.linkedlist;

import com.dsandalgos.util.ListNode;
import com.dsandalgos.util.DataStructureUtility;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list,
only nodes itself can be changed.
*/

public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode cur = head;
        ListNode newHead = head.next; // since the new head will be created after the first swap, save it now
        ListNode prev = null;

        while(cur != null && cur.next != null) {
            // since after swap next of prev should be cur.next that was saved in temp
            if(prev != null) {
                prev.next = cur.next;
            }

            ListNode temp = cur.next;
            // reverse the pair pair
            cur.next = cur.next.next;
            temp.next = cur;

            prev = cur;
            cur = cur.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        SwapNodesInPairs p = new SwapNodesInPairs();
        DataStructureUtility pm = new DataStructureUtility();

        ListNode l = pm.sampleSortedLinkedList(10);
        ListNode result = p.swapPairs(l);
        while(result != null) {
            System.out.printf("%d ", result.val);
            result = result.next;
        }
        System.out.println();

    }
}
