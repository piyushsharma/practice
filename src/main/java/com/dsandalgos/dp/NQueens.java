package com.dsandalgos.dp;

import java.util.Scanner;

public class NQueens {

    private boolean free(int[][]board, int p, int q) {
        int N = board.length;
        for(int i = 0; i < N; ++i) {
            if(board[p][i] == 1) {
                return false;
            }
        }

        for(int i = 0; i < N; ++i) {
            if(board[i][q] == 1) {
                return false;
            }
        }

        int i = p;
        int j = q;
        while(i >=0 && i < N && j >=0 && j < N) {
            if(board[i][j] == 1) {
                return false;
            }
            ++i;
            ++j;
        }

        i = p;
        j = q;
        while(i >=0 && i < N && j >=0 && j < N) {
            if(board[i][j] == 1) {
                return false;
            }
            --i;
            --j;
        }

        return true;
    }


    private boolean solveNQueens(int[][] board, int N) {
        if(N == 0) {
            return true;
        }
        int len = board.length;
        for(int i = 0; i < len; ++i) {
            for(int j = 0; j < len; ++j) {
                if (free(board, i, j)) {

                    System.out.println("Found position : " + i + ", " + j + " free for Queens = " + N);
                    board[i][j] = 1;

                    if (solveNQueens(board, N - 1)) {
                        return true;
                    }

                    System.out.println("Undo placing queen at : " + i + ", " + j + " for Queens = " + N);
                    board[i][j] = 0;
                }
            }
        }
        return false;
    }


    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

        // Write your code here
        Scanner s = new Scanner(System.in);
        String numQueens = s.nextLine();                 // Reading input from STDIN
        int N = Integer.parseInt(numQueens);

        int[][] board = new int[N][N];

        boolean foundPositions = new NQueens().solveNQueens(board, N);

        if(foundPositions) {
            System.out.println("YES");

            for(int i = 0; i < N; ++i) {
                for(int j = 0; j < N; ++j) {
                    System.out.printf(board[i][j] + " ");
                }
                System.out.println();
            }

        } else {
            System.out.println("NO");
        }
    }
}
