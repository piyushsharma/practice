package com.dsandalgos.linkedlist;

import com.dsandalgos.util.ListNode;
import com.dsandalgos.util.DataStructureUtility;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
 */

public class ReverseLinkedListTwo {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode prev = null;
        int count = 0;
        ListNode next;
        ListNode mNode = null;
        ListNode nNode = null;
        ListNode prevMthNode = null;
        ListNode nextNthNode = null;
        ListNode cur = head;

        while(cur != null) {
            ++count;
            if(count == m-1) prevMthNode = cur;
            if(count == n+1) nextNthNode = cur;
            if(count == m) mNode = cur;
            if(count == n) nNode = cur;

            next = cur.next;
            if(count >= m && count <= n) {
                cur.next = prev;
            }
            prev = cur;
            cur = next;
        }
        if(prevMthNode != null) {
            prevMthNode.next = nNode;
        } else {
            head = nNode;
        }
        mNode.next = nextNthNode;

        return head;
    }


    public static void main(String[] args) {
        ReverseLinkedListTwo r = new ReverseLinkedListTwo();

        DataStructureUtility p = new DataStructureUtility();
        ListNode l = p.sampleSortedLinkedList(10);

        l = r.reverseBetween(l, 3, 6);

        while(l != null) {
            System.out.printf("%d ", l.val);
            l = l.next;
        }
    }
}
