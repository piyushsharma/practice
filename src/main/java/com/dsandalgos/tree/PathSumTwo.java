package com.dsandalgos.tree;


/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
 */

import com.dsandalgos.util.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PathSumTwo {


    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        List<Integer> path = new ArrayList<Integer>();
        pathSum(root, sum, path, result);
        return result;
    }

    private void pathSum(TreeNode root, int sum, List<Integer> path, List<List<Integer>> result) {

        if(root == null)
            return;

        path.add(root.val);
        if(root.left == null && root.right == null && sum - root.val == 0) {
            result.add(new ArrayList<Integer>(path));
        } else {
            pathSum(root.left, sum - root.val, path, result);
            pathSum(root.right, sum - root.val, path, result);
        }

//        System.out.println("========================================");
//        for(int i = 0; i < path.size(); ++i) {
//            System.out.printf("%d ", path.get(i));
//        }
//        System.out.println("\n========================================");

        // this is done because once we have crossed the current root, we must have gone to all paths
        // and added any path to leaf that was == sum
        // now we remove the last leaf node (since this must have already been accounted for
        // and let only the parent remain in the path
        // we keep popping parent nodes until we see another leaf node
        path.remove(path.size() - 1);
    }


    public List<List<Integer>> pathSumUsingIndex(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        List<Integer> path = new ArrayList<Integer>();
        pathSumUsingIndex(root, sum, path, 0, result);
        return result;
    }

    private void pathSumUsingIndex(TreeNode root, int sum, List<Integer> path, int curIndex,
                                   List<List<Integer>> result) {

        if(root == null)
            return;

        try {
            path.get(curIndex); // throw exception if not present,
            // set if no exception thrown
            path.set(curIndex, root.val);
        } catch (IndexOutOfBoundsException e) {
            // add at that index in case of exception
            path.add(curIndex, root.val);
        }
        ++curIndex;
        if(root.left == null && root.right == null && sum - root.val == 0) {
            result.add(new ArrayList<Integer>(path));
        } else {
            pathSumUsingIndex(root.left, sum - root.val, path, curIndex, result);
            pathSumUsingIndex(root.right, sum - root.val, path, curIndex, result);
        }
    }

    // get all paths and then filter out for sum
    public List<List<Integer>> pathSumGetAllPaths(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        List<Integer> path = new ArrayList<Integer>();
        pathSum(root, path, result);

        // remove all paths that do not equal sum
        for(Iterator<List<Integer>> iterator = result.iterator(); iterator.hasNext();) {
            List<Integer> l = iterator.next();
            int curSum = 0;
            for(int i = 0; i < l.size(); ++i) {
                curSum += l.get(i);
            }
            if (curSum != sum) {
                iterator.remove();
            }
        }

        return result;
    }

    private void pathSum(TreeNode root, List<Integer> path, List<List<Integer>> result) {

        if(root == null)
            return;

        path.add(root.val);
        if(root.left == null && root.right == null) {
            result.add(new ArrayList<Integer>(path));
        } else {
            pathSum(root.left, path, result);
            pathSum(root.right, path, result);
        }
        path.remove(path.size() - 1);
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

        List<List<Integer>> result = new PathSumTwo().pathSum(root, 22);
        for(List l : result) {
            for (int i = 0; i < l.size(); ++i) {
                System.out.printf("%d ", l.get(i));
            }
            System.out.println();
        }
        System.out.println("++++++++++++++++++++++++++++++++++++");
        result = new PathSumTwo().pathSumGetAllPaths(root, 22);
        for(List l : result) {
            for (int i = 0; i < l.size(); ++i) {
                System.out.printf("%d ", l.get(i));
            }
            System.out.println();
        }
        System.out.println("++++++++++++++++++++++++++++++++++++");
        result = new PathSumTwo().pathSumUsingIndex(root, 22);
        for(List l : result) {
            for (int i = 0; i < l.size(); ++i) {
                System.out.printf("%d ", l.get(i));
            }
            System.out.println();
        }



    }


}