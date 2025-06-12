package com.tosort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given an index k, return the kth row of the Pascal's triangle.
For example, given k = 3,
Return [1,3,3,1].
Note:
Could you optimize your algorithm to use only O(k) extra space?
 */


public class PascalsTriangleTwo {

/* Notes:
    C(n,k+1) = C(n,k) * (n-k) / (k+1)
    So you can start with C(n,0) = 1 and then calculate the rest of the line using this identity,
    each time multiplying the previous element by (n-k) / (k+1).

    pascal's kth row is => kC0, kC1, kC2, ..., kCk
*/

    // correct solution but leetcode not accepting
    public List<Integer> getRow(int rowIndex) {

        List<Integer> result = new ArrayList<Integer>();
        result.add(0, 1);

        for(int i = 0; i < rowIndex; ++i) {
            float prev = (float)result.get(i);
            /* Converting to float because the multiplication causes integer overflow and
               numbers become negative.
            */
            float next = prev * (rowIndex - i) / (i + 1);
            int nextI = (int) next;
            result.add(nextI);
        }

        return result;
    }

    /*
    [
             [1],
            [1,1],
           [1,2,1],
          [1,3,3,1],
         [1,4,6,4,1]
    ]
     */

    // alternate solution
    public List<Integer> getRowAlternative(int numRows) {
        List<Integer> result = new ArrayList<Integer>();
        result.add(1);

        if(numRows <= 0)
            return result;

        // i <= numrows because for numRows = 3, we return 1,3,3,1 (4th row if we include [1])
        for(int i = 1; i <= numRows; ++i) {

            for(int j = i-1; j > 0; --j) {
                result.set(j, result.get(j-1) + result.get(j));
            }
            // last element
            result.add(1);
        }

        return result;
    }

    public static void main(String[] args) {
        PascalsTriangleTwo p = new PascalsTriangleTwo();
        List<Integer> r = p.getRow(30);
        for(int i : r) {
            System.out.println(i);
        }

        System.out.println("=============");
        r = p.getRowAlternative(30);
        for(int i : r) {
            System.out.println(i);
        }
    }

}
