import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Bot extends Player{
    List<Point> pawns;
    Bot(int idx){
        super(idx);
    }
    List<Point> createPawns(){
        pawns = new ArrayList<Point>();
        for(int i = 0;i<moves.getBoard().getHorizontal();i++)
            for(int j = 0;j<moves.getBoard().getVertical();j++)
                if( moves.getBoard().getField(i,j).getTaken() == idx)
                    pawns.add(new Point(i,j));
        return pawns;

    }
    void makeMove(){}
    List<Point> getPawns(){return pawns;}
    void setPawns(List<Point> pawns){this.pawns = pawns;}
}
