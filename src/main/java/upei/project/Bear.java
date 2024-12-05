package upei.project;

public class Bear extends Block {
    GameBoard currentBoard;
    public Bear(int position, GameBoard board) {
        super(position, "Bear");
        this.currentBoard = board;
    }

    @Override
    public void applyEffect(Player player)
    {
        //Current position of player
        int currentPosition=player.getCurrentPosition().getPosition();
        System.out.println(player.getName()+" with Power Level:"+player.getPower()+ " has encountered a bear, a Duel will start.");
        switch (player.getPower())
        {
            case 1:
                player.setPosition(GameBoard.getBlock(1));
                System.out.println(player.getName()+" was severely injured, moved to beginning of the game.");
                break;
            case 2:
                player.setPosition(GameBoard.getBlock(currentPosition-2));
                System.out.println(player.getName()+" was injured, retreated 2 blocks back."+"\nNew position: "+player.getCurrentPosition().getPosition());
                break;
            case 3:
                System.out.println();
                player.setPosition(GameBoard.getBlock(currentPosition+1));
                System.out.println(player.getName()+" won the duel!, moved past the bear."+"\nNew position: "+player.getCurrentPosition().getPosition());
                break;
            case 4:
                player.setPosition(GameBoard.getBlock(currentPosition+1));
                System.out.println(player.getName()+" made, the Bear at position: "+currentPosition+" ran away for good."+"\nNew position: "+player.getCurrentPosition().getPosition());
                int row = (currentPosition - 1) / 10;  // Get the row (0-based)
                int col = (currentPosition - 1) % 10;  // Get the column
                Block block=new Block(currentPosition,"Regular");
                Object[][] board = GameBoard.getBoard();
                board[row][col]=block;
                break;

        }


    }
}
