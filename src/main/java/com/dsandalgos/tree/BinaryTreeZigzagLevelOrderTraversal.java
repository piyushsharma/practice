package com.dsandalgos.tree;

import com.dsandalgos.util.TreeNode;

import java.util.*;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a binary tree, return the zigzag level order traversal of its nodes' values.
(ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if(root == null)
            return result;

        /* Using null as level markers */
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        List<Integer> levelList = new LinkedList<>();

        while(!q.isEmpty()) {
            TreeNode t = q.remove();
            if(t == null) {
                if(!q.isEmpty()) {
                    q.add(null);
                }
                result.add(levelList);
                levelList = new LinkedList<>();
            } else {
                levelList.add(t.val);
                if (t.left != null) q.add(t.left);
                if (t.right != null) q.add(t.right);
            }
        }

        for(int i = 0; i < result.size(); ++i) {
            // reverse all odd numbered list items
            if(i % 2 != 0) {

                List<Integer> l = result.get(i);
                int totalNodes = l.size();
                // reverse this list
                for(int j = 0; j < totalNodes; ++j) {
                    int temp = l.get(j);
                    l.set(j, l.get(totalNodes - 1));
                    l.set(totalNodes - 1, temp);
                    --totalNodes;
                }
            }
        }

        return result;
    }

    public List<List<Integer>> zigzagLevelOrderV2(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if(root == null)
            return result;

        /* Using null as level markers */
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        q.add(null);
        List<Integer> levelList = new LinkedList<Integer>();
        Stack<Integer> levelStack = new Stack<Integer>();
        boolean leftToRight = true;

        while(!q.isEmpty()) {
            TreeNode t = q.remove();
            if(t == null) {
                if(!q.isEmpty()) {
                    q.add(null);
                }
                if(leftToRight) {
                    result.add(levelList);
                } else {
                    // convert stack to list to add to the result set
                    List<Integer> stackList = new ArrayList<Integer>();
                    while(!levelStack.isEmpty()) {
                        stackList.add(levelStack.pop());
                    }
                    result.add(stackList);
                }
                leftToRight = !leftToRight;
                levelList = new LinkedList<Integer>();
                levelStack = new Stack<Integer>();
            } else {
                if(leftToRight) { levelList.add(t.val); }
                else { levelStack.push(t.val); }

                if (t.left != null) q.add(t.left);
                if (t.right != null) q.add(t.right);
            }
        }
        return result;
    }

}
