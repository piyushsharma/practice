package com.dsandalgos.math;

/**
 * @author Piyush Sharma
 * @date 3/21/18
 */

/**
 * Initially, there is a Robot at position (0, 0).
 * Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.
 *
 * The move sequence is represented by a string. And each move is represent by a character.
 * The valid robot moves are R (Right), L (Left), U (Up) and D (down).
 * The output should be true or false representing whether the robot makes a circle.
 *
 * Example 1:
 *      Input: "UD"
 *      Output: true
 * Example 2:
 *      Input: "LL"
 *      Output: false
 */

public class JudgeRouteCircle {

	public boolean judgeCircle(String moves) {
		if(moves == null || moves.length() == 0)
			return true;

		int x = 0; int y = 0;

		for (Character c : moves.toCharArray()) {
			if (c == 'L') {
				x -= 1;
			} else if (c == 'R') {
				x += 1;
			} else if (c == 'U') {
				y += 1;
			} else if (c == 'D') {
				y -= 1;
			}
		}
		return x == 0 && y == 0;
	}

	public boolean judgeCircleSwitch(String moves) {
		if(moves == null || moves.length() == 0)
			return true;

		int x = 0; int y = 0;

		for (Character c : moves.toCharArray()) {
			switch (c) {
				case 'L':
					x -= 1;
					break;
				case 'R':
					x += 1;
					break;
				case 'D':
					y -= 1;
					break;
				case 'U':
					y += 1;
					break;
			}
		}
		return x == 0 && y == 0;
	}
}
