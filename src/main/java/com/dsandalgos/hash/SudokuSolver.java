package com.dsandalgos.hash;

import java.util.*;

/**
 * Created by Piyush Sharma
 */

/*

Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.
*/


public class SudokuSolver {

    /*
    Algo:
        - Start moving left to right (from top left to bottom right)
        - check each cell while moving;
        - try each possible candidate from '1' to '9' while the checking cell is empty
            and if it's solvable from now on (recursive method) then just return it;
        - if all possible candidates failed, return false to indicate un-solvable in this condition
            which means indirectly that we should try another path - typical DFS solution using recursion.

        Note: We can optimize third step to loop across valid options (basically prune valid numbers)
    */

    // Space O(1), Time(9^k) => but we will stop at first valid solution, so should be less
    public void solveSudoku(char[][] board) {
        solveSudoku(board, 0, 0);
    }


    private List<Character> sudokuNums = new ArrayList<>(Arrays.asList('1','2','3','4','5','6','7','8','9'));

    public boolean solveSudoku(char[][] board, int row, int col) {

        if(row == 9) return true;

        if(col == 9) return solveSudoku(board, row + 1, 0);

        if(board[row][col] != '.') return  solveSudoku(board, row, col + 1);

        // else
        for(Character num : sudokuNums) {
            if(checkIfNumberSolvesSudoku(board, row, col, num)) {

                board[row][col] = num;
                if(solveSudoku(board, row, col + 1)) return true;

                board[row][col] = '.'; // reset it in case num does not solve sudoku
            }
        }
        return false;
    }

    private boolean checkIfNumberSolvesSudoku(char[][] board, int rowIndex, int colIndex, char num) {

        int rowStart = rowIndex - rowIndex%3;
        int colStart = colIndex - colIndex%3;

        for(int i = rowStart; i < rowStart + 3; ++i) {
            for(int j = colStart; j < colStart + 3; ++j) {
                if(board[i][j] == num) return false;
            }
        }
        for(int i = 0; i < 9; ++i) {
            if (board[rowIndex][i] == num || board[i][colIndex] == num) return false;
        }

        return true;
    }

    public void test(char[][] b) {b[0][2] = '3' ;}

    public static void main(String[] args) {
        char[][] b = new char[][]{
                {'5' , '3' , '.' , '.' , '7' , '.' , '.' , '.' , '.'},

                {'6' , '.' , '.' , '1' , '9' , '5' , '.' , '.' , '.'},

                {'.' , '9' , '8' , '.' , '.' , '.' , '.' , '6' , '.'},

                {'8' , '.' , '.' , '.' , '6' , '.' , '.' , '.' , '3'},

                {'4' , '.' , '.' , '8' , '.' , '3' , '.' , '.' , '1'},

                {'7' , '.' , '.' , '.' , '2' , '.' , '.' , '.' , '6'},

                {'.' , '6' , '.' , '.' , '.' , '.' , '2' , '8' , '.'},

                {'.' , '.' , '.' , '4' , '1' , '9' , '.' , '.' , '5'},

                {'.' , '.' , '.' , '.' , '8' , '.' , '.' , '7' , '9'}};


        new SudokuSolver().solveSudoku(b);
        System.out.println("Done");

    }
}
