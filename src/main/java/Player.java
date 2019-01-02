import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread {
    BufferedReader input;
    private PrintWriter output;
    private Socket socket;
    int idx;
    private ChineseCheckers game;

    Player(Socket socket, int idx) {
        this.socket = socket;
        this.idx = idx;
        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            output = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            System.out.println("Player died: " + e);


        }
    }

    void setGame(ChineseCheckers game){
     this.game = game;
    }

     void send(String command){
        this.output.println(command);
    }
    public void run(){
        try {
            String request;
            // The thread is only started after everyone connects.
            output.println("MESSAGE All players connected");

            // Tell the first player that it is her turn.
            //TODO: who's first

            // Repeatedly get commands from the client and process them.
            while (true) { //TODO wyjście
                request = input.readLine();
                String split[] = request.split(" ");
                if (request == null)
                      continue;

                switch (split[0]) {
                    case "MOVE_REQ":
                        System.out.println("move req");
                        if(game.tryMove(this,
                                Integer.parseInt(split[1]),
                                Integer.parseInt(split[2]),
                                Integer.parseInt(split[3]),
                                Integer.parseInt(split[4])
                           )
                        ){
                            game.notifyAboutMove(idx,
                                    Integer.parseInt(split[1]),
                                    Integer.parseInt(split[2]),
                                    Integer.parseInt(split[3]),
                                    Integer.parseInt(split[4])
                            );
                            game.nextTurn(idx);
                        }


                        break;
                    case "END_TURN_REQ":
                        System.out.println("end turn req");
                        game.nextTurn(idx);
                        break;


                    case "POSSIBLE_MOVES_REQ":
                        System.out.println("possible moves req");
                        game.possibleMoves(this,
                                Integer.parseInt(split[1]),
                                Integer.parseInt(split[2]),
                                false
                        );
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        } finally {
            try {

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
