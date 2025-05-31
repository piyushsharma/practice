package com.dsandalgos.tophundred;

/**
 * @author Piyush Sharma
 */

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

 Given two integers x and y, calculate the Hamming distance.

 Note:
 0 ≤ x, y < 231.

 Example:

 Input: x = 1, y = 4

 Output: 2

 Explanation:
 1   (0 0 0 1)
 4   (0 1 0 0)
 ↑   ↑
 The above arrows point to positions where the corresponding bits are different.

 */

public class HammingDistance {

	public int hammingDistance(int x, int y) {
		int result = 0;
		while(x != 0 || y != 0) {

			int bx = 0;
			if(x != 0) {
				bx = x % 2;
				// right shift unsigned
				x = x >>> 1;
			}

			int by = 0;
			if(y != 0) {
				by = y % 2;
				// right shift unsigned
				y = y >>> 1;
			}

			if(bx != by) ++result;
		}
		return result;
	}

	public int hammingDistanceXor(int x, int y) {
		return Integer.bitCount(x ^ y);
	}

	public static void main(String[] args) {
		System.out.println(new HammingDistance().hammingDistance(2, 4));
		System.out.println(new HammingDistance().hammingDistance(1, 4));

	}
}
