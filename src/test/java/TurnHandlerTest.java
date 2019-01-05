import junit.framework.TestCase;

public class TurnHandlerTest extends TestCase {
    TurnHandler turnHandler;
    public void testNextTurn() {
        turnHandler = new TurnHandler(4,0,null);
        turnHandler.nextTurn();
        assert(turnHandler.getTurnIdx() ==  1);
        turnHandler.endPlaying(2);
        turnHandler.nextTurn();
        assert(turnHandler.getTurnIdx() ==  3);
        turnHandler.nextTurn();
        assert(turnHandler.getTurnIdx() ==  0);
    }

    public void testEndPlaying() {
        turnHandler = new TurnHandler(2,0,null);
        turnHandler.endPlaying(0);
        assertTrue(!turnHandler.isPlaying(0));
    }

    public void testGetTurnIdx() {
        turnHandler = new TurnHandler(2,0,null);
        turnHandler.nextTurn();
        assert(turnHandler.getTurnIdx() ==  1);
    }
}