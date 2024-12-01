package upei.project;

public class GameBoard {
    private static final Block[][] board = new Block[10][10];  // 2D array for the board (static)

    // Constructor (no longer needed for static fields, but still good to initialize the board)
    public GameBoard() {
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                int position = row * 10 + (col + 1);  // Mapping to 1 to 100 position

                Block block;  // Declare a Block reference

                // Customize special blocks (for now just set some fixed ones for testing)
                if (position == 5 || position == 15 || position == 25 || position == 30 ||
                        position == 35 || position == 40 || position == 45 || position == 50) {
                    block = new Fox(position);  // Create a Fox block
                } else if (position == 6 || position == 9 || position == 21 || position == 41) {
                    block = new Crow(position);  // Create a Crow block
                } else if (position == 99 || position == 79 || position == 29 || position == 38) {
                    block = new Bear(position,this);  // Create a Bear block
                } else {
                    block = new Block(position, "Regular");  // Default to Regular block
                }

                board[row][col] = block;  // Set the block in the 2D array
            }
        }
    }

    // Get a block by its position (1 to 100)
    public static Block getBlock(int position) {
        if (position >= 1 && position <= 100) {
            int row = (position - 1) / 10;  // Get the row (0-based)
            int col = (position - 1) % 10;  // Get the column (0-based)

            return board[row][col];  // Return the corresponding block from the 2D array
        } else {
            return null;  // Invalid position
        }
    }

    // Print the board with player positions
//    public void printBoardWithPlayers(Player[] players) {
//        // Create a copy of the board to print
//        String[][] displayBoard = new String[10][10];
//
//        // Initialize the board with block type labels (Regular, Fox, Crow, Bear)
//        for (int row = 0; row < 10; row++) {
//            for (int col = 0; col < 10; col++) {
//                Block block = board[row][col];
//                if (block instanceof Fox) {
//                    displayBoard[row][col] = "F";  // Fox block
//                } else if (block instanceof Crow) {
//                    displayBoard[row][col] = "C";  // Crow block
//                } else if (block instanceof Bear) {
//                    displayBoard[row][col] = "B";  // Bear block
//                } else {
//                    displayBoard[row][col] = "R";  // Regular block
//                }
//            }
//        }
//
//        // Now, place players on the board
//        for (Player player : players) {
//            int playerPos = player.getCurrentPosition().getPosition();
//            int row = (playerPos - 1) / 10;  // Get row from position
//            int col = (playerPos - 1) % 10;  // Get column from position
//
//            // If there is already a player at this position, append the new player's initial
//            if (displayBoard[row][col].length() > 1) {
//                // Append player's initial, prefixed with a comma
//                displayBoard[row][col] += "," + player.getName().substring(0, 1);
//            } else {
//                // No player at this position, just place the initial
//                displayBoard[row][col] = player.getName().substring(0, 1);
//            }
//        }
//
//        // Print the board in a 10x10 grid format
//        System.out.println("Game Board:");
//        for (int row = 0; row < 10; row++) {
//            for (int col = 0; col < 10; col++) {
//                System.out.print(displayBoard[row][col] + "\t");
//            }
//            System.out.println();  // New line after each row
//        }
//    }
//    public void printBoardWithPlayers(Player[] players) {
//        System.out.println("Game Board:");
//
//        // Loop through each row and column
//        for (int row = 0; row < 10; row++) {
//            for (int col = 0; col < 10; col++) {
//                Block block = board[row][col];
//
//                // Print the block type (F, C, B, R) and players on the block
//                System.out.print(block.getPlayersInitials() + "\t");
//            }
//            System.out.println(); // Move to the next line after each row
//        }
//    }
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





    // Print the board for debugging purposes
    public void printBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                System.out.print(board[row][col] + "\t");
            }
            System.out.println();
        }
    }
}
