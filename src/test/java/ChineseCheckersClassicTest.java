import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChineseCheckersClassicTest {

    @Test
    public void shouldMoveTest(){
        ChineseCheckersClassic  game = new ChineseCheckersClassic();
        PlayerClassic player = new PlayerClassic(null,0,game);
        game.setBoard();
        game.getBoard().fillBoardForTwo();
        game.move(player,9,3,10,4);
        assertTrue(-1 == game.getBoard().getField(9,3).getTaken());
        assertTrue(0 == game.getBoard().getField(10,4).getTaken());
    }
    @Test public void shouldJumpTest(){
        ChineseCheckersClassic  game = new ChineseCheckersClassic();
        PlayerClassic player = new PlayerClassic(null,0,game);
        game.setBoard();
        game.getBoard().fillBoardForTwo();
        game.move(player,10,2,8,4);
        assertTrue(-1 == game.getBoard().getField(10,2).getTaken());
        assertTrue(0 == game.getBoard().getField(8,4).getTaken());
    }
}