package com.dsandalgos.tree;


/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations)
often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
*/

import com.dsandalgos.util.DataStructureUtility;
import com.dsandalgos.util.PrintUtil;
import com.dsandalgos.util.TreeNode;

import java.util.Stack;

public class KthSmallestElementBST {

    public int kthSmallest(TreeNode root, int k) {
        if(k <= 0 || root == null)
            return -1;

        Stack<TreeNode> s = new Stack();
        while(true) {
            while(root != null) {
                s.push(root);
                root = root.left;
            }
            if(s.empty()) break;

            root = s.pop();
            --k;
            if(k == 0)
                return root.val;

            root = root.right;
        }
        // should not reach here
        return root.val;
    }


    // when inserting a new node, we can update the left count
    // this should be used when constructing the bst where we might want to find kth smallest element frequently
    public TreeNode insertNodeWithLeftCount(TreeNode root, TreeNode newNode) {
        TreeNode curNode = root;
        TreeNode curParent = root;

        while(curNode != null) {
            curParent = curNode;

            if(newNode.val < curNode.val) {
                /* We are branching to left subtree increment node count */
                curNode.leftCount += 1;
                curNode = curNode.left;

            } else {
                curNode = curNode.right;
            }
        }

        // if root is null, return the new node as root
        if(root == null) {
            return newNode;
        }

        if(newNode.val < curParent.val) {
            curParent.left = newNode;
        } else {
            curParent.right = newNode;
        }

        return root;
    }


    public int kthSmallestWithLeftCount(TreeNode root, int k) {
        if(k <= 0 || root == null)
            return -1;

        while(root != null) {

            if(root.leftCount + 1 == k) {
                return root.val;
            }

            if(root.leftCount < k) {
                // go to the right subtree
                k -= root.leftCount + 1;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        // should not reach here
        return root.val;
    }



    public static void main(String[] args) {
        TreeNode root = new DataStructureUtility().sampleBinarySearchTree();
        PrintUtil.printInOrder(root);
        System.out.println("+++++++++++++++++++++");

        KthSmallestElementBST k = new KthSmallestElementBST();
        for(int i = 1; i < 8; ++i) {
            System.out.println(k.kthSmallest(root, i));
        }
        System.out.println("+++++++++++++++++++++");

        root = null;
        for(int i = 10; i < 80; i+=10) {
            root = k.insertNodeWithLeftCount(root, new TreeNode(i));
        }
        for(int i = 1; i < 8; ++i) {
            System.out.println(k.kthSmallestWithLeftCount(root, i));
        }
    }
}