package upei.project;

import java.util.ArrayList;
import java.util.List;

public class Block {
    private final int position;             // Position of the block (1 to 100)
    private final String type;              // Type of block (Regular, Fox, Crow, Bear)
    private final List<Player> currentPlayers;  // Players currently on this block

    // Constructor
    public Block(int position, String type) {
        if (position < 1 || position > 100) {
            throw new IllegalArgumentException("Position must be between 1 and 100.");
        }
        this.position = position;
        this.type = type;
        this.currentPlayers = new ArrayList<>();
    }

    // Getter for position
    public int getPosition() {
        return position;
    }

    // Getter for block type
    public String getType() {
        return type;
    }

    // Getter for players on this block
    public List<Player> getCurrentPlayers() {
        return currentPlayers;
    }

    // Add a player to the block
    public void addPlayer(Player player) {
        currentPlayers.add(player);
    }

    // Remove a player from the block
    public void removePlayer(Player player) {
        currentPlayers.remove(player);
    }

    // Apply effect (to be overridden by subclasses)
    public void  applyEffect(Player player) {
        System.out.println("Regular block: No effect.");
    }

    @Override
    public String toString() {
        return "Block " + position + " - Type: " + type;
    }
    public String getPlayersInitials() {
        StringBuilder initials = new StringBuilder();
        for (Player player : currentPlayers) {
            if (initials.length() > 0) {
                initials.append(","); // Add a comma between initials
            }
            initials.append(player.getName().substring(0, 1)); // Use first initial
        }
        return initials.toString();
    }

}