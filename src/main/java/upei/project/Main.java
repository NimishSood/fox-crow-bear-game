package upei.project;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameGUI gameGUI = new GameGUI();

            // Add manual game functionality
            gameGUI.addManualGameAction(() -> {
                Special.setGlobalStrategy(null); // Ensure no strategy is active
                gameGUI.log("Redirecting to terminal for manual gameplay...\n");
                runManualGame();
            });

            // Add strategy functionality
            gameGUI.addStrategyAction("BOOST", () -> {
                gameGUI.log("Running BOOST strategy for 20 games...\n");
                var results = StrategyRunner.runStrategyGames("BOOST");
                displayResultsInGUI(gameGUI, "BOOST", results);
            });

            gameGUI.addStrategyAction("PUNCH_NEAREST", () -> {
                gameGUI.log("Running PUNCH_NEAREST strategy for 20 games...\n");
                var results = StrategyRunner.runStrategyGames("PUNCH_NEAREST");
                displayResultsInGUI(gameGUI, "PUNCH_NEAREST", results);
            });

            // Add reset functionality
            gameGUI.addResetAction(gameGUI::reset);
        });
    }

    private static void runManualGame() {
        GameBoard gameBoard = new GameBoard();

        // Create players
        Player player1 = new Player("Aditya");
        Player player2 = new Player("Nimish");
        Player player3 = new Player("Harsh");
        Player player4 = new Player("Govind");

        // Store players in an array
        Player[] players = {player1, player2, player3, player4};
        gameBoard.players = players;

        // Print the initial board with players at the start
        gameBoard.printBoardWithPlayers(players);

        boolean gameOver = false;

        // Main game loop
        while (!gameOver) {
            for (Player player : players) {
                System.out.println("\n" + player.getName() + "'s turn:");
                takeTurn(player, gameBoard);

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
        System.out.println("Game Over.\n");
    }

    private static void takeTurn(Player player, GameBoard board) {
        int diceOutcome = diceCalculator(player);
        System.out.println(player.getName() + " rolls the dice: " + diceOutcome);
        player.takeTurn(diceOutcome, board);
    }

    private static int diceCalculator(Player player) {
        return switch (player.getLuck()) {
            case 1 -> (int) (Math.random() * 6) + 1;
            case 2 -> (int) (Math.random() * 7) + 1;
            case 3 -> (int) (Math.random() * 8) + 1;
            case 4 -> (int) (Math.random() * 9) + 1;
            case 5 -> (int) (Math.random() * 10) + 1;
            default -> 1;
        };
    }

    private static void displayResultsInGUI(GameGUI gameGUI, String strategy, java.util.Map<String, Integer> results) {
        gameGUI.log("\n--- Results for strategy: " + strategy + " ---\n");
        results.forEach((player, wins) -> gameGUI.log(player + " won " + wins + " times.\n"));
        gameGUI.log("\nStrategy run completed.\n");
    }
}
