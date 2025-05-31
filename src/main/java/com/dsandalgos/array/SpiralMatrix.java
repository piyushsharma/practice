package com.dsandalgos.array;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

	public List<Integer> spiral(int[][] m) {

		int rowStart = 0;
		int rowEnd = m.length-1;
		int colStart = 0;
		int colEnd = m[0].length-1;

		List<Integer> result = new ArrayList<>();
		while (rowStart <= rowEnd && colStart <= colEnd) {

			for (int i = colStart; i <= colEnd; ++i) {
				result.add(m[rowStart][i]);
			}
			++rowStart;

			for (int i = rowStart; i <= rowEnd; ++i) {
				result.add(m[i][colEnd]);
			}
			--colEnd;

			if(rowStart <= rowEnd) {
				for (int i = colEnd; i >= colStart; --i) {
					result.add(m[rowEnd][i]);
				}
				--rowEnd;
			}

			if(colStart <= colEnd) {
				for (int i = rowEnd; i >= rowStart; --i) {
					result.add(m[i][colStart]);
				}
				++colStart;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		new SpiralMatrix().spiral(new int[][]{{1,2}});
	}
	
}
