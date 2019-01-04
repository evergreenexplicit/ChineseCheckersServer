import java.net.Socket;
import java.util.ArrayList;

public interface Moves {
    void setBoard(Board board);
    Board getBoard();
    void move(int playerIdx,int x,int y,int newX, int newY);
    String tryMove(int playerIdx, int x,int y ,int newX,int newY);
    String possibleMoves(int playerIdx,int x,int y);
}
