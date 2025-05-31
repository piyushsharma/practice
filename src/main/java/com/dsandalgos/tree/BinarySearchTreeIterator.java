package com.dsandalgos.tree;

import com.dsandalgos.util.DataStructureUtility;
import com.dsandalgos.util.TreeNode;

import java.util.Stack;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
Calling next() will return the next smallest number in the BST.
Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
*/

public class BinarySearchTreeIterator {
    private Stack<TreeNode> stack;

    public BinarySearchTreeIterator(TreeNode root) {
        stack = new Stack<TreeNode>();
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode next = stack.pop();
        int val = next.val;

        /* Push the right child and then keep pushing all the left child of the right subtree */
        if(next.right != null) {
            stack.push(next.right);
            TreeNode node = next.right.left;
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        return val;
    }

    public static void main(String[] args) {
        TreeNode root = new DataStructureUtility().sampleBinarySearchTree();
        BinarySearchTreeIterator i = new BinarySearchTreeIterator(root);
        while (i.hasNext()) System.out.println(i.next());
    }
}