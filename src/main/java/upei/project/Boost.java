package upei.project;


import java.util.Random;
import java.util.Scanner;


/*
Boost gives player block a decision to make
 Push the selected player 3 steps behind
 move 3 steps ahead himself.
 */

public class Boost extends Block{
    Random rand = new Random();
    GameBoard currentBoard;

    public Boost(int position, GameBoard board) {
        super(position, "Boost");
        this.currentBoard = board;
    }

    public void applyEffect(Player player) {
        System.out.println("Boost block! " + player.getName() + " Has the potential to push down a player or move 2 steps ahead!");

        // Initialize the keyboard
        Scanner keyboardInput = new Scanner(System.in);

        // Ask for a decision to make
        System.out.println(
                "Decision needs to be made to move forward" +
                        " 'punch' to punch another player or 'move' " +
                        "to move forward"
        );

        // keyboard input
        // SIMULATION EXTENSION--

        // --Strat 1 take aditya constantly punching harsh
        // others may or may not punch him
        String decision = "punch";
        if (player.getName() == "Aditya") {
            decision = "punch";
        }
        else {
            int randomNumber = rand.nextInt(2);
            if (randomNumber == 0) {
                decision = "move";
                decision = "punch";
            }
        }

        if (decision.equals("punch")) {
            // iterate through all the players
            System.out.println("Enter the player's name: ");
            String playerName = "";
            if (player.getName() == "Aditya") {
                playerName = "Harsh";
            }
            else {
                // if player is not Aditya, they can punch anyone.
                String[] players = new String[]{"Nimish", "Harsh","Aditya","Govind"};
                String randomTargetToPunch = players[rand.nextInt(players.length)];
                playerName = randomTargetToPunch;
            }
            boolean foundPlayer = false;

            for (Player pl: currentBoard.players) {
                if (pl.getName().equals(playerName)) {
                    foundPlayer = true;
                    int targetPlayerPosition = pl.getCurrentPosition().getPosition();
                    if (targetPlayerPosition - 2 < 1) {
                        targetPlayerPosition = 1;
                        Block newBlock = GameBoard.getBlock(targetPlayerPosition);
                        pl.setPosition(newBlock);
                    }
                    else {
                        targetPlayerPosition = targetPlayerPosition -2;
                        Block newBlock = GameBoard.getBlock(targetPlayerPosition);
                        pl.setPosition(newBlock);
                        System.out.println(pl.getName()+ " was punched down to "+newBlock.getPosition());
                        // apply effects
                        newBlock.applyEffect(pl);
                    }
                }

            }

            if (!foundPlayer) {
                System.out.println("The player you want to punch does not exist! NEXT!");
            }

        } else if (decision.equals("move")) {
            MoveAhead(player);
        }
    }

    // Potential Decision: Player moves 2 steps
    public void MoveAhead(Player player) {
        System.out.println("Player "+ player.getName() +" decided to move forward");
        int currentPos = player.getCurrentPosition().getPosition();
        int newPos;
        newPos = currentPos + 2;
        if (newPos < 100) {
            newPos = currentPos+2;
        }
        else {
            newPos = 100;
        }

        // Move the player to the new position
        Block newBlock = GameBoard.getBlock(newPos);
        player.setPosition(newBlock);

        // Print the movement and apply effects
        System.out.println(player.getName() + " moves to " + newBlock);
        newBlock.applyEffect(player);

    }

    // Potential Decision: Player pushes other player down 2 steps
    public void PunchDown(Player player1, Player player2) {
            System.out.println("Player "+ player1.getName() +" decided to punch down"+" "+player2.getName());
            int player2Position = player2.getCurrentPosition().getPosition();
            player2Position = player2Position - 2;
            if (player2Position < 1) {
                player2Position = 1;
            }
            Block newBlock = GameBoard.getBlock(player2Position);
            player2.setPosition(newBlock);
            // Print the movement
            System.out.println(player2.getName() + " moves to " + newBlock);
            // apply effects to the player
            newBlock.applyEffect(player2);
    }

}
