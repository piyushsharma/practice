package com.dsandalgos.tophundred;

public class FindTheDuplicateNumber {


	public int findDuplicate(int[] nums) {
		int tortoise = nums[0];
		int hare = nums[0];
		do {
			tortoise = nums[tortoise];
			hare = nums[nums[hare]];
		} while (tortoise != hare);

		// Find the "entrance" to the cycle.
		int ptr1 = nums[0];
		int ptr2 = tortoise;
		while (ptr1 != ptr2) {
			ptr1 = nums[ptr1];
			ptr2 = nums[ptr2];
		}

		return ptr1;
	}


	public static void main(String[] args) {

		int[] test = new int[]{5,4,3,2,1,1};

		System.out.println(new FindTheDuplicateNumber().findDuplicate(test));

	}
}
