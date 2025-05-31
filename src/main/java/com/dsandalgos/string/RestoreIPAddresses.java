package com.dsandalgos.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */

public class RestoreIPAddresses {

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        if(null == s || s.length() < 4 || s.length() > 12) {
            return result;
        }

        List<String> current = new ArrayList<String>();
        dfs(result, current, s, 0, s.length());
        return result;
    }


    public void dfs(List<String> result, List<String> current, String input, int index, int end) {

        // == 4 because index != end, so we have not consumed the entire input string
        if(current.size() >= 4 && index != end) {
            return;
        }

        // since index is starting from 0
        int remLength = end - index + 1;
        //make sure current's size + remaining string's length >=4
        if(current.size() + remLength < 4) {
            return;
        }

        if(current.size() == 4 && index == end) {
            StringBuilder sb = new StringBuilder();
            for(String s : current) {
                sb.append(s + ".");
            }
            result.add(sb.substring(0, sb.length() - 1));
            return;
        }

        // index = 0, so 0 to 1, 0 to 2, and 0 to 3 => is what goes in the substring
        for(int i = 1; i <= 3; ++i) {
            if (index + i > end) {
                break;
            }
            //handle case like 001. i.e., if length > 1 and first char is 0, ignore the case.
            // because we cannot consume the entire lenght properly, 255.001.255.255 is invalid
            if (i > 1 && input.charAt(index) == '0') {
                break;
            }

            String sub = input.substring(index, index + i);
            Integer x = Integer.valueOf(sub);
            if (x > 255) {
                break;
            }
            current.add(x.toString());
            dfs(result, current, input, index + i, end);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        RestoreIPAddresses r = new RestoreIPAddresses();

        List<String> res = r.restoreIpAddresses("255511135");
        for (String x : res) {
            System.out.println(x);
        }
    }
}
