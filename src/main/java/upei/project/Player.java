package upei.project;

/**
 * Represents a player in the game.
 * Tracks position, power, luck, and strategy usage counts (boost/punch).
 */
public class Player {
    private final String name;
    private Block currentPosition;
    private int lastMove;
    private int power;
    private int luck;

    private int boostCount;
    private int punchCount;

    /**
     * Creates a player with the given name. Position set later.
     */
    public Player(String name) {
        this.name = name;
        this.power = 1;
        this.luck = 1;
        // currentPosition set after GameBoard initialization
    }

    public String getName() {
        return name;
    }

    public Block getCurrentPosition() {
        return currentPosition;
    }

    public int getLastMove() {
        return lastMove;
    }

    public void setLastMove(int lastMove) {
        this.lastMove = lastMove;
    }

    public int getPower() {
        return this.power;
    }

    public int getLuck() {
        return this.luck;
    }

    public void setPower(int power) {
        this.power = Math.max(1, Math.min(5, power));
    }

    public void setLuck(int luck) {
        this.luck = Math.min(luck, 5);
    }

    public void setPosition(Block newBlock) {
        if (currentPosition != null) {
            currentPosition.removePlayer(this);
        }
        currentPosition = newBlock;
        newBlock.addPlayer(this);
    }

    /**
     * Simulates a turn by rolling dice, moving, and applying effects.
     */
    public void takeTurn(int diceRoll, GameBoard board) {
        int currentPositionIndex = currentPosition.getPosition();
        int newPositionIndex = currentPositionIndex + diceRoll;
        setLastMove(diceRoll);

        if (newPositionIndex > 100) {
            newPositionIndex = 100;
        } else if (newPositionIndex < 1) {
            newPositionIndex = 1;
        }

        Block newBlock = GameBoard.getBlock(newPositionIndex);
        setPosition(newBlock);

        GameGUI.getInstance().log(">> " + name + " moves to " + newBlock + "\n");
        GameGUI.getInstance().log(">> " + name + " Stats: Power=" + power + ", Luck=" + luck + "\n");

        newBlock.applyEffect(this);
    }

    public void incrementBoostCount() {
        boostCount++;
    }

    public void incrementPunchCount() {
        punchCount++;
    }

    public int getBoostCount() {
        return boostCount;
    }

    public int getPunchCount() {
        return punchCount;
    }
}
