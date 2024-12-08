package upei.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoxTest {
    GameBoard gb = new GameBoard();
    
    @Test
    void testApplyEffect_PlayerMovesForwardWithinBounds() {
        // Set up initial player
        Player dummyPlayer = new Player("dummy");
        // Set up fox block
        Fox foxBlock = new Fox(10);
        // set last position of the player
        dummyPlayer.setLastMove(6);
        // set the current position to fox block.
        dummyPlayer.setPosition(foxBlock);
        // apply the effect.
        foxBlock.applyEffect(dummyPlayer);
        // if the effect is applied, the current position must be equal to 16....
        assertEquals(16, dummyPlayer.getCurrentPosition().getPosition());
    }

    @Test
    void testApplyEffect_PlayerMovesForwardToMaxPosition() {
        // Set up initial state
        Player player = new Player("TestPlayer");
        player.setLastMove(10); // Last dice roll was 10
        Fox foxBlock = new Fox(95);
        player.setPosition(foxBlock);
        // Apply the effect of the Fox block
        foxBlock.applyEffect(player);
        // Assert the player's new position is capped at 100
        assertEquals(100, player.getCurrentPosition().getPosition());
    }
//
//    @Test
//    void testApplyEffect_NoMovementForZeroLastMove() {
//        // Set up initial state
//        Player player = new Player("TestPlayer");
//        player.setLastMove(0); // Last dice roll was 0
//        Fox foxBlock = new Fox(10);
//
//        // Apply the effect of the Fox block
//        foxBlock.applyEffect(player);
//
//        // Assert the player's position remains unchanged
//        assertEquals(10, player.getCurrentPosition().getPosition());
//    }
}
