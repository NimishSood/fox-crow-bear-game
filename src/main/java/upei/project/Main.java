package upei.project;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Initialize the game board (static initialization)
        GameBoard gameBoard = new GameBoard();

        // Create players
        Player player1 = new Player("Aditya");
        Player player2 = new Player("Nimish");
        Player player3 = new Player("Harsh");
        Player player4 = new Player("Govind");

        // Let players take turns until one of them reaches the end (position 100)

        // initialize scanner for input
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;
        gameBoard.printBoard();

        // Main game loop
        while (!gameOver) {
            // Player 1's turn
            System.out.println("\n" + player1.getName() + "'s turn:");
            takeTurn(player1, scanner,gameBoard);

            // Check if player1 has reached position 100
            if (player1.getCurrentPosition().getPosition() == 100) {
                System.out.println(player1.getName() + " wins!");
                gameOver = true;
                break;
            }

            // Player 2's turn
            System.out.println("\n" + player2.getName() + "'s turn:");
            takeTurn(player2, scanner, gameBoard);

            // Check if player2 has reached position 100
            if (player2.getCurrentPosition().getPosition() == 100) {
                System.out.println(player2.getName() + " wins!");
                gameOver = true;
                break;
            }

            // Player 3's turn
            System.out.println("\n" + player3.getName() + "'s turn:");
            takeTurn(player3, scanner, gameBoard);

            // Check if player2 has reached position 100
            if (player3.getCurrentPosition().getPosition() == 100) {
                System.out.println(player3.getName() + " wins!");
                gameOver = true;
                break;
            }

            // Player 4's turn
            System.out.println("\n" + player4.getName() + "'s turn:");
            takeTurn(player4, scanner, gameBoard);

            // Check if player2 has reached position 100
            if (player4.getCurrentPosition().getPosition() == 100) {
                System.out.println(player4.getName() + " wins!");
                gameOver = true;
                break;
            }

        }

        scanner.close();
    }

    // Method to handle a player's turn
    private static void takeTurn(Player player, Scanner scanner, GameBoard board) {
        // Simulate dice roll (1 to 6)
        int diceRoll = (int) (Math.random() * 6) + 1;
        System.out.println(player.getName() + " rolls the dice: " + diceRoll);

        // Player takes their turn with the dice roll
        player.takeTurn(diceRoll, board);
    }
}
