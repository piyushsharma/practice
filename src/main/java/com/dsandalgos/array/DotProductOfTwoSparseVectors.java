package com.dsandalgos.array;

public class DotProductOfTwoSparseVectors {

    class SparseVector {

        int[] v1;
        SparseVector(int[] nums) {
            v1 = nums;
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            int[] v1 = this.v1;
            int[] v2 = vec.v1;

            int p = 0;
            for(int i = 0; i < v1.length; ++i) {
                p += v1[i] * v2[i];
            }


            return p;

        }
    }

}
