package com.dsandalgos.misc;

import java.util.HashMap;
import java.util.Map;

/**
 *

 The Westfield Mall management is trying to figure out what the busiest moment at the mall was last year.
 You’re given data extracted from the mall’s door detectors.
 Each data point is represented as an integer array whose size is 3.
 The values at indices 0, 1 and 2 are the timestamp, the count of visitors,
 and whether the visitors entered or exited the mall (0 for exit and 1 for entrance), respectively.
 Here’s an example of a data point: [ 1440084737, 4, 0 ].

 Note that time is given in a Unix format called Epoch, which is a non-negative integer holding the number of seconds
 that have elapsed since 00:00:00 UTC, Thursday, 1 January 1970.

 Given an array, data, of data points, write a function findBusiestPeriod that returns the time at which
 the mall reached its busiest moment last year. The return value is the timestamp, e.g. 1480640292.
 Note that if there is more than one period with the same visitor peak, return the earliest one.

 Assume that the array data is sorted in an ascending order by the timestamp.
 Explain your solution and analyze its time and space complexities.

 Example:

 input:  data = [
 [1487799425, 14, 1],
 [1487799425, 4,  0],
 [1487799425, 2,  0],
 [1487800378, 10, 1],
 [1487801478, 18, 0],
 [1487801478, 18, 1],
 [1487901013, 1,  0],
 [1487901211, 7,  1],
 [1487901211, 7,  0]
 ]

 output: 1487800378 # since the increase in the number of people # in the mall is the highest at that point

 Constraints:
 [time limit] 5000ms
 [input]  1 ≤ data.length ≤ 100
 [output] integer
 */


public class BusiestTime {

	public static int findBusiestPeriod(int[][] data) {
		
		int maxTime = 0;
		int maxPeople = 0;

		int curTime = 0;
		int curNumberOfPeople = 0;

		int i = 0;
		while(i < data.length) {
			curTime = data[i][0];

			while(i < data.length && data[i][0] <= curTime) {
				if (data[i][2] == 0) {
					curNumberOfPeople -= data[i][1];
				} else {
					curNumberOfPeople += data[i][1];
				}

				++i;
			}
			// at end of time, update max
			if(maxPeople < curNumberOfPeople) {
				maxPeople = curNumberOfPeople;
				maxTime = curTime;
			}
		}

		return maxTime;
	}


	public static void main(String[] args) {

		int[][] data = new int[][]{
					{1487799425, 14, 1},
					{1487799425, 4, 0},
					{1487799425, 2, 0},
					{1487800378, 10, 1},
					{1487801478, 18, 0},
					{1487801478, 18, 1},
					{1487901013, 1, 0},
					{1487901211, 7, 1},
					{1487901211, 7, 0}
				};

		System.out.println(findBusiestPeriod(data));


	}



}
