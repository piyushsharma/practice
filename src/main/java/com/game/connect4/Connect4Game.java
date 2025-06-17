package com.game.connect4;

import java.util.Scanner;

public class Connect4Game {
    public static void main(String[] args) {
        // Create players
        Player player1 = new Player("Player 1", 'X');
        Player player2 = new Player("Player 2", 'O');

        // Create game service with standard 6x7 board
        GameService game = new GameService(6, 7, player1, player2);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Connect 4!");
        System.out.println("Enter column number (0-6) to make a move.");

        while (!game.isGameOver()) {
            // Display current state
            System.out.println("\n" + game.getBoard());
            System.out.println(game.getGameStatus());

            // Get move from current player
            System.out.print("Enter column (0-6): ");
            int column = scanner.nextInt();

            // Make move
            if (!game.makeMove(column)) {
                System.out.println("Invalid move! Try again.");
            }
        }

        // Game over
        System.out.println("\n" + game.getBoard());
        System.out.println(game.getGameStatus());
        scanner.close();
    }
} 