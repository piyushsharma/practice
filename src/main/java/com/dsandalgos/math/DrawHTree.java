package com.dsandalgos.math;

/**
 * Created by Piyush Sharma
 */

/*
Construct an H-tree, given its center (x and y coordinates),
starting_length and depth. You can assume that you have a drawLine method.

https://en.wikipedia.org/wiki/H_tree#/media/File:H_tree.svg


 |      |    |      |
 |------|    |------|
 |   |  |    |   |  |
     |           |
     |-----------|
     |           |
 |   |  |        |
 |------|   .......
 |      |

How to construct an H-tree?

An H-tree can be constructed by starting with a line segment of arbitrary length,
drawing two segments of the same length at right angles to the first through its endpoints,
and continuing in the same vein, reducing (dividing) the length of the line segments drawn at each stage by √2.

*/

public class DrawHTree {

    public void drawLine(int x1, int y1, int x2, int y2) {

    }

    // Space complexity => O(depth) since at any point there will be depth number of stacks in the recursion
    // Time complexity => O(4^depth)
    public void constructHTree(int x, int y, int len, int depth) {

        if(depth == 0) return;

        // draw center line
        drawLine(x - len/2, y, x + len/2, y);

        // draw left line at left end
        drawLine(x - len/2, y + len/2, x - len/2, y - len/2);

        // draw right line at right end
        drawLine(x + len/2, y + len/2, x + len/2, y - len/2);

        // for both left and right lines call construct recursively until depth becomes 0

        // call for left line top point as center
        constructHTree(x - len/2, y + len/2, (int)Math.sqrt(len), depth - 1);
        // call for left line bottom point as center
        constructHTree(x - len/2, y - len/2, (int)Math.sqrt(len), depth - 1);

        // call for right line top point as center
        constructHTree(x + len/2, y + len/2, (int)Math.sqrt(len), depth - 1);
        // call for right line bottom point as center
        constructHTree(x + len/2, y - len/2, (int)Math.sqrt(len), depth - 1);
    }

}
