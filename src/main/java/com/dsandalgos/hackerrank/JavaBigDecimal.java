package com.dsandalgos.hackerrank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Java's BigDecimal class can handle arbitrary-precision signed decimal numbers. Let's test your knowledge of them!
 *
 * Given an array, , of  real number strings, sort them in descending order — but wait, there's more!
 * Each number must be printed in the exact same format as it was read from stdin, meaning that .1 is printed as .1,
 * and .01 is printed as .01. If two numbers represent numerically equivalent values (e.g., .1 == .01),
 * then they must be listed in the same order as they were received as input).
 *
 * Complete the code in the unlocked section of the editor below. You must rearrange array s's elements
 * according to the instructions above.
 *
 * Input Format
 *
 * The first line consists of a single integer, n, denoting the number of integer strings.
 * Each line i of the n subsequent lines contains a real number denoting the value of s[i].
 *
 * Constraints
 * 1 <= n <= 200
 * Each s[i] has at most 300 digits
 *
 * Output Format
 *
 * Locked stub code in the editor will print the contents of array s to stdout.
 * You are only responsible for reordering the array's elements.
 *
 * Sample Input
 * 9
 * -100
 * 50
 * 0
 * 56.6
 * 90
 * 0.12
 * .12
 * 02.34
 * 000.000
 *
 *
 * Sample Output
 * 90
 * 56.6
 * 50
 * 02.34
 * 0.12
 * .12
 * 0
 * 000.000
 * -100
 */

public class JavaBigDecimal {


	public static void main(String []args){
		//Input
//		Scanner sc = new Scanner(System.in);
//		int n = sc.nextInt();
//		String[] s = new String[n+2];
//		for(int i = 0; i < n; ++i){
//			s[i] = sc.next();
//		}
//		sc.close();

		String[] s = new String[]{"-100", "50", "0", "56.6", "90", ".01", ".1", "000.000", "02.34", "0.12"};
		int n = s.length;

		// write code here
//		Map<BigDecimal, List<String>> input = new HashMap<>();
//		for(int i = 0; i < n; ++i) {
//			String bd = s[i];
//			input.computeIfAbsent(new BigDecimal(bd), (ignored) -> new ArrayList<>()).add(bd);
//		}
//
//		List<BigDecimal> l = new ArrayList<>(input.keySet());
//		Collections.sort(l, Collections.reverseOrder());
//
//		int newI = 0;
//		int j = 0;
//		while(newI < n) {
//
//			List<String> vals = input.get(l.get(j));
//			for(String v : vals) {
//				s[newI] = v;
//				++newI;
//			}
//			++j;
//		}

		Arrays.sort(s, 0, n, Collections.reverseOrder(
			(s1, s2) -> {
				//convert to big decimal inside comparator
				//so permanent string values are never changed
				//aka you only use the BigDecimal values to
				//compare the strings!
				BigDecimal a = new BigDecimal(s1);
				BigDecimal b = new BigDecimal(s2);
				return a.compareTo(b);
			}));

		//Output
		for(int i = 0; i < n; i++) {
			System.out.println(s[i]);
		}
	}
}
