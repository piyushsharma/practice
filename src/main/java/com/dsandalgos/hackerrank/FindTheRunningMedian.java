package com.dsandalgos.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * The median of a set of integers is the midpoint value of the data set for which an equal
 * number of integers are less than and greater than the value.
 *
 * To find the median, you must first sort your set of integers in non-decreasing order, then:
 *
 * If your set contains an odd number of elements, the median is the middle element of the sorted sample. In the sorted set ,  is the median.
 * If your set contains an even number of elements, the median is the average of the two middle elements of the sorted sample. In the sorted set ,  is the median.
 * Given an input stream of  integers, you must perform the following task for each  integer:
 *
 * Add the  integer to a running list of integers.
 * Find the median of the updated list (i.e., for the first element through the  element).
 * Print the list's updated median on a new line. The printed value must be a double-precision number scaled to  decimal place (i.e.,  format).
 *
 */

public class FindTheRunningMedian {

	/*
	 * Complete the runningMedian function below.
	 */
	static double[] runningMedian(int[] a) {

		PriorityQueue<Integer> lowerThanCurMedian = new PriorityQueue<>((o1, o2) -> o2 - o1);
		PriorityQueue<Integer> moreThanCurMedian = new PriorityQueue<>((o1, o2) -> o1 - o2);
		double[] result = new double[a.length];

		double currentMedian = 0;
		int i = 0;
		for(int item : a) {
			if(item > currentMedian) {
				moreThanCurMedian.add(item);
			} else {
				lowerThanCurMedian.add(item);
			}

			// balance the heap size
			if(lowerThanCurMedian.size() - moreThanCurMedian.size() > 1) {
				moreThanCurMedian.add(lowerThanCurMedian.remove());
			} else if(moreThanCurMedian.size() - lowerThanCurMedian.size() > 1) {
				lowerThanCurMedian.add(moreThanCurMedian.remove());
			}

			if(lowerThanCurMedian.size() == moreThanCurMedian.size()) {
				currentMedian = (double)(lowerThanCurMedian.peek() + moreThanCurMedian.peek())/2;
			} else if (lowerThanCurMedian.size() > moreThanCurMedian.size()) {
				currentMedian = (double) lowerThanCurMedian.peek();
			} else {
				currentMedian = (double) moreThanCurMedian.peek();
			}
			result[i++] = currentMedian;
		}
		return result;
	}




	static double[] runningMedianSlow(int[] a) {
		/*
		 * Write your code here.
		 */
		List<Integer> input = new ArrayList<>();
		double[] result = new double[a.length];
		for(int item : a) {
			input.add(item);
			Collections.sort(input);

			double median = 0;
			if(input.size() % 2 == 0) {
				int sumOfMedians = input.get(input.size()/2)
					+ input.get((input.size()/2) - 1);
				median = (double) sumOfMedians/2;

			} else {
				median = (double) input.get(input.size()/2);
			}
			result[input.size() - 1] = median;
		}
		return result;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
//		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//		int aCount = Integer.parseInt(scanner.nextLine().trim());
//
//		int[] a = new int[aCount];
//
//		for (int aItr = 0; aItr < aCount; aItr++) {
//			int aItem = Integer.parseInt(scanner.nextLine().trim());
//			a[aItr] = aItem;
//		}
//
//		double[] result = runningMedian(a);
//
//		for (int resultItr = 0; resultItr < result.length; resultItr++) {
//			bufferedWriter.write(String.valueOf(result[resultItr]));
//
//			if (resultItr != result.length - 1) {
//				bufferedWriter.write("\n");
//			}
//		}
//
//		bufferedWriter.newLine();
//
//		bufferedWriter.close();

		double[] result = runningMedian(new int[]{6,9,12,3});
		System.out.println("done");

	}


}
