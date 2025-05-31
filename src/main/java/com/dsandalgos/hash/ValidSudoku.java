package com.dsandalgos.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Piyush Sharma
 */

/*

Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

*/


public class ValidSudoku {

    // just check that we never see the same number again
    public boolean isValidSudoku(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        Set<String> sudokuStrs = new HashSet<>();
        // validate all rows
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {

                char curNumber = board[i][j];
                if(curNumber != '.') {

                    int val = curNumber - '0';

                    // validate row
                    String s1 = curNumber + " at row " + i;

                    // validate column
                    String s2 = curNumber + " at column " + j;

                    // validate 3*3 subboard area
                    String s3 = curNumber + " at " + i/3 + " and " + j/3;

                    if(val < 0 || val > 9 || !sudokuStrs.add(s1) || !sudokuStrs.add(s2) || !sudokuStrs.add(s3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // space optimized
    public boolean isValidSudokuV2(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        // validate all rows
        for(int i = 0; i < m; ++i) {
            if(!validateSubBoard(board, i, i, 0, n - 1)) return false;
        }

        // validate all columns
        for(int j = 0; j < n; ++j) {
            if(!validateSubBoard(board, 0, m-1, j, j)) return false;
        }

        // validate sub areas
        for(int i = 0; i < m; i += 3) {
            for(int j = 0; j < n; j += 3) {
                if(!validateSubBoard(board, i, i+2, j, j+2)) return false;
            }
        }
        return true;
    }

    public boolean validateSubBoard(char[][] board, int rs, int re, int cs, int ce) {
        Set<Integer> validNumbers = new HashSet<>();
        for(int i = rs; i <= re; ++i) {
            for(int j = cs; j <= ce; ++j) {
                char curChar = board[i][j];

                if(curChar != '.') {
                    int val = curChar - '0';
                    if(val < 0 || val > 9 || validNumbers.contains(val)) return false;
                    validNumbers.add(val);
                }
            }
        }

        return true;
    }


    public static void main(String[] args) {
        char[][] b = new char[][]{
                {'.' , '8' , '7' , '6' , '5' , '4' , '3' , '2' , '1'},
                {'2' , '.' , '.' , '.' , '.' , '.' , '.' , '.' , '.'},
                {'3' , '.' , '.' , '.' , '.' , '.' , '.' , '.' , '.'},
                {'4' , '.' , '.' , '.' , '.' , '.' , '.' , '.' , '.'},
                {'5' , '.' , '.' , '.' , '.' , '.' , '.' , '.' , '.'},
                {'6' , '.' , '.' , '.' , '.' , '.' , '.' , '.' , '.'},
                {'7' , '.' , '.' , '.' , '.' , '.' , '.' , '.' , '.'},
                {'8' , '.' , '.' , '.' , '.' , '.' , '.' , '.' , '.'},
                {'9' , '.' , '.' , '.' , '.' , '.' , '.' , '.' , '.'}};

        System.out.println(new ValidSudoku().isValidSudoku(b));

    }
}
