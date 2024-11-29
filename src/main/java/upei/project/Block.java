package upei.project;

public class Block {
    String property;
    Player[] currentPlayers;

    public Block() {
        this.property = "0";
        currentPlayers = new Player[3];
    }

    public Block(String property) {
        this.property = property;
        currentPlayers = new Player[3];
    }
}


