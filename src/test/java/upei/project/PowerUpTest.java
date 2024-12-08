package upei.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowerUpTest {

    @Test
    void testApplyEffect_IncreasesPowerIfBelowMax() {
        // Set up game board and initial state
        GameBoard board = new GameBoard();
        Player player = new Player("TestPlayer"); // Create player without setting initial block
        player.setPosition(board.getBlock(10));   // Set player's initial position to block 10
        player.setPower(3); // Initial power level is 3
        PowerUp powerUpBlock = new PowerUp(10);

        // Apply the effect of the PowerUp block
        powerUpBlock.applyEffect(player);

        // Assert the player's power level is increased by 1
        assertEquals(4, player.getPower());
    }

    @Test
    void testApplyEffect_DoesNotIncreasePowerIfAtMax() {
        // Set up game board and initial state
        GameBoard board = new GameBoard();
        Player player = new Player("TestPlayer"); // Create player without setting initial block
        player.setPosition(board.getBlock(10));   // Set player's initial position to block 10
        player.setPower(5); // Initial power level is 5 (max)
        PowerUp powerUpBlock = new PowerUp(10);

        // Apply the effect of the PowerUp block
        powerUpBlock.applyEffect(player);

        // Assert the player's power level remains unchanged
        assertEquals(5, player.getPower());
    }

    @Test
    void testApplyEffect_IncreasesPowerToMax() {
        // Set up game board and initial state
        GameBoard board = new GameBoard();
        Player player = new Player("TestPlayer"); // Create player without setting initial block
        player.setPosition(board.getBlock(10));   // Set player's initial position to block 10
        player.setPower(4); // Initial power level is 4
        PowerUp powerUpBlock = new PowerUp(10);

        // Apply the effect of the PowerUp block
        powerUpBlock.applyEffect(player);

        // Assert the player's power level is increased to the max (5)
        assertEquals(5, player.getPower());
    }
}
