package com.tosort;

/**
 * Created by Piyush Sharma
 */


public class BitStuff {

    public boolean isKthBitSet(int n, int k) {
        return (n & (1 << k)) == 0 ? false : true;
    }

    public int toggleKthBit(int n, int k) {
        return n ^ (1 << k);
    }

    public int setKthBit(int n, int k) {
        return (n | (1 << k));
    }

    public int clearKthBit(int n, int k) {
        return n & ~(1 << k);
    }

    // n must be positive
    public boolean isPowerOfTwo(int n) {
        if(n == 0) {
            return false;
        }
        // works because if n is a power of 2, only one bit will be set
        // and for n - 1 -> all other bits must have everything set except the bit set in 1
        // eg. 16 => 1 0 0 0 0
        //     15 => 0 1 1 1 1
        // so an and == 0 means n is a power of 2. This does not work for n = 0,
        // for that we could do   n & ~(n & (n - 1)) or handle is separately

        // other ways could be count that number of bits set = 1

        return (n & (n-1)) == 0 ? true : false;
    }

    private int clearAllBitsFromLSBToI(int n, int i) {
        int mask = 0;
        for(int j = 0; j <= i; ++j) {
            // create mask with i bits set in it
            mask = mask | (1 << j);

            // 1, 1 1, 1 1 1, 1 1 1 1 for i = 3 i.e. clear last 4 bits
        }

        // another way to make mask can be:
        // say n = 29 = 0 0 1 1 1 0 1
        // mask required =>  11...11000 => 1 << (i + 1) => 1 0 0 0 0, now subtract 1 from this
        // the subtracted number will be 0 1 1 1 1 => ((1 << (i + 1) - 1) => 0 1 1 1 1

        // ~ mask will have all bits set except LSB to i
        return n & ~mask;
    }


    private int countSetBits(int n) {
        int count = 0;

        while(n != 0) {
            if((n & 1) == 1) {
                ++count;
            }
            n = n >> 1;
        }

        // alternative way
        // Brian Kernighan’s Algorithm:
        /**
         * subtraction of 1 - toggles all bits from right to left
         * till the rightmost set bit - so if we do n & (n - 1)
         * we unset the right most bit - keep doing this until n = 0
         *
         *  n           = 15 => 0 1 1 1 1
         *  n - 1       = 14 => 0 1 1 1 0
         *  n & (n - 1) = 14 => 0 1 1 1 0 ; 1
         *  n - 1       = 13 => 0 1 1 0 1
         *  n & (n - 1) = 12 => 0 1 1 0 0 ; 2
         *  n - 1       = 11 => 0 1 0 1 1
         *  n & (n - 1) = 8  => 0 1 0 0 0 ; 3
         *  n - 1       = 7  => 0 0 1 1 1
         *  n & (n - 1) = 0  => 0 0 0 0 0 ; 4
         *
         *  Beauty - the loop runs only as many times as the number of *set* bits
         */
//        while(n != 0) {
//            n = n & (n - 1);
//            ++count;
//        }

        return count;
    }

    private int logBase2Of32BitInteger(int n) {
        int result = 0;
        while(n != 0) {
            n = n >> 1;
            ++result;
        }
        return result;
    }


    private int reverseBitsInInt(int n) {
        int totalBits = 32;

        int reverseNum = 0;
        int mask = 0;
        for(int i = 0; i < totalBits; ++i) {

            // check if i'th bit is set
            mask = n & (1 << i);

            // if mask = 1, set the 31 - i'th bit in reverseNum
            if (mask == 1) {
                reverseNum = reverseNum | (1 << (totalBits - 1 - i));
            }
        }

        return reverseNum;
    }


    public int reverseBits(int n) {

        int intBits = 31;
        int reverseNum = n;

        n = n >> 1;

        while(n > 0) {
            reverseNum = reverseNum << 1;

            reverseNum = reverseNum | (n & 1);

            n = n >> 1;
            --intBits;
        }

        reverseNum = reverseNum << intBits;
        return reverseNum;
    }


    /**
     * Find the largest power of 2 (most significant bit in binary form), which is less than or
     * equal to the given number N.
     *
     * Idea: Change all the bits which are at the right side of the most significant digit, to 1.
     *
     * Property: As we know that when all the bits of a number N are 1, then N must be equal to the 2i -1,
     * where i is the number of bits in N.
     *
     * Example:
     * Let’s say binary form of a N is {1111}2 which is equal to 15.
     * 15 = 2^4-1, where 4 is the number of bits in N.
     *
     * This property can be used to find the largest power of 2 less than or equal to N. How?
     * If we somehow, change all the bits which are at right side of the most significant bit of N to 1,
     * then the number will become x + (x-1) = 2 * x -1 , where x is the required answer.
     *
     * Example:
     * Let’s say N = 21 = {10101}2, here most significant bit is the 4th one. (counting from 0th digit)
     * and so the answer should be 16.
     *
     * So lets change all the right side bits of the most significant bit to 1. Now the number changes to
     * {11111} = 31 = 2 * 16 -1 = Y (let’s say).
     *
     * Now the required answer is (Y+1)>>1 or (Y+1)/2.
     *
     * Now the question arises here is how can we change all right side bits of most significant bit to 1?
     *
     * Let’s take the N as 16 bit integer and binary form of N is {1000000000000000}.
     * Here we have to change all the right side bits to 1.
     *
     * Initially we will copy that most significant bit to its adjacent right side by:
     *
     * N = N | (N>>1).
     *
     * then we keep doing the same operation to set more bits to 1
     *
     * N = N | (N>>2)
     * N = N | (N>>4)
     * N = N | (N>>8)
     * -- result would be {1111111111111111}
     *
     * now we just need to do (N + 1) >> 1 for answer
     */
    private int largestPower(int N) {
        // works on 16 bit integers -- can be extended to 32 or 64  bits solutions

        //changing all right side bits to 1.
        N = N| (N>>1);
        N = N| (N>>2);
        N = N| (N>>4);
        N = N| (N>>8);

        //as now the number is 2 * x-1, where x is required answer, so adding 1 and dividing it by
        return (N+1)>>1;
    }


    /**
     *
     * rightmost 1
     *
     * Approach 1:
     *
     * x ^ ( x & (x-1)) : Returns the rightmost 1 in binary representation of x.
     *
     * (x & (x - 1)) will have all the bits equal to the x except for the rightmost 1 in x.
     * So if we do bitwise XOR of x and (x & (x-1)), it will simply return the rightmost 1.
     *
     * Let’s see an example.
     * x = 10 = (1010)2
     * x & (x-1) = (1010)2 & (1001)2 = (1000)2
     * =>
     * x ^ (x & (x-1)) = (1010)2 ^ (1000)2 = (0010)2
     *
     * Approach 2:
     * x & (-x) : Returns the rightmost 1 in binary representation of x
     *
     * (-x) is the two’s complement of x. (-x) will be equal to one’s complement of x plus 1.
     * Therefore (-x) will have all the bits flipped that are on the left of the rightmost 1 in x.
     * So x & (-x) will return rightmost 1.
     *
     * x = 10 = (1010)2
     * (-x) = -10 = (0110)2
     * x & (-x) = (1010)2 & (0110)2 = (0010)2
     */
    private int rightMostSetBit(int x) {
        // return x ^ (x & (x - 1))

        return x & -x;
    }


    public static void main(String[] args) {
        System.out.println(new BitStuff().clearAllBitsFromLSBToI(29, 3));

        for(int i = 0; i < 20; ++i) {
            System.out.println("i = " + i + "  bits = " + new BitStuff().countSetBits(i));
        }
    }
}
