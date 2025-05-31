package com.dsandalgos.tree;

import com.dsandalgos.util.DataStructureUtility;
import com.dsandalgos.util.TreeNode;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is
defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
*/

public class BalancedBinaryTree {

    // O(n) runtime, O(n) stack space
    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }

    // use sentinal value of -1 to reduce the number of recursions
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;

        int lh = maxDepth(root.left);
        if(lh == -1) return -1;  // avoid extra recursion

        int rh = maxDepth(root.right);
        if(rh == -1) return -1;  // avoid extra recursion

        if(Math.abs(lh - rh) > 1)
            return -1;

        return 1 + Math.max(lh, rh);
    }


    /*
    An empty tree is height-balanced. A non-empty binary tree T is balanced if:
    1) Left subtree of T is balanced
    2) Right subtree of T is balanced
    3) The difference between heights of left subtree and right subtree is not more than 1.
    *
    */
    // O(n2) run time and O(n) stack space
    // we are recursing for max depth at each node
    public boolean isBalancedV2(TreeNode root) {
        if(root == null)
            return true;

        int lh = maxDepthV2(root.left);
        int rh = maxDepthV2(root.right);

        if(Math.abs(lh - rh) <= 1 && isBalanced(root.left) && isBalanced(root.right)) {
            return true;
        }

        return false;
    }

    public int maxDepthV2(TreeNode root) {

        if(root == null)
            return 0;

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }






    public static void main(String[] args) {
        DataStructureUtility p = new DataStructureUtility();
        BalancedBinaryTree b = new BalancedBinaryTree();

        TreeNode t = p.sampleBinaryTree(10);
        System.out.println(b.isBalanced(t));

        TreeNode x = new TreeNode(1);
        x.left = new TreeNode(2);
        x.left.left = new TreeNode(3);
        System.out.println(b.isBalanced(x));
    }
}
