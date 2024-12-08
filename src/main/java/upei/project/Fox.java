package upei.project;

public class Fox extends Block {
    public Fox(int position) {
        super(position, "Fox");
    }

    @Override
    public void applyEffect(Player player) {
        GameGUI.getInstance().log(">> Fox block! " + player.getName() + " springs forward!\n");
        int lastMove = player.getLastMove();
        if (lastMove > 0) {
            int newPosition = player.getCurrentPosition().getPosition() + lastMove;
            if (newPosition > 100) {
                newPosition = 100;
            }
            Block newBlock = GameBoard.getBlock(newPosition);
            player.setPosition(newBlock);
            GameGUI.getInstance().log(">> " + player.getName() + " moves to " + newBlock + "\n");
            newBlock.applyEffect(player);
        }
    }
}
