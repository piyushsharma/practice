package com.dsandalgos.linkedlist;

import com.dsandalgos.util.DataStructureUtility;
import com.dsandalgos.util.ListNode;

import java.util.Stack;

/**
 * Created by Piyush Sharma
 */


/*
Print out an immutable singly linked list in reverse in linear time (O(n)) and less than linear space (space<(O(n))
*/


public class ReverseImmutableList {


    // idea is to store in multiple chunks and then
    public void printReversedList(ListNode head) {
        if(head == null) return;

        if(head.next == null) {
            System.out.printf("%d \n", head.val);
            return;
        }

        int numberOfNodes = 0;
        ListNode cur = head;
        while(cur != null) {
            cur = cur.next;
            ++numberOfNodes;
        }

        // minimize x + n/x => for a list of size n
        int chunkSize = (int) Math.sqrt(numberOfNodes);
        int numberOfChunks = 0;
        if(numberOfNodes % chunkSize == 0) {
            numberOfChunks = numberOfNodes/chunkSize;
        } else {
            numberOfChunks = numberOfNodes/chunkSize + 1;
        }

        ListNode[] chunkHeads = new ListNode[numberOfChunks];

        int chunkCounter = 0;
        chunkHeads[chunkCounter] = head;
        int counter = chunkSize;

        cur = head;
        while(cur != null) {
            if(counter == 0) {
                ++chunkCounter;
                chunkHeads[chunkCounter] = cur;
                // reset the chunkSize
                counter = chunkSize;
            }
            --counter;
            cur = cur.next;
        }

        for(int i = chunkHeads.length - 1; i >= 0; --i) {
            printReversedList(chunkHeads[i], chunkSize);
        }
        System.out.printf("\n");
    }

    private void printReversedList(ListNode chunkHead, int chunkSize) {
        Stack<ListNode> stack = new Stack<>();
        while(chunkHead != null && chunkSize > 0) {
            stack.push(chunkHead);
            chunkHead = chunkHead.next;
            --chunkSize;
        }

        while (!stack.isEmpty()) {
            System.out.printf("%d ", stack.pop().val);
        }
    }


    public static void main(String[] args) {

        DataStructureUtility d = new DataStructureUtility();
        ReverseImmutableList r = new ReverseImmutableList();
        for(int i = 0; i <= 10; ++i) {
            ListNode head = d.sampleSortedLinkedList(i);
            r.printReversedList(head);
        }
    }

}
