package com.dsandalgos.linkedlist;

import com.dsandalgos.util.DataStructureUtility;
import com.dsandalgos.util.ListNode;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
*/

public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;
        int count = 0;

        // Making sure that we at least have k nodes to reverse
        while(cur != null) {
            cur = cur.next;
            ++count;
            if(count >= k)
                break;
        }
        // if we don't have k nodes, we will not reverse
        // because this might happen when we call reversively
        // total elements in list does not have to be a proper multiple of k
        if(count < k) {
            return head;
        }

        count = 0;
        cur = head;

        while(cur != null && count < k) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            ++count;
        }
        /* next is now pointing to k+1 pointer */
        // head will now be at the end of the reversed part, so head.next = new reversed part
        if(next != null) {
            head.next = reverseKGroup(next, k);
        }

        // if you draw a diagram and run through the loop until k, you will see that when ++count == k,
        // at that point, next == cur == the next head that we want to reverse,
        // prev = the head of the last k nodes we reversed
        return prev;
    }


    public static void main(String[] args) {

        DataStructureUtility p = new DataStructureUtility();
        ListNode head = p.sampleSortedLinkedList(5);

        ListNode x = new ReverseNodesInKGroup().reverseKGroup(head, 3);

        while(x != null) {
            System.out.printf("%d ", x.val);
            x = x.next;
        }


    }





}
