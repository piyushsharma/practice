package com.dsandalgos.tree;

import com.dsandalgos.util.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
 */

public class BinaryTreeRightSideView {


    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root == null)
            return result;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        TreeNode prev = null;
        q.add(root);
        q.add(null);
        while(!q.isEmpty()) {
            TreeNode t = q.remove();
            if(t == null) {
                if(!q.isEmpty()) q.add(null);
                if(prev != null) result.add(prev.val);
            } else {
                if(t.left != null) q.add(t.left);
                if(t.right != null) q.add(t.right);
            }
            // will contain the right most node of that level when we are adding to the result
            prev = t;
        }
        return result;
    }


    public List<Integer> rightSideViewEfficient(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while(!queue.isEmpty()){
            //get size here
            int size = queue.size();

            for(int i = 0; i < size; ++i) {
                TreeNode top = queue.remove();

                //the first element in the queue (right-most of the tree)
                if(i == 0) {
                    result.add(top.val);
                }
                //add right first to be able to use the i == 0 construct
                if(top.right != null){
                    queue.add(top.right);
                }
                //add left
                if(top.left != null){
                    queue.add(top.left);
                }
            }
        }
        return result;
    }
}
