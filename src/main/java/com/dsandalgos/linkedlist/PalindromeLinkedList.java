package com.dsandalgos.linkedlist;

import com.dsandalgos.util.ListNode;
import com.dsandalgos.util.DataStructureUtility;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?
 */

public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        if(head == null)
            return false;
        if(head.next == null)
            return true;

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null) {
            fast = fast.next;
            if(fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        /* Split the list into two halves with second half reversed */
        ListNode secondHalf = slow.next;
        slow.next = null; // split the list
        // reverse the second half
        ListNode reverseSecondHalf = reverseLinkedList(secondHalf);

        ListNode cur = head;
        ListNode reverseCur = reverseSecondHalf;
        boolean isPalindrome = true;

        while(reverseCur != null) {
            if(cur.val != reverseCur.val) {
                isPalindrome = false;
                break;
            }
            cur = cur.next;
            reverseCur = reverseCur.next;
        }

        /* Restore the original list */
        slow.next = reverseLinkedList(reverseSecondHalf);
        return isPalindrome;
    }

    private ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }


    public static void main(String[] args) {
        PalindromeLinkedList r = new PalindromeLinkedList();

        ListNode l = new ListNode(1);
        DataStructureUtility pds = new DataStructureUtility();
        l = pds.insertAtEnd(l, new ListNode(1));
        l = pds.insertAtEnd(l, new ListNode(3));
        l = pds.insertAtEnd(l, new ListNode(3));
        l = pds.insertAtEnd(l, new ListNode(1));
        l = pds.insertAtEnd(l, new ListNode(1));

        System.out.println(r.isPalindrome(l));
        while(l != null) {
            System.out.printf("%d", l.val);
            l = l.next;
        }
        System.out.printf("\n");

    }
}
