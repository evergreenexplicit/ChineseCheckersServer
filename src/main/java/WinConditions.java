public interface WinConditions {
    String checkConditions(int playerIdx, int oldTarget,int newTarget);
    int getLastWinner();
    public int getGoodPawns(int idx);
    public int getBadPawns(int idx);
}
