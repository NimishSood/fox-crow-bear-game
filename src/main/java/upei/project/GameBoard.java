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
                    block = new Dalal(position,this);  // Create a Dalal block
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

    // Print the board (for debugging purposes)
    public void printBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                System.out.print(board[row][col] + "\t");
            }
            System.out.println();
        }
    }
}
