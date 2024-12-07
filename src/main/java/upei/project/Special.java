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
        System.out.println("Special block! " + player.getName() + " has a choice to make!");

        Scanner keyboardInput = new Scanner(System.in);

        // Prompt the player to choose an action
        System.out.println("Choose an action:");
        System.out.println("1: Punch another player down");
        System.out.println("2: Boost yourself up");

        int choice = -1;
        while (choice != 1 && choice != 2) {
            try {
                System.out.print("Enter your choice (1 or 2): ");
                choice = Integer.parseInt(keyboardInput.nextLine().trim());

                if (choice == 1) {
                    punchAnotherPlayer(player, keyboardInput);
                } else if (choice == 2) {
                    boostPlayer(player);
                } else {
                    System.out.println("Invalid choice. Please choose 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
            }
        }
    }

    private void punchAnotherPlayer(Player player, Scanner keyboardInput) {
        System.out.println("You chose to punch another player! Enter the player's name:");

        // Display all players
        for (Player p : currentBoard.players) {
            System.out.println(p.getName() + ", Position: " + p.getCurrentPosition().getPosition());
        }

        String playerName = keyboardInput.nextLine().trim();
        boolean foundPlayer = false;

        for (Player pl : currentBoard.players) {
            if (pl.getName().equalsIgnoreCase(playerName)) {
                foundPlayer = true;
                if (pl == player) {
                    System.out.println("You cannot punch yourself! Try again.");
                    applyEffect(player);
                    return;
                }
                executePunch(player, pl);
                break;
            }
        }

        if (!foundPlayer) {
            System.out.println("Player not found! Please enter a valid name.");
            punchAnotherPlayer(player, keyboardInput);
        }
    }

    private void executePunch(Player puncher, Player target) {
        System.out.println(puncher.getName() + " decided to punch " + target.getName() + " down!");
        System.out.println(puncher.getName() + ", Power level: " + puncher.getPower());

        int newPosition = target.getCurrentPosition().getPosition() - puncher.getPower();
        if (newPosition < 1) {
            newPosition = 1;
        }

        Block newBlock = GameBoard.getBlock(newPosition);
        target.setPosition(newBlock);
        System.out.println(target.getName() + " was punched down to position " + newBlock.getPosition());

        newBlock.applyEffect(target);
    }

    private void boostPlayer(Player player) {
        System.out.println(player.getName() + " decided to boost themselves up!");
        System.out.println(player.getName() + ", Power level: " + player.getPower());

        int newPosition = player.getCurrentPosition().getPosition() + player.getPower();
        if (newPosition > 100) {
            newPosition = 100;
        }

        Block newBlock = GameBoard.getBlock(newPosition);
        player.setPosition(newBlock);
        System.out.println(player.getName() + " boosted up to position " + newBlock.getPosition());

        newBlock.applyEffect(player);
    }
}
