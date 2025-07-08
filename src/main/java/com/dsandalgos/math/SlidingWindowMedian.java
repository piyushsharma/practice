package com.dsandalgos.math;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SlidingWindowMedian {

    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    public double[] medianSlidingWindow(int[] nums, int k) {

        double[] median = new double[nums.length - k + 1];
        for(int i = 0; i < nums.length; i++){
            add(nums[i]);
            // When the index reaches size of k, we can find the median and remove the first element in the window
            if (i >= k - 1) {
                median[i-k+1] = findMedian();
                remove(nums[i-k+1]);
            }
        }
        return median;
    }

    // For odd number of elements, top most element in maxHeap is the median of the current window,
    // else mean of maxHeap top & minHeap top represents the median
    private double findMedian(){
        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : (double) (minHeap.peek() + maxHeap.peek()) / 2.0;
    }

    // This method adds the next element in the sliding window in the appropriate heap and rebalances the heaps
    private void add(int num){
        if (maxHeap.size() == 0 || maxHeap.peek() >= num)
            maxHeap.add(num);
        else minHeap.add(num);
        rebalanceHeaps();
    }

    // This method removes the first element in the sliding window from the appropriate heap and rebalances the heaps
    private void remove(int num){
        if (num > maxHeap.peek())
            minHeap.remove(num);
        else maxHeap.remove(num);
        rebalanceHeaps();
    }

    // This method keeps the height of the 2 heaps same
    private void rebalanceHeaps(){
        if (maxHeap.size() == minHeap.size())
            return;
        if (maxHeap.size() > minHeap.size() + 1)
            minHeap.add(maxHeap.poll());
        else if (maxHeap.size() < minHeap.size())
            maxHeap.add(minHeap.poll());
    }


    public double[] medianSlidingWindowV2(int[] nums, int k) {
        double[] medians = new double[nums.length - k + 1];
        Map<Integer, Integer> tombStone = new HashMap<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < k; i++) {
            maxHeap.offer(nums[i]);
        }

        for (int i = 0; i < k / 2; i++) {
            minHeap.offer(maxHeap.poll());
        }


        int j = 0;
        for (int i = k;; i++) {
            if (k % 2 == 1) {
                medians[j++] = maxHeap.peek();
            } else {
                medians[j++] = ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
            }

            if (i >= nums.length)
                break;

            int outNum = nums[i - k];
            int inNum = nums[i];

            int balance = (outNum <= maxHeap.peek()) ? -1 : 1;
            if (inNum <= maxHeap.peek()) {
                balance++;
                maxHeap.offer(inNum);
            } else {
                balance--;
                minHeap.offer(inNum);
            }

            if (balance < 0) {
                maxHeap.offer(minHeap.poll());
            } else if (balance > 0) {
                minHeap.offer(maxHeap.poll());
            }

            tombStone.put(outNum, tombStone.getOrDefault(outNum, 0) + 1);
            while (tombStone.getOrDefault(maxHeap.peek(), 0) > 0) {
                tombStone.put(maxHeap.peek(), tombStone.get(maxHeap.peek()) - 1);
                maxHeap.poll();
            }

            while (!minHeap.isEmpty() && tombStone.getOrDefault(minHeap.peek(), 0) > 0) {
                tombStone.put(minHeap.peek(), tombStone.get(minHeap.peek()) - 1);
                minHeap.poll();
            }
        }

        return medians;
    }

}
