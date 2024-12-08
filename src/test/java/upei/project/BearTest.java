package upei.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BearTest {

    @Test
    void testApplyEffect_PowerLevel1() {
        // Set up game board and initial state
        GameBoard board = new GameBoard();
        Player player = new Player("TestPlayer"); // Create player without setting initial block
        player.setPosition(board.getBlock(10));   // Set player's initial position to block 10
        player.setPower(1); // Power level is 1
        Bear bearBlock = new Bear(10, board);

        // Apply the effect of the Bear block
        bearBlock.applyEffect(player);

        // Assert the player's position is reset to the beginning
        assertEquals(1, player.getCurrentPosition().getPosition());
    }

    @Test
    void testApplyEffect_PowerLevel2() {
        // Set up game board and initial state
        GameBoard board = new GameBoard();
        Player player = new Player("TestPlayer"); // Create player without setting initial block
        player.setPosition(board.getBlock(10));   // Set player's initial position to block 10
        player.setPower(2); // Power level is 2
        Bear bearBlock = new Bear(10, board);

        // Apply the effect of the Bear block
        bearBlock.applyEffect(player);

        // Assert the player's position is moved back 2 blocks
        assertEquals(8, player.getCurrentPosition().getPosition());
    }

    @Test
    void testApplyEffect_PowerLevel3() {
        // Set up game board and initial state
        GameBoard board = new GameBoard();
        Player player = new Player("TestPlayer"); // Create player without setting initial block
        player.setPosition(board.getBlock(10));   // Set player's initial position to block 10
        player.setPower(3); // Power level is 3
        Bear bearBlock = new Bear(10, board);

        // Apply the effect of the Bear block
        bearBlock.applyEffect(player);

        // Assert the player's position is moved forward 1 block
        assertEquals(11, player.getCurrentPosition().getPosition());
    }

    @Test
    void testApplyEffect_PowerLevel4() {
        // Set up game board and initial state
        GameBoard board = new GameBoard();
        Player player = new Player("TestPlayer"); // Create player without setting initial block
        player.setPosition(board.getBlock(10));   // Set player's initial position to block 10
        player.setPower(4); // Power level is 4
        Bear bearBlock = new Bear(10, board);

        // Apply the effect of the Bear block
        bearBlock.applyEffect(player);

        // Assert the player's position is moved forward 1 block
        assertEquals(11, player.getCurrentPosition().getPosition());

        // Assert the Bear block is turned into a Regular block
        assertEquals("Regular", board.getBlock(10).getType());
    }

    @Test
    void testApplyEffect_PowerLevel5() {
        // Set up game board and initial state
        GameBoard board = new GameBoard();
        Player player = new Player("TestPlayer"); // Create player without setting initial block
        player.setPosition(board.getBlock(10));   // Set player's initial position to block 10
        player.setPower(5); // Power level is 5
        Bear bearBlock = new Bear(10, board);

        // Apply the effect of the Bear block
        bearBlock.applyEffect(player);

        // Assert the player's position is moved forward 1 block
        assertEquals(11, player.getCurrentPosition().getPosition());

        // Assert the Bear block is turned into a Regular block
        assertEquals("Regular", board.getBlock(10).getType());
    }
}
