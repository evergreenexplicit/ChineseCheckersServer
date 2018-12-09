import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ChineseCheckersClassic implements ChineseCheckers {
    ArrayList<Player> players = new ArrayList<Player>();
    Board board;
    int playersTurnIdx;
    ArrayList<int[]> fieldsInTurn = new ArrayList<int[]>();
    ChineseCheckersClassic(int boardSize, int playerNum){


    }
    public void addPlayer(Socket socket){
        int idx = players.size();
        players.add(new Player(socket,idx));
    }
    public void setBoard(){
        board = new Board( 4);
    }

    public void getBoard() {

    }




    public void move(Player player,int x,int y,int newX, int newY) {
        if (playersTurnIdx == player.idx) {
            if (board.getField(newX, newY).getTaken() == -1
                    && ((newX - x == 2 && newY - y == 0)
                    || (newX - x == -2 && newY - y == 0)
                    || (newX - x == 1 && newY - y == 1)
                    || (newX - x == 1 && newY - y == -1)
                    || (newX - x == -1 && newY - y == 1)
                    || (newX - x == -1 && newY - y == -1)


                    )
            ) {
                board.getField(newX, newY).setTaken(player.idx);
                board.getField(x, y).setTaken(-1);

                notifyAboutMove(player.idx, x, y, newX, newY);
                nextTurn();
            } else if (board.getField(newX, newY).getTaken() == -1
                    && ((newX - x == 4 && newY - y == 0 && board.getField(x + 2, y).getTaken() != -1)
                    || (newX - x == -4 && newY - y == 0 && board.getField(x - 2, y).getTaken() != -1)
                    || (newX - x == 2 && newY - y == 2 && board.getField(x + 1, y + 1).getTaken() != -1)
                    || (newX - x == 2 && newY - y == -2 && board.getField(x + 1, y - 1).getTaken() != -1)
                    || (newX - x == -2 && newY - y == 2 && board.getField(x - 1, y + 1).getTaken() != -1)
                    || (newX - x == -2 && newY - y == -2 && board.getField(x - 1, y - 1).getTaken() != -1)
                         )
            ) {
                board.getField(newX, newY).setTaken(player.idx);
                board.getField(x, y).setTaken(-1);
                int tempXY[] = new int[]{newX,newY};
                fieldsInTurn.add(tempXY);
                notifyAboutMove(player.idx, x, y, newX, newY);
            }

    //TODO zajecie sie endturn;
        }
    }
    void notifyAboutMove(int senderIdx,int x,int y,int newX,int newY){
        for(int i = 0; i < players.size();i++){
            if(senderIdx != i) // no need to alarm when right move;
             players.get(i).output.println("MOVE " + x + " " + y + " " + newX + " " + newY);//TODO: sending and finishing move
        }
    }
    void nextTurn(){

    }//TODO

    public class Player extends Thread {
        BufferedReader input;
        PrintWriter output;
        Socket socket;
        int idx;

        public Player(Socket socket, int idx) {
            this.socket = socket;
            this.idx = idx;
            try {
                input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
                output.println("WELCOME ");
                output.println("MESSAGE Waiting for opponents to connect");
            } catch (IOException e) {
                System.out.println("Player died: " + e);


            }
            public void run() {
                try {
                    // The thread is only started after everyone connects.
                    output.println("MESSAGE All players connected");

                    // Tell the first player that it is her turn.
                    //TODO: who's first

                    // Repeatedly get commands from the client and process them.
                    while (true) {
                        String request[] = input.readLine().split(" ");

                       switch (request[0]){
                           case "MOVE":
                             /*  if(players)
                                   break;*/
                           case "QUIT":
                               break;
                       }
                    }
                } catch (IOException e) {
                    System.out.println("Player died: " + e);
                } finally {
                    try {socket.close();} catch (IOException e) {}
                }
            }


        }
    }





}
