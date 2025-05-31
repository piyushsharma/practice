package com.dsandalgos.stack;

import java.util.Stack;

/**
 * Created by Piyush Sharma
 */

/*

Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
find the area of largest rectangle in the histogram.

Example: Input is a histogram where width of each bar is 1, and height = [2,1,5,6,2,3].
For the given heights = [2,1,5,6,2,3], return 10.

*/



public class LargestRectangleInHistogram {


    /*
    ALGO:
        For every bar ‘x’, we calculate the area with ‘x’ as the smallest bar in the rectangle.
        If we calculate such area for every bar ‘x’ and find the maximum of all areas, our task is done.

        How to calculate area with ‘x’ as smallest bar?
            We need to know index of the first smaller (smaller than ‘x’) bar on left of ‘x’
            and index of first smaller bar on right of ‘x’.

            Let us call these indexes as ‘left index’ and ‘right index’ respectively.

            We traverse all bars from left to right, maintain a stack of bars.
            Every bar is pushed to stack once. A bar is popped from stack when a bar of smaller height is seen.
            When a bar is popped, we calculate the area with the popped bar as smallest bar.

            How do we get left and right indexes of the popped bar?
                The current index tells us the ‘right index’ and index of previous item in stack is the ‘left index’
     */

    /*
    1) Create an empty stack.
    2) Start from first bar, and do following for every bar ‘height[i]’ where ‘i’ varies from 0 to n-1.
        a) If stack is empty or hist[i] is higher than the bar at top of stack, then push ‘i’ to stack.
        b) If this bar is smaller than the top of stack, then keep removing the top of stack
        while top of the stack is greater. Let the removed bar be hist[top].
        Calculate area of rectangle with hist[top] as smallest bar.
        For hist[tp], the ‘left index’ is previous (previous to tp) item in stack and ‘right index’ is ‘i’ (current index).

    3) If the stack is not empty, then one by one remove all bars from stack and do step 2.b for every removed bar.
     */

    public int largestRectangleArea(int[] heights) {

        if (heights == null || heights.length == 0) return 0;
        if (heights.length == 1) return heights[0];

        // Use stack to push indexes (increasing in size) to the stack
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int i = 0;
        while (i < heights.length) {

            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i);
                ++i;
            } else {

                // we see a smaller bar, so pop and calculate the area of the popped bar as the smallest bar
                int poppedBarHeight = heights[stack.pop()];
                int leftIndex = stack.isEmpty() ? 0 : stack.peek();
                int width = i - leftIndex - 1;
                maxArea = Math.max(maxArea, width * poppedBarHeight);
            }
        }

        while (!stack.isEmpty()) {

            int poppedBarHeight = heights[stack.pop()];
            int width = stack.isEmpty() ? heights.length : heights.length - stack.peek() - 1;
            maxArea = Math.max(maxArea, width * poppedBarHeight);
        }

        return maxArea;
    }


    // O(n^2)
    public int largestRectangleAreaBruteForce(int[] heights) {

        int maxArea = 0;
        for (int i = 0; i < heights.length; ++i) {

            int minHeight = heights[i];
            int maxStartingAtI = 0;

            for (int j = i + 1; j < heights.length; ++j) {
                minHeight = Math.min(minHeight, heights[j]);
                int areaEndingAtJ = (j - i + 1) * minHeight;
                maxStartingAtI = Math.max(maxStartingAtI, areaEndingAtJ);
            }
            maxArea = Math.max(maxArea, maxStartingAtI);
        }

        return maxArea;
    }

    private static int getLargestArea(int[] hist) {
        int maxArea = 0;
        int i = 0;
        Stack<Integer> stack = new Stack<Integer>();

        while (i < hist.length) {
            if (stack.isEmpty() || hist[i] >= hist[stack.peek()])
                stack.push(i++);
            else {
                int topIndex = stack.pop();

                // It should be (i - stack.peek() - 1), and not (topIndex - 1).
                // This is because in case of ascending histogram, it would calculate only
                // the area of two consecutive bars.
                int area = hist[topIndex] * (stack.isEmpty() ? i : i - stack.peek() - 1);

                if (area > maxArea)
                    maxArea = area;
            }
        }

        // It should not be (i < hist.length).
        // 'i' is already at the end. So don't increment anymore.
        while (!stack.isEmpty()) {
            int topIndex = stack.pop();
            int area = hist[topIndex] * (stack.isEmpty() ? i : i - stack.peek() - 1);
            maxArea = Math.max(area, maxArea);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram l = new LargestRectangleInHistogram();

        System.out.println(l.largestRectangleArea(new int[]{1, 1}));
        System.out.println(l.largestRectangleArea(new int[]{6, 2, 5, 4, 5, 1, 6}));
    }

}
