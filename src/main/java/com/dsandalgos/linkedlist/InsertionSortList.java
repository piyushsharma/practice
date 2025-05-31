package com.dsandalgos.linkedlist;

import com.dsandalgos.util.ListNode;
import com.dsandalgos.util.DataStructureUtility;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/* Sort a linked list using insertion sort */
/* Insertion sort
      -- find the minimum value
      -- insert the value at the right place
 */


public class InsertionSortList {

    public void insertionSortArrayEfficient(int a[]) {
        for(int i = 1; i < a.length; ++i) {
            if(a[i-1] > a[i]) {
                int j;
                int x = a[i];
                for (j = i; j > 0; --j) {
                    if(a[j - 1] > x) {
                        a[j] = a[j - 1];
                    }
                }
                a[j] = x;
            }
        }
        for(int i = 0; i < a.length; ++i)
            System.out.printf("%d ", a[i]);
        System.out.println();
    }


    public void insertionSortArray(int a[]) {
        for(int i = 1; i < a.length; ++i) {
            if(a[i-1] <= a[i]) {
                continue;
            }
            int j = i;
            while(j > 0 && a[j-1] > a[j]) {
                int temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
                --j;
            }
        }
        for(int i = 0; i < a.length; ++i)
            System.out.printf("%d ", a[i]);
        System.out.println();
    }

    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null)
            return head;

        // Copied to a new head to have next pointer as null, we will
        // add the sorted values to next
        ListNode sortedHead = new ListNode(head.val);
        // Since head is already added to the sorted list, start from next node
        ListNode cur = head.next;
        while(cur != null) {
            ListNode nextNode = cur.next;
            if(sortedHead.val > cur.val) {
                cur.next = sortedHead;
                sortedHead = cur;
            } else {
                ListNode sCur = sortedHead;
                while(sCur != null) {
                    if(sCur.next == null // last element of the sorted list
                            || cur.val < sCur.next.val) {// middle of the list
                        cur.next = sCur.next;
                        sCur.next = cur;
                        break;
                    }
                    sCur = sCur.next;
                }
            }
            cur = nextNode;
        }
        return sortedHead;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 9, 10, 1, 4};
        InsertionSortList isl = new InsertionSortList();
        isl.insertionSortArrayEfficient(arr);

        DataStructureUtility p = new DataStructureUtility();

        ListNode l = new ListNode(3);
        l = p.insertAtEnd(l, new ListNode(2));
        l = p.insertAtEnd(l, new ListNode(9));
        l = p.insertAtEnd(l, new ListNode(10));
        l = p.insertAtEnd(l, new ListNode(1));
        l = p.insertAtEnd(l, new ListNode(4));
        l = p.insertAtEnd(l, new ListNode(7));

        ListNode result = isl.insertionSortList(l);
        while(result != null) {
            System.out.printf("%d ", result.val);
            result = result.next;
        }
        System.out.println();
    }

}
