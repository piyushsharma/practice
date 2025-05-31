package com.dsandalgos.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 *
 * A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 *
 *
 *
 * Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 2, y = 1
 * Output: 1
 * Explanation: [0, 0] → [2, 1]
 * Example 2:
 *
 * Input: x = 5, y = 5
 * Output: 4
 * Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 *
 *
 * Constraints:
 *
 * |x| + |y| <= 300
 *
 */

public class MinimumKnightMoves {


    public int minKnightMovesA(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});

        HashSet<Integer> visited = new HashSet<>();
        int steps = 0;

        int[] xMoves = new int[]{1, 1, 2, 2, -1, -1, -2, -2};
        int[] yMoves = new int[]{2, -2, 1, -1, 2, -2, 1, -1};

        while(!queue.isEmpty()) {

            // we will add all neighbors in each step
            int startingPoints = queue.size();

            while(startingPoints > 0) {

                int[] sp = queue.remove();
                int curX = sp[0];
                int curY = sp[1];

                if(curX == x && curY == y) {
                    return steps;
                }

                int hash = 1000 * curX + curY;
                if (!visited.contains(hash)) {

                    for (int i = 0; i < 8; ++i) {

                        int nextX = curX + xMoves[i];
                        int nextY = curY + yMoves[i];
                        if (Math.abs(nextX) + Math.abs(nextY) > 300) {  // constraint is |x| + |y| <= 300, pruning based on the constraint
                            continue;
                        }
                        queue.add(new int[]{nextX, nextY});
                    }
                }
                visited.add(hash);

                --startingPoints;
            }

            ++steps;

        }
        return -1;
    }

    public int minKnightMoves(int x, int y) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        HashSet<Integer> visited = new HashSet<>();
        int steps = 0;

        int[] xMoves = new int[]{1, 1, 2, 2, -1, -1, -2, -2};
        int[] yMoves = new int[]{2, -2, 1, -1, 2, -2, 1, -1};

        // since board is fully symmetric acorss quadrants and diagonals
        x = Math.abs(x);
        y = Math.abs(y);

        // max co-ordinates allowed
        int p = 601;

        while(!queue.isEmpty()) {

            // we will add all neighbors in each step
            int startingPoints = queue.size();

            while(startingPoints > 0) {

                Integer sp = queue.remove();
                int curX = sp / p;
                int curY = sp % p;

                if(curX == x && curY == y) {
                    return steps;
                }

                for (int i = 0; i < 8; ++i) {

                    int nextX = curX + xMoves[i];
                    int nextY = curY + yMoves[i];
                    int hash = p * nextX + nextY;

                    if (nextX >= 0 && nextY >= 0 && !visited.contains(hash)) {
                        queue.add(hash);
                        visited.add(hash);
                    }
                }
                --startingPoints;
            }

            ++steps;

        }
        return -1;
    }

}
