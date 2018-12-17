import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChineseCheckersClassic implements ChineseCheckers {
    ChineseCheckersClassic(){}
    private ArrayList<Player> players;
    private List<Integer> possibleMovesList= new ArrayList<>();
    private Board board;
    private int playerTurnIdx =0;


    public void addPlayers(ArrayList<Player> players){

        this.players = players;
        for(int i = 0;i<this.players.size();i++)
            players.get(i).start();

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



    public void possibleMoves(Player player,int x,int y,boolean onlyJumps){
        if(!onlyJumps) {
            possibleOneStepMoves(player,x + 2, y);
            possibleOneStepMoves(player,x - 2, y);
            possibleOneStepMoves(player,x - 1, y - 1);
            possibleOneStepMoves(player,x - 1, y + 1);
            possibleOneStepMoves(player,x + 1, y - 1);
            possibleOneStepMoves(player,x + 1, y + 1);
        }
        possibleJumps(player,x-4,y);
        possibleJumps(player,x+4,y);
        possibleJumps(player,x-2,y-2);
        possibleJumps(player,x-2,y+2);
        possibleJumps(player,x+2,y-2);
        possibleJumps(player,x+2,y+2);
        if(!onlyJumps){
            String out ="";
            for(int i =0;i<possibleMovesList.size();i++)
                out+=possibleMovesList.get(i) + " ";
            player.send("POSSIBLE_MOVES " + out);
            possibleMovesList.clear();
        }

    }
    private void possibleOneStepMoves(Player player, int x, int y){
        if (board.getField(x , y).getTaken() == -1
                && board.getField(x, y).getVisible()) {
            possibleMovesList.add(x);
            possibleMovesList.add(y);
        }

    }
    private void possibleJumps(Player player, int x, int y){
        if(board.getField(x,y).getTaken() == -1
                && board.getField(x, y).getVisible()){
            if(isInPossibleMoves(x,y)) {
                possibleMovesList.add(x);
                possibleMovesList.add(y);
                possibleMoves(player,x,y,true);
            }
        }
    }
     private boolean isInPossibleMoves(int x, int y){
        for(int i = 0;i <possibleMovesList.size();i+=2){
            if(possibleMovesList.get(i) == x
            && possibleMovesList.get(i+1) == y)
                return true;
        }
        return false;
    }
    public void move(Player player,int x,int y,int newX, int newY) {

        if (playerTurnIdx != player.idx) {
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
    void notifyAboutMove(int senderIdx,int x,int y,int newX,int newY){ //todo  SEPARATE CLASSES FROM EACH OTHER
        for(int i = 0; i < players.size();i++){
            if(senderIdx != i) // no need to alarm when right move;
             players.get(i).send("OPPONENT_MOVED " + x + " " + y + " " + newX + " " + newY);
        }
    }
    public void nextTurn(int playerIdx){
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
