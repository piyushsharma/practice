package com.dsandalgos.tree;

public class ConvertBinarySearchTreeToSortedDLL {

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };

    Node head;
    Node tail;

    public Node treeToDoublyList(Node root) {
        if(root == null) {
            return root;
        }

        traverse(root);

        tail.right = head;
        head.left = tail;

        return head;

    }

    private void traverse(Node cur) {

        if(cur == null) {
            return;
        }

        traverse(cur.left);

        if(head == null) {
            head = cur;
        }
        if(tail != null) {
            tail.right = cur;
            cur.left = tail;
        }
        tail = cur;

        traverse(cur.right);
    }
}
