package upei.project;

import java.util.HashMap;
import java.util.Map;

public class StrategyRunner {
    private static String globalStrategy = null; // Default to manual mode

    public static final int GAME_RUNS = 20;

    /**
     * Sets the global strategy for the game.
     * This will influence how the Special block behaves during gameplay.
     *
     * @param strategy The strategy to set ("BOOST", "PUNCH_NEAREST", or null for manual mode)
     */
    public static void setGlobalStrategy(String strategy) {
        globalStrategy = strategy;
        Special.setGlobalStrategy(strategy);
        if (strategy == null) {
            System.out.println("Strategy set to manual mode.");
        } else {
            System.out.println("Global strategy set to: " + strategy);
        }
    }

    /**
     * Gets the current global strategy.
     *
     * @return The current global strategy.
     */
    public static String getGlobalStrategy() {
        return globalStrategy;
    }

    /**
     * Runs automated games based on the specified strategy and returns results.
     *
     * @param strategy The strategy to use for the games ("BOOST" or "PUNCH_NEAREST").
     * @return A map containing the win counts for each player.
     */
    public static Map<String, Integer> runStrategyGames(String strategy) {
        // Remove existing `System.out.println` and collect output for GUI
        StringBuilder output = new StringBuilder();
        output.append("\n--- Running ").append(GAME_RUNS).append(" games with strategy: ").append(strategy).append(" ---\n");

        HashMap<String, Integer> results = new HashMap<>();
        results.put("Aditya", 0);
        results.put("Nimish", 0);
        results.put("Harsh", 0);
        results.put("Govind", 0);

        setGlobalStrategy(strategy);

        for (int i = 1; i <= GAME_RUNS; i++) {
            String winner = runSingleGame();
            results.put(winner, results.get(winner) + 1);
            output.append("Game ").append(i).append(" Winner: ").append(winner).append("\n");
        }

        output.append("\n--- Results for strategy: ").append(strategy).append(" ---\n");
        results.forEach((player, wins) ->
                output.append(player).append(" won ").append(wins).append(" times.\n")
        );

        setGlobalStrategy(null); // Reset to manual mode
        System.out.println(output.toString()); // Optional for debugging
        return results;
    }

    /**
     * Runs a single game with the current strategy.
     *
     * @return The name of the winning player.
     */
    private static String runSingleGame() {
        GameBoard gameBoard = new GameBoard();

        // Create players
        Player player1 = new Player("Aditya");
        Player player2 = new Player("Nimish");
        Player player3 = new Player("Harsh");
        Player player4 = new Player("Govind");

        // Add players to the game
        Player[] players = {player1, player2, player3, player4};
        gameBoard.players = players;

        // Play the game
        while (true) {
            for (Player player : players) {
                int diceOutcome = diceCalculator(player);
                player.takeTurn(diceOutcome, gameBoard);

                // Check if the player has reached the goal
                if (player.getCurrentPosition().getPosition() == 100) {
                    return player.getName();
                }
            }
        }
    }


    /**
     * Simulates a dice roll based on the player's luck level.
     *
     * @param player The player rolling the dice.
     * @return The outcome of the dice roll.
     */
    private static int diceCalculator(Player player) {
        switch (player.getLuck()) {
            case 1:
                return (int) (Math.random() * 6) + 1;
            case 2:
                return (int) (Math.random() * 7) + 1;
            case 3:
                return (int) (Math.random() * 8) + 1;
            case 4:
                return (int) (Math.random() * 9) + 1;
            case 5:
                return (int) (Math.random() * 10) + 1;
            default:
                return 1;
        }
    }
}
