import junit.framework.TestCase;
import org.junit.Before;

import java.lang.reflect.Method;

public class MovesClassicTest extends TestCase {
    Moves moves;




    public void testGetSetBoard() {
        Moves moves = new MovesClassic();
        moves.setBoard(new BoardClassic(4,2));
        assertNotNull(moves.getBoard());
    }


    public void testPossibleMoves() {
        Moves moves = new MovesClassic();
        moves.setBoard(new BoardClassic(4,2));

        moves.move(0,9,3,12,4);
        moves.move(0,11,3,12,6);
        moves.move(0,13,3,12,8);
        moves.move(0,15,3,12,10);
        moves.move(0,10,2,12,12);
        assertEquals("POSSIBLE_MOVES 10 12 13 11 11 9 13 7 11 5 13 3 ",moves.possibleMoves(1,11,13));
        assertEquals("MOVED 11 13 13 3",moves.tryMove(1,11,13,13,3));
        assertEquals("POSSIBLE_MOVES 15 3 11 3 ",moves.possibleMoves(1,13,3));
    }
    public void testOutOfBounds(){
        Moves moves = new MovesClassic();
        moves.setBoard(new BoardClassic(4,2));
        assertEquals("NO_POSSIBLE_MOVES",moves.possibleMoves(0,12,0));
    }
    public void testMoveCanceled(){
        Moves moves = new MovesClassic();
        moves.setBoard(new BoardClassic(4,2));
        assertEquals("POSSIBLE_MOVES 8 4 10 4 ",moves.possibleMoves(0,9,3));
        assertEquals("MESSAGE Move cancelled",moves.tryMove(0,9,3,9,3));
    }

    public void testInvalidMove(){
        Moves moves = new MovesClassic();
        moves.setBoard(new BoardClassic(4,2));
        assertEquals("POSSIBLE_MOVES 8 4 10 4 ",moves.possibleMoves(0,9,3));
        assertEquals("MESSAGE Invalid move",moves.tryMove(0,9,3,11,3));
    }
    /*public void testTryMove() {
    }*/
    public void testCheckForLosers(){
        Moves moves = new MovesClassic();
        moves.setBoard(new BoardClassic(4,2));
        moves.move(0,12,0,12,8);
        moves.move(1,12,16,12,0);
        int result = moves.checkForLoser(1).get(0);
        assertEquals(0,result);
    }
}