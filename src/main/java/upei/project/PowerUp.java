package upei.project;

public class PowerUp extends Block{
    public PowerUp(int position) {
        super(position, "PowerUp");
        // Initial the PowerUp block
    }

    @Override
    public void applyEffect(Player player) {
        System.out.println("Powerup block! "+player.getName()+" gets +1 power");
        player.setPower(player.getPower() + 1);
    }
}
