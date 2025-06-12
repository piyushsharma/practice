package com.tosort;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/* Convert a binary search tree to a circular doubly linked list.
   -- left becomes previous and right becomes next
 */

public class BSTToCircularDoublyLL {

    class TreeNode {
        public int val;

        // this is to store the number of nodes in the left subtree
        // binary search tree can be augmented like this to find kth smallest element faster
        public int leftCount;

        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
            leftCount = 0;
        }
    }


    private TreeNode bstToCircularDList(TreeNode root) {
        if(root == null)
            return root;

        TreeNode leftList = bstToCircularDList(root.left);
        TreeNode rightList = bstToCircularDList(root.right);

        root.left = root;
        root.right = root;

        TreeNode head = appendCircularDoublyLists(leftList, root);
        head = appendCircularDoublyLists(head, rightList);
        return head;
    }


    private TreeNode appendCircularDoublyLists(TreeNode a, TreeNode b) {
        if(a == null) return b;
        if(b == null) return a;

        TreeNode aLastNode = a.left;
        TreeNode bLastNode = b.left;

        a.left = bLastNode;
        b.left = aLastNode;

        aLastNode.right = b;
        bLastNode.right = a;
        return a;
    }


    private TreeNode bstToDList(TreeNode root) {
        if(root == null)
            return root;

        TreeNode leftList = bstToDList(root.left);
        TreeNode rightList = bstToDList(root.right);

        root.left = null;
        root.right = null;

        TreeNode head = appendDoublyLists(leftList, root);
        head = appendDoublyLists(head, rightList);
        return head;
    }

    private TreeNode appendDoublyLists(TreeNode a, TreeNode b) {
        if(a == null) return b;
        if(b == null) return a;

        TreeNode tempA = a;
        while(tempA.right != null) {
            tempA = tempA.right;
        }
        TreeNode aLastNode = tempA;

        aLastNode.right = b;
        b.left = aLastNode;
        return a;
    }



    /* Inorder printing */
    public void printTreeInOrder(TreeNode root) {
        if(root == null) {
            return;
        }

        printTreeInOrder(root.left);
        System.out.printf("%d\n", root.val);
        printTreeInOrder(root.right);
    }

    public void printCirclularDoublyLists(TreeNode head) {
        TreeNode cur = head;
        System.out.println("Print left circular list..");
        while(cur != null) {
            System.out.println(cur.val);
            cur = cur.left;
            if(cur == head) break;
        }

        cur = head;
        System.out.println("Print right circular list..");
        while(cur != null) {
            System.out.println(cur.val);
            cur = cur.right;
            if(cur == head) break;
        }
    }


    private static void printDoublyList(TreeNode head) {
        TreeNode cur = head;
        TreeNode tail = head;

        System.out.println("Print next doubly list..");
        while(cur != null) {
            System.out.println(cur.val);
            tail = cur;
            cur = cur.right;
        }

        cur = tail;
        System.out.println("Print prev circular list..");
        while(cur != null) {
            System.out.println(cur.val);
            cur = cur.left;
        }
    }

    public static void main(String[] args) {
        new BSTToCircularDoublyLL().test();
    }

    private void test() {
        TreeNode n1 = new TreeNode(10);
        TreeNode n2 = new TreeNode(20);
        TreeNode n3 = new TreeNode(30);
        TreeNode n4 = new TreeNode(40);
        TreeNode n5 = new TreeNode(50);
        TreeNode n6 = new TreeNode(60);
        TreeNode n7 = new TreeNode(70);

        n2.left = n1;
        n2.right = n3;
        n6.left = n5;
        n6.right = n7;
        n4.left = n2;
        n4.right = n6;

        printTreeInOrder(n4);
        TreeNode head = bstToCircularDList(n4);
        printCirclularDoublyLists(head);

//        Node head = bstToDList(n4);
//        printDoublyList(head);
    }
}
