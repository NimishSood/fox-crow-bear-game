package upei.project;

public class Fox extends Block
{
    public Fox(int position) {
        super(position, "Fox");
        // Initialize the Fox block with its position
    }

    @Override
    public void applyEffect(Player player) {
        System.out.println("Fox block! " + player.getName() + " springs forward!");

        // Get the player's last move (dice roll)
        int lastMove = player.getLastMove();  // Assume Player tracks the last dice roll

        // If lastMove is positive (meaning they had moved earlier), repeat the move
        if (lastMove > 0) {
            // Calculate the new position after applying the last move
            int newPosition = player.getCurrentPosition().getPosition() + lastMove;

            // Ensure the new position does not exceed the board limits
            if (newPosition > 100) {
                newPosition = 100;  // Cap at the last position (100)
            }

            // Get the new block based on the updated position
            Block newBlock = GameBoard.getBlock(newPosition);

            // Move the player to the new block
            player.setPosition(newBlock);
            // Apply effect to the new set position
            if (newBlock instanceof Fox || newBlock instanceof  Crow || newBlock instanceof Bear) {
                newBlock.applyEffect(player);
            }
            else {
                System.out.println(player.getName() + " moves to " + newBlock);
            }
        }
    }
}
