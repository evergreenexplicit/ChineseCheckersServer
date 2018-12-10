import java.net.Socket;
import java.util.ArrayList;

public class ChineseCheckersClassic implements ChineseCheckers {
    private ArrayList<Player> players = new ArrayList<Player>();
    private Board board;
    private int playerTurnIdx =0;
    ChineseCheckersClassic(){


    }
    public void addPlayer(Socket socket){
        int idx = players.size();
        players.add(new PlayerClassic(socket,idx,this));
    }
    public Player getPlayer(int idx){
        return players.get(idx);
    }
    public void setBoard(){
        board = new BoardClassic(4);
    }

    public Board getBoard() {
            return board;
    }




    public void move(Player player,int x,int y,int newX, int newY) {

        if (playerTurnIdx == player.idx) {
            player.send("INVALID_MOVE");
            return;
        }
        if(board.getField(x,y).getPlayerTarget() == player.idx
         && board.getField(newX,newY).getPlayerTarget() != player.idx){
            player.send("INVALID_MOVE");
            return;
        }

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
             player.send("INVALID_MOVE");
        }



    }
    void notifyAboutMove(int senderIdx,int x,int y,int newX,int newY){ //todo separate class?
        for(int i = 0; i < players.size();i++){
            if(senderIdx != i) // no need to alarm when right move;
             players.get(i).send("OPPONENT_MOVED " + x + " " + y + " " + newX + " " + newY);
        }
    }
    void nextTurn(int playerIdx){
        if (playerIdx != playerTurnIdx) {
            players.get(playerIdx).send("NOT_YOUR_TURN");
            return;

        }else {
            playerTurnIdx++;
            if (players.size() == playerTurnIdx)
                playerTurnIdx = 0;
            for (int i = 0; i < players.size(); i++) {
                if(i == playerIdx)
                    players.get(i).send("YOUR_TURN");
                else
                     players.get(i).send("TURN " + playerTurnIdx);
            }
        }
    }





}
