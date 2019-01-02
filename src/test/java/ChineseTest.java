/*import org.junit.Test;

import java.net.Socket;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChineseTest {

    @Test
    public void shouldMoveTest(){
        ArrayList<Player> players = new ArrayList<Player>();
        ChineseCheckersClassic  game = new ChineseCheckersClassic();
        Player player = new Player(new Socket(),0);
        players.add(player);
        game.addPlayers(players);
        game.addPlayers( players );
        game.setBoard();
        game.getBoard().fillBoardForTwo();
        game.move(player,9,3,10,4);
        assertTrue(-1 == game.getBoard().getField(9,3).getTaken());
        assertTrue(0 == game.getBoard().getField(10,4).getTaken());
        //need to mock socket connection
    }
    @Test
    public void shouldJumpTest(){
        ChineseCheckersClassic  game = new ChineseCheckersClassic();
        Player player = new Player(null,0);
        game.setBoard();
        game.getBoard().fillBoardForTwo();
        game.move(player,10,2,8,4);
        assertTrue(-1 == game.getBoard().getField(10,2).getTaken());
        assertTrue(0 == game.getBoard().getField(8,4).getTaken());
    }
}
*/