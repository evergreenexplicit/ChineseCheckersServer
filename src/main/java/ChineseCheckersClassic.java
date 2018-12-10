import java.net.Socket;
import java.util.ArrayList;

public class ChineseCheckersClassic implements ChineseCheckers {
    private ArrayList<Player> players = new ArrayList<Player>();
    private Board board;
    private int playerTurnIdx =0;
    private ArrayList<Integer[]> fieldsInTurn = new ArrayList<Integer[]>();
    ChineseCheckersClassic(){


    }
    public void addPlayer(Socket socket){
        int idx = players.size();
        players.add(new PlayerClassic(socket,idx,this));
    }
    public void setBoard(){
        board = new BoardClassic(4);
    }

    public Board getBoard() {
            return board;
    }




    public void move(Player player,int x,int y,int newX, int newY) {

        if (playerTurnIdx == player.idx) {
            /* one step move */
            if (board.getField(newX, newY).getTaken() == -1
                    && ((newX - x == 2 && newY - y == 0)
                    || (newX - x == -2 && newY - y == 0)
                    || (newX - x == 1 && newY - y == 1)
                    || (newX - x == 1 && newY - y == -1)
                    || (newX - x == -1 && newY - y == 1)
                    || (newX - x == -1 && newY - y == -1))
            ) {
                board.getField(newX, newY).setTaken(player.idx);
                board.getField(x, y).setTaken(-1);
                notifyAboutMove(player.idx, x, y, newX, newY);
                nextTurn(player.idx);
                /*jumping through pawns*/
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
                notifyAboutMove(player.idx, x, y, newX, newY);


            }else{
                 player.output.println("INVALID_MOVE"); //TODO storing last move in client
            }

    //TODO taking care of endturn;
        } else
            player.output.println("NOT_YOUR_TURN");
    }
    void notifyAboutMove(int senderIdx,int x,int y,int newX,int newY){ //todo separate class?
        for(int i = 0; i < players.size();i++){
            if(senderIdx != i) // no need to alarm when right move;
             players.get(i).output.println("OPPONENT_MOVED " + x + " " + y + " " + newX + " " + newY);//TODO:  finishing move
        }
    }
    void nextTurn(int playerIdx){
        if (playerIdx != playerTurnIdx) {
            players.get(playerIdx).output.println("NOT_YOUR_TURN");
            return;

        }else {
            fieldsInTurn.clear();
            playerTurnIdx++;
            if (players.size() == playerTurnIdx)
                playerTurnIdx = 0;
            for (int i = 0; i < players.size(); i++)
                players.get(i).output.println("TURN " + playerTurnIdx);//TODO: sending and finishing move
        }
    }//TODO





}
