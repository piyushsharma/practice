package com.dsandalgos.tophundred;

import com.dsandalgos.util.TreeNode;

import java.util.*;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed
later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree.
There is no restriction on how your serialization/deserialization algorithm should work.
You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the
original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree.
You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states.
Your serialize and deserialize algorithms should be stateless.
 */

public class SerializeAndDeserializeBinaryTree {

    private static String nullValue = "null";

    public String serialize(TreeNode root) {
        StringBuilder data = new StringBuilder();
        preOrderTraversal(root, data);
        return data.toString().substring(0, data.length()-1);
    }

    public void preOrderTraversal(TreeNode root, StringBuilder data) {
        if(root == null) {
            data.append(nullValue);
            data.append(",");
            return;
        }
        data.append(Integer.toString(root.val));
        data.append(",");
        preOrderTraversal(root.left, data);
        preOrderTraversal(root.right, data);
    }

    public TreeNode deserialize(String data) {
        if(data.equals(nullValue)) {
            return null;
        }
        String[] splitData = data.split(",");
        Queue<String> values = new LinkedList<String>(Arrays.asList(splitData));
        return preOrderDeserializer(values);
    }

    private TreeNode preOrderDeserializer(Queue<String> values) {
        if(values.isEmpty())
            return null;

        String s = values.poll();
        if(s.equals(nullValue))
            return null;

        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = preOrderDeserializer(values);
        root.right = preOrderDeserializer(values);
        return root;
    }

    // Encodes a tree to a single string.
    public String serializeLevelOrder(TreeNode root) {
        String nullValue = "null";
        StringBuilder s = new StringBuilder();

        // level order and add integers, for null add the string 'null'
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);

        while(!q.isEmpty()) {
            TreeNode t = q.poll();
            if (t == null) {
                s.append(nullValue);
                s.append(",");
                continue;
            }
            q.add(t.left);
            q.add(t.right);
            s.append(Integer.toString(t.val));
            s.append(",");
        }
        return s.toString().substring(0, s.length()-1);
    }

    // Decodes your encoded data to tree.   # Not Working
    public TreeNode deserializeLevelOrder(String data) {
        String nullValue = "null";
        if(data.equals(nullValue)) {
            return null;
        }

        String[] values = data.split(",");
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        for(int i = 0; i < values.length; ++i) {
            if(values[i].equals(nullValue)) {
                nodes.add(null);
            } else {
                nodes.add(new TreeNode(Integer.parseInt(values[i])));
            }
        }

        int level = 0;
        for(int i = 0; i < values.length; ++i) {
            if((2*i + 2) >= values.length) {
                break;
            }
            TreeNode t = nodes.get(level);
            if(t != null) {
                t.left = nodes.get(2*level + 1);
                t.right = nodes.get(2*level + 2);
            }
            ++level;
        }

        return nodes.get(0);
    }


    public static void main(String[] args) {
        SerializeAndDeserializeBinaryTree x = new SerializeAndDeserializeBinaryTree();

        //[5,2,3,null,null,2,4,3,1]
        TreeNode root = new TreeNode(5);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(2);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(3);
        TreeNode n6 = new TreeNode(1);
        root.left = n1;
        root.right = n2;
        n2.left = n3;
        n2.right = n4;
        n3.left = n5;
        n3.right = n6;


        String serializedData = x.serializeLevelOrder(root);
//        String serializedData = x.serialize(null);
        System.out.println(serializedData);
        TreeNode deRoot = x.deserializeLevelOrder(serializedData);
        System.out.println(deRoot.val);
    }

}
