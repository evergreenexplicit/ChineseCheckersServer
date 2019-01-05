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
     Moves moves;
    private WinConditions winConditions;
    private Notifier notifier;
    private TurnHandler turnHandler;
    private boolean endGame = false;
    static class Builder{

        private Socket socket;
        private int idx;
        private Moves moves;
        private WinConditions winConditions;
        private Notifier notifier;
        private TurnHandler turnHandler;


        Builder(int idx){
            this.idx = idx;


        }
        Builder withSocket(Socket socket){
            this.socket = socket;
            return this;
        }
        Builder withMoves(Moves moves){
            this.moves = moves;

            return this;
        }
        Builder withWinConditions(WinConditions winConditions){
            this.winConditions = winConditions;

            return this;
        }
        Builder withNotifier(Notifier notifier){
            this.notifier = notifier;

            return this;
        }
        Builder withTurnHandler(TurnHandler turnHandler){
            this.turnHandler = turnHandler;

            return this;
        }
        Player build(){
                Player player = new Player(socket,idx);
                player.moves = this.moves;
                player.winConditions = this.winConditions;
                player.turnHandler = this.turnHandler;
                player.notifier = this.notifier;
                return player;
        }

    }
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
    Player(int idx) {

        this.idx = idx;
    }

    void setMoves(Moves moves){
     this.moves = moves;
    }
    Moves getMoves(){return moves;}
    void setNotifier(Notifier notifier){
        this.notifier = notifier;
    }
    Notifier getNotifier(){return notifier;}
    void setTurnHandler(TurnHandler turnHandler){
        this.turnHandler = turnHandler;
    }
    TurnHandler getTurnHandler(){return turnHandler;}
    void setWinConditions(WinConditions winConditions){
        this.winConditions = winConditions;
    }
    WinConditions getWinConditions(){return winConditions;}


     void send(String command){
        this.output.println(command);
    }



    private void possibleMovesReq(int x, int y){
        if(turnHandler.getTurnIdx() == idx)
            send(moves.possibleMoves(idx,x,y));
        else
            send("MESSAGE Not your turn");



    }
    private void moveReq(int x,int y, int newX, int newY){
        String result =moves.tryMove(idx,x,y,newX,newY);
        if(result.startsWith("MOVED"))
            notifier.notifyAll(result);
        else if(result.equals("MESSAGE Move cancelled")) {
            send(result);
            return;
        }
        else if(result.equals("MESSAGE Invalid move")) {
            send(result);
            return;
        }
        else
            send(result);
        String conditions =
            winConditions.checkConditions(
                idx,
                moves.getBoard().getField(x,y).getPlayerTarget(),
                moves.getBoard().getField(newX,newY).getPlayerTarget()
           );
        if(conditions !=null) {

            turnHandler.endPlaying(winConditions.getLastWinner());

            for (int i : moves.checkForLoser(winConditions.getLastWinner())) {
                notifier.notifyAll("LOSE " + i);
                turnHandler.endPlaying(i);
            }
            if(turnHandler.getPlayersPlaying() <=1) {
                winConditions.setEndGame(true);
                String split[] = conditions.split(" ");
                notifier.notifyAll("END " + split[1]);
            } else
                notifier.notifyAll(conditions);


        }
        endTurn();
    }

    private void endTurn(){
        if(turnHandler.getTurnIdx() == idx) {
            turnHandler.nextTurn();
            notifier.notifyAboutTurn(turnHandler.getTurnIdx());
        }
        else
            send("MESSAGE Not your turn");
    }

    public void run(){
        try {
            String request;
            // The thread is only started after everyone connects.
            output.println("MESSAGE All players connected");

            while (!winConditions.getEndGame()) {
                request = input.readLine();
                if(request ==null)
                    break;
                String split[] = request.split(" ");

                switch (split[0]) {
                    case "MOVE_REQ":
                        System.out.println("move req "+split[1]+" "+split[2]+" "+split[3]+" "+split[4]);
                        moveReq(
                                Integer.parseInt(split[1]),
                                Integer.parseInt(split[2]),
                                Integer.parseInt(split[3]),
                                Integer.parseInt(split[4])
                           );


                        break;
                    case "END_TURN_REQ":
                        System.out.println("end turn req");
                       endTurn();
                        break;


                    case "POSSIBLE_MOVES_REQ":
                        System.out.println("possible moves req, player:"+ idx + "taken:" + moves.getBoard().getField(Integer.parseInt(split[1]),
                                Integer.parseInt(split[2])).getTaken()+
                                "xy:"+
                                split[1]+" "+
                                split[2]

                        );
                        possibleMovesReq(
                                Integer.parseInt(split[1]),
                                Integer.parseInt(split[2])
                        );
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Player " +idx+ " died: " + e);
            turnHandler.endPlaying(idx);
        } finally {
            try {

                socket.close();
            } catch (IOException e) {
                turnHandler.endPlaying(idx);
                e.printStackTrace();
            }
        }
    }


}
