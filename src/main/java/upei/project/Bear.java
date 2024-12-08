package upei.project;

public class Bear extends Block {
    GameBoard currentBoard;
    public Bear(int position, GameBoard board) {
        super(position, "Bear");
        this.currentBoard = board;
    }

    @Override
    public void applyEffect(Player player) {
        GameGUI.getInstance().log(">> " + player.getName() + " with Power Level:" + player.getPower() + " has encountered a bear, a Duel will start.\n");
        int currentPosition;
        switch (player.getPower()) {
            case 1:
                player.setPosition(GameBoard.getBlock(1));
                GameGUI.getInstance().log(">> " + player.getName() + " was severely injured, moved to beginning of the game.\n");
                break;
            case 2:
                currentPosition = player.getCurrentPosition().getPosition();
                player.setPosition(GameBoard.getBlock(currentPosition - 2));
                GameGUI.getInstance().log(">> " + player.getName() + " was injured, retreated 2 blocks back.\nNew position: " + player.getCurrentPosition().getPosition() + "\n");
                break;
            case 3:
                currentPosition = player.getCurrentPosition().getPosition();
                player.setPosition(GameBoard.getBlock(currentPosition + 1));
                GameGUI.getInstance().log(">> " + player.getName() + " won the duel!, moved past the bear.\nNew position: " + player.getCurrentPosition().getPosition() + "\n");
                break;
            case 4:
                currentPosition = player.getCurrentPosition().getPosition();
                player.setPosition(GameBoard.getBlock(currentPosition + 1));
                GameGUI.getInstance().log(">> " + player.getName() + " made the Bear at position: " + currentPosition + " run away for good.\nNew position: " + player.getCurrentPosition().getPosition() + "\n");
                int row = (currentPosition - 1) / 10;
                int col = (currentPosition - 1) % 10;
                Block block = new Block(currentPosition, "Regular");
                Object[][] board = GameBoard.getBoard();
                board[row][col] = block;
                break;
        }
    }
}
