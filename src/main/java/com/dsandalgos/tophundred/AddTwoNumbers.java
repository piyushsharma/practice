package com.dsandalgos.tophundred;


/**
 * Created by Piyush Sharma on 6/16/15.
 */


/*
You are given two linked lists representing two non-negative numbers.
The digits are stored in reverse order and each of their nodes contain a single digit.
Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
*/

public class AddTwoNumbers {

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        ListNode temp = result;

        int carry = 0;
        int l1val;
        int l2val;

        while(l1 != null || l2 != null) {
            l1val = l1 == null ? 0 : l1.val;
            l2val = l2 == null ? 0 : l2.val;

            temp.next = new ListNode((l1val + l2val + carry) % 10);
            carry = (l1val + l2val + carry)/10;

            temp = temp.next;
            if(null != l1) l1 = l1.next;
            if(null != l2) l2 = l2.next;
        }

        /* Make sure the last carry is also carried over */
        if(carry != 0) {
            temp.next = new ListNode(carry);
        }
        return result.next;
    }
}
