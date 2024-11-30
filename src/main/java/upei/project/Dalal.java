package upei.project;

public class Dalal extends Block {
    GameBoard currentBoard;
    public Dalal(int position, GameBoard board) {
        super(position, "Dalal");
        this.currentBoard = board;
    }

    @Override
    public void applyEffect(Player player)
    {
        System.out.println("Dalal block! " + player.getName() + " is sent back to their previous position!");
        // No further actions, just print the message and end the turn
        // reference to the current position of the block
        int currentPositionOfPlayer = player.getCurrentPosition().getPosition();
        //  current position - dice number rolled before
        int calculatedNewPosition = currentPositionOfPlayer - player.getLastMove();
        player.setPosition(GameBoard.getBlock(calculatedNewPosition));
        // The player will stay on the current position and proceed with the next player's turn
    }
}
