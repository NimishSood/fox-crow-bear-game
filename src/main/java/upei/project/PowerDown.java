package upei.project;

public class PowerDown extends Block{
    // Constructor
    public PowerDown(int position) {
        super(position, "PowerDown");
    }

    @Override
    public void applyEffect(Player player) {
        System.out.println("Power down block! " + player.getName() + " -1 power!");
        if (player.getPower() > 0) {
            player.setPower(player.getPower() - 1);
        }
    }
}

