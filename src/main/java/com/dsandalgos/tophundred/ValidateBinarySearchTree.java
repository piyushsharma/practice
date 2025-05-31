package com.dsandalgos.tophundred;

import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class ValidateBinarySearchTree {

    // In order traversal and keep track of the previous node
    public boolean isValidBST(TreeNode root) {
        if(root == null)
            return true;

        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode prev = null;
        while (true) {
            while (root != null) {
                s.add(root);
                root = root.left;
            }
            if(s.isEmpty()) break;

            root = s.pop();
            if(prev != null && root.val <= prev.val) {
                return false;
            }
            prev = root;
            root = root.right;
        }
        return true;
    }

    // pass null to signify infinity
    public boolean isValidBSTRecursive(TreeNode root) {
        return isBST(root, null, null);
    }

    // this way we can handle case when root's value = Integer.MAX_VALUE or Integer.MIN_VALUE
    private boolean isBST(TreeNode root, Integer minValue, Integer maxValue) {
        if(root == null)
            return true;

        return (minValue == null || root.val > minValue) && (maxValue == null || root.val < maxValue)
                && isBST(root.left, minValue, root.val)
                && isBST(root.right, root.val, maxValue);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

}
