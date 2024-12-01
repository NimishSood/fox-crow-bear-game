package upei.project;

public class Crow extends Block {
    public Crow(int position) {
        super(position, "Crow");
    }

    @Override
    public void applyEffect(Player player) {
        System.out.println("Caw caw! " + player.getName() + " is picked up by a crow!");
        // have to fix crow logic for picking up
        int currentPos = player.getCurrentPosition().getPosition();
        int newPos;
        if (currentPos%10==0)
        {
            newPos = currentPos+10;
        }
        else
        {
            newPos = currentPos+(10-currentPos%10);
        }
//        int newPos = currentPos + 1;  // Move to the next position

        // If the new position exceeds 100, cap it at position 100
        if (newPos > 100) {
            newPos = 100;
        }

        // Move the player to the new position
        Block newBlock = GameBoard.getBlock(newPos);
        player.setPosition(newBlock);

        // Print the movement and apply effects
        System.out.println(player.getName() + " moves to " + newBlock);
        newBlock.applyEffect(player);
    }
}
