package com.dsandalgos.tophundred;

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * Example 1:
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2:
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 *
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
 * Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */


public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        Map<Integer, Set<Integer>> graph = buildGraph(numCourses, prerequisites);

        if(graph.size() > numCourses) {
            return false;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(Integer key : graph.keySet()) {
            if(graph.get(key).size() == 0) {
                queue.add(key);
            }
        }

        int count = 0;
        while(!queue.isEmpty()) {

            int course = queue.poll();
            ++count;
            for(int i = 0; i < numCourses; ++i) {
                if(graph.get(i).contains(course)) {
                    graph.get(i).remove(course);

                    if(graph.get(i).size() == 0) {
                        queue.add(i);
                    }
                }
            }
        }

        return count == numCourses;
    }


    public int[] returnValidPath(int numCourses, int[][] prerequisites) {

        int[] result = new int[numCourses];
        if(prerequisites == null || prerequisites.length == 0) {
            for(int i = 0; i < numCourses; ++i) {
                result[i] = i;
            }
            return result;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];

        for(int i = 0; i < numCourses; ++i) {
            graph.putIfAbsent(i, new HashSet<>());
            inDegree[i] = 0;
        }

        for(int i = 0; i < prerequisites.length; ++i) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            inDegree[prerequisites[i][0]] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; ++i) {
            if(inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        while(!queue.isEmpty()) {

            int course = queue.poll();
            result[count] = course;
            ++count;
            for(int dependent : graph.get(course)) {
                inDegree[dependent] -= 1;
                if(inDegree[dependent] == 0) {
                    queue.add(dependent);
                }
            }
        }

        return count == numCourses ? result : new int[]{};
    }


    private Map<Integer, Set<Integer>> buildGraph(int n, int[][] prerequisites) {
        Map<Integer,Set<Integer>> graph = new HashMap<>();

        for(int i = 0; i < prerequisites.length; ++i) {

            int courseNumber = prerequisites[i][0];
            int prereq = prerequisites[i][1];

            graph.putIfAbsent(courseNumber, new HashSet<>());
            graph.putIfAbsent(prereq, new HashSet<>());

            graph.get(courseNumber).add(prereq);
        }

        // this is to make sure if numCourses passed > prereq courses, we can still check count == numCourses
        for(int i = 0; i < n; ++i) {
            graph.putIfAbsent(i, new HashSet<>());
        }

        return graph;
    }


    public static void main(String[] args) {

//        boolean x = new CourseSchedule().canFinish(2, new int[][]{{0,1},{1,0}});
        boolean x = new CourseSchedule().canFinish(3, new int[][]{{0,1}});
        System.out.println(x);

    }

}
