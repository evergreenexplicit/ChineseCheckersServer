import java.util.List;

public class TurnHandler {
    boolean bot[];
    boolean playing[];
    int turnIdx;
    int playersNumber; // bots too
    int botsNumber;
    List<Bot> bots;
    TurnHandler(int playersNumber,int botsNumber, List<Bot> bots){
        this.bots = bots;
        playing = new boolean[playersNumber];
        bot = new boolean[playersNumber];
        for(int i =0;i<playersNumber;i++) {
            playing[i] = true;
            bot[i] = false;
        }
        turnIdx = 0;
        this.playersNumber = playersNumber;
    }
    void nextTurn(){
        int counter = 1;
        int newTurnIdx = turnIdx + 1;
        if(newTurnIdx == playersNumber)
            newTurnIdx = 0;
        while(counter < playersNumber) {//todo playersNumber?
            if(isPlaying(newTurnIdx))
                break;

            newTurnIdx++;
            if(newTurnIdx == playersNumber)
                newTurnIdx = 0;


            counter++;


        }
        turnIdx = newTurnIdx;
        if(bot[turnIdx])
            bots.get(turnIdx - (playersNumber - botsNumber)).makeMove();


    }
    public boolean isPlaying(int idx){
        return playing[idx];
    }
    void endPlaying(int playerIdx){
        playing[playerIdx] = false;
    }
    int getTurnIdx(){
        return turnIdx;
    }
    int getPlayersPlaying(){
        int counter = 0;
        for(int i = 0;i < playersNumber;i++)
            if(playing[i])
                counter++;


        return counter;
    }
}
