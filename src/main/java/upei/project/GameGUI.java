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

        statusLabel = new JLabel("Ready");
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        try {
            Image icon = Toolkit.getDefaultToolkit().getImage("resources/game_icon.png");
            mainFrame.setIconImage(icon);
        } catch (Exception e) {
            // ignore if no icon
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
            // no banner
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
            log("Welcome to the Fox, Crow & Bear Game!\n");
            log("----------------------------------------\n\n");
            log("This game is inspired by Snakes and Ladders, but with a twist:\n");
            log("Instead of snakes and ladders, you'll encounter special blocks:\n");
            log("* Fox blocks: Propels you forward, depending upon your previous move.\n");
            log("* Crow blocks: carry you forward to the end of the current row.\n");
            log("* Bear blocks: Challenges your power, you must defeat the bear to proceed forward.\n");
            log("* PowerUp blocks: Increases your power.\n");
            log("* PowerDown blocks: Decreases your power.\n");
            log("* Luck blocks: Improves your luck, hence increasing the dice size(7,8,9,10).\n");
            log("* Special blocks: Offer unique choices, build your own fate.\n\n");
            log("Your goal is still to reach block 100.\n");
            log("Use the menu above to start a manual game or run a specific number of strategies.\n\n");
            log("About Strategies:\n");
            log("* BOOST: Always choose to boost themselves according to the power levels, whenever encountering Special blocks.\n");
            log("* PUNCH_NEAREST: Always choose to punch the player nearest to the finish, whenever encountering Special blocks.\n");
            log("* BALANCED: Boost the player if distance from player in front >10, otherwise punch them.\n");
            log("Run multiple games to see which approach leads to more wins!\n\n");
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
        JMenuItem balancedStrategyItem = new JMenuItem("Run BALANCED Strategy");

        boostStrategyItem.addActionListener(e -> {
            int runs = Main.promptForStrategyRuns(GameGUI.getInstance(), "BOOST");
            if (runs == 0) {
                log(">> Strategy run canceled.\n");
                return;
            }
            log(">> Running BOOST strategy for " + runs + " games...\n");
            new Thread(() -> {
                StrategyResult result = StrategyRunner.runStrategyGames("BOOST", runs);
                SwingUtilities.invokeLater(() -> Main.displayExtendedResultsInGUI(GameGUI.getInstance(), result));
            }).start();
        });

        punchStrategyItem.addActionListener(e -> {
            int runs = Main.promptForStrategyRuns(GameGUI.getInstance(), "PUNCH_NEAREST");
            if (runs == 0) {
                log(">> Strategy run canceled.\n");
                return;
            }
            log(">> Running PUNCH_NEAREST strategy for " + runs + " games...\n");
            new Thread(() -> {
                StrategyResult result = StrategyRunner.runStrategyGames("PUNCH_NEAREST", runs);
                SwingUtilities.invokeLater(() -> Main.displayExtendedResultsInGUI(GameGUI.getInstance(), result));
            }).start();
        });

        balancedStrategyItem.addActionListener(e -> {
            int runs = Main.promptForStrategyRuns(GameGUI.getInstance(), "BALANCED");
            if (runs == 0) {
                log(">> Strategy run canceled.\n");
                return;
            }
            log(">> Running BALANCED strategy for " + runs + " games...\n");
            new Thread(() -> {
                StrategyResult result = StrategyRunner.runStrategyGames("BALANCED", runs);
                SwingUtilities.invokeLater(() -> Main.displayExtendedResultsInGUI(GameGUI.getInstance(), result));
            }).start();
        });

        strategyMenu.add(boostStrategyItem);
        strategyMenu.add(punchStrategyItem);
        strategyMenu.add(balancedStrategyItem);

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
            - BOOST: Always boost at special blocks.
            - PUNCH_NEAREST: Always punch at special blocks.
            - BALANCED: If behind the leading player by more than 10 blocks, boost; otherwise, punch.
            
            After running these strategy simulations multiple times, we track:
            - How many times each player won.
            - How many times each player executed the strategy action (BOOST or PUNCH).
            - Their win rate (percentage of games won).
            - Actions per win (how many times they had to boost/punch on average for each win).
            
            Interpreting the Results:
            - BOOST aims for fast forward progress.
            - PUNCH_NEAREST slows others down but may not advance you enough.
            - BALANCED adapts, boosting when behind and punching when not.
            
            In manual games, at the end you see each player's final stats,
            providing insight into how choices affected the outcome.
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
            GameBoard.currentGame = null;
            log("Welcome to the Fox, Crow & Bear Game!\n");
            log("----------------------------------------\n\n");
            log("This game is inspired by Snakes and Ladders, but with a twist:\n");
            log("Instead of snakes and ladders, you'll encounter special blocks:\n");
            log("* Fox blocks: Propels you forward, depending upon your previous move.\n");
            log("* Crow blocks: carry you forward to the end of the current row.\n");
            log("* Bear blocks: Challenges your power, you must defeat the bear to proceed forward.\n");
            log("* PowerUp blocks: Increases your power.\n");
            log("* PowerDown blocks: Decreases your power.\n");
            log("* Luck blocks: Improves your luck, hence increasing the dice size(7,8,9,10).\n");
            log("* Special blocks: Offer unique choices, build your own fate.\n\n");
            log("Your goal is still to reach block 100.\n");
            log("Use the menu above to start a manual game or run a specific number of strategies.\n\n");
            log("About Strategies:\n");
            log("* BOOST: Always choose to boost themselves according to the power levels, whenever encountering Special blocks.\n");
            log("* PUNCH_NEAREST: Always choose to punch the player nearest to the finish, whenever encountering Special blocks.\n");
            log("* BALANCED: Boost the player if distance from player in front >10, otherwise punch them.\n");
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