package upei.project;

/**
 * Luck block increases player's luck if less than 5.
 */
public class Luck extends Block {
    public Luck(int position) {
        super(position, "Lucky Place");
    }

    @Override
    public void applyEffect(Player player) {
        if (player.getLuck() < 5) {
            GameGUI.getInstance().log(">> Lucky block! " + player.getName() + " gets +1 luck\nCurrent Luck: " + (player.getLuck() + 1) + "\n");
            player.setLuck(player.getLuck() + 1);
            GameGUI.getInstance().log(">> " + player.getName() + "'s Dice size increased!\n");
        } else {
            GameGUI.getInstance().log(">> " + player.getName() + " is already at max luck (5).\n");
        }
    }
}
