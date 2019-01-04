import junit.framework.TestCase;

import java.util.ArrayList;

public class NotifierTest extends TestCase {
    Notifier notifier;
    public void testAddPlayers() {
        notifier = new Notifier();
        notifier.addPlayers(new ArrayList<Player>());
        assert(notifier.getPlayers() != null);
    }
}