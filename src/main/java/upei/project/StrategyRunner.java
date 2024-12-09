package upei.project;

import java.util.HashMap;
import java.util.Map;

/**
 * Runs multiple strategy games and returns a StrategyResult with wins and usage stats.
 * Now tracks boost and punch usage separately.
 */
public class StrategyRunner {
    private static String globalStrategy = null;

    public static void setGlobalStrategy(String strategy) {
        globalStrategy = strategy;
        Special.setGlobalStrategy(strategy);
    }

    public static String getGlobalStrategy() {
        return globalStrategy;
    }

    public static StrategyResult runStrategyGames(String strategy, int runs) {
        Map<String,Integer> results = new HashMap<>();
        results.put("Aditya",0);
        results.put("Nimish",0);
        results.put("Harsh",0);
        results.put("Govind",0);

        // usage: total actions used by that strategy (if single action)
        Map<String,Integer> usage = new HashMap<>();
        Map<String,Integer> usageBoost = new HashMap<>();
        Map<String,Integer> usagePunch = new HashMap<>();
        for (String p : results.keySet()) {
            usage.put(p,0);
            usageBoost.put(p,0);
            usagePunch.put(p,0);
        }

        setGlobalStrategy(strategy);

        for (int i = 1; i <= runs; i++) {
            Player[] players = runSingleGamePlayers();
            String winner = determineWinner(players);
            results.put(winner, results.getOrDefault(winner, 0) + 1);

            // Record usage based on strategy:
            for (Player p : players) {
                int boostCount = p.getBoostCount();
                int punchCount = p.getPunchCount();

                if ("BOOST".equalsIgnoreCase(strategy)) {
                    // Only boosts matter
                    usageBoost.put(p.getName(), usageBoost.get(p.getName()) + boostCount);
                } else if ("PUNCH_NEAREST".equalsIgnoreCase(strategy)) {
                    // Only punches matter
                    usagePunch.put(p.getName(), usagePunch.get(p.getName()) + punchCount);
                } else if ("BALANCED".equalsIgnoreCase(strategy)) {
                    // BALANCED does both
                    usageBoost.put(p.getName(), usageBoost.get(p.getName()) + boostCount);
                    usagePunch.put(p.getName(), usagePunch.get(p.getName()) + punchCount);
                }
            }
        }

        setGlobalStrategy(null);

        // For single-action strategies, usage = usageBoost or usagePunch as appropriate
        // For BALANCED, usage can remain as zero or sum of both, but we have separate maps anyway.
        if ("BOOST".equalsIgnoreCase(strategy)) {
            // usage map is basically usageBoost
            for (String pl : results.keySet()) {
                usage.put(pl, usageBoost.get(pl));
            }
        } else if ("PUNCH_NEAREST".equalsIgnoreCase(strategy)) {
            for (String pl : results.keySet()) {
                usage.put(pl, usagePunch.get(pl));
            }
        } else if ("BALANCED".equalsIgnoreCase(strategy)) {

            for (String pl : results.keySet()) {
                usage.put(pl, usageBoost.get(pl) + usagePunch.get(pl));
            }
        }

        return new StrategyResult(strategy, runs, results, usage, usageBoost, usagePunch);
    }

    @Deprecated
    public static Map<String,Integer> runStrategyGames(String strategy) {
        return runStrategyGames(strategy, 20).getWins();
    }

    private static Player[] runSingleGamePlayers() {
        GameBoard gameBoard = new GameBoard();

        Player player1 = new Player("Aditya");
        Player player2 = new Player("Nimish");
        Player player3 = new Player("Harsh");
        Player player4 = new Player("Govind");

        for (Player p : new Player[]{player1, player2, player3, player4}) {
            p.setPosition(GameBoard.getBlock(1));
        }

        gameBoard.players = new Player[]{player1, player2, player3, player4};

        while (true) {
            for (Player player : gameBoard.players) {
                int diceOutcome = diceCalculator(player);
                player.takeTurn(diceOutcome, gameBoard);
                if (player.getCurrentPosition().getPosition() == 100) {
                    return gameBoard.players;
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
