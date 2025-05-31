package com.dsandalgos.design;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Piyush Sharma
 */


/*

Median is the middle value in an ordered integer list.
If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

add(1)
add(2)
findMedian() -> 1.5
add(3)
findMedian() -> 2

 */

/*

    - Max Heap to represent elements that are less than effective median
    - Min Heap to represent elements that are greater than effective median

     After processing an incoming element, the number of elements in heaps differ utmost by 1 element.
     When both heaps contain same number of elements, we pick average of heaps root data as effective median.
     When the heaps are not balanced, we select effective median from the root of heap containing more elements.
 */

public class FindMedianFromDataStream {

    // by default java's PQ is a minHeap
    private Queue<Integer> minHeap = new PriorityQueue<>();
    private Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    // Adds a number into the data structure.
    public void addNum(int num) {

        maxHeap.add(num);
        minHeap.add(maxHeap.poll());
        if(maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(maxHeap.size() == minHeap.size()) {
            return (double) (maxHeap.peek() + minHeap.peek())/2;
        }
        return maxHeap.peek();
    }


    public static void main(String[] args) {
        FindMedianFromDataStream f = new FindMedianFromDataStream();

        for(int i = 1; i < 10; ++i) {
            f.addNum(i);
            System.out.println(f.findMedian());
        }
    }


}
