import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChineseCheckersClassic implements ChineseCheckers {
    ChineseCheckersClassic(){}
    private ArrayList<Player> players;
    private List<Integer> possibleMovesList= new ArrayList<>();
    private Board board;
    private int playerTurnIdx =0;
    private int goodPawnsAtTarget[][];
    private int badPawnsAtTarget[][];

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
        if (playerTurnIdx != player.idx || player.idx != board.getField(x,y).getTaken()) {
            player.send("INVALID_MOVE");
            return;
        }
        if(!onlyJumps) {
            possibleOneStepMoves(player,x + 2, y);
            possibleOneStepMoves(player,x - 2, y);
            possibleOneStepMoves(player,x - 1, y - 1);
            possibleOneStepMoves(player,x - 1, y + 1);
            possibleOneStepMoves(player,x + 1, y - 1);
            possibleOneStepMoves(player,x + 1, y + 1);
        }
        possibleJumps(player,x-2,y,x-4,y);
        possibleJumps(player,x+2,y,x+4,y);
        possibleJumps(player,x-1,y-1,x-2,y-2);
        possibleJumps(player,x-1,y+1,x-2,y+2);
        possibleJumps(player,x+1,y-1,x+2,y-2);
        possibleJumps(player,x+1,y+1,x+2,y+2);
        if(!onlyJumps){
            if(board.getField(x,y).getPlayerTarget() == player.idx)
                for(int i =0;i<possibleMovesList.size();i+=2)
                    if(board.getField(possibleMovesList.get(i),possibleMovesList.get(i+1)).getPlayerTarget() != player.idx){
                        possibleMovesList.remove(i+1);
                        possibleMovesList.remove(i);
                    }
            String out ="";
            for(int i =0;i<possibleMovesList.size();i++)
                out+=possibleMovesList.get(i) + " ";
            player.send("POSSIBLE_MOVES " + out);

        }

    }
    private void possibleOneStepMoves(Player player, int x, int y){
        if (board.getField(x , y).getTaken() == -1
                && board.getField(x, y).getVisible()) {
            possibleMovesList.add(x);
            possibleMovesList.add(y);
        }

    }
    private void possibleJumps(Player player,int middleX,int middleY, int x, int y){ //todo checking whether middle field is taken
        if(board.getField(x,y).getTaken() == -1
                && board.getField(x, y).getVisible()
                && board.getField(middleX, middleY).getTaken() != -1){
            if(!isInPossibleMoves(x,y)) {
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
    public boolean tryMove(Player player,int x,int y,int newX, int newY) {


        /* one step move */
        if (isInPossibleMoves(newX,newY)) {

            board.getField(newX, newY).setTaken(player.idx);
            board.getField(x, y).setTaken(-1);

            /*jumping through pawns*/
            return true;


        }else if(x != newX || y !=newY)
            player.send("INVALID_MOVE");


        return false;



    }
    public void notifyAboutMove(int senderIdx,int x,int y,int newX,int newY){ //todo  SEPARATE CLASSES FROM EACH OTHER
        for(int i = 0; i < players.size();i++){
            if(senderIdx != i) // no need to alarm when right move;
             players.get(i).send("OPPONENT_MOVED " + x + " " + y + " " + newX + " " + newY);
        }
    }
    public void nextTurn(int playerIdx){
        if (playerIdx != playerTurnIdx) {
            return;

        }else {
            possibleMovesList.clear();
            playerTurnIdx++;
            if (players.size() == playerTurnIdx)
                playerTurnIdx = 0;
            for (int i = 0; i < players.size(); i++) {
                if(i == playerIdx)
                    players.get(i).send("MESSAGE your turn");
                else
                     players.get(i).send("MESSAGE turn of player nr " + playerTurnIdx);
            }
        }
    }





}
