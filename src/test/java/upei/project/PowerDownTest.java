package upei.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowerDownTest {

    @Test
    void testApplyEffect_DecreasesPowerIfAboveMin() {
        // Set up game board and initial state
        GameBoard board = new GameBoard();
        Player player = new Player("TestPlayer"); // Create player without setting initial block
        player.setPosition(board.getBlock(10));   // Set player's initial position to block 10
        player.setPower(3); // Initial power level is 3
        PowerDown powerDownBlock = new PowerDown(10);

        // Apply the effect of the PowerDown block
        powerDownBlock.applyEffect(player);

        // Assert the player's power level is decreased by 1
        assertEquals(2, player.getPower());
    }

    @Test
    void testApplyEffect_DoesNotDecreasePowerIfAtMin() {
        // Set up game board and initial state
        GameBoard board = new GameBoard();
        Player player = new Player("TestPlayer"); // Create player without setting initial block
        player.setPosition(board.getBlock(10));   // Set player's initial position to block 10
        player.setPower(1); // Initial power level is 1 (minimum)
        PowerDown powerDownBlock = new PowerDown(10);

        // Apply the effect of the PowerDown block
        powerDownBlock.applyEffect(player);

        // Assert the player's power level remains unchanged
        assertEquals(1, player.getPower());
    }

    @Test
    void testApplyEffect_DecreasesPowerToMin() {
        // Set up game board and initial state
        GameBoard board = new GameBoard();
        Player player = new Player("TestPlayer"); // Create player without setting initial block
        player.setPosition(board.getBlock(10));   // Set player's initial position to block 10
        player.setPower(2); // Initial power level is 2
        PowerDown powerDownBlock = new PowerDown(10);

        // Apply the effect of the PowerDown block
        powerDownBlock.applyEffect(player);

        // Assert the player's power level is decreased to the minimum (1)
        assertEquals(1, player.getPower());
    }
}
