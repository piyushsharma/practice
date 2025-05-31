package com.dsandalgos.stack;

import java.util.Stack;

/**
 * Created by Piyush Sharma
 */

/*
One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false

*/

public class VerifyPreOrderSerializationBinaryTree {

    public boolean isValidSerialization(String preorder) {

        if (preorder == null) return false;

        Stack<String> stack = new Stack<>();
        String[] arr = preorder.split(",");
        for (int i = 0; i < arr.length; ++i) {
            String cur = arr[i];

            // note that this is a while loop, we keep popping elements from stack until we can
            while (cur.equals("#") && !stack.isEmpty() && stack.peek().equals(cur)) {
                stack.pop();
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
            stack.push(cur);
        }
        return stack.size() == 1 && stack.peek().equals("#");
    }

    public boolean isValidSerializationRecursion(String preorder) {
        String[] arr = preorder.split(",");
        return isValidSerialization(arr, 0) == arr.length - 1;
    }

    public int isValidSerialization(String[] s, int index) {
        if(index >= s.length) {
            return index;
        }

        if(s[index].equals("#")) {
            return index;
        }

        int left = isValidSerialization(s, index + 1);
        if(left >= s.length) {
            return left;
        }
        int right = isValidSerialization(s, left + 1);
        return right;
    }


    // using regex
    public boolean isValidSerializationRegex(String preorder) {

        while(true) {
            preorder = preorder.replaceAll("\\d+,#,#", "#");
            int y = preorder.lastIndexOf("#,#");

            // if y == -1, that means we could not find any occurrence of #,#
            // if y == 0 or 1, we should break because we are done replacing #,# to #
            if(y <= 1) break;

            // this is to handle cases where we have input like 1,#,#,#,# (basically more nulls than required)
            if(y == 2 && preorder.charAt(y - 2) == '#') break;
        }

        return preorder.equals("#");
    }

    public static void main(String[] args) {

        VerifyPreOrderSerializationBinaryTree v = new VerifyPreOrderSerializationBinaryTree();

        String[] arr = new String[]{"9,3,4,#,#,1,#,#,2,#,6,#,#",
                "1,#",
                "9,#,#,1",
                "9,3,#,1,#,#,4,#,#",
                "1,#,#,#,#",
                "9,#,92,#,#",
                "9,9,91,#,#,9,#,49,#,#,#",
                "9,3,#,#,4,#,#",
                "#,#,#",
                "1,#,1,#",
                "100,#,#",
                "100,#,#,1,#,#"};

        for(String input : arr) {
            System.out.println(v.isValidSerialization(input));
        }
    }

}
