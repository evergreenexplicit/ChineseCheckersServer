public class WinConditionsClassic implements WinConditions{
    private int goodPawns[];
    private int badPawns[];
    private int playersPlaying;
    private int pawnsNumber;
    private int playersNumber;
    private int lastWinner;
    WinConditionsClassic(int sideLength,int playersNumber){
        playersPlaying = playersNumber;
        this.playersNumber = playersNumber;
        pawnsNumber = sideLength*(sideLength+1)/2;
        goodPawns = new int[playersNumber];
        badPawns = new int[playersNumber];
        for(int i = 0;i<playersNumber;i++) {
            goodPawns[i] = 0;
            if(playersNumber ==3)
                 badPawns[i] = 0;
            else
                badPawns[i] =pawnsNumber;
        }
    }

    public String checkConditions(int playerIdx,int oldTarget,int newTarget){
        if(oldTarget != playerIdx && newTarget == playerIdx)
            goodPawns[playerIdx]++;

        else if(oldTarget != newTarget) {
            if(oldTarget == -1)
                badPawns[newTarget]++;
            else
                badPawns[oldTarget]--;

        }

        for(int i =0 ;i<playersNumber;i++){
            if(goodPawns[i]+badPawns[i] ==pawnsNumber && goodPawns[i] > 0){
                lastWinner = i;
                playersPlaying--;
                if(playersPlaying == 1)
                    return("END " + i);
                else
                   return("WIN " + i);
            }

        }
        return null;

    }
    public int getLastWinner(){
        return lastWinner;
    }
}
