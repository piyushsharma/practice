package com.dsandalgos.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Add the mathematical operators + or - before any of the digits in the decimal numeric string 123456789
 * such that the resulting mathematical expression adds up to 100. Return all possible solutions.
 *
 * There are 12 solutions:
 *
 * 1+2+3-4+5+6+78+9
 * 1+2+34-5+67-8+9
 * 1+23-4+5+6+78-9
 * 1+23-4+56+7+8+9
 * 12+3+4+5-6-7+89
 * 12+3-4+5+67+8+9
 * 12-3-4+5-6+7+89
 * 123+4-5+67-89
 * 123+45-67+8-9
 * 123-4-5-6-7+8-9
 * 123-45-67+89
 * -1+2-3+4+5+6+78+9
 */

public class StringDFSSum {


    public List<String> solution(String input) {

        List<String> result = new ArrayList<>();
        dfs(input, result, 0,"");
        return result;
    }

    private void dfs(String input, List<String> result, int answer, String path) {

        if(input.length() == 0 && answer == 100) {
            result.add(path);
        }

        for(int i = 0; i < input.length(); ++i) {

            dfs(input.substring(i+1), result, answer + Integer.parseInt(input.substring(0, i+1)), path + "+" + input.substring(0, i+1));

            dfs(input.substring(i+1), result, answer - Integer.parseInt(input.substring(0, i+1)), path + "-" + input.substring(0, i+1));

        }

    }

    public static void main(String[] args) {
        List<String> res = new StringDFSSum().solution("123456789");

        for(String r : res) {
            System.out.println(r);
        }

    }


}
