package com.dsandalgos.tree;

import com.dsandalgos.util.TreeNode;

public class RangeSumBST {


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

    int sum = 0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        recur(root, low, high);
        return sum;
    }

    public void recur(TreeNode root, int low, int high) {
        if(root == null) {
            return;
        }

        if(low <= root.val && root.val <= high) {
            sum += root.val;
        }

        if(low < root.val) {
            recur(root.left, low, high);

        }

        if(root.val < high) {
            recur(root.right, low, high);
        }

    }
}
