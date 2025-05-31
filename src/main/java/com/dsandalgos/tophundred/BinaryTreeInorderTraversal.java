package com.dsandalgos.tophundred;

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
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?
*/

public class BinaryTreeInorderTraversal {

    // iterative
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root == null)
            return result;

        Stack<TreeNode> r = new Stack<>();
        while(true) {
            // doing this, we add the right child and then add all the left child of that right child to the stack
            while(root != null) {
                r.push(root);
                root = root.left;
            }
            if(r.empty()) break;

            root = r.pop();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }

    // recursive
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        inorderTraversal(root, result);
        return result;
    }

    public void inorderTraversal(TreeNode root, List<Integer> result) {
        if(root == null)
            return;

        inorderTraversal(root.left, result);
        result.add(root.val);
        inorderTraversal(root.right, result);
    }

    public static void main(String[] args) {
        DataStructureUtility p = new DataStructureUtility();
        TreeNode root = p.sampleBinarySearchTree();

        List<Integer> r = new BinaryTreeInorderTraversal().inorderTraversal(root);
        PrintUtil.printIntegerList(r);
    }
}
