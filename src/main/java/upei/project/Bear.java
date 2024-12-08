package upei.project;

/**
 * Bear block challenges the player based on their power level.
 * If power low, they are knocked back; if high, they move forward.
 */
public class Bear extends Block {
    private final GameBoard currentBoard;
    public Bear(int position, GameBoard board) {
        super(position, "Bear");
        this.currentBoard = board;
    }

    @Override
    public void applyEffect(Player player) {
        GameGUI.getInstance().log(">> " + player.getName() + " with Power Level:" + player.getPower() + " has encountered a bear, a Duel will start.\n");
        int currentPosition = player.getCurrentPosition().getPosition();
        switch (player.getPower()) {
            case 1:
                player.setPosition(GameBoard.getBlock(1));
                GameGUI.getInstance().log(">> " + player.getName() + " was severely injured, moved to beginning of the game.\n");
                break;
            case 2:
                player.setPosition(GameBoard.getBlock(currentPosition - 2));
                GameGUI.getInstance().log(">> " + player.getName() + " was injured, retreated 2 blocks back.\nNew position: " + player.getCurrentPosition().getPosition() + "\n");
                break;
            case 3:
                player.setPosition(GameBoard.getBlock(currentPosition + 1));
                GameGUI.getInstance().log(">> " + player.getName() + " won the duel!, moved past the bear.\nNew position: " + player.getCurrentPosition().getPosition() + "\n");
                break;
            case 4:
            case 5:
                player.setPosition(GameBoard.getBlock(currentPosition + 1));
                GameGUI.getInstance().log(">> " + player.getName() + " made the Bear at position: " + currentPosition + " run away.\nNew position: " + player.getCurrentPosition().getPosition() + "\n");
                // optionally turn that block into Regular
                int row = (currentPosition - 1) / 10;
                int col = (currentPosition - 1) % 10;
                Block block = new Block(currentPosition,"Regular");
                Object[][] board = GameBoard.getBoard();
                board[row][col] = block;
                break;
        }
    }
}
