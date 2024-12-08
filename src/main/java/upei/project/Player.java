package upei.project;

public class Player {
    private final String name;
    private Block currentPosition;
    private int lastMove;
    private int power;
    private int luck;

    // New counters for strategy usage
    private int boostCount;
    private int punchCount;

    public Player(String name) {
        this.name = name;
        // currentPosition and GameBoard must be initialized first in Main now
        this.power = 1;
        this.luck = 1;
        // No position set here, it will be set after GameBoard is ready
        // in runManualGame or runSingleGame of StrategyRunner
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
        this.power = Math.max(1, Math.min(power, 5));
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

        // Log player stats after moving
        GameGUI.getInstance().log(">> " + name + " Stats: Power=" + power + ", Luck=" + luck + "\n");

        if (newBlock instanceof Fox) {
            ((Fox) newBlock).applyEffect(this);
        } else if (newBlock instanceof Crow) {
            ((Crow) newBlock).applyEffect(this);
        } else if (newBlock instanceof Bear) {
            ((Bear) newBlock).applyEffect(this);
        } else if (newBlock instanceof PowerUp) {
            ((PowerUp) newBlock).applyEffect(this);
        } else if (newBlock instanceof PowerDown) {
            ((PowerDown) newBlock).applyEffect(this);
        } else if (newBlock instanceof Luck) {
            ((Luck) newBlock).applyEffect(this);
        } else if (newBlock instanceof Special) {
            ((Special) newBlock).applyEffect(this);
        } else {
            newBlock.applyEffect(this);
        }
    }

    // Increment counters when executing strategy actions
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
