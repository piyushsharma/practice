package com.dsandalgos.array;


import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

	private static void longestConsecutiveRange(int[] array) {
		Set<Integer> set = new HashSet<>();
		for (int x : array) {
			set.add(x);
		}

		int seqLeft = 0, seqRight = 0;
		int result = 0;
		for (int i = 0; i < array.length; i++) {
			// if it does not contain the element,
			// it must have been accounted for at some other place
			if (!set.contains(array[i])) {
				continue;

			} else {
				int curLength = 1;

				int curSeqLeft = array[i];
				int left = array[i] - 1;
				while (set.contains(left)) {
					++curLength;
					curSeqLeft = left;
					set.remove(left);
					--left;
				}

				int curSeqRight = array[i];
				int right = array[i] + 1;
				while (set.contains(right)) {
					curLength++;
					curSeqRight = right;
					set.remove(right);
					++right;
				}

				if (curLength > result) {
					result = curLength;
					seqLeft = curSeqLeft;
					seqRight = curSeqRight;
				}
			}
		}
		System.out.println("Range: " + seqLeft + "-" + seqRight);
	}

	public static void main(String[] args) {
		int[] buffer = {2, 6, 3, 9, 4, 8, 13, 7};
		longestConsecutiveRange(buffer);
	}
}
