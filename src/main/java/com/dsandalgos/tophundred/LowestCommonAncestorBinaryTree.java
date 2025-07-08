package com.dsandalgos.tophundred;

/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia:
“The lowest common ancestor is defined between two nodes v and w
as the lowest node in T that has both v and w as descendants
(where we allow a node to be a descendant of itself).”

         _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4

For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3.
Another example is LCA of nodes 5 and 4 is 5,
since a node can be a descendant of itself according to the LCA definition.
*/

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorBinaryTree {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    // you have node with parent but no root
    public Node lowestCommonAncestorThree(Node p, Node q) {
        Set<Node> vals = new HashSet<>();
        Node cur = p;
        while(cur != null) {
            vals.add(cur);
            cur = cur.parent;
        }


        while(q != null) {
            if(vals.contains(q)) return q;
            q = q.parent;
        }
        return q;
    }

    public Node lowestCommonAncestorThreeV2(Node p, Node q) {
        Node p1 = p;
        Node q1 = q;
        while(p1 != q1) {
            p1 = p1 == null ? q : p1.parent;
            q1 = q1 == null ? p : q1.parent;
        }
        return p1;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
            return root;


        if(p == root || q == root)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null) {
            return root;
        }

        return left == null ? right : left;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}