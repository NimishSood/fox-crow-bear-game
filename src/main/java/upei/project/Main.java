package upei.project;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.Map;

/**
 * Main class runs manual games, prompts user, runs strategy experiments,
 * displays final stats and results.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameGUI gameGUI = GameGUI.getInstance();

            gameGUI.addManualGameAction(() -> {
                logToAll(gameGUI, "Starting manual game setup...\n");
                int playerCount = promptForPlayerCount(gameGUI);
                if (playerCount < 2) {
                    logToAll(gameGUI, "Not enough players to start a game.\n");
                    return;
                }

                String[] playerNames = promptForPlayerNames(gameGUI, playerCount);
                if (playerNames == null || playerNames.length == 0) {
                    logToAll(gameGUI, "Game setup canceled.\n");
                    return;
                }

                new Thread(() -> runManualGame(gameGUI, playerNames)).start();
            });

            gameGUI.addStrategyAction("BOOST", () -> {
                int runs = promptForStrategyRuns(gameGUI, "BOOST");
                if (runs == 0) {
                    logToAll(gameGUI, "Strategy run canceled.\n");
                    return;
                }
                logToAll(gameGUI, "Running BOOST strategy for " + runs + " games...\n");
                StrategyResult result = StrategyRunner.runStrategyGames("BOOST", runs);
                displayExtendedResultsInGUI(gameGUI, result);
            });

            gameGUI.addStrategyAction("PUNCH_NEAREST", () -> {
                int runs = promptForStrategyRuns(gameGUI, "PUNCH_NEAREST");
                if (runs == 0) {
                    logToAll(gameGUI, "Strategy run canceled.\n");
                    return;
                }
                logToAll(gameGUI, "Running PUNCH_NEAREST strategy for " + runs + " games...\n");
                StrategyResult result = StrategyRunner.runStrategyGames("PUNCH_NEAREST", runs);
                displayExtendedResultsInGUI(gameGUI, result);
            });

            gameGUI.addResetAction(() -> {
                gameGUI.reset();
                gameGUI.updateBoardDisplay();
            });
        });
    }

    private static int promptForPlayerCount(GameGUI gameGUI) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, "Enter the number of players (2-4):", "Player Count", JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                return 0;
            }
            try {
                int count = Integer.parseInt(input.trim());
                if (count >= 2 && count <= 4) {
                    return count;
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a number between 2 and 4.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
            }
        }
    }

    private static String[] promptForPlayerNames(GameGUI gameGUI, int playerCount) {
        String[] names = new String[playerCount];
        for (int i = 0; i < playerCount; i++) {
            String name = JOptionPane.showInputDialog(null, "Enter name for Player " + (i + 1) + ":", "Player Name", JOptionPane.QUESTION_MESSAGE);
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid name. Canceling game setup.");
                return null;
            }
            names[i] = name.trim();
        }
        return names;
    }

    static int promptForStrategyRuns(GameGUI gameGUI, String strategyName) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, "How many times do you want to run the " + strategyName + " strategy?", "Strategy Runs", JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                return 0;
            }
            try {
                int runs = Integer.parseInt(input.trim());
                if (runs > 0) {
                    return runs;
                } else {
                    JOptionPane.showMessageDialog(null, "Enter a positive number of runs.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a positive integer.");
            }
        }
    }

    private static void runManualGame(GameGUI gameGUI, String[] playerNames) {
        GameBoard gameBoard = new GameBoard();

        Player[] players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            players[i] = new Player(playerNames[i]);
            players[i].setPosition(GameBoard.getBlock(1));
        }
        gameBoard.players = players;

        gameGUI.updateBoardDisplay();
        boolean gameOver = false;

        logToAll(gameGUI, ">> A new game has begun!\n");

        Player winner = null;

        while (!gameOver) {
            for (Player player : players) {
                logToAll(gameGUI, "\n----------------------------------------\n");
                logToAll(gameGUI, player.getName() + "'s turn:\n");
                takeTurn(player, gameGUI);

                if (player.getCurrentPosition().getPosition() == 100) {
                    logToAll(gameGUI, player.getName() + " wins the game!\n");
                    winner = player;
                    gameOver = true;
                    break;
                }
            }
        }

        logToAll(gameGUI, "Game Over.\n");
        gameGUI.updateBoardDisplay();

        displayManualGameResults(gameGUI, players, winner);

        int choice = JOptionPane.showConfirmDialog(null, "Game Over! Would you like to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            SwingUtilities.invokeLater(() -> {
                GameGUI.getInstance().reset();
                int playerCount = promptForPlayerCount(gameGUI);
                if (playerCount < 2) {
                    logToAll(gameGUI, "Not enough players to start a new game.\n");
                    return;
                }
                String[] newNames = promptForPlayerNames(gameGUI, playerCount);
                if (newNames == null || newNames.length == 0) {
                    logToAll(gameGUI, "Game setup canceled.\n");
                    return;
                }
                logToAll(gameGUI, "Starting a new manual game...\n");
                new Thread(() -> runManualGame(gameGUI, newNames)).start();
            });
        } else {
            logToAll(gameGUI, "Thank you for playing!\n");
        }
    }

    private static void displayManualGameResults(GameGUI gameGUI, Player[] players, Player winner) {
        logToAll(gameGUI, "\n--- Manual Game Final Results ---\n");
        if (winner != null) {
            logToAll(gameGUI, "Winner: " + winner.getName() + "\n");
        }
        logToAll(gameGUI, "\nPlayer Stats (Final):\n");
        for (Player p : players) {
            int boostCount = p.getBoostCount();
            int punchCount = p.getPunchCount();
            logToAll(gameGUI, p.getName() + ": Position=" + p.getCurrentPosition().getPosition() +
                    ", Power=" + p.getPower() +
                    ", Luck=" + p.getLuck() + "\n" +
                    "  Boost Actions Performed: " + boostCount + "\n" +
                    "  Punch Actions Performed: " + punchCount + "\n\n");
        }

        logToAll(gameGUI, "These stats show how many times each player chose to boost or punch at special blocks during the manual game.\n");
        logToAll(gameGUI, "Use this info to understand the impact of those choices on the game's outcome.\n");
    }

    private static void takeTurn(Player player, GameGUI gameGUI) {
        int diceOutcome = diceCalculator(player);
        logToAll(gameGUI, player.getName() + " rolls the dice: " + diceOutcome + "\n");

        player.takeTurn(diceOutcome, GameBoard.currentGame);

        Block currentBlock = player.getCurrentPosition();
        logToAll(gameGUI, player.getName() + " moved to Block " + currentBlock.getPosition()
                + " - Type: " + currentBlock.getType() + "\n");

        gameGUI.updateBoardDisplay();

        if (currentBlock instanceof Special) {
            Special specialBlock = (Special) currentBlock;
            pauseGameForSpecialBlock(player, specialBlock, gameGUI);
        }
    }

    private static void pauseGameForSpecialBlock(Player player, Special specialBlock, GameGUI gameGUI) {
        CountDownLatch latch = new CountDownLatch(1);

        logToAll(gameGUI, "----------------------------------------\n");
        logToAll(gameGUI, "Special block! " + player.getName() + " has a choice to make!\n");
        logToAll(gameGUI, "Choose an action:\n1: Punch another player of your choice\n2: Boost yourself up\n");

        gameGUI.setInputCallback(userInput -> {
            if ("1".equals(userInput)) {
                promptForPlayerNameToPunch(gameGUI, player, specialBlock, latch);
            } else if ("2".equals(userInput)) {
                logToAll(gameGUI, player.getName() + " chose to boost themselves.\n");
                specialBlock.applyEffect(player, "BOOST");
                gameGUI.updateBoardDisplay();
                cleanupInputAndCountDown(gameGUI, latch);
            } else {
                logToAll(gameGUI, "Invalid choice. Turn skipped.\n");
                cleanupInputAndCountDown(gameGUI, latch);
            }
        });

        SwingUtilities.invokeLater(gameGUI::requestInputFocus);

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logToAll(gameGUI, "Game was interrupted while waiting for input.\n");
        }
    }

    private static void promptForPlayerNameToPunch(GameGUI gameGUI, Player currentPlayer, Special specialBlock, CountDownLatch latch) {
        Player[] players = GameBoard.currentGame.players;
        logToAll(gameGUI, "You chose to punch another player! Enter the player's name:\n");
        for (Player p : players) {
            logToAll(gameGUI, p.getName() + ", Position: " + p.getCurrentPosition().getPosition() + "\n");
        }

        gameGUI.setInputCallback(playerName -> {
            boolean success = specialBlock.punchPlayerByName(currentPlayer, playerName);
            if (!success) {
                logToAll(gameGUI, "Invalid player or you tried to punch yourself. Please enter a valid player name:\n");
                for (Player p : players) {
                    logToAll(gameGUI, p.getName() + ", Position: " + p.getCurrentPosition().getPosition() + "\n");
                }
                SwingUtilities.invokeLater(gameGUI::requestInputFocus);
            } else {
                gameGUI.updateBoardDisplay();
                cleanupInputAndCountDown(gameGUI, latch);
            }
        });

        SwingUtilities.invokeLater(gameGUI::requestInputFocus);
    }

    private static void cleanupInputAndCountDown(GameGUI gameGUI, CountDownLatch latch) {
        gameGUI.setInputCallback(null);
        latch.countDown();
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

    static void displayExtendedResultsInGUI(GameGUI gameGUI, StrategyResult result) {
        Map<String,Integer> wins = result.getWins();
        Map<String,Integer> usage = result.getUsage();
        int runs = result.getRuns();
        String strategy = result.getStrategy();

        logToAll(gameGUI, "\n--- Results for strategy: " + strategy + " ---\n");
        wins.forEach((player, w) -> logToAll(gameGUI, player + " won " + w + " times.\n"));
        logToAll(gameGUI, "\nStrategy run completed.\n");

        logToAll(gameGUI, "\n--- Strategy Usage Analysis ---\n");
        logToAll(gameGUI, "Strategy: " + strategy + "\n");

        String actionName = "BOOST".equalsIgnoreCase(strategy) ? "Boosts" : "Punches";
        logToAll(gameGUI, "We tracked how many times each player performed the strategy action (" + actionName + "):\n\n");

        for (String player : wins.keySet()) {
            int w = wins.get(player);
            int u = usage.get(player);
            double winRate = (double) w / runs * 100.0;
            String usagePerWin = (w > 0) ? String.format("%.2f", (double)u / w) : "N/A";

            logToAll(gameGUI, player + ": " + w + " wins out of " + runs + " (Win Rate: " + String.format("%.2f", winRate) + "%)\n");
            logToAll(gameGUI, "Total " + actionName + ": " + u + "\n");
            logToAll(gameGUI, "Actions per Win: " + usagePerWin + "\n\n");
        }
    }

    private static void logToGUI(GameGUI gameGUI, String message) {
        gameGUI.log(message);
    }

    private static void logToAll(GameGUI gameGUI, String message) {
        logToGUI(gameGUI, ">> " + message);
    }
}
