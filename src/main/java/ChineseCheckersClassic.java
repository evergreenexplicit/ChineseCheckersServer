import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChineseCheckersClassic implements ChineseCheckers {
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
    public void setBoard(int playersNumber){
        board = new BoardClassic(4,playersNumber);
    }
    public Board getBoard() {
            return board;
    }



    public void possibleMoves(Player player,int x,int y,boolean onlyJumps){ //TODO OUT OF BOUNDS


        if(!onlyJumps) {
            if (playerTurnIdx != player.idx) {
                player.send("MESSAGE Not your turn");
                return;
            }
            if(player.idx != board.getField(x,y).getTaken()){
                player.send("MESSAGE Not your pawn");
                return;
            }

            possibleOneStepMoves(x + 2, y);
            possibleOneStepMoves(x - 2, y);
            possibleOneStepMoves(x - 1, y - 1);
            possibleOneStepMoves(x - 1, y + 1);
            possibleOneStepMoves(x + 1, y - 1);
            possibleOneStepMoves(x + 1, y + 1);
        }
        possibleJumps(player,x-2,y,x-4,y);
        possibleJumps(player,x+2,y,x+4,y);
        possibleJumps(player,x-1,y-1,x-2,y-2);
        possibleJumps(player,x-1,y+1,x-2,y+2);
        possibleJumps(player,x+1,y-1,x+2,y-2);
        possibleJumps(player,x+1,y+1,x+2,y+2);
        if(!onlyJumps){
            if(board.getField(x,y).getPlayerTarget() == player.idx){
                int i = 0;
                while(i<possibleMovesList.size())
                    if(board.getField(possibleMovesList.get(i),possibleMovesList.get(i+1)).getPlayerTarget() != player.idx){
                        possibleMovesList.remove(i+1);
                        possibleMovesList.remove(i);
                    }
                    else
                        i+=2;
            }
            String out ="";
            for(int i =0;i<possibleMovesList.size();i++)
                out+=possibleMovesList.get(i) + " ";
            player.send("POSSIBLE_MOVES " + out);

        }

    }
    private void possibleOneStepMoves(int x, int y){
        if(x < 0 || x >= board.getHorizontal() || y < 0 || y >= board.getVertical())
            return;
        if (board.getField(x , y).getTaken() == -1
                && board.getField(x, y).getVisible()) {
            possibleMovesList.add(x);
            possibleMovesList.add(y);
        }

    }
    private void possibleJumps(Player player,int middleX,int middleY, int x, int y){
        if(x < 0 || x >= board.getHorizontal() || y < 0 || y >= board.getVertical())
            return;
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


        if (isInPossibleMoves(newX,newY)) {
            move(player,x,y,newX,newY);
            return true;
        }else if(x == newX && y == newY)
            player.send("MESSAGE Move cancelled");
        else
            player.send("MESSAGE Invalid move");

        possibleMovesList.clear();
        return false;



    }
   private  void move(Player player,int x,int y,int newX, int newY){
        board.getField(newX, newY).setTaken(player.idx);
        board.getField(x, y).setTaken(-1);

    }
    public void notifyAboutMove(int senderIdx,int x,int y,int newX,int newY){ //todo  SEPARATE CLASSES FROM EACH OTHER
        for(int i = 0; i < players.size();i++){

             players.get(i).send("MOVED " + x + " " + y + " " + newX + " " + newY);
        }
    }
    public void nextTurn(int playerIdx){
        if (playerIdx == playerTurnIdx) {
            possibleMovesList.clear();
            playerTurnIdx++;
            if (players.size() == playerTurnIdx)
                playerTurnIdx = 0;
            for (int i = 0; i < players.size(); i++) {
                if(i == playerIdx)
                    players.get(i).send("MESSAGE Your turn!");
                else
                     players.get(i).send("MESSAGE Turn of player nr " + playerTurnIdx);
            }
        }
    }





}
