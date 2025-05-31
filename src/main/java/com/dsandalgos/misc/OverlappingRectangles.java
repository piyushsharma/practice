package com.dsandalgos.misc;

/**
 * Write a method to find the rectangular intersection of two given  rectangles.
 *
 * Rectangles are always "straight" and never "diagonal."
 * More rigorously: each side is parallel with either the x-axis or the y-axis.
 *
 */

public class OverlappingRectangles {

	public class Rectangle {

		// coordinates of bottom left corner
		private int leftX;
		private int bottomY;

		// dimensions
		private int width;
		private int height;

		public Rectangle() {}

		public Rectangle(int leftX, int bottomY, int width, int height) {
			this.leftX = leftX;
			this.bottomY = bottomY;
			this.width  = width;
			this.height = height;
		}

		public int getLeftX() {
			return leftX;
		}

		public int getBottomY() {
			return bottomY;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}
	}

	private int overlap(int point1, int length1, int point2, int length2) {

		int highestStartPoint = Math.max(point1, point2);
		int lowestEndPoint = Math.min(point1 + length1, point2 + length2);

		if(highestStartPoint >= lowestEndPoint) {
			return 0;
		}

		return lowestEndPoint - highestStartPoint;
	}


	private int findOverlap(Rectangle one, Rectangle two) {

		int x = overlap(one.leftX, one.width, two.leftX, two.width);
		if(x == 0) return 0;

		int y = overlap(one.bottomY, one.height, two.bottomY, two.height);
		if(y == 0) return 0;

		return x*y;
	}

	public static void main(String[] args) {

		OverlappingRectangles o = new OverlappingRectangles();
//		Rectangle one = o.new Rectangle(11,12,2,3);
//		Rectangle two = o.new Rectangle(17,1,0,2);

//		Rectangle one = o.new Rectangle(0,0,1,1);
//		Rectangle two = o.new Rectangle(2,2,1,1);

		Rectangle one = o.new Rectangle(-3,0,6,4);
		Rectangle two = o.new Rectangle(0,-1,9,3);

		int overlap = o.findOverlap(one, two);
		System.out.println(overlap);
	}
}



