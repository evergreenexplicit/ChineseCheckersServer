public interface Board {
    void createBoard();
    Field getField(int x,int y);
    void fillBoardForTwo();
    void fillBoardForThree();
    int getHorizontal();
    int getVertical();
}
