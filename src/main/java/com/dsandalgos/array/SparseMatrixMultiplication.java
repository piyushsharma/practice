package com.dsandalgos.array;

public class SparseMatrixMultiplication {

    // brute force
    public int[][] multiplyBF(int[][] A, int[][] B) {

        if(A == null || A.length == 0) {
            return A;
        }

        // note: A[0].length = B.length always
        int[][] result = new int[A.length][B[0].length];

        for(int i = 0; i < result.length; ++i) {
            for(int j = 0; j < result[0].length; ++j) {

                for(int k = 0; k < A[0].length; ++k) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;

    }

    /**
     * For brute force solution, for each result[i][j], we do result[i][j] += A[i][k] * B[k][j] where k = [0, n].
     * Note: even when A[i][k] or B[k][j] is 0, the multiplication is still executed.
     *
     * We could be smarter : if A[i][k] == 0 or B[k][j] == 0, just skip the multiplication.
     * This can be achieved by moving for-loop for (k = 0; k < n; ++k) from inner-most loop to middle loop,
     * so that we can use if-statement to tell whether A[i][k] == 0 or B[k][j] == 0.
     */

    public int[][] multiply(int[][] A, int[][] B) {
        if(A == null || A.length == 0) {
            return A;
        }

        // note: A[0].length = B.length always
        int[][] result = new int[A.length][B[0].length];

        for(int i = 0; i < result.length; ++i) {
            for(int k = 0; k < A[0].length; ++k) {

                if(A[i][k] != 0) {

                    for (int j = 0; j < result[0].length; ++j) {
                        if (B[k][j] != 0) {
                            result[i][j] += A[i][k] * B[k][j];
                        }
                    }

                }
            }
        }

        return result;

    }
}
