import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class BotTest extends TestCase {

    public void testGetPawns() {
        Bot bot = new Bot(0);
        bot.setPawns(new ArrayList<Point>());
        assertNotNull(bot.getPawns());

    }
}