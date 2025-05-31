package com.dsandalgos.dp;

import java.util.*;

/**
 * Created by Piyush Sharma
 */

/*

Given a task sequence tasks such as ABBABBC, and an integer k,
which is the cool down time between two same tasks. Assume the execution for each individual task is 1 unit.

For example, if k = 3, then tasks BB takes a total of 5 unit time to finish, because B takes 1 unit of time to execute,
then wait for 3 unit, and execute the second B again for another unit of time. So 1 + 3 + 1 = 5.

Given a task sequence and the cool down time, return the total execution time.

Follow up: Given a task sequence and the cool down time,
          rearrange the task sequence such that the execution time is minimal.
*/

public class MinimumRuntimeScheduler {

    // naive implementation where we simply measure time
    public int totalExecutionTime(String taskSequence, int k) {

        // Note: cool down is only between two same tasks
        char prev = taskSequence.charAt(0);
        int totalTime = 1;
        for(int i = 1; i < taskSequence.length(); ++i) {
            char c = taskSequence.charAt(i);
            if(c == prev) {
                totalTime += k;
            }
            totalTime += 1;
            prev = c;
        }
        return totalTime;
    }


    class CharFrequency {
        int freq;
        char c;
        public CharFrequency(char c, int f) {
            this.c = c;
            this.freq = f;
        }
    }


    public int totalExecutionTimeRearrange(String taskSequence, int k) {
        // goal is to separate similar strings as much as possible, (as close to k distance apart)

        Map<Character, CharFrequency> m = new HashMap<>();
        for(char ch : taskSequence.toCharArray()) {
            if(m.containsKey(ch)) {
                m.get(ch).freq += 1;
            } else {
                m.put(ch, new CharFrequency(ch, 1));
            }
        }

        // create a priority queue to store characters as per frequencies
        Queue<Character> pq = new PriorityQueue<>(m.size(), new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return m.get(o2).freq - m.get(o1).freq;
            }
        });

        pq.addAll(m.keySet());

        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()) {
            CharFrequency charFrequency = m.get(pq.poll());
            sb.append(charFrequency.c);
            charFrequency.freq -= 1;

            CharFrequency second = null;
            if(!pq.isEmpty()) {
                second = m.get(pq.poll());
                second.freq -= 1;
                sb.append(second.c);
            }

            if(charFrequency.freq > 0) pq.add(charFrequency.c);
            if(second != null && second.freq > 0) pq.add(second.c);
        }

        String rearrangedString = new String(sb.toString());
        System.out.println(rearrangedString);

        return totalExecutionTime(rearrangedString, k);
    }

    public static void main(String[] args) {
        MinimumRuntimeScheduler m = new MinimumRuntimeScheduler();
        System.out.println(m.totalExecutionTime("AA", 3));
        System.out.println(m.totalExecutionTime("ACBACBACB", 3));


        System.out.println(m.totalExecutionTimeRearrange("AAABBBCCC", 3));
        System.out.println(m.totalExecutionTimeRearrange("AAABBBCC", 3));
        System.out.println(m.totalExecutionTimeRearrange("AAAABC", 3));
    }


}
