package com.tosort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given numRows, generate the first numRows of Pascal's triangle.
For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

*/

public class PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if(numRows <= 0)
            return result;

        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        result.add(row);

        generateRecursively(result, row, numRows-1);
        return result;
    }

    private void generateRecursively(List<List<Integer>> result, List<Integer> row, int numRows) {
        if(numRows == 0)
            return;

        List<Integer> cur = new ArrayList<Integer>();
        cur.add(row.get(0));
        for(int i = 1; i < row.size(); ++i) {
            cur.add(row.get(i-1) + row.get(i));
        }
        cur.add(row.get(row.size()-1));
        result.add(cur);

        generateRecursively(result, cur, numRows - 1);
    }


    public List<List<Integer>> generateIterative(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if(numRows <= 0)
            return result;

        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        result.add(row);

        for(int i = 1; i < numRows; ++i) {
            List<Integer> cur = new ArrayList<Integer>();
            // first element
            cur.add(1);
            for(int j = 1; j < row.size(); ++j) {
                cur.add(row.get(j-1) + row.get(j));
            }
            // last element
            cur.add(1);
            result.add(cur);
            row = cur;
        }

        return result;
    }

}
