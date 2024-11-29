package upei.project;

import java.util.ArrayList;

public class Block {
    String property;
    ArrayList<Player> currentPlayers;

    public Block() {
        this.property = "0";
        currentPlayers = new ArrayList<Player>();
    }

    public Block(String property) {
        this.property = property;
        currentPlayers = new ArrayList<Player>();

    }

    public void AddPlayerToBlock( Player targetPlayer) {
        if (!this.currentPlayers.contains(targetPlayer)) {
            this.currentPlayers.add(targetPlayer);
            if (this.property=="0") {
                this.property = "["+targetPlayer.name+"]";
            }
            else {
                this.property = this.property.substring(0,1) + targetPlayer.name + " " + this.property.substring(1);
            }
        }
    }

}


