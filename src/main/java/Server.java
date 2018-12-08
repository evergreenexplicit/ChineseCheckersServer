import java.net.ServerSocket;

public class Server {
        /**
         * Runs the application. Pairs up clients that connect.
         */
        public static void main(String[] args) throws Exception {
            ServerSocket listener = new ServerSocket(8901);
            System.out.println("Tic Tac Toe Server is Running");
            try {
                while (true) {
                    ChineseCheckers game = new ChineseCheckersClassic();
                    Player playerX = new Player(listener.accept(), 'X');
                    Player playerO = new Player(listener.accept(), 'O');
                    playerX.setOpponent(playerO);
                    playerO.setOpponent(playerX);
                    game.currentPlayer = playerX;
                    playerX.start();
                    playerO.start();
                }
            } finally {
                listener.close();
            }
        }


}
