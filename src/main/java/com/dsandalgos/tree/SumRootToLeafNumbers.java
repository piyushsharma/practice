package com.dsandalgos.tree;
/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
An example is the root-to-leaf path 1->2->3 which represents the number 123.
Find the total sum of all root-to-leaf numbers.
For example,
    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Return the sum = 12 + 13 = 25.
*/

import com.dsandalgos.util.TreeNode;

public class SumRootToLeafNumbers {

    public int sumNumbers(TreeNode root) {
        if(root == null)
            return 0;

        return sumNumbers(root, 0, 0);
    }

    private int sumNumbers(TreeNode root, int pathSum, int totalSum) {
        if(root == null)
            return 0;

        pathSum = pathSum * 10 + root.val;
        if(root.left == null && root.right == null) {
            totalSum += pathSum;
        } else {
            totalSum += sumNumbers(root.left, pathSum, totalSum)
                            + sumNumbers(root.right, pathSum, totalSum);
        }
        return totalSum;
    }

    public int sumNumbersV2(TreeNode root) {
        if(root == null)
            return 0;

        int pathSum[] = new int[1];
        int totalSum[] = new int[1];

        sumNumbers(root, pathSum, totalSum);
        return totalSum[0];
    }

    private void sumNumbers(TreeNode root, int[] pathSum, int[] totalSum) {
        if(root == null)
            return;

        pathSum[0] = pathSum[0] * 10 + root.val;
        if(root.left == null && root.right == null) {
            totalSum[0] += pathSum[0];
        } else {
            sumNumbers(root.left, pathSum, totalSum);
            sumNumbers(root.right, pathSum, totalSum);
        }
        pathSum[0] = pathSum[0]/10;
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        t.right = new TreeNode(3);

        System.out.println(new SumRootToLeafNumbers().sumNumbers(t));
        System.out.println(new SumRootToLeafNumbers().sumNumbersV2(t));
    }

}