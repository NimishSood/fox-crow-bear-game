package upei.project;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockTest {
    Block testBlock = new Block(1, "Regular");
    GameBoard gb= new GameBoard();
    List<Player> currentPlayers = new ArrayList<>();
    Player harshTestPlayer = new Player("Harsh");

    @Test
    public void testGetPosition() {
        assertEquals(1, testBlock.getPosition());
    }

    @Test
    public void testGetCurrentPlayers() {
        assertEquals(new ArrayList<>(), testBlock.getCurrentPlayers());
    }

    @Test
    public void testAddPlayer() {
        currentPlayers.add(harshTestPlayer);
        testBlock.addPlayer(harshTestPlayer);
        assertEquals(currentPlayers,testBlock.getCurrentPlayers());
    }

    @Test
    public void testRemovePlayer() {
        currentPlayers.remove(harshTestPlayer);
        testBlock.removePlayer(harshTestPlayer);
        assertEquals(currentPlayers, testBlock.getCurrentPlayers());
    }

    @Test
    public void testApplyEffect() {

    }

    @Test
    public void testToString() {
        assertEquals("Block 1 - Type: Regular",testBlock.toString());
    }

    @Test
    public void testGetPlayerInitials() {
        // String builder initials
        currentPlayers.add(new Player("Harsh"));
        currentPlayers.add(new Player("Aarsh"));
        currentPlayers.add(new Player("Narsh"));
        testBlock.addPlayer(new Player("Harsh"));
        testBlock.addPlayer(new Player("Aarsh"));
        testBlock.addPlayer(new Player("Narsh"));
        assertEquals("H,A,N",testBlock.getPlayersInitials());
    }

}
