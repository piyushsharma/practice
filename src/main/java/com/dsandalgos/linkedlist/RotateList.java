package com.dsandalgos.linkedlist;

import com.dsandalgos.util.DataStructureUtility;
import com.dsandalgos.util.ListNode;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */


/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
 */

public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        // Check here because later if you get k % length and length = 0, it will throw exception
        if(head == null || head.next == null)
            return head;

        int length = 0;
        ListNode cur = head;
        while(cur != null) {
            ++length;
            cur = cur.next;
        }
        k = k % length; // so that if k == length, no need to rotate and k > length, we get correct k
        if(k == 0) return head;

        // get the k + 1 th node from end
        int i = 0;
        cur = head;
        ListNode temp = head;
        ListNode prev = null;
        while (cur != null) {
            if(i > k)
                temp = temp.next;
            ++i;
            prev = cur;
            cur =cur.next;
        }

        // temp now points to k + 1 th node from end and prev points to tail node
        prev.next = head;
        head = temp.next;
        temp.next = null;
        return head;
    }
    
    public static void main(String[] args) {
        RotateList r = new RotateList();
        DataStructureUtility p = new DataStructureUtility();
        ListNode l = p.sampleSortedLinkedList(10);
        ListNode newHead = r.rotateRight(l, 1);

        while(newHead != null) {
            System.out.printf("%d ", newHead.val);
            newHead = newHead.next;
        }
        System.out.printf("\n");
    }
}
