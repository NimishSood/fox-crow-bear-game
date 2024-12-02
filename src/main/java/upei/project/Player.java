package upei.project;

public class Player {
    private final String name;           // Player's name
    private Block currentPosition;       // Player's current position on the board
    private int lastMove;                // Tracks the last dice roll
    private int power;
    // Constructor
    public Player(String name) {
        this.name = name;
        this.currentPosition = GameBoard.getBlock(1);  // All players start at position 1
        this.lastMove = 0;  // No last move initially
        currentPosition.addPlayer(this);  // Add player to the starting block
        this.power = 0;
    }

    // Getter for the player's name
    public String getName() {
        return name;
    }

    // Getter for the player's current position
    public Block getCurrentPosition() {
        return currentPosition;
    }

    // Getter for lastMove (the dice roll)
    public int getLastMove() {
        return lastMove;
    }

    // Setter for lastMove
    public void setLastMove(int lastMove) {
        this.lastMove = lastMove;
    }

    // getter for power
    public int getPower() {
        return this.power;
    }

    // setter for power
    public void setPower(int power) {
        this.power = power;
    }

    // Method to update the player's position
    public void setPosition(Block newBlock) {
        // Remove the player from their current position
        currentPosition.removePlayer(this);

        // Set the new position
        currentPosition = newBlock;

        // Add the player to the new block
        newBlock.addPlayer(this);
    }

    // Method to simulate taking a turn (e.g., rolling the dice)
    public void takeTurn(int diceRoll, GameBoard board) {
        int currentPositionIndex = currentPosition.getPosition();  // Get the current position (1 to 100)
        int newPositionIndex = currentPositionIndex + diceRoll;    // Add dice roll to position

        // Set the player's last move (dice roll)
        setLastMove(diceRoll);

        // Check if the player exceeds the board limit and adjust if necessary
        if (newPositionIndex > 100) {
            newPositionIndex = 100;  // Cap at the last position (100)
        } else if (newPositionIndex < 1) {
            newPositionIndex = 1;    // Ensure it doesn't go below position 1
        }

        // Get the new block based on the updated position
        Block newBlock = GameBoard.getBlock(newPositionIndex);

        // Set the new position for the player
        setPosition(newBlock);

        // Print player's movement
        System.out.println(name + " moves to " + newBlock);

        // Apply any effects of the new block (e.g., Fox, Crow, Bear)
        if (newBlock instanceof Fox) {
            Fox foxBlock = (Fox) newBlock;
            foxBlock.applyEffect(this);
        }
        else if (newBlock instanceof  Crow) {
            Crow crowBlock = (Crow) newBlock;
            crowBlock.applyEffect(this);
        }
        else if (newBlock instanceof Bear) {
            Bear BearBlock = (Bear) newBlock;
            BearBlock.applyEffect(this);
        }

        else {
            newBlock.applyEffect(this);
        }
    }
}