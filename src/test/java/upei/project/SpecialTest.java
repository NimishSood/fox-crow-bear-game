package upei.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpecialTest {

    // Test the BOOST functionality of Special block
    @Test
    void testApplyEffect_WithBoostStrategy() {
        // Set up game board and initial state
        GameBoard board = new GameBoard();
        Player player = new Player("TestPlayer");
        player.setPosition(board.getBlock(11)); // Set player's initial position to block 10
        player.setPower(3); // Set player's power level to 3
        Special.setGlobalStrategy("BOOST");
        Special specialBlock = new Special(11, board);

        // Apply the effect of the Special block
        specialBlock.applyEffect(player);

        // Assert the player's position is moved forward by power (3)
        assertEquals(14, player.getCurrentPosition().getPosition());
    }

    // Test the Punch functionality of Special block
    @Test
    void testApplyEffect_WithPunch() {
        // Set up game board and initial state
        GameBoard board = new GameBoard();
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        // initialize current players
        board.players = new Player[] {player1, player2};
        player1.setPosition(board.getBlock(20)); // Player1 starts at block 20
        player2.setPosition(board.getBlock(15)); // Player2 starts at block 15
        player1.setPower(4); // Player1's power level is 4
        board.getBlock(20).addPlayer(player1);
        board.getBlock(15).addPlayer(player2);
        Special.setGlobalStrategy("PUNCH_NEAREST");
        Special specialBlock = new Special(20, board);

        // Apply the effect of the Special block
        specialBlock.applyEffect(player1);

        // Assert the nearest player (Player2) is punched back by power (4)
        assertEquals(11, player2.getCurrentPosition().getPosition());
    }
}
