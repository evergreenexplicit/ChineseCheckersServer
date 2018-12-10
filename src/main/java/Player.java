import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class Player extends Thread {
    BufferedReader input;
    PrintWriter output;
    Socket socket;
    int idx;
    ChineseCheckersClassic game;
    void send(String command);
    String receive();
}
