import java.net.Socket;

public interface ChineseCheckers {
    void setBoard();
    Board getBoard();

    void move(Player player, int x,int y ,int newX,int newY);

    void addPlayer(Socket socket);
}
