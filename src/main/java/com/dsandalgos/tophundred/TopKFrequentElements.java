package com.dsandalgos.tophundred;


import java.util.*;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 *
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 *
 */


public class TopKFrequentElements {

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // uses max heap
        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>((n1, n2) -> (n2.getValue() - n1.getValue()));

        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            pq.add(entry);
            if(pq.size() > k) {
                pq.remove();
            }
        }

        List<Integer> result = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : pq) {
            result.add(entry.getKey());
        }
        return result;
    }


    public List<Integer> topKFrequentPQ(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // uses min heap -- optimized for when k is small
        PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2) -> (map.get(n1) - map.get(n2)));

        for(Integer n : map.keySet()) {
            pq.add(n);
            if(pq.size() > k) {
                pq.remove();
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!pq.isEmpty()) {
            result.add(pq.poll());
        }

        // reverse to get most frequent first
        Collections.reverse(result);

        return result;
    }

}
