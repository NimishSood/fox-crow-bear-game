package upei.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerTest {
    String name = "Harsh";
    GameBoard gb = new GameBoard();
    Player harshTestPlayer = new Player("Harsh");

    @Test
    public void testGetName() {
        assertEquals(name, harshTestPlayer.getName());
    }

    @Test
    public void testGetCurrentPosition() {
        // current position index should be 1
        assertEquals(1,harshTestPlayer.getCurrentPosition().getPosition());
    }

    @Test
    public void testGetLastMove() {
        assertEquals(0,harshTestPlayer.getLastMove());
    }

    @Test
    public void testTakeTurn() {
        int testDiceRoll = 6;
        int testNewPositionIndex = 7;
        harshTestPlayer.takeTurn(testDiceRoll,gb);
        assertEquals(7,harshTestPlayer.getCurrentPosition().getPosition());
        assertEquals(6,harshTestPlayer.getLastMove());
    }

    @Test
    public void testSetPosition() {
        harshTestPlayer.setPosition(new Block(1,"Regular"));
        assertEquals(1, harshTestPlayer.getCurrentPosition().getPosition());
    }

}
