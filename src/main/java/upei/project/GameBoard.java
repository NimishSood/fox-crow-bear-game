package upei.project;

import java.util.HashMap;
import java.util.function.Function;

public class GameBoard {
    private static  Block[][] board = new Block[10][10];  // 2D array for the board (static)
    public Player[] players; //
    // Constructor (no longer needed for static fields, but still good to initialize the board)
    public GameBoard() {
        initializeBoard();
    }

    public static Object[][] getBoard()
    {
        return board;
    }

    // initialize with players
    public GameBoard(Player[] players) {
        initializeBoard();
        this.players = players;
    }
    private static final HashMap<Integer, Function<Integer, Block>> BLOCK_CONFIG = new HashMap<>();

    {
        BLOCK_CONFIG.put(3, pos -> new Special(pos, this));
        BLOCK_CONFIG.put(5, Fox::new);
        BLOCK_CONFIG.put(7, Luck::new);
        BLOCK_CONFIG.put(10, PowerUp::new);
        BLOCK_CONFIG.put(15, Fox::new);
        BLOCK_CONFIG.put(19, PowerDown::new);

        // Mid game
        BLOCK_CONFIG.put(23, pos -> new Special(pos, this));
        BLOCK_CONFIG.put(25, Luck::new);
        BLOCK_CONFIG.put(27, PowerUp::new);
        BLOCK_CONFIG.put(33, pos -> new Special(pos, this));
        BLOCK_CONFIG.put(35, Crow::new);
        BLOCK_CONFIG.put(38, PowerDown::new);
        BLOCK_CONFIG.put(45, PowerUp::new);
        BLOCK_CONFIG.put(48, Crow::new);
        BLOCK_CONFIG.put(50, PowerUp::new);
        BLOCK_CONFIG.put(56, Crow::new);
        BLOCK_CONFIG.put(59, PowerDown::new);

        // Late game
        BLOCK_CONFIG.put(65, PowerUp::new);
        BLOCK_CONFIG.put(67, Fox::new);
        BLOCK_CONFIG.put(70, PowerDown::new);
        BLOCK_CONFIG.put(75, PowerUp::new);
        BLOCK_CONFIG.put(78, Luck::new);
        BLOCK_CONFIG.put(79, PowerDown::new);
        BLOCK_CONFIG.put(85, PowerUp::new);
        BLOCK_CONFIG.put(99, PowerUp::new);
        BLOCK_CONFIG.put(87, PowerDown::new);
        BLOCK_CONFIG.put(89, Luck::new);
        BLOCK_CONFIG.put(95, Fox::new);
        BLOCK_CONFIG.put(98, PowerUp::new);
        BLOCK_CONFIG.put(100, pos -> new Bear(pos, this));  // Final challenge
    }
    private void initializeBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                int position = row * 10 + (col + 1);  // Calculate position from row and column

                // Use the map to create blocks, or default to Regular block
                Block block = BLOCK_CONFIG.getOrDefault(position,
                        pos -> new Block(pos, "Regular")).apply(position);

                board[row][col] = block;  // Assign the block to the board
            }
        }
    }





    // Get a block by its position (1 to 100)
    public static Block getBlock(int position) {
        // One case player reaches the end of the game
        if (position==101)
        {
            return board[9][9];
        }
        if (position >= 1 && position <= 100) {
            int row = (position - 1) / 10;  // Get the row (0-based)
            int col = (position - 1) % 10;  // Get the column (0-based)

            return board[row][col];  // Return the corresponding block from the 2D array
        } else {
            return null;  // Invalid position
        }
    }

    public void printBoardWithPlayers(Player[] players) {
        System.out.println("Game Board:");

        // Loop through each row and column
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Block block = board[row][col];

                // Print the block type and players on the block
                String blockType = block.getType();
                String playersOnBlock = block.getPlayersInitials();

                // Format the output to show both the block type and players
                if (playersOnBlock.isEmpty()) {
                    System.out.print(blockType.substring(0, 1) + "\t");  // Print only the first letter of block type
                } else {
                    System.out.print(playersOnBlock + "\t");  // Print player initials
                }
            }
            System.out.println(); // Move to the next line after each row
        }
    }

}
