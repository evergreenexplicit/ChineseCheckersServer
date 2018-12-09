import java.net.Socket;
import java.util.ArrayList;

public interface ChineseCheckers {
    void setBoard();
    void getBoard();
    void move();
     void addPlayer(Socket socket);
     class Player extends Thread {
        Socket socket;

}
