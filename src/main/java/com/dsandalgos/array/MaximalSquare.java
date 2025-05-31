package com.dsandalgos.array;

/**
 * Created by Piyush Sharma
 */

/*

Given a matrix containing either 0 or 1 in each cell,

1. find the *square* with the longest side containing all 1s in its boundary.
2. find the *square* with the longest side containing all 1s.

Cells inside the square may contain either 0 or 1.
For example, given matrix

[
  [0, 1, 0, 1, 1]
  [0, 1, 1, 1, 0]
  [0, 1, 0, 1, 1]
  [1, 1, 1, 1, 1]
  [1, 1, 1, 1, 1]
  [1, 1, 1, 1, 1]
]

The square with the maximum size containing all 1s in its boundary has top-left corner at (1,1)
and bottom-right corner at (3, 3).

Note: You only need to return the length of each side of the square,
In the above example, the length of each side of the square is 3.

*/


public class MaximalSquare {

    /*
        1) Construct a sum matrix S[R][C] for the given M[R][C].
            a)	Copy first row and first columns as it is from M[][] to S[][]
            b)	For other entries, use following expressions to construct S[][]
                If M[i][j] is 1 then
                    S[i][j] = min(S[i][j-1], S[i-1][j], S[i-1][j-1]) + 1
                Else If M[i][j] is 0
                    S[i][j] = 0
        2) Find the maximum entry in S[R][C]
        3) Using the value and coordinates of maximum entry in S[i], print sub-matrix of M[][]
     */

