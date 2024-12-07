package upei.project;

public class Luck extends Block
{
    public Luck( int position)
    {
        super(position,"Lucky Place");
    }

    @Override
    public void applyEffect(Player player) {
        if (player.getLuck()<5)
        {
            System.out.println("Lucky block! "+player.getName()+" gets +1 luck \nCurrent Luck: "+(player.getLuck() +1));
            player.setLuck(player.getLuck() + 1);
            System.out.println(player.getName()+"'s Dice size increased to "+(5+player.getLuck())+"!");

        }
        else
        {
            System.out.println("You are already blessed enough");

        }



    }
}
