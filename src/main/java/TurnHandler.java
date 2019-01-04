public class TurnHandler {
    boolean bot[];
    boolean playing[];
    int turnIdx;
    int playersNumber;
    TurnHandler(int playersNumber){
        playing = new boolean[playersNumber];
        for(int i =0;i<playersNumber;i++)
          playing[i] = true;
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
    }
    private boolean isPlaying(int idx){
        return playing[idx];
    }
    void endPlaying(int playerIdx){
        playing[playerIdx] = false;
    }
    int getTurnIdx(){
        return turnIdx;
    }
}
