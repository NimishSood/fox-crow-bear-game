package upei.project;

import java.util.HashMap;

public class StrategyRunner {
    private static final int GAME_RUNS = 20;

    public static void main(String[] args) {
        // Strategies to test
        String[] strategies = {"BOOST", "PUNCH_NEAREST"};

        for (String strategy : strategies) {
            System.out.println("\n--- Running 20 games with strategy: " + strategy + " ---");

            // Store results
            HashMap<String, Integer> results = new HashMap<>();
            results.put("Aditya", 0);
            results.put("Nimish", 0);
            results.put("Harsh", 0);
            results.put("Govind", 0);

            // Set strategy for Special block
            Special.setGlobalStrategy(strategy);

            // Run 20 games
            for (int i = 1; i <= GAME_RUNS; i++) {
                String winner = runSingleGame(strategy);
                results.put(winner, results.get(winner) + 1);
                System.out.println("Game " + i + " Winner: " + winner);
            }

            // Print final results
            System.out.println("\n--- Results for strategy: " + strategy + " ---");
            for (String player : results.keySet()) {
                System.out.println(player + " won " + results.get(player) + " times.");
            }
        }
    }

    private static String runSingleGame(String strategy) {
        // Initialize the game board
        GameBoard gameBoard = new GameBoard();

        // Create players
        Player player1 = new Player("Aditya");
        Player player2 = new Player("Nimish");
        Player player3 = new Player("Harsh");
        Player player4 = new Player("Govind");

        // Store players in an array
        Player[] players = {player1, player2, player3, player4};
        gameBoard.players = players;

        // Run the game
        boolean gameOver = false;
        while (!gameOver) {
            for (Player player : players) {
                int diceOutcome = diceCalculator(player);
                player.takeTurn(diceOutcome, gameBoard);

                // Check if the player has reached position 100
                if (player.getCurrentPosition().getPosition() == 100) {
                    return player.getName();
                }
            }
        }
        return null; // This should never happen
    }

    private static int diceCalculator(Player player) {
        int diceRoll = 0;
        switch (player.getLuck()) {
            case 1:
                diceRoll = (int) (Math.random() * 6) + 1;
                break;
            case 2:
                diceRoll = (int) (Math.random() * 7) + 1;
                break;
            case 3:
                diceRoll = (int) (Math.random() * 8) + 1;
                break;
            case 4:
                diceRoll = (int) (Math.random() * 9) + 1;
                break;
            case 5:
                diceRoll = (int) (Math.random() * 10) + 1;
                break;
        }
        return diceRoll;
    }
}
