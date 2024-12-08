package upei.project;

/**
 * PowerUp block increases player's power if less than 5.
 */
public class PowerUp extends Block {
    public PowerUp(int position) {
        super(position, "PowerUp");
    }

    @Override
    public void applyEffect(Player player) {
        if (player.getPower() < 5) {
            GameGUI.getInstance().log(">> Powerup block! " + player.getName() + " gets +1 power.\n");
            player.setPower(player.getPower() + 1);
            GameGUI.getInstance().log(">> " + player.getName() + " Current Power Level: " + player.getPower() + "\n");
        } else {
            GameGUI.getInstance().log(">> " + player.getName() + " is already at max power (5). No power up gained.\n");
        }
    }
}
