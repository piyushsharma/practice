package com.dsandalgos.tree;

import com.dsandalgos.util.DataStructureUtility;
import com.dsandalgos.util.PrintUtil;
import com.dsandalgos.util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

Note: Recursive solution is trivial, could you do it iteratively?
 */

public class BinaryTreePostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();

        Stack<TreeNode> s = new Stack<>();
        HashMap<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();

        while(true) {
            while(root != null) {
                map.put(root, 1);
                s.push(root);
                root = root.left;
            }

            if(s.empty()) break;

            root = s.pop();
            if(map.get(root) == 1) {
                s.push(root);  // push it back, since this is the first visit
                map.put(root, 2); // update visit count
                root = root.right; // traverse the right subtree
            } else {
                result.add(root.val); // second visit, print the node
                root = null;  // pop the next node, make root null
            }
        }
        return result;
    }

    public List<Integer> postorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        postorderTraversal(root, result);
        return result;
    }

    public void postorderTraversal(TreeNode root, List<Integer> result) {
        if(root == null)
            return;

        postorderTraversal(root.left, result);
        postorderTraversal(root.right, result);
        result.add(root.val);
    }

    public static void main(String[] args) {
        DataStructureUtility p = new DataStructureUtility();
        TreeNode root = p.sampleBinarySearchTree();

        List<Integer> r = new BinaryTreePostorderTraversal().postorderTraversalRecursive(root);
        PrintUtil.printIntegerList(r);

        r = new BinaryTreePostorderTraversal().postorderTraversal(root);
        PrintUtil.printIntegerList(r);
    }
}


