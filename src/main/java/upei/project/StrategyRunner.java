package upei.project;

import java.util.HashMap;
import java.util.Map;

public class StrategyRunner {
    private static String globalStrategy = null;

    public static void setGlobalStrategy(String strategy) {
        globalStrategy = strategy;
        Special.setGlobalStrategy(strategy);
    }

    public static String getGlobalStrategy() {
        return globalStrategy;
    }

    // Now returns StrategyResult instead of just a Map
    public static StrategyResult runStrategyGames(String strategy, int runs) {
        // Wins tracking
        HashMap<String, Integer> results = new HashMap<>();
        results.put("Aditya", 0);
        results.put("Nimish", 0);
        results.put("Harsh", 0);
        results.put("Govind", 0);

        // Usage tracking
        HashMap<String, Integer> usageCounts = new HashMap<>();
        usageCounts.put("Aditya", 0);
        usageCounts.put("Nimish", 0);
        usageCounts.put("Harsh", 0);
        usageCounts.put("Govind", 0);

        setGlobalStrategy(strategy);

        for (int i = 1; i <= runs; i++) {
            Player[] players = runSingleGamePlayers();
            String winner = determineWinner(players);

            // Update wins
            results.put(winner, results.getOrDefault(winner, 0) + 1);

            // Update usage based on strategy
            for (Player p : players) {
                int count = 0;
                if ("BOOST".equalsIgnoreCase(strategy)) {
                    count = p.getBoostCount();
                } else if ("PUNCH_NEAREST".equalsIgnoreCase(strategy)) {
                    count = p.getPunchCount();
                }
                usageCounts.put(p.getName(), usageCounts.get(p.getName()) + count);
            }
        }

        setGlobalStrategy(null);

        // Return everything in a StrategyResult object
        return new StrategyResult(strategy, runs, results, usageCounts);
    }


    private static Player[] runSingleGamePlayers() {
        GameBoard gameBoard = new GameBoard();

        Player player1 = new Player("Aditya");
        Player player2 = new Player("Nimish");
        Player player3 = new Player("Harsh");
        Player player4 = new Player("Govind");

        Player[] players = {player1, player2, player3, player4};
        // Set initial positions
        for (Player p : players) {
            p.setPosition(GameBoard.getBlock(1));
        }

        gameBoard.players = players;

        while (true) {
            for (Player player : players) {
                int diceOutcome = diceCalculator(player);
                player.takeTurn(diceOutcome, gameBoard);

                if (player.getCurrentPosition().getPosition() == 100) {
                    return players;
                }
            }
        }
    }

    private static String determineWinner(Player[] players) {
        for (Player p : players) {
            if (p.getCurrentPosition().getPosition() == 100) {
                return p.getName();
            }
        }
        return null;
    }

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
