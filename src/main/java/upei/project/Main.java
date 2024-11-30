package upei.project;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Initialize the game board (static initialization)
        GameBoard gameBoard = new GameBoard();

        // Create players
        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");

        // Let players take turns until one of them reaches the end (position 100)
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        // Main game loop
        while (!gameOver) {
            // Player 1's turn
            System.out.println("\n" + player1.getName() + "'s turn:");
            takeTurn(player1, scanner);

            // Check if player1 has reached position 100
            if (player1.getCurrentPosition().getPosition() == 100) {
                System.out.println(player1.getName() + " wins!");
                gameOver = true;
                break;
            }

            // Player 2's turn
            System.out.println("\n" + player2.getName() + "'s turn:");
            takeTurn(player2, scanner);

            // Check if player2 has reached position 100
            if (player2.getCurrentPosition().getPosition() == 100) {
                System.out.println(player2.getName() + " wins!");
                gameOver = true;
                break;
            }
        }

        scanner.close();
    }

    // Method to handle a player's turn
    private static void takeTurn(Player player, Scanner scanner) {
        // Simulate dice roll (1 to 6)
        int diceRoll = (int) (Math.random() * 6) + 1;
        System.out.println(player.getName() + " rolls the dice: " + diceRoll);

        // Player takes their turn with the dice roll
        player.takeTurn(diceRoll);
    }
}
