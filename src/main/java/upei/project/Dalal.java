package upei.project;

public class Dalal extends Block {
    public Dalal(int position) {
        super(position, "Dalal");
    }

    @Override
    public void applyEffect(Player player)
    {
        System.out.println("Dalal block! " + player.getName() + " is sent back to their previous position!");
        // No further actions, just print the message and end the turn
        // The player will stay on the current position and proceed with the next player's turn
    }
}
