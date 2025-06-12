package com.dsandalgos.tree;

import com.dsandalgos.util.DataStructureUtility;
import com.dsandalgos.util.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values.
(ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]

*/

public class BinaryTreeLevelOrderTraversalTwo {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if(root == null)
            return result;

        /* Using null as level markers */
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        List<Integer> levelList = new LinkedList<Integer>();

        while(!q.isEmpty()) {
            TreeNode t = q.remove();
            if(t == null) {
                if(!q.isEmpty()) {
                    q.add(null);
                }
                result.add(levelList);
                levelList = new LinkedList<Integer>();
            } else {
                levelList.add(t.val);
                if(t.left != null) q.add(t.left);
                if(t.right != null) q.add(t.right);
            }
        }

        int levels = result.size();
        for(int i = 0; i < levels; ++i) {
            List<Integer> l = result.get(i);
            result.set(i, result.get(levels - 1));
            result.set(levels - 1, l);
            --levels;
        }
        return result;
    }


    public static void main(String[] args) {
        DataStructureUtility p = new DataStructureUtility();
        TreeNode root = p.sampleBinarySearchTree();

        List<List<Integer>> result = new BinaryTreeLevelOrderTraversalTwo().levelOrderBottom(root);
        for (List<Integer> t : result) {
            for(Integer v : t) {
                System.out.printf("%d ", v);
            }
            System.out.printf("\n");
        }
    }

}
