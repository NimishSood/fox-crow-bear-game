package upei.project;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for any block on the board.
 */
public class Block {
    private final int position;
    private final String type;
    private final List<Player> currentPlayers;

    public Block(int position, String type) {
        if (position < 1 || position > 100) {
            throw new IllegalArgumentException("Position must be between 1 and 100.");
        }
        this.position = position;
        this.type = type;
        this.currentPlayers = new ArrayList<>();
    }

    public int getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public synchronized List<Player> getCurrentPlayers() {
        return new ArrayList<>(currentPlayers);
    }

    public synchronized void addPlayer(Player player) {
        currentPlayers.add(player);
    }

    public synchronized void removePlayer(Player player) {
        currentPlayers.remove(player);
    }

    public void applyEffect(Player player) {
        GameGUI.getInstance().log(">> Regular block: No effect.\n");
    }

    @Override
    public String toString() {
        return "Block " + position + " - Type: " + type;
    }

    public String getPlayersInitials() {
        List<Player> snapshot;
        synchronized (this) {
            snapshot = new ArrayList<>(currentPlayers);
        }

        StringBuilder initials = new StringBuilder();
        for (Player player : snapshot) {
            if (initials.length() > 0) {
                initials.append(",");
            }
            initials.append(player.getName().substring(0, 1));
        }
        return initials.toString();
    }
}
