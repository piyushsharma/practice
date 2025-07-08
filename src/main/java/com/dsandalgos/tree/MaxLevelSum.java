package com.dsandalgos.tree;

import com.dsandalgos.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MaxLevelSum {

    public int maxLevelSum(TreeNode root) {


        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int maxSum = Integer.MIN_VALUE;
        int level = 0;
        int answer = 0;

        while(!q.isEmpty()) {
            ++level;
            int curSum = 0;
            int size = q.size();
            for(int i = 0; i < size; ++i) {

                TreeNode cur = q.remove();

                curSum += cur.val;

                if(cur.left != null) q.add(cur.left);
                if(cur.right != null) q.add(cur.right);
            }

            if(maxSum < curSum) {
                maxSum = curSum;
                answer = level;
            }

        }


        return answer;
    }
}
