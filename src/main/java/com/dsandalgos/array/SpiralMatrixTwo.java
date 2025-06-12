package com.dsandalgos.array;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrixTwo {

	public int[][] generate(int n) {


		int rowStart = 0;
		int rowEnd = n - 1;
		int colStart = 0;
		int colEnd = n - 1;
		int count = 1;
		
		int[][] result = new int[n][n];

		while (rowStart <= rowEnd && colStart <= colEnd) {

			for (int i = colStart; i <= colEnd; ++i) {
				result[rowStart][i] = count++;
			}
			++rowStart;

			for (int i = rowStart; i <= rowEnd; ++i) {
				result[i][colEnd] = count++;
			}
			--colEnd;

			if(rowStart <= rowEnd) {
				for (int i = colEnd; i >= colStart; --i) {
					result[rowEnd][i] = count++;
				}
				--rowEnd;
			}

			if(colStart <= colEnd) {
				for (int i = rowEnd; i >= rowStart; --i) {
					result[i][colStart] = count++;
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
