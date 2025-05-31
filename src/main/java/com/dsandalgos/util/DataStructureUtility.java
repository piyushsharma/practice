package com.dsandalgos.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */
public class DataStructureUtility {

    /*
                  50
                /    \
              30     60
            /  \     / \
           5   35  55  70
                      /  \
                     65   80
        */

    public TreeNode sampleBinarySearchTree() {
        TreeNode n1 = new TreeNode(10);
        TreeNode n2 = new TreeNode(20);
        TreeNode n3 = new TreeNode(30);
        TreeNode n4 = new TreeNode(40);
        TreeNode n5 = new TreeNode(50);
        TreeNode n6 = new TreeNode(60);
        TreeNode n7 = new TreeNode(70);

        n2.left = n1;
        n2.right = n3;
        n6.left = n5;
        n6.right = n7;
        n4.left = n2;
        n4.right = n6;
        return n4;
    }

    public TreeNode sampleBinaryTree(int numberOfNodes) {
        TreeNode root = new TreeNode(1);
        TreeNode prev = root;
        for (int i = 2; i < numberOfNodes; i += 2) {
            prev.left = new TreeNode(i);
            prev.right = new TreeNode(i + 1);
            if (i % 2 == 0)
                prev = prev.left;
            else
                prev = prev.right;
        }
        if (numberOfNodes == 0)
            return null;
        return root;
    }


    public ListNode sampleSortedLinkedList(int numberOfNodes) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i <= numberOfNodes; ++i) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return head.next;
    }

    public ListNode insertAtEnd(ListNode head, ListNode node) {
        if (head == null) {
            return node;
        }
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = node;
        return head;
    }


    public void printTreeUsingLevelOrderTraversal(TreeNode root) {

        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if(root == null)
            System.out.println("Tree is empty!");

        /* Using null as level markers */
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        q.add(null);
        List<Integer> levelList = new LinkedList<Integer>();

        while(!q.isEmpty()) {
            TreeNode t = q.remove();
            if(t == null) {
                if(!q.isEmpty()) {
                    q.add(null);
                }
                result.add(levelList);
                levelList = new LinkedList<Integer>();
            } else {
                levelList.add(t.val);
                if(t.left != null) q.add(t.left);
                if(t.right != null) q.add(t.right);
            }
        }
        System.out.println("------------------------------------");
        for(List<Integer> l : result) {
            for (Integer i : l) {
                System.out.printf("%d ", i);
            }
            System.out.printf("\n");
        }
    }

    public static void printMatrix(int[][] matrix) {

        for(int i = 0; i < matrix.length; ++i) {
            for(int j = 0; j < matrix[0].length; ++j) {
                System.out.printf("%d    ", matrix[i][j]);
            }
            System.out.printf("\n");
        }
    }
}