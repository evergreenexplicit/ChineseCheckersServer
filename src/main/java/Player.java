import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread {
    BufferedReader input;
    PrintWriter output;
    Socket socket;
    int idx;
    ChineseCheckers game;

    public Player(Socket socket, int idx,ChineseCheckers game) {
        this.game = game;
        this.socket = socket;
        this.idx = idx;
        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            if(idx == 0){
                output.println("FIRST");
            }




        } catch (IOException e) {
            System.out.println("Player died: " + e);


        }
    }
    public void send(String command){
        this.output.println(command);
    }
    public void run(){
        try {
            // The thread is only started after everyone connects.
            output.println("MESSAGE All players connected");

            // Tell the first player that it is her turn.
            //TODO: who's first

            // Repeatedly get commands from the client and process them.
            while (true) {
                String request[] = input.readLine().split(" ");

                switch (request[0]) {
                    case "MOVE_REQ":
                        game.move(this,
                                Integer.parseInt(request[1]),
                                Integer.parseInt(request[2]),
                                Integer.parseInt(request[3]),
                                Integer.parseInt(request[4])
                        );
                    case "END_TURN_REQ":
                        game.nextTurn(idx);
                        break;


                    case "POSSIBLE_MOVES_REQ":
                        game.possibleMoves(this,
                                Integer.parseInt(request[1]),
                                Integer.parseInt(request[2]),
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
            } catch (IOException e) {}
        }
    }


}
