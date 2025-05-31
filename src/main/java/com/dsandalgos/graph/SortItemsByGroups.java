package com.dsandalgos.graph;

import java.util.*;

/**
 * There are n items each belonging to zero or one of m groups where group[i] is the group that the i-th
 * item belongs to and it's equal to -1 if the i-th item belongs to no group.
 * The items and the groups are zero indexed. A group can have no item belonging to it.
 *
 * Return a sorted list of the items such that:
 *
 * The items that belong to the same group are next to each other in the sorted list.
 * There are some relations between these items where beforeItems[i] is a list containing all the items that should come before the i-th item in the sorted array (to the left of the i-th item).
 * Return any solution if there is more than one solution and return an empty list if there is no solution.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
 * Output: [6,3,4,1,5,2,0,7]
 * Example 2:
 *
 * Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
 * Output: []
 * Explanation: This is the same as example 1 except that 4 needs to be before 6 in the sorted list.
 *
 *
 *
 * Constraints:
 *
 * 1 <= m <= n <= 3*10^4
 * group.length == beforeItems.length == n
 * -1 <= group[i] <= m-1
 * 0 <= beforeItems[i].length <= n-1
 * 0 <= beforeItems[i][j] <= n-1
 * i != beforeItems[i][j]
 * beforeItems[i] does not contain duplicates elements.
 */

public class SortItemsByGroups {


    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        Map<Integer, Set<Integer>> nodeGraph = new HashMap<>();
        Map<Integer, Integer> inDegreeNode = new HashMap<>();

        Map<Integer, Set<Integer>> groupGraph = new HashMap<>();
        Map<Integer, Set<Integer>> groupMembers = new HashMap<>();
        Map<Integer, Integer> inDegreeGroup = new HashMap<>();

        for(int i = 0; i < n; ++i) {
            nodeGraph.put(i, new HashSet<>());
            inDegreeNode.put(i, 0);
            if(group[i] == -1) {
                group[i] = m++;
            }

            groupGraph.putIfAbsent(group[i], new HashSet<>());
            groupMembers.putIfAbsent(group[i], new HashSet<>());
            inDegreeGroup.putIfAbsent(group[i], 0);
        }

        for(int i = 0; i < n; ++i) {

            groupMembers.get(group[i]).add(i);
            List<Integer> before = beforeItems.get(i);
            for(Integer item : before) {
                nodeGraph.get(item).add(i);
                inDegreeNode.put(i, inDegreeNode.get(i) + 1);

                if(group[i] != group[item]) {
                    // note: increment in degree count only if the group is a new dependency
                    if(groupGraph.get(group[item]).add(group[i])) {
                        inDegreeGroup.put(group[i], inDegreeGroup.get(group[i]) + 1);
                    }
                }
            }
        }

        // topological sort of nodes - find group, try to add all items in the group
        Queue<Integer> zeroGroup = new LinkedList<>();
        for(Integer groupNo : inDegreeGroup.keySet()) {
            if(inDegreeGroup.get(groupNo) == 0) {
                zeroGroup.add(groupNo);
            }
        }

        List<Integer> result = new ArrayList<>();

        while(!zeroGroup.isEmpty()) {

            Integer groupNo = zeroGroup.poll();
            Set<Integer> groupNodes = groupMembers.get(groupNo);

            Queue<Integer> zeroNodes = new LinkedList<>();
            for(Integer node : groupNodes) {
                if(inDegreeNode.get(node) == 0) {
                    zeroNodes.add(node);
                }
            }

            while(!zeroNodes.isEmpty()) {

                Integer node = zeroNodes.poll();
                result.add(node);

                Set<Integer> neighbors = nodeGraph.get(node);
                for (Integer neighbor : neighbors) {
                    inDegreeNode.put(neighbor, inDegreeNode.get(neighbor) - 1);
                    if (inDegreeNode.get(neighbor) == 0 && group[neighbor] == groupNo) {
                        zeroNodes.add(neighbor);
                    }
                }
            }

            for(Integer neighborGroup : groupGraph.get(groupNo)) {
                 inDegreeGroup.put(neighborGroup, inDegreeGroup.get(neighborGroup) - 1);
                 if(inDegreeGroup.get(neighborGroup) == 0) {
                     zeroGroup.add(neighborGroup);
                 }
            }
        }

        if(result.size() != n) {
            return new int[]{};
        }

        return result.stream().mapToInt(Integer :: intValue).toArray();
    }


    public int[] testcase1() {

        int[] group = new int[]{-1, -1, 1, 0, 0, 1, 0, -1};
        List<List<Integer>> beforeItems = new ArrayList<>();
        for(int i = 0; i < 8; ++i) {
            beforeItems.add(new ArrayList<>());
        }

        //        beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
        beforeItems.get(1).add(6);
        beforeItems.get(2).add(5);
        beforeItems.get(3).add(6);
        beforeItems.get(4).add(3);
        beforeItems.get(4).add(6);

        return sortItems(8, 2, group, beforeItems);
    }

    public int[] testcase2() {

        int[] group = new int[]{0,0,2,1,0};
        List<List<Integer>> beforeItems = new ArrayList<>();
        for(int i = 0; i < 5; ++i) {
            beforeItems.add(new ArrayList<>());
        }

        //        beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
        beforeItems.get(0).add(3);
        beforeItems.get(4).add(2);
        beforeItems.get(4).add(1);
        beforeItems.get(4).add(3);

        return sortItems(5, 3, group, beforeItems);
    }

    public static void main(String[] args) {

//        new SortItemsByGroups().testcase1();
        new SortItemsByGroups().testcase2();

        System.out.println("Done");
    }
}
