package upei.project;

import java.util.HashMap;
import java.util.function.Function;

public class GameBoard {
    private static Block[][] board = new Block[10][10];
    public Player[] players;
    public static GameBoard currentGame;

    public GameBoard() {
        currentGame = this;
        initializeBoard();
    }

    public GameBoard(Player[] players) {
        currentGame = this;
        initializeBoard();
        this.players = players;
    }

    public static Object[][] getBoard() {
        return board;
    }

    private static final HashMap<Integer, Function<Integer, Block>> BLOCK_CONFIG = new HashMap<>();

    {
        // Early game
        BLOCK_CONFIG.put(3, PowerUp::new);
        BLOCK_CONFIG.put(5, Luck::new);
        BLOCK_CONFIG.put(6, pos -> new Special(pos, this));
        BLOCK_CONFIG.put(10, Fox::new);
        BLOCK_CONFIG.put(12, Crow::new);
        BLOCK_CONFIG.put(15, PowerDown::new);
        BLOCK_CONFIG.put(18, pos -> new Special(pos, this));

        // Mid game
        BLOCK_CONFIG.put(23, Luck::new);
        BLOCK_CONFIG.put(25, PowerUp::new);
        BLOCK_CONFIG.put(27, Crow::new);
        BLOCK_CONFIG.put(30, pos -> new Special(pos, this));
        BLOCK_CONFIG.put(33, Fox::new);
        BLOCK_CONFIG.put(35, PowerDown::new);
        BLOCK_CONFIG.put(38, pos -> new Special(pos, this));
        BLOCK_CONFIG.put(40, PowerUp::new);
        BLOCK_CONFIG.put(91, PowerUp::new);
        BLOCK_CONFIG.put(93, PowerUp::new);
        BLOCK_CONFIG.put(96, PowerUp::new);
        BLOCK_CONFIG.put(45, Crow::new);
        BLOCK_CONFIG.put(48, Luck::new);
        BLOCK_CONFIG.put(50, pos -> new Special(pos, this));
        BLOCK_CONFIG.put(58, pos -> new Bear(pos, this));
        BLOCK_CONFIG.put(59, Fox::new);

        // Late game
        BLOCK_CONFIG.put(65, PowerUp::new);
        BLOCK_CONFIG.put(67, pos -> new Bear(pos, this));
        BLOCK_CONFIG.put(70, PowerDown::new);
        BLOCK_CONFIG.put(75, Crow::new);
        BLOCK_CONFIG.put(78, Luck::new);
        BLOCK_CONFIG.put(79, pos -> new Special(pos, this));
        BLOCK_CONFIG.put(85, PowerUp::new);
        BLOCK_CONFIG.put(87, pos -> new Special(pos, this));
        BLOCK_CONFIG.put(89, Fox::new);
        BLOCK_CONFIG.put(95, Crow::new);
        BLOCK_CONFIG.put(98, pos -> new Special(pos, this));
        BLOCK_CONFIG.put(99, PowerUp::new);
        BLOCK_CONFIG.put(100, pos -> new Bear(pos, this)); // Final challenge
    }

    private void initializeBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                int position = row * 10 + (col + 1);
                Block block = BLOCK_CONFIG.getOrDefault(position,
                        pos -> new Block(pos, "Regular")).apply(position);
                board[row][col] = block;
            }
        }
    }

    public static Block getBlock(int position) {
        if (position == 101) {
            return board[9][9];
        }
        if (position >= 1 && position <= 100) {
            int row = (position - 1) / 10;
            int col = (position - 1) % 10;
            return board[row][col];
        } else {
            return null;
        }
    }

    public void printBoardWithPlayers(Player[] players) {
        GameGUI.getInstance().log(">> Game Board:\n");
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Block block = board[row][col];
                String blockType = block.getType();
                String playersOnBlock = block.getPlayersInitials();
                if (playersOnBlock.isEmpty()) {
                    GameGUI.getInstance().log(blockType.substring(0, 1) + "\t");
                } else {
                    GameGUI.getInstance().log(playersOnBlock + "\t");
                }
            }
            GameGUI.getInstance().log("\n");
        }
    }
}
