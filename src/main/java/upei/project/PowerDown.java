package upei.project;

public class PowerDown extends Block{
    // Constructor
    public PowerDown(int position) {
        super(position, "PowerDown");
    }

    @Override
    public void applyEffect(Player player) {
        if (player.getPower()>1) {
            System.out.println("Power down block! " + player.getName() + " looses 1 power!");
            player.setPower(player.getPower() - 1);
            System.out.println(player.getName() + " Current Power Level: " + player.getPower());
        }else {
            System.out.println("Didn't know it was possible to be this weak. Can't power down "+player.getName()+"further. \nCurrent Power level: 1");
        }
    }
}

