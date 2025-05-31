package com.dsandalgos.tree;

import com.dsandalgos.util.TreeLinkNode;

import java.util.LinkedList;
import java.util.Queue;

/*
Given a binary tree

    class TreeLinkNode {
      TreeLinkNode left;
      TreeLinkNode right;
      TreeLinkNode next;
    }
Populate each next pointer to point to its next right node.
If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.
Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level,
and every parent has two children).

For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7

After calling your function, the tree should look like:

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
 */

public class PopulatingNextRightPointersEachNode {

    // works for any tree
    // but not constant space
    public void connectLevelOrder(TreeLinkNode root) {
        if (root == null)
            return;

        Queue<TreeLinkNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        TreeLinkNode prev = null;

        while(!q.isEmpty()) {
            TreeLinkNode tl = q.remove();

            if(tl == null) {
                if(!q.isEmpty()) q.add(null); // add marker for end of level
            } else {
                if(tl.left != null) q.add(tl.left);
                if(tl.right != null) q.add(tl.right);
            }
            // when prev is the last node of the level, tl == null, otherwise it will be the one before
            if (prev != null) prev.next = tl;
            prev = tl;
        }
    }


    // constant space solution
    public void connect(TreeLinkNode root) {
        if (root == null)
            return;
        root.next = null;
        connect(root.left, root);
        connect(root.right, root);
    }

    private void connect(TreeLinkNode curNode, TreeLinkNode parent) {
        if(curNode == null)
            return;

        // since we know it is a complete binary tree
        if(parent.right != curNode) {
            curNode.next = parent.right;
        } else {
            if(parent.next != null) {
                curNode.next = parent.next.left;
            } else {
                curNode.next = null;
            }
        }

        connect(curNode.left, curNode);
        connect(curNode.right, curNode);
    }


    // another constant space solution // without any need of parent
    public void connectR(TreeLinkNode root) {
        if (root == null)
            return;
        root.next = null;
        connectRecur(root);
    }

    private void connectRecur(TreeLinkNode curNode) {
        if(curNode == null)
            return;

        // since we know it is a complete binary tree

        // set next for curNode's left child
        if(curNode.left != null) {
            curNode.left.next = curNode.right;
        }

        // set next for curNode's right child
        if(curNode.right != null) {
            if(curNode.next == null) {
                curNode.right.next = null;
            } else {
                curNode.right.next = curNode.next.left;
            }
        }
        connectRecur(curNode.left);
        connectRecur(curNode.right);
    }

    public static void main(String[] args) {
        PopulatingNextRightPointersEachNode p = new PopulatingNextRightPointersEachNode();

        TreeLinkNode r = new TreeLinkNode(1);
        r.left = new TreeLinkNode(2);
        r.right = new TreeLinkNode(3);

        p.connect(r);
        System.out.println("Done");
    }

}
