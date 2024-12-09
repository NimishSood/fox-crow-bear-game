package upei.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CrowTest {
    GameBoard gb = new GameBoard();
    Player testHarshPlayer = new Player("Harsh");

    @Test
    public void testApplyEffect() {
        Crow crowBlock = new Crow(1);
        crowBlock.addPlayer(testHarshPlayer);
        testHarshPlayer.setPosition(crowBlock);
        crowBlock.applyEffect(testHarshPlayer);
        assertEquals(10,testHarshPlayer.getCurrentPosition().getPosition());
    }
}
