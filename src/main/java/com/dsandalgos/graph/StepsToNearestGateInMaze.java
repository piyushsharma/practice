package com.dsandalgos.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Piyush Sharma
 */

/*

This was a phone screen problem.
Given a maze with cells being: gates, walls or empty spaces.

INPUT maze:

_ W G _
_ _ _ W
_ W _ W
G W _ _

RESULT should be:

3 W G 1
2 2 1 W
1 W 2 W
G W 3 4

Fill the empty spaces with the number of steps to the closest gate.
Allowed steps: UP, RIGHT, LEFT & DOWN

*/


public class StepsToNearestGateInMaze {

    class MazeGraphNode {
        int i;
        int j;
        int depth;

        public MazeGraphNode(int i, int j, int depth) {
            this.i = i;
            this.j = j;
            this.depth = depth;
        }
    }

    public static final char GATE = 'G';
    public static final char WALL = 'W';
    public static final char EMPTY = '_';

    public void stepsToNearestGate(char[][] maze) {

        int m = maze.length;
        int n = maze[0].length;

        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {

                if(maze[i][j] == GATE) {
                    bfs(maze, i, j);
                }
            }
        }
    }


    public void bfs(char[][] maze, int gateRowIndex, int gateColumnIndex) {
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];

        Queue<MazeGraphNode> q = new LinkedList<>();
        MazeGraphNode gateNode = new MazeGraphNode(gateRowIndex, gateColumnIndex, 0);
        q.add(gateNode);

        while(!q.isEmpty()) {

            MazeGraphNode node = q.remove();
            int i = node.i;
            int j = node.j;
            visited[i][j] = true;

            // check all neighbors and add to queue if processing required

            // node on left
            if(j > 0 && visited[i][j-1] == false) {
                MazeGraphNode topNode = getExistingDepthIfAny(maze, i, j-1, node);
                if(topNode != null) {
                    q.add(topNode);
                }
            }

            // node on top
            if(i > 0 && visited[i-1][j] == false) {
                MazeGraphNode topNode = getExistingDepthIfAny(maze, i-1, j, node);
                if(topNode != null) {
                    q.add(topNode);
                }
            }

            // node on right
            if(j + 1 < n && visited[i][j+1] == false) {
                MazeGraphNode topNode = getExistingDepthIfAny(maze, i, j + 1, node);
                if(topNode != null) {
                    q.add(topNode);
                }
            }

            // node on bottom
            if(i + 1 < m && visited[i+1][j] == false) {
                MazeGraphNode topNode = getExistingDepthIfAny(maze, i+1, j, node);
                if(topNode != null) {
                    q.add(topNode);
                }
            }
        }
    }

    public MazeGraphNode getExistingDepthIfAny(char[][] maze, int i, int j, MazeGraphNode parent) {
        if(maze[i][j] == GATE || maze[i][j] == WALL) {
            return null;
        }

        if(maze[i][j] == EMPTY) {
            maze[i][j] = (char) ('0' + parent.depth + 1);
            return new MazeGraphNode(i, j, parent.depth + 1);
        }

        int updatedDepth = Math.min( maze[i][j] - '0', parent.depth + 1);
        maze[i][j] = (char) ('0' + updatedDepth);
        return new MazeGraphNode(i, j, updatedDepth);
    }


    public static void main(String[] args) {
        StepsToNearestGateInMaze s = new StepsToNearestGateInMaze();

        char[][] maze = {
                            {'_', 'W', 'G', '_', '_'},
                            {'_', '_', '_', 'W', '_'},
                            {'_', 'W', '_', 'W', 'W'},
                            {'G', 'W', '_', '_', '_'}
                        };

        s.stepsToNearestGate(maze);

        for(int i = 0; i < maze.length; ++i) {
            for(int j = 0; j < maze[0].length; ++j) {
                System.out.printf("%s ", maze[i][j]);
            }
            System.out.printf("\n");
        }

    }


}
