import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MovesClassic implements Moves {
    private List<Integer> possibleMovesList= new ArrayList<>();
    private Board board;


    public void setBoard(Board board){
       this.board = board;
    }
    public Board getBoard() {
            return board;
    }


    public String possibleMoves(int playerIdx, int x, int y){

        if(playerIdx != board.getField(x,y).getTaken())
            return"MESSAGE Not your pawn";


        checkOneStepMove(x + 2, y);
        checkOneStepMove(x - 2, y);
        checkOneStepMove(x - 1, y - 1);
        checkOneStepMove(x - 1, y + 1);
        checkOneStepMove(x + 1, y - 1);
        checkOneStepMove(x + 1, y + 1);


       possibleJumps(playerIdx,x,y);

        if(board.getField(x,y).getPlayerTarget() == playerIdx){
            int i = 0;
            while(i<possibleMovesList.size()) // TODO no possible moves
                if(board.getField(possibleMovesList.get(i),possibleMovesList.get(i+1)).getPlayerTarget() != playerIdx){
                    possibleMovesList.remove(i+1);
                    possibleMovesList.remove(i);
                }
                else
                    i+=2;
        }

        String out ="";
        if(possibleMovesList.size() ==0)
            return ("NO_POSSIBLE_MOVES");
        else {
            for (int i = 0; i < possibleMovesList.size(); i++)
                out += possibleMovesList.get(i) + " ";
            return ("POSSIBLE_MOVES " + out);
        }



    }

    private void checkOneStepMove(int x, int y){
        if(x < 0 || x >= board.getHorizontal() || y < 0 || y >= board.getVertical())
            return;
        if (board.getField(x , y).getTaken() == -1
                && board.getField(x, y).getVisible()) {
            possibleMovesList.add(x);
            possibleMovesList.add(y);
        }

    }
    private void possibleJumps(int playerIdx, int x, int y){
        checkJump(playerIdx,x-2,y,x-4,y);
        checkJump(playerIdx,x+2,y,x+4,y);
        checkJump(playerIdx,x-1,y-1,x-2,y-2);
        checkJump(playerIdx,x-1,y+1,x-2,y+2);
        checkJump(playerIdx,x+1,y-1,x+2,y-2);
        checkJump(playerIdx,x+1,y+1,x+2,y+2);
    }

    private void checkJump(int playerIdx,int middleX,int middleY, int x, int y){
        if(x < 0 || x >= board.getHorizontal() || y < 0 || y >= board.getVertical())
            return;
        if(board.getField(x,y).getTaken() == -1
                && board.getField(x, y).getVisible()
                && board.getField(middleX, middleY).getTaken() != -1){
            if(!isInPossibleMoves(x,y)) {
                possibleMovesList.add(x);
                possibleMovesList.add(y);
                possibleJumps(playerIdx,x,y);
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
    public String tryMove(int playerIdx,int x,int y,int newX, int newY) {

        String result;
        if (isInPossibleMoves(newX,newY)) {
            move(playerIdx,x,y,newX,newY);
            result = "MOVED " + x + " " + y + " " + newX + " " + newY;
        }else if(x == newX && y == newY)
            result = "MESSAGE Move cancelled";
        else
            result = "MESSAGE Invalid move";

        possibleMovesList.clear();
        return result;


    }
     public void move(int playerIdx,int x,int y,int newX, int newY){
        board.getField(newX, newY).setTaken(playerIdx);
        board.getField(x, y).setTaken(-1);
    }

    public ArrayList<Integer> checkForLoser(int idx){
        ArrayList<Integer> losers = new ArrayList<>();
        for(int i = 0;i < board.getHorizontal();i++)
            for(int j = 0;j < board.getVertical();j++){

                if(board.getField(i,j).getPlayerTarget() == idx
                     && board.getField(i,j).getTaken() != idx
                        && board.getField(i,j).getTaken() != -1


                ) {
                    if (!losers.contains(board.getField(i, j).getTaken())) {
                        losers.add(board.getField(i, j).getTaken());
                    }

                }

            }
            return losers;
    }




}
