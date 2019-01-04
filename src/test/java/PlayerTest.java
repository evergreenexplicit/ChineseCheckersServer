/*import junit.framework.TestCase;
import org.junit.Before;

public class PlayerTest extends TestCase {
    Player player;
    @Before
    public void setUp() {
        player = new Player.Builder(0)
                .withMoves(new MovesClassic())
                .withTurnHandler(new TurnHandler(2))
                .withNotifier(new Notifier() )
                .withWinConditions(new WinConditionsClassic(4,2))
                .build();
    }
    public void testSetMoves() {
        assertTrue(player.getMoves() !=null);
    }

    public void testGetNotifier() {
        assertTrue(player.getNotifier() !=null);
    }

    public void testGetTurnHandler() {
        assertTrue(player.getTurnHandler() !=null);
    }

    public void testGetWinConditions() {
        assertTrue(player.getWinConditions() !=null);
    }
}*/