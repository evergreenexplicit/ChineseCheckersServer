import java.net.Socket;

public interface ChineseCheckers {
    void setBoard();
    Board getBoard();
    Player getPlayer(int idx)
    void move(Player player, int x,int y ,int newX,int newY);

    void addPlayer(Socket socket);
}
