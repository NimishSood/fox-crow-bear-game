package upei.project;

public class Crow extends Block {
    public Crow(int position) {
        super(position, "Crow");
    }

    @Override
    public void applyEffect(Player player) {
        System.out.println("Caw caw! " + player.getName() + " is picked up by a crow!");

        int currentPos = player.getCurrentPosition().getPosition();
        int newPos = currentPos + 1;  // Move to the next position

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
