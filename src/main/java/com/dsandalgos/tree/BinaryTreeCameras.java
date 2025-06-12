package com.dsandalgos.tree;

import java.util.*;

/**
 * Given a binary tree, we install cameras on the nodes of the tree.
 *
 * Each camera at a node can monitor its parent, itself, and its immediate children.
 *
 * Calculate the minimum number of cameras needed to monitor all nodes of the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as shown.
 * Example 2:
 *
 *
 * Input: [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
 *
 * Note:
 *
 * The number of nodes in the given tree will be in the range [1, 1000].
 * Every node has value 0.
 */

public class BinaryTreeCameras {


     //Definition for a binary tree node.
    class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
     }

     int ans;
     Set<TreeNode> covered;
     public int minCameraCover(TreeNode root) {
         ans = 0;
         covered = new HashSet<TreeNode>();
         covered.add(null);
 
         dfs(root, null);
         return ans;
     }
 
     public void dfs(TreeNode node, TreeNode par) {
         if (node != null) {
             dfs(node.left, node);
             dfs(node.right, node);
 

            // if a node has children covered and has a parent, then better to place at it's parent 
            // if a node has children that ar not covered - we should place camera here
            // if a node has NO parent - we should place camera hereO

             if (par == null && !covered.contains(node) || !covered.contains(node.left) || !covered.contains(node.right)) {
                 ans++;
                 covered.add(node);
                 covered.add(par);
                 covered.add(node.left);
                 covered.add(node.right);
             }
         }
     }



    // class Node {
    //     boolean camera;
    //     boolean visited;
    //     Node parent;
    //     Node left;
    //     Node right;
    //     int val;
    //     Node(int val) {
    //         this.val = val;
    //     }
    // }

    // int min = Integer.MAX_VALUE;
    // public int minCameraCover(TreeNode root) {
    //     if(root == null) {
    //         return 0;
    //     }

    //     Map<TreeNode, Node> map = new HashMap<>();
    //     Node rootNode = new Node(root.val);
    //     Queue<TreeNode> queue = new LinkedList<>();
    //     queue.add(root);
    //     map.put(root, rootNode);

    //     while(!queue.isEmpty()) {
    //         TreeNode cur = queue.remove();
    //         if (cur.left != null) {
    //             Node left = new Node(cur.left.val);
    //             left.parent = map.get(cur);
    //             map.get(cur).left = left;
    //             queue.add(cur.left);
    //         }
    //         if (cur.right != null) {
    //             Node right = new Node(cur.right.val);
    //             right.parent = map.get(cur);
    //             map.get(cur).right = right;
    //             queue.add(cur.right);
    //         }
    //     }
    //     dfs(rootNode);
    //     return min;
    // }

    // private int dfs(Node node) {
    //     if(node == null || node.visited) {
    //         return 0;
    //     }
    //     node.visited = true;

    //     // node has coverage from parent or left or right children
    //     if((node.parent != null && node.parent.camera) || (node.left != null && node.left.camera)
    //             || (node.right != null && node.right.camera)) {
    //         return 0;
    //     }

    //     // node has no coverage, put camera here
    //     node.camera = true;

    //     int left = dfs(node.left);
    //     int right = dfs(node.right);

    //     int sum = 1 + left + right;
    //     min = Math.min(sum, min);

    //     // revert the camera to try other places
    //     node.camera = false;
    //     node.visited = false;

    //     return sum;
    // }
}
