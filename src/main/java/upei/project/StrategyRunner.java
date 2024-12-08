package upei.project;

import java.util.HashMap;
import java.util.Map;

/**
 * Runs multiple strategy games and returns a StrategyResult with wins and usage stats.
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

        Map<String,Integer> usage = new HashMap<>();
        usage.put("Aditya",0);
        usage.put("Nimish",0);
        usage.put("Harsh",0);
        usage.put("Govind",0);

        setGlobalStrategy(strategy);

        for (int i = 1; i <= runs; i++) {
            Player[] players = runSingleGamePlayers();
            String winner = determineWinner(players);
            results.put(winner, results.get(winner) + 1);

            // Accumulate usage based on chosen strategy
            for (Player p : players) {
                int count = 0;
                if ("BOOST".equalsIgnoreCase(strategy)) {
                    count = p.getBoostCount();
                } else if ("PUNCH_NEAREST".equalsIgnoreCase(strategy)) {
                    count = p.getPunchCount();
                }
                usage.put(p.getName(), usage.get(p.getName()) + count);
            }
        }

        setGlobalStrategy(null);
        return new StrategyResult(strategy, runs, results, usage);
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

        Player[] players = {player1, player2, player3, player4};
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
