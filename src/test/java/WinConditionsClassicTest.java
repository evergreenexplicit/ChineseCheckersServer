import junit.framework.TestCase;

public class WinConditionsClassicTest extends TestCase {
    WinConditions winConditions;

    public void testCheckConditionsforEnd() {
        winConditions = new WinConditionsClassic(4,2);
        winConditions.checkConditions(0,1,-1);
        assertEquals(9,winConditions.getBadPawns(1));
        winConditions.checkConditions(1,0,-1);
        assertEquals("END 0",winConditions.checkConditions(0,-1,0));

    }
    public void testCheckConditionsforWin() {
        winConditions = new WinConditionsClassic(4,4);
        winConditions.checkConditions(0,2,-1);
        winConditions.checkConditions(1,3,-1);
        winConditions.checkConditions(2,0,-1);
        winConditions.checkConditions(3,1,-1);

        assertEquals("WIN 0",winConditions.checkConditions(0,-1,0));
        assertEquals("WIN 1",winConditions.checkConditions(1,-1,1));
        assertEquals("END 2",winConditions.checkConditions(2,-1,2));

    }
    public void testGetLastWinner() {
        winConditions = new WinConditionsClassic(4,4);
        winConditions.checkConditions(0,2,-1);
        assertEquals(9,winConditions.getBadPawns(2));
        winConditions.checkConditions(0,0,-1);
        winConditions.checkConditions(0,-1,0);
        assertEquals(0,winConditions.getLastWinner());
    }
}