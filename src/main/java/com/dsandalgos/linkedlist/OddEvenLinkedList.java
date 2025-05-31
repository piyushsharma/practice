package com.dsandalgos.linkedlist;

import com.dsandalgos.util.ListNode;
import com.dsandalgos.util.DataStructureUtility;

/**
 * Created by Piyush Sharma on 9/5/16.
 */

/*
Given a singly linked list, group all odd nodes together followed by the even nodes.
Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...
*
*/


public class OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode evenHead = head.next;
        ListNode cur = head;

        while(cur != null) {
            ListNode evenCur = cur.next;
            // we make sure that cur does not become null, we will actually break here so that cur points to the
            // last odd numbered element
            if(evenCur == null || evenCur.next == null) {
                break;
            }

            cur.next = evenCur.next;
            evenCur.next = evenCur.next.next;
            cur = cur.next;
        }

        // we break in the middle of the loop before to be able to do this here
        cur.next = evenHead;

        return head;
    }

    public static void main(String[] args) {
        OddEvenLinkedList o = new OddEvenLinkedList();

        DataStructureUtility p = new DataStructureUtility();
        ListNode l = p.sampleSortedLinkedList(3);

        ListNode head = o.oddEvenList(l);
        while(head != null) {
            System.out.printf("%d ", head.val);
            head = head.next;
        }
    }

}
