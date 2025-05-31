package com.dsandalgos.hackerrank;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * In this problem, you have to add and multiply huge numbers!
 * These numbers are so big that you can't contain them in any ordinary data types like a long integer.
 *
 * Use the power of Java's BigIntegerOperations class and solve this problem.
 *
 * Input Format
 *
 * There will be two lines containing two numbers, a and b.
 *
 * Constraints
 *
 * a and b are non-negative integers and can have maximum 200 digits.
 *
 * Output Format
 *
 * Output two lines. The first line should contain a+b,
 * and the second line should contain a*b. Don't print any leading zeros.
 *
 * Sample Input
 *
 * 1234
 * 20
 *
 * Sample Output
 *
 * 1254
 * 24680
 *
 */


public class BigIntegerOperations {





	public static void main(String[] args) {
		/* Enter your code here. Read input from STDIN. Print output to STDOUT.
		Your class should be named Solution. */

		Scanner scanner = new Scanner(System.in);
		String a = scanner.nextLine().trim();
		String b = scanner.nextLine().trim();

		BigInteger b1 = new BigInteger(a);
		BigInteger b2 = new BigInteger(b);

		BigInteger mul = b1.multiply(b2);
		BigInteger add = b1.add(b2);

		System.out.println(add.toString());
		System.out.println(mul.toString());
	}
}
