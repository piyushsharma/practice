package com.tosort;

/* Given a range [m, n] where 0 <= m <= n <= 2147483647,
return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.
 */


public class BitwiseAndOfNumbersRange {

    public int rangeBitwiseAnd(int m, int n) {
        if(m == 0 || n == 0) {
            return 0;
        }

        /* Note: If the number of bits required for m != n, result will always be 0 */
        int result = 0;
        for(int i = 31; i >=0 ; --i) {
            int mBit = 1 & (m >> i);
            int nBit = 1 & (n >> i);

            /* continue if you see both 0 bits */
            if(mBit == 0 && nBit == 0)
                continue;

            if((mBit & nBit) == 1) {
                result = result | (1 << i);
            } else {
                /* After the first non 1 bit in both m and n, some number in the range is going to cause everything
                 else to be 0 */
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new BitwiseAndOfNumbersRange().rangeBitwiseAnd(2147483646, 2147483647));
    }


}
