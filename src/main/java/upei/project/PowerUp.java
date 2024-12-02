package upei.project;

public class PowerUp extends Block{
    public PowerUp(int position) {
        super(position, "PowerUp");
        // Initial the PowerUp block
    }

    @Override
    public void applyEffect(Player player) {
        if (player.getPower()<5)
        {
            System.out.println("Powerup block! "+player.getName()+" gets +1 power.\n Current Power Level: "+player.getPower()+1);
            player.setPower(player.getPower() + 1);
        }
        else
        {
            System.out.println("You are already too strong for the power up!");
        }

    }
}
