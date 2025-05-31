package com.dsandalgos.linkedlist;

import com.dsandalgos.util.ListNode;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
 */
public class RemoveDuplicatesFromSortedListTwo {


    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;

        while(cur != null) {
            int val = cur.val;
            int count = 1;

            while(cur.next != null && val == cur.next.val) {
                ++count;
                 cur.next = cur.next.next;
            }

            if(count > 1) {
              if (prev == null) {
                  head = cur.next;
              } else {
                  // note this here, you have to update the next of prev
                  // this is how we delete the last occurence of cur which is duplicate
                  prev.next = cur.next;
              }
            }
            // update prev only when count is 1, i.e. cur is a distinct element
            if(count == 1) {
                prev = cur;
            }
            // this has to be the last step
            cur = cur.next;
        }

        return head;
    }


    public ListNode deleteDuplicatesV2(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;
        ListNode nextNode;

        while(cur != null) {
            nextNode = cur.next;
            int val = cur.val;
            int count = 1;

            while(nextNode != null && val == nextNode.val) {
                ++count;
                nextNode = nextNode.next;
            }

            if(count > 1) {
                if (prev != null) {
                    prev.next = nextNode;
                } else {
                    head = nextNode;
                }
            } else {
                prev = cur;
            }

            cur = nextNode;
        }

        return head;
    }


    public static void main(String[] args) {
        RemoveDuplicatesFromSortedListTwo r = new RemoveDuplicatesFromSortedListTwo();

        ListNode l = new ListNode(1);
        l.next = new ListNode(2);
        l.next.next = new ListNode(2);
        l.next.next.next = new ListNode(2);
        l.next.next.next.next = new ListNode(2);
        l.next.next.next.next.next = new ListNode(2);
//        l.next.next.next.next.next.next = new ListNode(3);

        ListNode cur = r.deleteDuplicates(l);

        while(cur != null) {
            System.out.printf("%d ", cur.val);
            cur = cur.next;
        }
    }

}
