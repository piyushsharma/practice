package com.dsandalgos.linkedlist;

import com.dsandalgos.util.ListNode;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

        For example,
        Given 1->1->2, return 1->2.
        Given 1->1->2->3->3, return 1->2->3.
*/
public class RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;

        while(cur != null) {
            int val = cur.val;
            while (cur.next != null && val == cur.next.val) {
                cur.next = cur.next.next;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedList r = new RemoveDuplicatesFromSortedList();

        ListNode l = new ListNode(1);
        l.next = new ListNode(1);
        l.next.next = new ListNode(2);
        l.next.next.next = new ListNode(2);
        l.next.next.next.next = new ListNode(2);
        l.next.next.next.next.next = new ListNode(2);

        ListNode head = r.deleteDuplicates(l);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
