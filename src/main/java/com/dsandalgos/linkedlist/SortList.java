package com.dsandalgos.linkedlist;

import com.dsandalgos.util.ListNode;
import com.dsandalgos.util.DataStructureUtility;

/**
 * Created by Piyush Sharma on 6/13/14.
 */


/*
Problem Statement:
    Sort a linked list in O(n log n) time using constant space complexity.
*/

public class SortList {

    // Using MergeSort
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode listTwo = splitList(head);
        return sortedMerge(sortList(head), sortList(listTwo));
    }

    private ListNode splitList(ListNode head) {
        ListNode one = head;
        ListNode two = head.next;
        while(two != null) {
            two = two.next;
            if(two != null) {
                one = one.next;
                two = two.next;
            }
        }
        ListNode sp = one.next;
        one.next = null; // Break the list into two
        return sp;
    }

    private ListNode sortedMerge(ListNode a, ListNode b) {
        /* Using dummy reference */
        ListNode sH = new ListNode(-1);
        ListNode curr = sH;

        while(a != null && b != null) {
            if (a.val <= b.val) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }
        if(a == null) {
            curr.next = b;
        } else {
            curr.next = a;
        }
        return sH.next;
    }

    /* Merging two lists using recursion -> Gives StackOverflowError on
       oj.leetcode server
     */
    private ListNode sortedMergeRecursion(ListNode a, ListNode b) {
        ListNode sH = null;
        if(a == null)
            return b;
        if(b == null)
            return a;

        if (a.val <= b.val) {
            sH = a;
            sH.next = sortedMerge(a.next, b);
        } else {
            sH = b;
            sH.next = sortedMerge(a, b.next);
        }
        return sH;
    }


    public static void main(String[] args) {
        SortList s = new SortList();

        DataStructureUtility p = new DataStructureUtility();
        ListNode l = new ListNode(3);
        l = p.insertAtEnd(l, new ListNode(40));
        l = p.insertAtEnd(l, new ListNode(8));
        l = p.insertAtEnd(l, new ListNode(4));
        l = p.insertAtEnd(l, new ListNode(1));
        l = p.insertAtEnd(l, new ListNode(2));

        ListNode head = s.sortList(l);
        while(head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
