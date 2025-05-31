package com.dsandalgos.util;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */
public class PrintUtil {

    public static void printIntegerList(List<Integer> list) {
        for(int i = 0; i < list.size(); ++i) {
            System.out.printf("%d ", list.get(i));
        }
        System.out.printf("\n");
    }


    public static void printStringList(List<String> list) {
        for(int i = 0; i < list.size(); ++i) {
            System.out.println(list.get(i));
        }
    }

    public static void printLinkedList(ListNode head) {
        while(head != null) {
            System.out.printf("%d ", head.val);
            head = head.next;
        }
        System.out.println();
    }

    public static void printPostOrder(TreeNode root) {
        if(root == null)
            return;
        printPostOrder(root.left);
        printPostOrder(root.right);
        System.out.printf("%d ", root.val);
    }

    public static void printInOrder(TreeNode root) {
        if(root == null)
            return;
        printInOrder(root.left);
        System.out.printf("%d ", root.val);
        printInOrder(root.right);
    }

    public static void printPreOrder(TreeNode root) {
        if(root == null)
            return;
        System.out.printf("%d ", root.val);
        printPreOrder(root.left);
        printPreOrder(root.right);
    }
}
