package com.dsandalgos.tree;

import java.util.*;

public class VerticalOrder {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class TreeNodePos {
        TreeNode node;
        int col;

        public TreeNodePos(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }


    public List<List<Integer>> verticalOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        Map<Integer, List<Integer>> map = new HashMap<>();
        int minCol = 0;
        int maxCol = 0;
        List<TreeNodePos> cur = new ArrayList<>();
        cur.add(new TreeNodePos(root, 0));

        while(!cur.isEmpty()) {

            List<TreeNodePos> next = new ArrayList<>();
            for(TreeNodePos t : cur) {

                int col = t.col;
                List<Integer> colVals = map.getOrDefault(col, new ArrayList<>());
                colVals.add(t.node.val);
                map.put(col, colVals);

                if(t.node.left != null) {
                    next.add(new TreeNodePos(t.node.left, col - 1));
                    minCol = Math.min(minCol, col - 1);
                }

                if(t.node.right != null) {
                    next.add(new TreeNodePos(t.node.right, col + 1));
                    maxCol = Math.max(maxCol, col + 1);
                }
            }
            cur = next;
        }

        for(int i = minCol; i <= maxCol; ++i) {
            result.add(map.get(i));
        }

        return result;
    }

    private TreeNode createTestRootNode() {

        TreeNode root = new TreeNode(3);

        TreeNode left1 = new TreeNode(9);
        TreeNode right1 = new TreeNode(20);

        TreeNode right1left = new TreeNode(15);
        TreeNode right1right = new TreeNode(7);

        root.left = left1;
        root.right = right1;

        root.right.left = right1left;
        root.right.right = right1right;

        return root;

    }

    public static void main(String[] args) {
        VerticalOrder v = new VerticalOrder();
        TreeNode root = v.createTestRootNode();

        List<List<Integer>> result = v.verticalOrder(root);
        System.out.println("done");

    }


}
