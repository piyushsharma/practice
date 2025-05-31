package com.dsandalgos.linkedlist;

import com.dsandalgos.util.ListNode;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/* Given a linked list and a value x, partition it such that all nodes less than x come before
nodes greater than or equal to x.
You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5 */

public class PartitionList {

    public ListNode partition(ListNode head, int x) {

        ListNode lessTail = null;
        ListNode geTail = null;
        ListNode geHead = null;

        ListNode cur = head;
        while(cur != null) {
            if(cur.val < x) {
                if(lessTail == null) {
                    head = cur;
                    lessTail = cur;
                } else {
                    lessTail.next = cur;
                    lessTail = lessTail.next;
                }
            } else {
                if(geTail == null) {
                    geHead = cur;
                    geTail = cur;
                } else {
                    geTail.next = cur;
                    geTail = geTail.next;
                }
            }
            cur = cur.next;
        }

        if(geTail != null) geTail.next = null;
        if (lessTail != null) lessTail.next = geHead;

        return head;
    }

    // cleaner solution
    public ListNode partitionV2(ListNode head, int x) {
          /* Dummy nodes */
        ListNode lessHead = new ListNode(-1);
        ListNode geHead = new ListNode(-1);

        ListNode a = lessHead;
        ListNode b = geHead;

        ListNode cur = head;
        while(cur != null) {
            if(cur.val < x) {
                a.next = cur;
                a = a.next;
            } else {
                b.next = cur;
                b = b.next;
            }
            cur = cur.next;
        }

        b.next = null;
        a.next = geHead.next;

        return lessHead.next;
    }

    public static void main(String[] args) {
        PartitionList p = new PartitionList();

        ListNode l = new ListNode(1);
        l.next = new ListNode(4);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(5);
        l.next.next.next.next = new ListNode(2);
        l.next.next.next.next.next = new ListNode(1);

        ListNode head = p.partition(l, 3);
        while(head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
