package com.dsandalgos.array;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

Could you devise a constant space solution?

*/

public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for(int i = 0; i < m; ++i) {
            if(row[i]) {
                for (int j = 0; j < n; ++j) {
                    matrix[i][j] = 0;
                }
            }
        }

        for(int j = 0; j < n; ++j) {
            if(col[j]) {
                for (int i = 0; i < m; ++i) {
                    matrix[i][j] = 0;
                }
            }
        }
    }


    public void setZeroesConstantSpace(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        boolean firstRowHasZeroes = false;
        for(int i = 0; i < m; ++i) {
            if(matrix[i][0] == 0) {
                firstRowHasZeroes = true;
                break;
            }
        }

        boolean firstColHasZeroes = false;
        for(int j = 0; j < n; ++j) {
            if(matrix[0][j] == 0) {
                firstColHasZeroes = true;
                break;
            }
        }

        // use zeroth row and column to store the flag to clear the row
        // flag must be zero, if you use anything else, it will fail when the values of those entries were actually
        // the value that you assumed as your flag. If the actual values at matrix[i][0] and matrix[0][j] is zero,
        // we are ok, and if they are not zero,
        // we can use them as flag since matrix[i][j] = 0 will make them zero anyways
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for(int i = 1; i < m; ++i) {
            for(int j = 1; j < n; ++j) {
                // notice the OR condition here, this is so that the columns also get cleared
                // note that it will only happen when both [i][0] and [0][j] are zeroes!
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if(firstRowHasZeroes) {
            for(int i = 0; i < m; ++i) {
                matrix[i][0] = 0;
            }
        }

        if(firstColHasZeroes) {
            for(int j = 0; j < n; ++j) {
                matrix[0][j] = 0;
            }
        }
    }


    public static void main(String[] args) {
        SetMatrixZeroes s = new SetMatrixZeroes();

//        int[][] test = {{1,1,1}, {1,1,1}};
        int[][] test = {{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
        s.setZeroesConstantSpace(test);
        System.out.println("Done");

    }

}
