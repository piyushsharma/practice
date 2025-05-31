package com.dsandalgos.tophundred;

import com.dsandalgos.util.RandomListNode;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
A linked list is given such that each node contains an additional random pointer
which could point to any node in the list or null.
Return a deep copy of the list.
*/

public class CopyListWithRandomPointer {

    // O(n) runtime, O(1) space
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null)
            return head;

        RandomListNode cur = head;
        // make a copy of each node
        // 2 -> 4 -> 3  to
        // 2 -> 2 -> 4 -> 4 -> 3 -> 3)
        while(cur != null) {
            RandomListNode copyNode = new RandomListNode(cur.label);
            copyNode.next = cur.next;
            cur.next = copyNode;
            cur = copyNode.next;
        }

        // now iterate and copy random
        cur = head;
        while(cur != null) {
            // note, we must check this inside the while loop
            if(cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        RandomListNode deepCopyHead = head.next;

        // break the list and restore original list
        cur = head;
        RandomListNode rCur = deepCopyHead;
        // 2 -> 2 -> 4 -> 4 -> 3 -> 3 to
        // 2 -> 4 -> 3
        while(cur != null) {
            cur.next = rCur.next;
            if(rCur.next != null) {
                rCur.next = rCur.next.next;
            }
            cur = cur.next;
            rCur = rCur.next;
        }
        return deepCopyHead;
    }


    // O(n) runtime, O(n) space
    public RandomListNode copyRandomListHashMap(RandomListNode head) {
        if(head == null)
            return head;

        RandomListNode deepCopyhead = null;
        RandomListNode cur = head;

        HashMap<RandomListNode, RandomListNode> m = new HashMap<>();

        while(cur != null) {
            RandomListNode copy = new RandomListNode(cur.label);
            if(deepCopyhead == null) {
                deepCopyhead = copy;
            }
            m.put(cur, copy);
            cur = cur.next;
        }

        cur = head;
        RandomListNode rCur = deepCopyhead;
        while(cur != null) {
            rCur.random = m.get(cur.random);
            rCur.next = m.get(cur.next);
            cur = cur.next;
            rCur = rCur.next;
        }

        return deepCopyhead;
    }


    public void printRandomList(RandomListNode cur) {
        while(cur != null) {
            System.out.printf("cur = %d", cur.label);
            if(cur.random != null) {
                System.out.printf(", random = %d", cur.random.label);
            }
            System.out.printf("\n");
            cur = cur.next;
        }
    }

    public static void main(String[] args) {

        RandomListNode r = new RandomListNode(1);
        r.random = r;
        r.next = new RandomListNode(2);
        r.next.random = r.next;

        CopyListWithRandomPointer c = new CopyListWithRandomPointer();
        RandomListNode cur = r;
        c.printRandomList(cur);
        System.out.println("+++++++++++++++++++++++++++++++");

        RandomListNode result = c.copyRandomList(r);
        c.printRandomList(result);
    }
}
