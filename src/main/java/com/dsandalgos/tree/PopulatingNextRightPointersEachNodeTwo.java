package com.dsandalgos.tree;

import com.dsandalgos.util.TreeLinkNode;

/*
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7

After calling your function, the tree should look like:

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/

public class PopulatingNextRightPointersEachNodeTwo {

    // recursive solution

    /*
    We traverse the next right node before the left and right children (root, next, left),
    then we can make sure that all nodes at level i have the next right set, before the level i+1 nodes.

    Let us consider the example above: We have to set the right for level with nodes 2 and 3 before the last level.
    In this method, we make sure that all nodes at the 2's level (level 2) have next right set,
    before we try to set the next right of 5. So when we set the next right of 5, we search for a nonleaf
    node on right side of node 3 (getNextRight() does this for us).
    */
    public void connect(TreeLinkNode root) {
        if (root == null)
            return;

        root.next = null;
        connectRecur(root);
    }

    /* Set next right of all descendents of curNode. This function makes sure that
       next right of nodes at level i are set before level i+1 nodes. */
    public void connectRecur(TreeLinkNode curNode) {
        if(curNode == null)
            return;

        // first recursively set the next pointer of nodes at the same level
        if(curNode.next != null) {
            connectRecur(curNode.next);
        }

        if(curNode.left != null) {

            if(curNode.right != null) {
                curNode.left.next = curNode.right;
                // we can just fill the next for the right child here
                curNode.right.next = getNextRight(curNode);
            } else {
                curNode.left.next = getNextRight(curNode);
            }

            /* Recursively call for next level nodes. Note that we call only
               for left child. The call for left child will call for right child */
            connectRecur(curNode.left);

        } else if(curNode.right != null) {

            // i.e. left child is null,
            curNode.right.next = getNextRight(curNode);
            connectRecur(curNode.right);

        } else {
            // both left and right child are null, so the next node at the next level will be
            // whatever is returned by getNextRight(curNode)
            connectRecur(getNextRight(curNode));
        }
    }


    public TreeLinkNode getNextRight(TreeLinkNode curNode) {
        TreeLinkNode curNextRight = curNode.next;

         /* Traverse nodes at curNode's level and find and return the first node's first child */
        while(curNextRight != null) {
            if(curNextRight.left != null) {
                return curNextRight.left;
            } else if (curNextRight.right != null) {
                return curNextRight.right;
            }
            curNextRight = curNextRight.next;
        }
        // If all the nodes at curNode's level are leaf nodes then return NULL
        return null;
    }


    // iterative solution: much easier to understand
    public void connectIterative(TreeLinkNode root) {
        if (root == null)
            return;

        root.next = null;

        // set next right of all levels one by one
        while(root != null) {
            TreeLinkNode cur = root;

            while(cur != null) {
                if(cur.left != null) {
                    if(cur.right != null) {
                        cur.left.next = cur.right;
                    } else {
                        cur.left.next = getNextRight(cur);
                    }
                }

                if(cur.right != null) {
                    cur.right.next = getNextRight(cur);
                }

                cur = cur.next;
            }

            if(root.left != null) {
                root = root.left;
            } else if (root.right != null) {
                root = root.right;
            } else {
                root = getNextRight(root);
            }
        }
    }

    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(1);

        TreeLinkNode n1 = new TreeLinkNode(2);
        TreeLinkNode n2 = new TreeLinkNode(3);
        TreeLinkNode n3 = new TreeLinkNode(4);
        TreeLinkNode n4 = new TreeLinkNode(5);
        TreeLinkNode n5 = new TreeLinkNode(6);
        TreeLinkNode n6 = new TreeLinkNode(7);
        TreeLinkNode n7 = new TreeLinkNode(8);

        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.right = n5;
        n3.left = n6;
        n5.right = n7;

        new PopulatingNextRightPointersEachNodeTwo().connectIterative(root);
        System.out.println("Done!");
    }

}
