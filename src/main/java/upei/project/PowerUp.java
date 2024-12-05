package upei.project;

public class PowerUp extends Block{
    public PowerUp(int position) {
        super(position, "PowerUp");
        // Initial the PowerUp block
    }

    @Override
    public void applyEffect(Player player) {
        if (player.getPower()<4)
        {
            System.out.println("Powerup block! "+player.getName()+" gets +1 power");
            player.setPower(player.getPower() + 1);
            System.out.println(player.getName()+" Current Power Level: "+player.getPower());
        }
        else
        {
            System.out.println("You are already too strong for the power up!");
        }

    }
}
