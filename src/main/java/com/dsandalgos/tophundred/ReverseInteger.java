package com.dsandalgos.tophundred;

public class ReverseInteger {

	// If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
	// the reversed integer might overflow?
	// Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows.
	// For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.


	// efficient and clean
	public int reverseInt(int x) {
		int result = 0;
		while(x != 0) {
			// to handle overflow and underflow =>
			// Note that INTMAX = 2147483647 and INTMIN = -2147483648
			// if res is already 214748364 or -214748364, the next  res * 10 + x % 10 will
			// overflow or underflow if (x % 10) is > 7 || < -8, but since res is reverse of
			// an integer, we will not get an input where x % 10 at this point could be 7 or 8

			// thus, to handle overflow and underflow we can simply compare res to 214748364 and -214748364
			// note if res == 214748364, we do not want to return here, if it is a valid number, x will become
			// zero and we will have the right res anyways
			if(Math.abs(result) > 214748364) return 0;

			result = result * 10 + x % 10;
			x = x / 10;
		}
		return result;
	}

	// more detailed
	public int reverseIntV2(int x) {

		boolean negative = false;
		double reverse = 0;
		double num = x;

		if (x < 0) {
			negative = true;
			num = -x;
		}

	    while(num != 0) {
			reverse = reverse*10 + num %10;
			// casting as int important else it will use decimal
	        num = (int)num / 10;
		}

		if(negative) {
			reverse = -reverse;
		}
		if (reverse > Integer.MAX_VALUE || reverse < Integer.MIN_VALUE) {
			return 0;
		}
		int result = (int) reverse;
	    return result;
	}
	
	public static void main(String[] args) {
		ReverseInteger r = new ReverseInteger();
		System.out.println(r.reverseInt(0));
		System.out.println(r.reverseInt(1));
		System.out.println(r.reverseInt(-878));
		// overflow check
		System.out.println(r.reverseInt(1534236469));
	}
}
