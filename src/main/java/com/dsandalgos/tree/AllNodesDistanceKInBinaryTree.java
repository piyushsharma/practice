package com.dsandalgos.tree;

import java.util.*;

public class AllNodesDistanceKInBinaryTree {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    List<Integer> res = new ArrayList<>();
    Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
    Set<TreeNode> visited = new HashSet<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        buildGraph(root, null);
        visited.add(target);
        dfs(target, 0, k);
        return res;
    }

    private void dfs(TreeNode cur, int dist, int k) {

        if(dist == k) {
            res.add(cur.val);
            return;
        }

        for(TreeNode n : graph.getOrDefault(cur, new ArrayList<>())) {
            if(!visited.contains(n)) {
                visited.add(n);
                dfs(n, dist + 1, k);
            }
        }
    }

    private void buildGraph(TreeNode root, TreeNode parent) {
        if(root != null && parent != null) {
            List<TreeNode> neighbors = graph.getOrDefault(root, new ArrayList<>());
            neighbors.add(parent);
            graph.put(root, neighbors);

            neighbors = graph.getOrDefault(parent, new ArrayList<>());
            neighbors.add(root);
            graph.put(parent, neighbors);
        }

        if(root.left != null) {
            buildGraph(root.left, root);
        }

        if(root.right != null) {
            buildGraph(root.right, root);
        }
    }
}
