package com.dsandalgos.tophundred;

/*
Problem Statement:
    Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode mergeKListsSimpler(ListNode[] lists) {
        
        if(lists == null || lists.length == 0) {
            return null;
        }

        Queue<ListNode> pq = new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.val));

        for(int i = 0; i < lists.length; ++i) {
            if(lists[i] != null)
                pq.add(lists[i]);
        } 

        ListNode head = new ListNode(0);
        ListNode mergedCur = head;

        while(!pq.isEmpty()) {

            mergedCur.next = pq.poll();
            mergedCur = mergedCur.next;

            if(mergedCur.next != null) {
                pq.add(mergedCur.next);
            }
        }

        return head.next;
    }

    // log k insertion into heap, and nk insertions
    // O(nklogk) // Space => O(k)
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }

        /* We will always have at most one element from the linked list in the pq structure,
           So it is safe to specify the size as lists.size()
          */
        Queue<ListNode> pq = new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.val));

        /* Add first element of each linked list to the priority queue */
        for(int i = 0; i < lists.length; ++i) {
            ListNode listNode = lists[i];
            if(listNode == null) {
                continue;
            }
            pq.add(listNode);
        }

        ListNode head = null;
        ListNode cur = null;

        while(!pq.isEmpty()) {
            ListNode shortestNode = pq.poll();

            if(head == null) {
                head = shortestNode;
                cur = shortestNode;
            } else {
                cur.next = shortestNode;
                cur = cur.next;
            }

            if(shortestNode.next != null) {
                pq.add(shortestNode.next);
            }
        }
        return  head;
    }


    // we could do O(nk logk) and O(1) space
    // merge two lists at a time reducing total lists to k / 2
    // keep doing this until number of lists = 1
    public ListNode mergeKListsNoExtraSpace(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }

        int end = lists.length - 1;

        while(end > 0) {
            // we do this here because we will do this logk times, merging 2 lists at a time
            int start = 0;

            while(start < end) {
                lists[start] = mergeTwoLists(lists[start], lists[end]);
                ++start;
                --end;
            }
        }

        return lists[0];
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;

        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        /* One of l1 or l2 must have finished, add the remaining nodes to res */
        if(l1 != null) {
            cur.next = l1;
        } else {
            cur.next = l2;
        }
        return head.next;
    }


    public ListNode mergeKLists(List<ListNode> lists) {
        if(lists == null || lists.isEmpty()) {
            return null;
        }

        /* We will always have at most one element from the linked list in the pq structure,
           So it is safe to specify the size as lists.size()
          */
        Queue<ListNode> pq = new PriorityQueue<>(lists.size(), Comparator.comparing(a -> a.val));

        /* Add first element of each linked list to the priority queue */
        for(int i = 0; i < lists.size(); ++i) {
            ListNode listNode = lists.get(i);
            if(listNode == null) {
                continue;
            }
            pq.add(listNode);
        }

        /* The minimum of all the first elements of all the lists */
        ListNode head = pq.poll();
        ListNode temp = head;

        while(pq.size() > 0) {
            /* Add the next element from the list from which the element is taken out to the pq */
            if(temp.next != null) {
                pq.add(temp.next);
            }

            /* Get the next minimum element and add it to head */
            temp.next = pq.poll();
            temp = temp.next;
        }
        return  head;
    }

    public ListNode getNewNode(int data) {
        return new ListNode(data);
    }

    public static void main(String args[]) {
        MergeKSortedLists m = new MergeKSortedLists();
        ListNode l1 = m.getNewNode(1);
        ListNode l2 = m.getNewNode(2);
        ListNode l3 = m.getNewNode(3);
        ListNode l4 = m.getNewNode(4);
        ListNode l5 = m.getNewNode(5);

        List<ListNode> lists = new ArrayList<ListNode>();
        lists.add(null);
        lists.add(l5);
        lists.add(l4);
        lists.add(l3);
        lists.add(l2);
        lists.add(l1);
        ListNode resultNode = m.mergeKLists(lists);

        while(resultNode != null) {
            System.out.println(resultNode.val);
            resultNode = resultNode.next;
        }
    }

}
