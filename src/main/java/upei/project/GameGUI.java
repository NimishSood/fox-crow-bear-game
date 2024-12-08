package upei.project;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.function.Consumer;

public class GameGUI {
    private static GameGUI instance;

    private final JFrame mainFrame;
    private final JPanel mainPanel;
    private final JTextArea logArea;
    private final JTextField inputField;
    private final JScrollPane logScrollPane;
    private final HashMap<String, Runnable> strategyActions;
    private Consumer<String> inputCallback;

    private JPanel boardPanel;
    private JLabel[][] boardLabels;
    private JLabel statusLabel;

    private javax.swing.Timer highlightTimer;
    private boolean highlightState = false;

    public static GameGUI getInstance() {
        if (instance == null) {
            instance = new GameGUI();
        }
        return instance;
    }

    private GameGUI() {
        strategyActions = new HashMap<>();
        mainFrame = new JFrame("Fox, Crow & Bear Game");
        mainPanel = new JPanel(new BorderLayout());
        logArea = new JTextArea();
        inputField = new JTextField();
        logScrollPane = new JScrollPane(logArea);

        initializeGUI();
    }

    private void initializeGUI() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 600);
        mainFrame.setResizable(false);

        // Put status at the bottom
        statusLabel = new JLabel("Ready");
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        try {
            Image icon = Toolkit.getDefaultToolkit().getImage("resources/game_icon.png");
            mainFrame.setIconImage(icon);
        } catch (Exception e) {
            // ignore
        }

        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        logArea.setBackground(Color.WHITE);
        logArea.setForeground(Color.BLACK);

        inputField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        inputField.setBackground(Color.WHITE);

        mainFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
        logScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        try {
            JLabel banner = new JLabel(new ImageIcon("resources/banner.png"));
            mainPanel.add(banner, BorderLayout.NORTH);
        } catch (Exception e) {
            // ignore if no banner
        }

        boardPanel = new JPanel(new GridLayout(10, 10));
        boardPanel.setPreferredSize(new Dimension(400, 400));
        boardLabels = new JLabel[10][10];
        Dimension cellSize = new Dimension(40, 40);

        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                JLabel cellLabel = new JLabel("", SwingConstants.CENTER);
                cellLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
                cellLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                cellLabel.setPreferredSize(cellSize);
                cellLabel.setOpaque(true);
                boardLabels[r][c] = cellLabel;
                boardPanel.add(cellLabel);
            }
        }

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, logScrollPane, boardPanel);
        splitPane.setDividerLocation(600);
        splitPane.setOneTouchExpandable(false);
        splitPane.setResizeWeight(0.0);

        mainPanel.add(splitPane, BorderLayout.CENTER);
        mainPanel.add(inputField, BorderLayout.NORTH);

        JMenuBar menuBar = createMenuBar();
        mainFrame.setJMenuBar(menuBar);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

        SwingUtilities.invokeLater(() -> {
            log("----------------------------------------\n");
            log("Welcome to the Fox, Crow & Bear Game!\n");
            log("----------------------------------------\n\n");
            log("This game is inspired by Snakes and Ladders, but with a twist:\n");
            log("Instead of snakes and ladders, you'll encounter special blocks:\n");
            log("* Fox blocks: propel you forward based on your last dice roll.\n");
            log("* Crow blocks: carry you forward to the end of the current row.\n");
            log("* Bear blocks: challenge your power; win and move ahead, lose and retreat!\n");
            log("* PowerUp blocks: increase your power, making duels easier.\n");
            log("* PowerDown blocks: decrease your power, watch out!\n");
            log("* Luck blocks: improve your luck, increasing your dice range.\n");
            log("* Special blocks: offer choices like boosting forward or punching other players back.\n\n");
            log("Just like in Snakes and Ladders, your goal is to reach block 100.\n");
            log("Use the menu above to start a manual game, or run a strategy.\n");
            log("Have fun exploring these unique twists!\n\n");

            log("About Strategies:\n");
            log("* BOOST: When hitting a special block, the player will always choose to boost forward.\n");
            log("* PUNCH_NEAREST: When hitting a special block, the player will always punch the nearest player.\n");
            log("Running strategies simulates multiple automated games to see who wins most often.\n\n");
        });

        inputField.addActionListener(e -> handleUserInput());
    }

    private void handleUserInput() {
        String userInput = inputField.getText().trim();
        if (inputCallback != null) {
            stopHighlightingInputField();
            inputCallback.accept(userInput);
            inputField.setText("");
        } else {
            log("[INFO] No action required for input: " + userInput + "\n");
        }
    }

    public void updateStatus(String message) {
        SwingUtilities.invokeLater(() -> statusLabel.setText(message));
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        JMenuItem manualGameItem = new JMenuItem("Play Manual Game");
        JMenuItem resetGameItem = new JMenuItem("Reset Game");
        manualGameItem.addActionListener(e -> triggerAction("MANUAL_GAME"));
        resetGameItem.addActionListener(e -> triggerAction("RESET_GAME"));

        gameMenu.add(manualGameItem);
        gameMenu.add(resetGameItem);

        JMenu strategyMenu = new JMenu("Strategy");
        JMenuItem boostStrategyItem = new JMenuItem("Run BOOST Strategy");
        JMenuItem punchStrategyItem = new JMenuItem("Run PUNCH_NEAREST Strategy");
        boostStrategyItem.addActionListener(e -> triggerAction("BOOST"));
        punchStrategyItem.addActionListener(e -> triggerAction("PUNCH_NEAREST"));

        strategyMenu.add(boostStrategyItem);
        strategyMenu.add(punchStrategyItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem strategyInfoItem = new JMenuItem("Strategy Info");
        strategyInfoItem.addActionListener(e -> showStrategyInfoDialog());
        helpMenu.add(strategyInfoItem);

        JMenuItem showStatsItem = new JMenuItem("Show Current Players/Stats");
        showStatsItem.addActionListener(e -> showCurrentStats());
        helpMenu.add(showStatsItem);

        menuBar.add(gameMenu);
        menuBar.add(strategyMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private void showCurrentStats() {
        if (GameBoard.currentGame == null || GameBoard.currentGame.players == null) {
            JOptionPane.showMessageDialog(mainFrame, "No game is currently running.", "Players/Stats", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Player[] players = GameBoard.currentGame.players;
        StringBuilder sb = new StringBuilder("Current Players/Stats:\n\n");
        for (Player p : players) {
            sb.append(p.getName())
                    .append(": Position=").append(p.getCurrentPosition().getPosition())
                    .append(", Power=").append(p.getPower())
                    .append(", Luck=").append(p.getLuck())
                    .append(", BoostActions=").append(p.getBoostCount())
                    .append(", PunchActions=").append(p.getPunchCount())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(mainFrame, sb.toString(), "Players/Stats", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showStrategyInfoDialog() {
        String info = """
            Strategy and Stats Info:
            
            In this game, you can run multiple automated games using a chosen strategy:
            - BOOST Strategy: Players always boost at special blocks.
            - PUNCH_NEAREST Strategy: Players always punch at special blocks.
            
            After running these strategy simulations multiple times, we track:
            - How many times each player won.
            - How many times each player executed the strategy action (BOOST or PUNCH).
            - Their win rate (percentage of games won).
            - Actions per win (how many times they had to boost/punch on average for each win).
            
            Interpreting the Results:
            - A higher win count and high win rate suggests a strategy works well for that player.
            - A high number of actions (boosts/punches) might indicate reliance on the strategy.
            - Actions per win shows efficiency: fewer actions per win might mean the strategy is effective.
            
            In manual games (where users choose actions at special blocks), at the end of the game,
            you'll also see each player's final stats, including how many times they boosted or punched,
            their final power, and luck levels.
            
            Use this info to understand how strategic choices influence outcomes and performance.
            """;
        JOptionPane.showMessageDialog(mainFrame, info, "Strategy Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void addManualGameAction(Runnable action) {
        strategyActions.put("MANUAL_GAME", action);
    }

    public void addStrategyAction(String strategy, Runnable action) {
        strategyActions.put(strategy, action);
    }

    public void addResetAction(Runnable action) {
        strategyActions.put("RESET_GAME", action);
    }

    private void triggerAction(String actionKey) {
        Runnable action = strategyActions.get(actionKey);
        if (action != null) {
            action.run();
        }
    }

    public void log(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append(message);
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }

    public void reset() {
        SwingUtilities.invokeLater(() -> {
            logArea.setText("");
            log("----------------------------------------\n");
            log("Game reset. You can start a new game or test a strategy.\n");
            log("----------------------------------------\n\n");
            // After reset, clear currentGame to ensure a fresh start
            GameBoard.currentGame = null;
            log("Welcome to the Fox, Crow & Bear Game!\n");
            log("----------------------------------------\n\n");
            log("This game is inspired by Snakes and Ladders, but with a twist:\n");
            log("Instead of snakes and ladders, you'll encounter special blocks:\n");
            log("* Fox blocks: propel you forward based on your last dice roll.\n");
            log("* Crow blocks: carry you forward to the end of the current row.\n");
            log("* Bear blocks: challenge your power; win and move ahead, lose and retreat!\n");
            log("* PowerUp blocks: increase your power.\n");
            log("* PowerDown blocks: decrease your power.\n");
            log("* Luck blocks: improve your luck.\n");
            log("* Special blocks: offer unique choices.\n\n");
            log("Your goal is still to reach block 100.\n");
            log("Use the menu above to start a manual game or run a strategy.\n\n");
            // Strategy reminder after reset
            log("About Strategies:\n");
            log("* BOOST: Always choose to boost on special blocks.\n");
            log("* PUNCH_NEAREST: Always choose to punch on special blocks.\n");
            log("Run multiple games to see which approach leads to more wins!\n\n");
            updateBoardDisplay();
        });
    }

    public void setInputCallback(Consumer<String> callback) {
        this.inputCallback = callback;
        if (this.inputCallback != null) {
            startHighlightingInputField();
        } else {
            stopHighlightingInputField();
        }
    }

    public void requestInputFocus() {
        SwingUtilities.invokeLater(() -> inputField.requestFocusInWindow());
    }

    private void startHighlightingInputField() {
        if (highlightTimer != null && highlightTimer.isRunning()) {
            return;
        }
        highlightTimer = new javax.swing.Timer(500, e -> {
            highlightState = !highlightState;
            inputField.setBackground(highlightState ? Color.YELLOW : Color.WHITE);
        });
        highlightTimer.start();
    }

    private void stopHighlightingInputField() {
        if (highlightTimer != null) {
            highlightTimer.stop();
        }
        inputField.setBackground(Color.WHITE);
    }

    public void updateBoardDisplay() {
        SwingUtilities.invokeLater(() -> {
            if (GameBoard.currentGame == null) {
                // If no current game, possibly show empty or default?
                // For now, do nothing which means board stays as is without players.
                return;
            }

            for (int row = 0; row < 10; row++) {
                for (int col = 0; col < 10; col++) {
                    Block block = GameBoard.getBlock(row * 10 + col + 1);
                    if (block != null) {
                        String type = block.getType();
                        String playersOnBlock = block.getPlayersInitials();

                        if (!playersOnBlock.isEmpty()) {
                            boardLabels[row][col].setText(playersOnBlock);
                            boardLabels[row][col].setToolTipText(type);
                        } else {
                            boardLabels[row][col].setText(type.substring(0, 1));
                            boardLabels[row][col].setToolTipText(type);
                        }

                        Color bgColor = switch (type.toLowerCase()) {
                            case "fox" -> Color.ORANGE;
                            case "crow" -> Color.LIGHT_GRAY;
                            case "bear" -> new Color(139,69,19);
                            case "powerup" -> Color.GREEN;
                            case "powerdown" -> Color.RED;
                            case "lucky place" -> Color.CYAN;
                            case "special" -> Color.MAGENTA;
                            case "regular" -> Color.WHITE;
                            default -> Color.WHITE;
                        };
                        boardLabels[row][col].setBackground(bgColor);

                    } else {
                        boardLabels[row][col].setText("");
                        boardLabels[row][col].setToolTipText("");
                        boardLabels[row][col].setBackground(Color.WHITE);
                    }
                }
            }
        });
    }
}
