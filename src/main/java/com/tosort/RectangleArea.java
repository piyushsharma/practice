package com.tosort;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

             Y
             |
     ________|_____(C,D):(3,4)
     |       |     |
     |       |_____|_______ (G,H):(9,2)
     |       |     |       |
     |_______|_____|_______|__________X
(A,B):       |             |
(-3,0)       |             |
             |_____________|
        (E,F):(0, -3)

Rectangle Area
Assume that the total area is never beyond the maximum possible value of int.
 */

public class RectangleArea {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        // subtract overlapping area
        long x = (long)Math.min(C, G) - (long)Math.max(A, E);
        long overlappingX = Math.max(x, 0);
        long y = (long) Math.min(D, H) - (long)Math.max(B, F);
        long overlappingY = Math.max(0, y);

        int overlappingArea = (int)(overlappingX * overlappingY);
        int totalArea = Math.abs(A - C) * Math.abs(B - D) + Math.abs(E - G) * Math.abs(F - H);
        return totalArea - overlappingArea;
    }

    public static void main(String[] args) {
        RectangleArea r = new RectangleArea();
        System.out.println(r.computeArea(-2,-2,2,2,-2,-2,2,2));
        System.out.println(r.computeArea(-1500000001, 0, -1500000000, 1, 1500000000, 0, 1500000001, 1));
    }
}
