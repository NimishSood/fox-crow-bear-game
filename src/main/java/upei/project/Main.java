package upei.project;

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

        // Store players in an array for easier iteration
        Player[] players = {player1, player2, player3, player4};

        // Print the initial board with players at the start
        gameBoard.printBoardWithPlayers(players);

        // initialize scanner for input
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        // Main game loop
        while (!gameOver) {
            for (Player player : players) {
                System.out.println("\n" + player.getName() + "'s turn:");
                takeTurn(player, scanner, gameBoard);

                // Print the board after each player's turn
                gameBoard.printBoardWithPlayers(players);

                // Check if the player has reached position 100
                if (player.getCurrentPosition().getPosition() == 100) {
                    System.out.println(player.getName() + " wins!");
                    gameOver = true;
                    break;
                }
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
