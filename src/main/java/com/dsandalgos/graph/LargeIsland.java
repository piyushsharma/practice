package com.dsandalgos.graph;

import com.dsandalgos.misc.IslandCount;

import java.util.*;

public class LargeIsland {

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    Map<Integer, Integer> islandCountMap;

    public int largestIsland(int[][] grid) {
        int m =  grid.length;
        int n = grid[0].length;

        int islandKey = 2;
        islandCountMap = new HashMap<>();

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    int count = markIsland(i, j, grid, islandKey);
                    islandCountMap.put(islandKey, count);
                    islandKey++;
                }
            }
        }

        boolean flipFound = false;
        int result = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    flipFound = true;
                    int val = getMaxByFlip(grid, i, j);
                    result = Math.max(result, val);
                }
            }
        }

        return flipFound ? result : m * n;
    }

    private int getMaxByFlip(int[][] grid, int i, int j) {
        Set<Integer> neighbors = new HashSet<>();

        // left
        if(j - 1 >= 0) {
            neighbors.add(grid[i][j - 1]);
        }
        // right
        if(j + 1 < grid[0].length) {
            neighbors.add(grid[i][j + 1]);
        }

        // top
        if(i - 1 >= 0) {
            neighbors.add(grid[i - 1][j]);
        }

        // bottom
        if(i + 1 < grid.length) {
            neighbors.add(grid[i + 1][j]);
        }

        int result = 0;
        for(int neighbor : neighbors) {
            if(islandCountMap.containsKey(neighbor)) {
                result += islandCountMap.get(neighbor);
            }
        }
        return result + 1;
    }

    private int markIsland(int i, int j, int[][] grid, int islandKey) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i, j));
        grid[i][j] = islandKey;

        int  count = 0;

        while (!q.isEmpty()) {
            Point p = q.remove();
            ++count;
            int curI = p.x;
            int curJ = p.y;

            // left
            pushIfValid(grid, q, curI, curJ - 1, islandKey);
            // right
            pushIfValid(grid, q, curI, curJ + 1, islandKey);
            // top
            pushIfValid(grid, q, curI - 1, curJ, islandKey);
            // bottom
            pushIfValid(grid, q, curI + 1, curJ, islandKey);

        }
        return count;
    }

    private void pushIfValid(int[][] grid, Queue<Point> q, int i, int j, int islandKey) {
        if(i >=0 && i < grid.length && j >=0 && j < grid[0].length) {
            if(grid[i][j] == 1) {
                grid[i][j] = islandKey;
                q.add(new Point(i, j));
            }
        }
    }


    public static void main(String[] args) {

        int[][] grid = new int[][]{{1, 0}, {1, 0}};

        int out = new LargeIsland().largestIsland(grid);
        System.out.println(out);


    }

}
