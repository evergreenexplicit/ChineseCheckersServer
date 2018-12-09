import java.net.Socket;
import java.util.ArrayList;

public interface ChineseCheckers {
    void setBoard();
    void getBoard();
    void move(int playerIdx,int x,int y,int newX, int newY);
     void addPlayer(Socket socket);
     class Player extends Thread {
        Socket socket;
        public Player (Socket socket){
            this.socket = socket;
        }
    }
}
