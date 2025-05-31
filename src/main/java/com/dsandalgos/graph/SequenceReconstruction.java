package com.dsandalgos.graph;

import java.util.*;

/**
 * Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs.
 * The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104.
 * Reconstruction means building a shortest common supersequence of the sequences in seqs
 * (i.e., a shortest sequence so that all sequences in seqs are subsequences of it).
 * Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
 *
 * Example 1:
 *
 * Input:
 * org: [1,2,3], seqs: [[1,2],[1,3]]
 *
 * Output:
 * false
 *
 * Explanation:
 * [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
 * Example 2:
 *
 * Input:
 * org: [1,2,3], seqs: [[1,2]]
 *
 * Output:
 * false
 *
 * Explanation:
 * The reconstructed sequence can only be [1,2].
 * Example 3:
 *
 * Input:
 * org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
 *
 * Output:
 * true
 *
 * Explanation:
 * The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
 * Example 4:
 *
 * Input:
 * org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
 *
 * Output:
 * true
 */


public class SequenceReconstruction {

    // topological sort

    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {


        final Map<Integer, Set<Integer>> graph = new HashMap<>();
        final Map<Integer, Integer> inDegree = new HashMap<>();

        for (List<Integer> sequence : seqs) {
            for (int i = 0; i < sequence.size(); ++i) {
                graph.putIfAbsent(sequence.get(i), new HashSet<>());
                inDegree.putIfAbsent(sequence.get(i), 0);

                // add all these items as neighbors to all previous item
                if(i > 0) {
                    graph.get(sequence.get(i-1)).add(sequence.get(i));
                }
            }
        }

        //update in-degree
        for (Integer node : graph.keySet()) {
            for (Integer item : graph.get(node)) {
                inDegree.put(item, inDegree.get(item) + 1);
            }
        }

        if(org.length != inDegree.size()) {
            return false;
        }

        Queue<Integer> zeroInDegreeNodes = new LinkedList<>();
        for(Integer node : inDegree.keySet()) {
            if(inDegree.get(node) == 0) {
                zeroInDegreeNodes.add(node);
            }
        }

        int index = 0;
        while(!zeroInDegreeNodes.isEmpty()) {

            // more than one solution
            if(zeroInDegreeNodes.size() != 1) {
                return false;
            }

            Integer node = zeroInDegreeNodes.poll();
            if((index == org.length) || (org[index++] != node.intValue())) {
                return false;
            }

            for(Integer neighbor : graph.get(node)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if(inDegree.get(neighbor) == 0) {
                    zeroInDegreeNodes.add(neighbor);
                }
            }
        }

        return index == org.length;
    }


    public static void main(String[] args) {
        int[] org = new int[]{4,1,5,2,6,3};

        List<List<Integer>> seqs = new ArrayList<>();
        seqs.add(Arrays.asList(new Integer[]{5,2,6,3}));
        seqs.add(Arrays.asList(new Integer[]{4,1,5,2}));

        new SequenceReconstruction().sequenceReconstruction(org, seqs);

        System.out.printf("done");

    }

}
