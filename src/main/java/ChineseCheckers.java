import java.net.Socket;
import java.util.ArrayList;

public interface ChineseCheckers {
    void setBoard();
    Board getBoard();
    Player getPlayer(int idx);
    boolean tryMove(Player player, int x,int y ,int newX,int newY);
    void nextTurn(int playerIdx);
    void addPlayers(ArrayList<Player> players);
    void possibleMoves(Player player,int x,int y,boolean onlyJumps);
    void notifyAboutMove(int senderIdx,int x,int y,int newX,int newY);
}
