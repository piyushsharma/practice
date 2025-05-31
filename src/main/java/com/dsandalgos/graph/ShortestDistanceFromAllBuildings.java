package com.dsandalgos.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 *
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * Example:
 *
 * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 *
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 *
 * Output: 7
 *
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
 *              the point (1,2) is an ideal empty land to build a house, as the total
 *              travel distance of 3+3+1=7 is minimal. So return 7.
 */


public class ShortestDistanceFromAllBuildings {

    class GridPoint {
        int x;
        int y;

        public GridPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public int shortestDistance(int[][] grid) {

        if(grid == null || grid.length == 0) {
            return -1;
        }

        int r = grid.length;
        int c = grid[0].length;

        int[][] distance = new int[r][c];
        int[][] reach = new int[r][c];
        int buildings = 0;

        for(int i = 0; i < grid.length; ++i) {
            for(int j = 0; j < grid[0].length; ++j) {

                if(grid[i][j] == 1) {
                    ++buildings;

                    // start bfs and record distance to reach all 0s in the grid

                    boolean[][] visited = new boolean[r][c];
                    int level = 1;
                    Queue<GridPoint> q = new LinkedList<>();
                    q.offer(new GridPoint(i, j));

                    while(!q.isEmpty()) {

                        int size = q.size();
                        for(int p = 0; p < size; ++p) {

                            GridPoint g = q.remove();

                            // exploreTop
                            addToQueue(grid, q, g.x - 1, g.y, visited, distance, reach, level);

                            // exploreBottom
                            addToQueue(grid, q, g.x + 1, g.y, visited, distance, reach, level);

                            // exploreLeft
                            addToQueue(grid, q, g.x, g.y - 1, visited, distance, reach, level);

                            // exploreRight
                            addToQueue(grid, q, g.x, g.y + 1, visited, distance, reach, level);

                        }
                        ++level;
                    }
                }
            }
        }

        int minSum = Integer.MAX_VALUE;

        for(int i = 0; i < grid.length; ++i) {
            for(int j = 0; j < grid[0].length; ++j) {

                if(grid[i][j] == 0 && reach[i][j] == buildings) {
                    minSum = Math.min(minSum, distance[i][j]);
                }
            }
        }

        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }


    private void addToQueue(int[][] grid, Queue<GridPoint> q, int i, int j, boolean[][] visited,
                            int[][] distance, int[][] reach, int level) {

        if(i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && visited[i][j] == false
                && grid[i][j] == 0) {

            q.add(new GridPoint(i, j));
            distance[i][j] += level;
            reach[i][j] += 1;

            visited[i][j] = true;
        }
    }

    public static void main(String[] args) {

        int[][] grid = new int[][]{{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};

        int x = new ShortestDistanceFromAllBuildings().shortestDistance(grid);

        System.out.println(x);
    }
}
