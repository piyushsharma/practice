package com.dsandalgos.stack;

import java.util.List;
import java.util.Stack;

public class ExclusiveTimeOfFunctions {

    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[n];

        String[] item = logs.get(0).split(":");
        stack.push(Integer.parseInt(item[0]));
        int prev = Integer.parseInt(item[2]);

        for(int i = 1; i < logs.size(); ++i) {

            item = logs.get(i).split(":");
            int fid = Integer.parseInt(item[0]);
            int tid = Integer.parseInt(item[2]);

            if(item[1].equals("start")) {
                if(!stack.isEmpty()) {
                    res[stack.peek()] += tid - prev;
                }
                stack.push(fid);
                prev = tid;
            } else {
                res[stack.peek()] += tid - prev + 1;
                stack.pop();
                prev = tid + 1;
            }
        }

        return res;
    }


}
