package com.tosort;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */


public class BST {

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
    private TreeNode insertNode(TreeNode root, int key) {

        if(root == null)
            return new TreeNode(key);

        if(key < root.val) {
            root.left = insertNode(root.left, key);
        } else if (key > root.val) {
            root.right = insertNode(root.right, key);
        }
        return root;
    }


    /*
    1) Node to be deleted is leaf: Simply remove from the tree.
              50                            50
           /     \         delete(20)      /   \
          30      70       --------->    30     70
         /  \    /  \                     \    /  \
       20   40  60   80                   40  60   80

    2) Node to be deleted has only one child: Copy the child to the node and delete the child
              50                            50
           /     \         delete(30)      /   \
          30      70       --------->    40     70
            \    /  \                          /  \
            40  60   80                       60   80

    3) Node to be deleted has two children: Find inorder successor of the node.
        Copy contents of the inorder successor to the node and delete the inorder successor.
        Note that inorder predecessor can also be used.

              50                            60
           /     \         delete(50)      /   \
          40      70       --------->    40    70
                 /  \                            \
                60   80                           80


       The important thing to note is, inorder successor is needed only when right child is not empty.
       In this particular case, inorder successor can be obtained by
       finding the minimum value in right child of the node.
     */
    private TreeNode deleteNode(TreeNode root, int key) {
        // base case
        if(root == null)
            return root;

        // If the key to be deleted is smaller than the root's key,
        // then it lies in left subtree
        if (key < root.val)
            root.left = deleteNode(root.left, key);
        // If the key to be deleted is greater than the root's key,
        // then it lies in right subtree
        else if (key > root.val)
            root.right = deleteNode(root.right, key);

        // if key is same as root's key, then This is the node
        // to be deleted
        else
        {
            // node with only one child or no child
            if (root.left == null) {
                TreeNode temp = root.right;
                root.right = null;
                return temp;
            }
            else if (root.right == null) {
                TreeNode temp = root.left;
                root.left = null;
                return temp;
            }

            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            TreeNode temp = minValueNode(root.right);

            // Copy the inorder successor's content to this node
            root.val = temp.val;

            // Delete the inorder successor
            root.right = deleteNode(root.right, temp.val);
        }

        return root;
    }

    /* Given a non-empty binary search tree, return the node with minimum
        key value found in that tree. Note that the entire tree does not
        need to be searched. */
    private TreeNode minValueNode(TreeNode right) {
        TreeNode cur = right;

        /* loop down to find the leftmost leaf */
        while(cur.left != null)
            cur = cur.left;

        return cur;
    }


    private TreeNode searchKey(TreeNode root, int key) {

        if(root == null || root.val == key)
            return root;

        if(key < root.val) {
            return searchKey(root.left, key);
        } else {
            return searchKey(root.right, key);
        }
    }


    public static void main(String[] args) {
        new BST().test();
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

        TreeNode result = deleteNode(n4, 60);
        printInOrder(result);
    }

    private void printInOrder(TreeNode root) {
        if(root == null)
            return;

        printInOrder(root.left);
        System.out.println(root.val);
        printInOrder(root.right);
    }
}
