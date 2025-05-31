package com.dsandalgos.tree;

import com.dsandalgos.util.DataStructureUtility;
import com.dsandalgos.util.PrintUtil;
import com.dsandalgos.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
 */

public class BinaryTreePreorderTraversal {


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();

        Stack<TreeNode> s = new Stack<TreeNode>();

        while (true) {
            while(root != null) {
                result.add(root.val);
                s.push(root);
                root = root.left;
            }
            if(s.isEmpty()) break;

            root = s.pop();
            root = root.right;
        }

        return result;
    }

    public List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        preorderTraversal(root, result);
        return result;
    }

    public void preorderTraversal(TreeNode root, List<Integer> result) {
        if(root == null)
            return;

        result.add(root.val);
        preorderTraversal(root.left, result);
        preorderTraversal(root.right, result);
    }

    public static void main(String[] args) {
        DataStructureUtility p = new DataStructureUtility();
        TreeNode root = p.sampleBinarySearchTree();

        List<Integer> r = new BinaryTreePreorderTraversal().preorderTraversalRecursive(root);
        PrintUtil.printIntegerList(r);

        r = new BinaryTreePreorderTraversal().preorderTraversal(root);
        PrintUtil.printIntegerList(r);
    }

}
