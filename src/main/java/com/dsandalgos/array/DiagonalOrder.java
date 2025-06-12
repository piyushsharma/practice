package com.dsandalgos.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class DiagonalOrder {


    /**
     * 
     * 
     *   2  1  2  4
     *   3  3  3  6
     *   4  1  4  7
     *   4  2  5  8
     *  
     *  -- start from top col and move to bottom left to travel the diagonal
     *  -- all diagonal starts from row 0 or col = n - 1
     * 
     */

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        List<Integer> result = new ArrayList<>();
        
    
        for(int d = 0; d < m + n - 1; ++d) {

            List<Integer> diagonal = new ArrayList<>();
       
            // row start = 0;  when we cross the diagonals starting from row 0
            // we need to go to row (d - total columns) + 1
            int row = (d < n) ? 0 : d - n + 1;

            // move column 0 to n - 1; and then stay on last column as the starting of the diagonal 
            int col = (d < n) ? d : n - 1;

            // travel bottom left
            while(row >= 0 && row < m && col >= 0 && col < n) {
                diagonal.add(mat[row][col]);
                ++row;
                --col;
            }

            if(d % 2 == 0) Collections.reverse(diagonal);

            result.addAll(diagonal);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] findDiagonalOrderV2(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] result = new int[m*n];
        
        // when row + col == even : go up
        // when row + col == odd : go down
        int row = 0; 
        int col = 0;
        
        for(int i = 0; i < result.length; ++i) {

            result[i] = mat[row][col];

            if((row + col) % 2 == 0) {

                if(col == n-1) {
                    row++; 
                } else if (row == 0) {
                    col++;
                } else {
                    row--;
                    col++;
                }

            } else {

                if(row == m-1) {
                    col++; 
                } else if (col == 0) {
                    row++;
                } else {
                    row++;
                    col--;
                }
            }
        }
        return result;
    }



}