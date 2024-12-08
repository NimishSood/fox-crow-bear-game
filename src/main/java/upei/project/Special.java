package upei.project;

public class Special extends Block {
    private final GameBoard currentBoard;
    private static String globalStrategy = null;

    public Special(int position, GameBoard board) {
        super(position, "Special");
        this.currentBoard = board;
    }

    public void applyEffect(Player player) {
        if (globalStrategy != null) {
            applyEffect(player, globalStrategy);
        }
    }

    public void applyEffect(Player player, String strategy) {
        GameGUI.getInstance().log(">> Special block! " + player.getName() + " has triggered a special action!\n");

        if ("BOOST".equalsIgnoreCase(strategy)) {
            player.incrementBoostCount(); // Track usage
            boostPlayer(player);
        } else if ("PUNCH_NEAREST".equalsIgnoreCase(strategy)) {
            player.incrementPunchCount(); // Track usage
            punchNearestPlayer(player);
        } else {
            GameGUI.getInstance().log(">> Unknown strategy: " + strategy + "\n");
        }
    }

    public boolean punchPlayerByName(Player currentPlayer, String targetName) {
        boolean foundPlayer = false;
        Player target = null;

        for (Player pl : currentBoard.players) {
            if (pl.getName().equalsIgnoreCase(targetName)) {
                foundPlayer = true;
                if (pl == currentPlayer) {
                    GameGUI.getInstance().log(">> You cannot punch yourself!\n");
                    return false;
                }
                target = pl;
                break;
            }
        }

        if (!foundPlayer) {
            return false;
        }

        executePunch(currentPlayer, target);
        return true;
    }

    private void boostPlayer(Player player) {
        GameGUI.getInstance().log(">> " + player.getName() + " chose to boost themselves up!\n");
        GameGUI.getInstance().log(">> " + player.getName() + ", Power level: " + player.getPower() + "\n");

        int newPosition = player.getCurrentPosition().getPosition() + player.getPower();
        if (newPosition > 100) {
            newPosition = 100;
        }

        Block newBlock = GameBoard.getBlock(newPosition);
        player.setPosition(newBlock);
        GameGUI.getInstance().log(">> " + player.getName() + " boosted up to position " + newBlock.getPosition() + "\n");

        newBlock.applyEffect(player);
    }

    private void punchNearestPlayer(Player player) {
        GameGUI.getInstance().log(">> " + player.getName() + " is looking for the nearest player to punch!\n");

        Player nearestPlayer = null;
        int minDistance = Integer.MAX_VALUE;

        for (Player p : currentBoard.players) {
            if (p != player) {
                int distance = Math.abs(100 - p.getCurrentPosition().getPosition());
                if (nearestPlayer == null || distance < minDistance ||
                        (distance == minDistance && p.getName().compareTo(nearestPlayer.getName()) < 0)) {
                    nearestPlayer = p;
                    minDistance = distance;
                }
            }
        }

        if (nearestPlayer != null) {
            executePunch(player, nearestPlayer);
        } else {
            GameGUI.getInstance().log(">> No valid target for punching!\n");
        }
    }

    private void executePunch(Player puncher, Player target) {
        GameGUI.getInstance().log(">> " + puncher.getName() + " decided to punch " + target.getName() + " down!\n");
        GameGUI.getInstance().log(">> " + puncher.getName() + ", Power level: " + puncher.getPower() + "\n");

        int newPosition = target.getCurrentPosition().getPosition() - puncher.getPower();
        if (newPosition < 1) {
            newPosition = 1;
        }

        Block newBlock = GameBoard.getBlock(newPosition);
        target.setPosition(newBlock);
        GameGUI.getInstance().log(">> " + target.getName() + " was punched down to position " + newBlock.getPosition() + "\n");

        newBlock.applyEffect(target);
    }

    public static void setGlobalStrategy(String strategy) {
        globalStrategy = strategy;
    }
}
