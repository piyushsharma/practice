package com.dsandalgos.tree;

/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such
that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */


import com.dsandalgos.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PathSum {

    public boolean hasPathSum(TreeNode root, int sum) {

        if(root == null)
            return false;

        if(root.left == null && root.right == null && sum - root.val == 0) {
            return true;
        }

        sum -= root.val;
        if(hasPathSum(root.left, sum))
            return true;

        return hasPathSum(root.right, sum);
    }

    public boolean hasPathSumShorterMethod(TreeNode root, int sum) {

        if(root == null)
            return false;

        if(root.left == null && root.right == null && sum - root.val == 0) {
            return true;
        }

        return (hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val));
    }

    public boolean hasPathGetAllSums(TreeNode root, int sum) {

        if(root == null)
            return false;

        List<Integer> allPossibleSums = new ArrayList<Integer>();

        findAllPathsToLeaf(root, 0, allPossibleSums);

        for(int i = 0; i < allPossibleSums.size(); ++i) {
            if(allPossibleSums.get(i) == sum) {
                return true;
            }
        }
        return false;
    }

    public void findAllPathsToLeaf(TreeNode root, int curSum, List<Integer> results) {
        if(root == null)
            return;

        int sum = curSum + root.val;
        if(root.left == null && root.right == null) {
            results.add(sum);
        } else {
            findAllPathsToLeaf(root.left, sum, results);
            findAllPathsToLeaf(root.right, sum, results);
        }
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(8);
        TreeNode n3 = new TreeNode(11);
        TreeNode n4 = new TreeNode(13);
        TreeNode n5 = new TreeNode(4);
        TreeNode n6 = new TreeNode(7);
        TreeNode n7 = new TreeNode(2);
        TreeNode n8 = new TreeNode(5);
        TreeNode n9 = new TreeNode(1);

        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n5.left = n8;
        n5.right = n9;

        boolean hasSum = new PathSum().hasPathSum(root, 22);
        System.out.println(hasSum);
    }

}