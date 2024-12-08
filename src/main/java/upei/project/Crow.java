package upei.project;

public class Crow extends Block {
    public Crow(int position) {
        super(position, "Crow");
    }

    @Override
    public void applyEffect(Player player) {
        GameGUI.getInstance().log(">> Caw caw! " + player.getName() + " is picked up by a crow! Leaving player to the end of row.\n");
        int currentPos = player.getCurrentPosition().getPosition();
        int newPos;
        if (currentPos % 10 == 0) {
            newPos = currentPos + 10;
        } else {
            newPos = currentPos + (10 - currentPos % 10);
        }

        if (newPos > 100) {
            newPos = 100;
        }

        Block newBlock = GameBoard.getBlock(newPos);
        player.setPosition(newBlock);

        GameGUI.getInstance().log(">> " + player.getName() + " moves to " + newBlock + "\n");
        newBlock.applyEffect(player);
    }
}
