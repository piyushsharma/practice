package com.dsandalgos.tophundred;

import com.dsandalgos.util.ListNode;

/*
Write a program to find the node at which the intersection of two singly linked lists begins.
For example, the following two linked lists:

        A:          a1 → a2
                            ↘
                             c1 → c2 → c3
                            ↗
        B:      b1 → b2 → b3
begin to intersect at node c1.

Notes:
    If the two linked lists have no intersection at all, return null.
    The linked lists must retain their original structure after the function returns.
    You may assume there are no cycles anywhere in the entire linked structure.
    Your code should preferably run in O(n) time and use only O(1) memory.
*/

public class IntersectionOfTwoLinkedLists {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;

        ListNode result = null;

        int cA = 0;
        while(tempA != null) {
            ++cA;
            tempA = tempA.next;
        }

        int cB = 0;
        while(tempB != null) {
            ++cB;
            tempB = tempB.next;
        }

        if(cA > cB) {
            result = _getIntersectionNode(cA - cB, headA, headB);
        } else {
            result = _getIntersectionNode(cB - cA, headB, headA);
        }

        return result;
    }

    // list starting at headA is longer than list starting at headB
    private ListNode _getIntersectionNode(int i, ListNode headA, ListNode headB) {
        ListNode result = null;
        ListNode tempA = headA;
        ListNode tempB = headB;

        while(i > 0) {
            tempA = tempA.next;
            --i;
        }

        while(tempA != null && tempB != null) {
            if(tempA.equals(tempB)) {
                result = tempA;
                break;
            }
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return result;
    }
}
