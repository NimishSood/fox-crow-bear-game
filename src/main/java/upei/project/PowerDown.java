package upei.project;

public class PowerDown extends Block {
    public PowerDown(int position) {
        super(position, "PowerDown");
    }

    @Override
    public void applyEffect(Player player) {
        if (player.getPower() > 1) {
            GameGUI.getInstance().log(">> Power down block! " + player.getName() + " loses 1 power!\n");
            player.setPower(player.getPower() - 1);
            GameGUI.getInstance().log(">> " + player.getName() + " Current Power Level: " + player.getPower() + "\n");
        } else {
            GameGUI.getInstance().log(">> " + player.getName() + " can't lose more power. Already at minimum (1).\n");
        }
    }
}
