package com.dsandalgos.tophundred;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
 */

public class BinaryTreeMaximumSumPath {

    int result = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxSum(root);
        return result;
    }

    private int maxSum(TreeNode root) {
        if(root == null) {
            return 0;
        }

        // max sum on the left and right sub-trees of node
        int lsum = Math.max(maxSum(root.left), 0);
        int rSum = Math.max(maxSum(root.right), 0);

        // the price to start a new path where `root` is a highest node
        int maxWithNode = root.val + lsum + rSum;

        // update result if it's better to start a new path
        result = Math.max(result, maxWithNode);

        // for recursion :
        // return the max gain if continue the same path
        return root.val + Math.max(lsum, rSum);
    }


    public int maxPathSumV2(TreeNode root) {
        int result[] = new int[1];
        result[0] = Integer.MIN_VALUE;

        maxPathSum(root, result);
        return result[0];
    }

    /*
    For each node there can be four ways that the max path goes through the node:
    1. Node only
    2. Max path through Left Child + Node
    3. Max path through Right Child + Node
    4. Max path through Left Child + Node + Max path through Right Child

    The idea is to keep trace of four paths and pick up the max one in the end.
    An important thing to note is, root of every *subtree* need to return maximum path sum
    such that at most one child of the root (subtree root) is involved.
    */

    private int maxPathSum(TreeNode root, int[] result) {
        if(root == null)
            return 0;

        int lSum = maxPathSum(root.left, result);
        int rSum = maxPathSum(root.right, result);

        // case 1 , 2, 3
        // cases where we can at most choose one child
        int subTreeSum = Math.max(root.val, Math.max(lSum, rSum) + root.val);

        // case 4
        // curMaxSum represents the sum when the node under
        // consideration is the root of the maxsum path and no
        // ancestors of root are there in max sum path
        int curMaxSum = Math.max(subTreeSum, lSum + rSum + root.val);

        // result[0] was initialized as INT_MIN, we use it to store max sum observed so far
        result[0] = Math.max(result[0], curMaxSum);

        // Keep returning subtree sum, as we are storing the max sum so far in the result array
        // this is the max path for parent call of root. This path must include at-most one child of root
        return subTreeSum;
    }





    Integer res;

    public int maxPathSumNEW(TreeNode root) {
        maxSumNEW(root);

        return res == null ? Integer.MIN_VALUE : res;

    }

    private Integer maxSumNEW(TreeNode node) {

        if(node == null) {
            return null;
        }

        if(node.left == null && node.right == null) {
            return node.val;
        }

        int onlyNode = node.val;

        Integer maxLeft = maxSumNEW(node.left);
        Integer maxRight = maxSumNEW(node.right);

        Integer maxLR;
        Integer childSum;
        if(maxLeft == null) {
            maxLR = maxRight;
            childSum = maxRight;
        } else if(maxRight == null) {
            maxLR = maxLeft;
            childSum = maxLeft;
        } else {
            maxLR = Math.max(maxLeft, maxRight);
            childSum = maxLeft + maxRight;
        }

        int maxSoFar = Math.max(onlyNode, maxLR);

        int maxWithRoot = Math.max(maxSoFar, onlyNode + childSum);

        if(res == null) {
            res = maxWithRoot;
        } else {
            res = Math.max(res, maxWithRoot);
        }

        return maxWithRoot;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {
        BinaryTreeMaximumSumPath p = new BinaryTreeMaximumSumPath();

        TreeNode r1 = p.new TreeNode(1);
        TreeNode r2 = p.new TreeNode(2);
        TreeNode r3 = p.new TreeNode(3);

        r1.left = r2;
        r1.right = r3;

        int x = p.maxPathSumNEW(r1);
        System.out.println(x);









    }







}
