package upei.project;

import java.util.Random;

public class Player {
    // store name, score of the player
    String name;
    Integer score;
    Random random = new Random();
    // setup player constructor
    public Player(String name){
        this.name = name;
        this.score = 0;
    }

    // Roll the dice
    // get an outcome bw 1--6
    public Integer rollDice() {
        int diceroll = random.nextInt(6) + 1;
        return diceroll;
    }


}
