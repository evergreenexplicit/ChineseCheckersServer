public interface WinConditions {
    String checkConditions(int playerIdx, int x, int y, int newX, int newY,int oldTarget,int newTarget);
    int getLastWinner();
}
