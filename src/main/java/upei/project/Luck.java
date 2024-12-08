package upei.project;

public class Luck extends Block {
    public Luck(int position) {
        super(position, "Lucky Place");
    }

    @Override
    public void applyEffect(Player player) {
        if (player.getLuck() < 5) {
            GameGUI.getInstance().log(">> Lucky block! " + player.getName() + " gets +1 luck.\nCurrent Luck: " + (player.getLuck() + 1) + "\n");
            player.setLuck(player.getLuck() + 1);
            GameGUI.getInstance().log(">> " + player.getName() + "'s Dice size increased!\n");
        } else {
            GameGUI.getInstance().log(">> You are already blessed enough, " + player.getName() + ". Luck stays at 5.\n");
        }
    }
}
