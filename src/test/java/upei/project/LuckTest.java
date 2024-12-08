package upei.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LuckTest {

    @Test
    void testApplyEffect_IncreasesLuckIfBelowMax() {
        // Set up game board and initial state
        GameBoard board = new GameBoard();
        Player player = new Player("TestPlayer"); // Create player without setting initial block
        player.setPosition(board.getBlock(10));   // Set player's initial position to block 10
        player.setLuck(3); // Initial luck level is 3
        Luck luckBlock = new Luck(10);

        // Apply the effect of the Luck block
        luckBlock.applyEffect(player);

        // Assert the player's luck level is increased by 1
        assertEquals(4, player.getLuck());
    }

    @Test
    void testApplyEffect_DoesNotIncreaseLuckIfAtMax() {
        // Set up game board and initial state
        GameBoard board = new GameBoard();
        Player player = new Player("TestPlayer"); // Create player without setting initial block
        player.setPosition(board.getBlock(10));   // Set player's initial position to block 10
        player.setLuck(5); // Initial luck level is 5 (max)
        Luck luckBlock = new Luck(10);

        // Apply the effect of the Luck block
        luckBlock.applyEffect(player);

        // Assert the player's luck level remains unchanged
        assertEquals(5, player.getLuck());
    }

    @Test
    void testApplyEffect_IncreasesLuckToMax() {
        // Set up game board and initial state
        GameBoard board = new GameBoard();
        Player player = new Player("TestPlayer"); // Create player without setting initial block
        player.setPosition(board.getBlock(10));   // Set player's initial position to block 10
        player.setLuck(4); // Initial luck level is 4
        Luck luckBlock = new Luck(10);

        // Apply the effect of the Luck block
        luckBlock.applyEffect(player);

        // Assert the player's luck level is increased to the max (5)
        assertEquals(5, player.getLuck());
    }
}
