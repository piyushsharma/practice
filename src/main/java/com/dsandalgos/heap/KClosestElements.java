package com.dsandalgos.heap;

import java.util.*;

public class KClosestElements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        PriorityQueue<Integer> q = new PriorityQueue<>((a   , b) -> (a-x) - (b-x));

        for(int elem : arr) {

            if(q.size() < k) {
                q.add(elem);
            } else if(Math.abs(q.peek() - x) > Math.abs(elem - x)) {
                q.remove();
                q.add(elem);
            }
        }

        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < k; ++i) {
            result.add(q.remove());
            // result[i] = new int[]{item[0], item[1]};
        }

        return result;
    }

}
