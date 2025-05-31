package com.dsandalgos.tophundred;

import com.dsandalgos.util.DataStructureUtility;

/**
 * Created by Piyush Sharma
 */

/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
*/


public class RotateImage {

    public void rotate(int[][] matrix) {

        int n = matrix.length;
        int layers = n/2;

        for(int k = 0; k < layers; ++k) {

            int start = k;
            int end = (n - 1) - k;

            // rotate the layer
            // note we do +k at couple of places to offset the inner layers index
            // print the matrix with and without the +k offset and you will see why we need it
            for(int i = start; i < end; ++i) {

                int topLeft = matrix[start][i];

                // topLeft = bottomLeft
                matrix[start][i] = matrix[end-i+k][start];

                // bottomLeft = bottomRight
                matrix[end-i+k][start] = matrix[end][end-i+k];

                // bottomRight = topRight
                matrix[end][end-i+k] = matrix[i][end];

                // topRight = topLeft
                matrix[i][end] = topLeft;
            }
        }
    }

    public static void main(String[] args) {
        RotateImage r = new RotateImage();

        int[][] m = new int[][]{ {1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25} };

        DataStructureUtility.printMatrix(m);
        r.rotate(m);
        System.out.println("========================================");
        DataStructureUtility.printMatrix(m);
    }



}
