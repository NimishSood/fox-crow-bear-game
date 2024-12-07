package upei.project;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GameGUI {

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JTextArea logArea;
    private JScrollPane logScrollPane;
    private HashMap<String, Runnable> strategyActions;

    public GameGUI() {
        strategyActions = new HashMap<>();
        initializeGUI();
    }

    private void initializeGUI() {
        // Main Frame
        mainFrame = new JFrame("Snake and Ladders - Strategy Game");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);

        // Main Panel
        mainPanel = new JPanel(new BorderLayout());

        // Log Area
        logArea = new JTextArea();
        logArea.setEditable(false);
        logScrollPane = new JScrollPane(logArea);

        // Add log area to main panel
        mainPanel.add(logScrollPane, BorderLayout.CENTER);

        // Menu Bar
        JMenuBar menuBar = createMenuBar();
        mainFrame.setJMenuBar(menuBar);

        // Add main panel to frame
        mainFrame.add(mainPanel);

        // Show the frame
        mainFrame.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Game Menu
        JMenu gameMenu = new JMenu("Game");
        JMenuItem manualGameItem = new JMenuItem("Play Manual Game");
        JMenuItem resetGameItem = new JMenuItem("Reset Game");

        manualGameItem.addActionListener(e -> triggerAction("MANUAL_GAME"));
        resetGameItem.addActionListener(e -> triggerAction("RESET_GAME"));

        gameMenu.add(manualGameItem);
        gameMenu.add(resetGameItem);

        // Strategy Menu
        JMenu strategyMenu = new JMenu("Strategy");
        JMenuItem boostStrategyItem = new JMenuItem("Run BOOST Strategy");
        JMenuItem punchStrategyItem = new JMenuItem("Run PUNCH_NEAREST Strategy");

        boostStrategyItem.addActionListener(e -> triggerAction("BOOST"));
        punchStrategyItem.addActionListener(e -> triggerAction("PUNCH_NEAREST"));

        strategyMenu.add(boostStrategyItem);
        strategyMenu.add(punchStrategyItem);

        menuBar.add(gameMenu);
        menuBar.add(strategyMenu);

        return menuBar;
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
        logArea.append(message);
        logArea.setCaretPosition(logArea.getDocument().getLength()); // Scroll to the latest message
    }

    public void reset() {
        logArea.setText(""); // Clear the log
    }
}
