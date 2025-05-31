package com.dsandalgos.tophundred;

import com.dsandalgos.util.ListNode;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/* Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?
*/

public class LinkedListCycleStart {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null)
            return null;

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null) {
            fast = fast.next;
            if(fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }

            if(slow == fast) {
                slow = head;
                break;
            }
        }

        /* When your pointers meet,
            you'll be as many nodes away from the starting point of the loop as
            the starting of the loop is away from the start of the linked list
            slow ptr is again at head, so do the comparison first */
        while(slow != null && fast != null) {
            if(slow == fast) {
                return slow;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return null; // fast will be null if no cycle
    }


    public ListNode createCircularList() {
        ListNode l = new ListNode(1);
        l.next = new ListNode(2);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(4);
        l.next.next.next.next = l;
        return l;
    }

    public static void main(String[] args) {
        LinkedListCycleStart d = new LinkedListCycleStart();
        ListNode head = d.createCircularList();
        ListNode start = d.detectCycle(head);
        System.out.println(start.val);
    }

}