    // O(m * n)
    public void maximalSquareAllOnes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] numberOfOnes = new int[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {

                if(i == 0 || j == 0) {
                    numberOfOnes[i][j] = matrix[i][j];

                } else if (matrix[i][j] == 1) {
                    numberOfOnes[i][j] = 1 + Math.min(numberOfOnes[i-1][j-1],
                                             Math.min(numberOfOnes[i-1][j], numberOfOnes[i][j-1]));
                } else {
                    numberOfOnes[i][j] = 0;
                }
            }
        }

        int maxOnes = numberOfOnes[0][0];
        int maxI = 0;
        int maxJ = 0;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {

                if(maxOnes < numberOfOnes[i][j]) {
                    maxOnes = numberOfOnes[i][j];
                    maxI = i;
                    maxJ = j;
                }
            }
        }

        printSubMatrix(matrix, maxI - maxOnes + 1, maxI, maxJ - maxOnes + 1, maxJ, "Maximal Square with all 1's");
    }

    private void printSubMatrix(int[][] matrix, int iStart, int iEnd, int jStart, int jEnd, String id) {
        if(id == null)
            System.out.println("==================================================");
        else
            System.out.println("============" + id + "============");
        System.out.printf("rowStart => %d, rowEnd => %d, colStart = %d, colEnd = %d\n", iStart, iEnd, jStart, jEnd);
        for (int i = iStart; i <= iEnd; ++i) {
            for (int j = jStart; j <= jEnd; ++j) {
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.printf("\n");
        }
        System.out.println("====================================================");
    }



    /*
        The idea is to create two auxiliary arrays hor[N][N] and ver[N][N].
        The value stored in hor[i][j] is the number of horizontal continuous 1 in matrix
        Similarly, the value stored in ver[i][j] is the number of continuous 1 in matrix
        Following is an example.

            mat[6][6] =  1  0  1  1  1  1
                         1  0  1  1  0  1
                         1  1  1  0  0  1
                         0  1  1  1  1  1
                         1  1  1  0  1  0
                         0  0  1  0  0  0

            hor[6][6] = 1  0  1  2  3  4
                        1  0  1  2  0  1
                        1  2  3  0  0  1
                        0  1  2  3  4  5
                        1  2  3  0  1  0
                        0  0  1  0  0  0

            ver[6][6] = 1  0  1  1  1  1
                        2  0  2  2  0  2
                        3  1  3  0  0  3
                        0  2  4  1  1  4
                        1  3  5  0  2  0
                        0  0  6  0  0  0

           Once we have filled values in hor[][] and ver[][], we start from the bottommost-rightmost corner of
           matrix and move toward the leftmost-topmost in row by row manner.

           For every visited entry mat[i][j], we compare the values of hor[i][j] and ver[i][j],
           and pick the smaller of two as we need a square.
           Let the smaller of two be ‘small’.
           After picking smaller of two, we check if both ver[][] and hor[][] for left and up edges respectively.
           If they have entries for the same, then we found a subsquare. Otherwise we try for small - 1
        */
    // O(n^3)
    public int maximalSquareBoundaryOnes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] hor = new int[m][n];
        int[][] ver = new int[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {

                if(matrix[i][j] == 0) {
                    hor[i][j] = ver[i][j] = 0;
                } else {

                    hor[i][j] = j == 0 ? 1 : hor[i][j-1] + 1;
                    ver[i][j] = i == 0 ? 1 : ver[i-1][j] + 1;

                }
            }
        }

        int max = 0;

        // start from bottom most
        for (int i = m - 1; i > 0; --i) {
            for (int j = n-1; j > 0; --j) {

                // Find smaller of values in hor[][] and ver[][]
                // A Square can only be made by taking smaller value
                int small = Math.min(hor[i][j], ver[i][j]);

                // At this point, we are sure that there is a right vertical line
                // and bottom horizontal line of length at least "small"

                // We found a bigger square if following conditions are met:
                // 1)If side of square is greater than max
                // 2)There is a left vertical line of length >= 'small'
                // 3)There is a top horizontal line of length >= 'small'

                while(small > max) {

                    if(ver[i][j - small + 1] >= small &&
                            hor[i - small + 1][j] >= small) {
                        max = small;
                    }
                    --small;
                }
            }
        }

        return max;
    }




    // this is brute force approach, go over all sub matrix's and check if the boundary is all ones
    // O(n^4)
    public void maximalSquareBoundaryOnesBruteForce(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int rowStart, rowEnd, colStart, colEnd;
        rowStart = rowEnd = colStart = colEnd = 0;
        int maxAarea = 0;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {

                for(int iEnd = i; iEnd < m; ++iEnd) {
                    for (int jEnd = j; jEnd < n; ++jEnd) {

                        // check if it is a square
                        if((jEnd - j + 1) == (iEnd - i + 1)) {
                            // check if all boundaries are one
                            if (allOneBoundaryMatrix(matrix, i, iEnd, j, jEnd)) {
                                int area = (iEnd - i + 1) * (jEnd - j + 1);
                                if (area > maxAarea) {
                                    rowStart = i;
                                    rowEnd = iEnd;
                                    colStart = j;
                                    colEnd = jEnd;
                                    maxAarea = area;
                                }
                            }
                        }
                    }
                }
            }
        }
        printSubMatrix(matrix, rowStart, rowEnd, colStart, colEnd, "Maximal Square containing " +
                                                                    "all 1's in it's boundary");
    }

    private boolean allOneBoundaryMatrix(int[][] matrix, int iStart, int iEnd, int jStart, int jEnd) {
        for (int i = iStart; i <= iEnd; ++i) {
            if(matrix[i][jStart] == 0 || matrix[i][jEnd] == 0) return false;
        }

        for (int j = jStart; j <= jEnd; ++j) {
            if(matrix[iStart][j] == 0 || matrix[iEnd][j] == 0) return false;
        }
        return true;
    }

    // iterate over all possible sub matrix
    public void printAllSubMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {

                for(int iEnd = i; iEnd < m; ++iEnd) {
                    for (int jEnd = j; jEnd < n; ++jEnd) {

                        int area = allOneMatrix(matrix, i, iEnd, j, jEnd);
                        if (area > maxArea) {
                            maxArea = area;
                        }

                    }
                }

            }
        }
    }

    private int allOneMatrix(int[][] matrix, int iStart, int iEnd, int jStart, int jEnd) {
        int count = 0;
        for (int i = iStart; i <= iEnd; ++i) {
            for (int j = jStart; j <= jEnd; ++j) {
                if(matrix[i][j] == 0) return -1;
                ++count;
            }
        }
        return count;
    }


    public static void main(String[] args) {

        MaximalSquare m = new MaximalSquare();

        int M[][] = new int[][]{
                {0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 1, 0, 1, 1},
                {0, 1, 1, 1, 1}};

        m.maximalSquareAllOnes(M);
        m.maximalSquareBoundaryOnes(M);

        int matrix[][] = new int[][] {{0, 1, 0, 1, 1},
                                        {0, 1, 1, 1, 0},
                                        {0, 1, 0, 1, 1},
                                        {1, 1, 1, 1, 1},
                                        {1, 1, 1, 1, 1}};

        System.out.println(m.maximalSquareBoundaryOnes(matrix));

        m.maximalSquareBoundaryOnesBruteForce(matrix);
    }




}
