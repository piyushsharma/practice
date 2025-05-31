package com.dsandalgos.tophundred;

import com.dsandalgos.util.DataStructureUtility;
import com.dsandalgos.util.TreeNode;

import java.util.*;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
*/

public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new LinkedList<>();
        if(root == null)
            return result;

        /* Using null as level markers */
        Queue<TreeNode> q = new LinkedList();
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
                if(t.left != null) q.add(t.left);
                if(t.right != null) q.add(t.right);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        DataStructureUtility p = new DataStructureUtility();
        TreeNode root = p.sampleBinarySearchTree();

        List<List<Integer>> result = new BinaryTreeLevelOrderTraversal().levelOrder(root);
        for (List<Integer> t : result) {
            for(Integer v : t) {
                System.out.printf("%d ", v);
            }
            System.out.printf("\n");
        }
    }

}
