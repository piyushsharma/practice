package com.dsandalgos.linkedlist;

import com.dsandalgos.util.ListNode;
import com.dsandalgos.util.DataStructureUtility;
import com.dsandalgos.util.TreeNode;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/* Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST. */

public class ConvertSortedListToBinaryTree {


    public TreeNode sortedListToBST(ListNode head) {

        if (head == null)
            return null;

        if(head.next == null) {
            return new TreeNode(head.val);
        }

        ListNode midNode = findRootInList(head);
        ListNode rightSubTreeList = midNode.next;
        midNode.next = null;

        TreeNode root = new TreeNode(midNode.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(rightSubTreeList);

        return root;
    }

    public ListNode findRootInList(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;

        while(fast != null) {
            fast = fast.next;
            if(fast != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next;
            }
        }
        // make sure nothing is pointing to the mid pointer
        // split the list into two parts (before slow and from slow)
        if(prev != null) prev.next = null;
        return slow;
    }

    // Solution 2 => Using HashMap
    private HashMap<Integer, ListNode> listMap = new HashMap<Integer, ListNode>();

    public TreeNode sortedListToBSTV2(ListNode head) {

        if (head == null)
            return null;

        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            listMap.put(count, cur);
            cur = cur.next;
            count += 1;
        }

        return sortedListToBST(head, 0, count);
    }

    private TreeNode sortedListToBST(ListNode head, int start, int end) {

        if (head == null || end < start)
            return null;

        int mid = start + (end - start) / 2;
        ListNode midListNode = listMap.get(mid);
        if(midListNode == null)
            return null;

        TreeNode root = new TreeNode(midListNode.val);
        root.left = sortedListToBST(head, start, mid - 1);
        root.right = sortedListToBST(head, mid + 1, end);
        return root;
    }


    public static void main(String[] args) {
        ConvertSortedListToBinaryTree convertSortedListToBinaryTree = new ConvertSortedListToBinaryTree();

        DataStructureUtility p = new DataStructureUtility();
        ListNode head = p.sampleSortedLinkedList(10);

        TreeNode root =  convertSortedListToBinaryTree.sortedListToBST(head);
        p.printTreeUsingLevelOrderTraversal(root);

        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++");

        ListNode head2 = p.sampleSortedLinkedList(10);
        TreeNode root2 =  convertSortedListToBinaryTree.sortedListToBSTV2(head2);
        p.printTreeUsingLevelOrderTraversal(root2);
    }


}
