import java.net.ServerSocket;
import java.util.ArrayList;
public class Server {
        /**
         * Runs the application. Pairs up clients that connect.
         */
        public static void main(String[] args) throws Exception {
            ServerSocket listener = new ServerSocket(8901);
            System.out.println("ChineseCheckersServer is Running");
            try {
                while (true) { // czy zaczynac, liczba graczy, plansza pozniej
                    ChineseCheckers game = new ChineseCheckersClassic(4,2);
                    game.addPlayer(listener.accept());
                }


            } finally {
                listener.close();
            }
        }


}
/*
List of commands:
OUT:
    INVALID_MOVE
    OTHER_PLAYER_MOVED
    VICTORY
    DEFEAT
    YOURTURN
    ENDOFYOURTURN

IN:
    MOVE
    ENDMOVE

 */