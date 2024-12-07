package upei.project;

import java.util.Random;
import java.util.Scanner;

public class Special extends Block {
    Random rand = new Random();
    GameBoard currentBoard;

    public Special(int position, GameBoard board) {
        super(position, "Special");
        this.currentBoard = board;
    }

    public void applyEffect(Player player) {
        System.out.println("Special block! " + player.getName() + " has the potential to push down a player!");

        // Initialize the keyboard
        Scanner keyboardInput = new Scanner(System.in);

        // Ask for a decision to make
        System.out.println("You must punch another player. Enter the player's name:");

        // Display all players
        for (Player p : currentBoard.players) {
            System.out.println(p.getName() + ", Position: " + p.getCurrentPosition().getPosition());
        }

        // Get the player's decision (who to punch)
        String playerName = keyboardInput.nextLine().trim();
        boolean foundPlayer = false;

        // Iterate through all the players to find the punched player
        for (Player pl : currentBoard.players) {
            if (pl.getName().equalsIgnoreCase(playerName)) {
                foundPlayer = true;
                // Punch the selected player 2 blocks down
                PunchDown(player, pl); // Call PunchDown method to punch the selected player
                break;
            }
        }

        // If player is not found
        if (!foundPlayer) {
            System.out.println("Player not found! Please enter a valid name.");
            applyEffect(player);  // Ask again if player not found
        }
    }

    // Method to punch a player down by the puncher's power level
    public void PunchDown(Player player1, Player player2) {
        if (player1.getName().equals(player2.getName())) {
            // If the player tries to punch themselves
            Scanner keyboardInput = new Scanner(System.in);
            System.out.println("Are you sure you want to punch yourself?");
            System.out.println("YES- CONFIRM\nNO- Start Over");

            // Get the decision from the player
            String decision = keyboardInput.nextLine().trim();

            if (decision.equalsIgnoreCase("no")) {
                // Cancel the punch and return to the current block
                Block newBlock = GameBoard.getBlock(player1.getCurrentPosition().getPosition());
                newBlock.applyEffect(player1); // Apply the block's effects
                return;
            } else if (!decision.equalsIgnoreCase("yes")) {
                // Invalid input, ask again
                System.out.println("Please enter a valid command.");
                PunchDown(player1, player2); // Recurse to ask again
                return;
            }
        }

        System.out.println(player1.getName() + " decided to punch " + player2.getName() + " down!");
        System.out.println(player1.getName() + ", Power level: " + player1.getPower());

        // Get the current position of the punched player
        int player2Position = player2.getCurrentPosition().getPosition();
        player2Position -= player1.getPower();  // Move the player down by puncher's power level

        // Ensure the position doesn't go below 1
        if (player2Position < 1) {
            player2Position = 1;  // Set to position 1 if below
        }

        // Set the new position for the punched player
        Block newBlock = GameBoard.getBlock(player2Position);
        player2.setPosition(newBlock);

        // Print the movement
        System.out.println(player2.getName() + " was punched down to position " + newBlock.getPosition());

        // Apply the block's effects
        newBlock.applyEffect(player2);
    }
}
