public class BoardClassic implements Board {
    private Field[][] fields;
    private int sideLength;
    private int boardHeight;
    private int boardWidth;
    public BoardClassic(int sideLength){
        this.sideLength = sideLength;
        this.boardHeight = 4*sideLength+2;
        this.boardWidth = 6*sideLength+2;
        fields = new Field[6*sideLength+1][4*sideLength+1];
        for(int i = 0; i < 6*sideLength+1 ; i++)
            for(int j = 0; j < 3*sideLength+1 ; j++)
                    fields[i][j] = new Field();

            createBoard();


    }
    public Field getField(int x,int y){
        return fields[x][y];
    }
    public void fillBoardForTwo(){
        fillTriangleWithIdx(2 * sideLength,0,+1,0,0);
        fillTriangleWithTarget(2 * sideLength, 4 * sideLength,-1,0,0);

        fillTriangleWithIdx(2 * sideLength, 4 * sideLength,-1,1,0);
        fillTriangleWithTarget(2 * sideLength,0,+1,1,0);
    }
    public void fillBoardForThree(){
        fillTriangleWithIdx(sideLength-1,2 * sideLength-1,-1,0,0);
        fillTriangleWithTarget(3*sideLength,2*sideLength+1,1,0,0);

        fillTriangleWithIdx(3* sideLength,2 * sideLength-1, -1,1,0);
        fillTriangleWithTarget(sideLength-1,2*sideLength+1,1,1,0);

        fillTriangleWithIdx(2 * sideLength, 4 * sideLength,-1,2,0);
        fillTriangleWithTarget(2*sideLength,0,1,2,0);
    }
    void fillLineWithTarget(int x,int y,int direction,int playerIdx,int counter){
        this.getField(x,y).setPlayerTarget(playerIdx);
        counter++;
        if(counter<sideLength)
            fillLineWithTarget(x+1,y+direction,direction,playerIdx,counter);
    }
    void fillTriangleWithTarget(int x,int y,int direction,int playerIdx,int counter){
        this.getField(x,y).setPlayerTarget(playerIdx);
        counter++;
        if(counter<sideLength) {
            fillLineWithTarget(x + 1, y + direction, direction, playerIdx, counter);
            fillTriangleWithTarget(x - 1, y + direction, direction, playerIdx, counter);
        }
    }
    void fillLineWithIdx(int x,int y,int direction,int playerIdx,int counter){
        this.getField(x,y).setTaken(playerIdx);
        counter++;
        if(counter<sideLength)
            fillLineWithIdx(x+1,y+direction,direction,playerIdx,counter);
    }
    void fillTriangleWithIdx(int x, int y,int direction, int playerIdx,int counter){
        this.getField(x,y).setTaken(playerIdx);
        counter++;
        if(counter<sideLength) {
            fillLineWithIdx(x + 1, y + direction, direction, playerIdx, counter);
            fillTriangleWithIdx(x - 1, y + direction, direction, playerIdx, counter);
        }
    }
    public void createBoard(){ // TODO check
        for(int j = 0; j < boardHeight; j++)
            for(int i = 0; i < boardWidth ; i++) {
                fields[i][j].setVisible(false);
                if (j < sideLength){
                    if ((i >= boardWidth/2 - j) && (i <= boardWidth/2 + j)){
                        fields[i][j].setVisible(true);
                    }
                } else if (j >= boardHeight - sideLength){
                    if ((i >= (boardWidth / 2) - (boardWidth - j - 1))
                     && (i <= boardWidth / 2 + (boardHeight - j - 1))){
                        fields[i][j].setVisible(true);
                    }
                } else {
                    if((i >= (boardWidth / 2) - (2 * sideLength + Math.abs(2 * sideLength - j)))
                    && (i <= (boardWidth / 2) + (2 * sideLength + Math.abs(2 * sideLength - j)))){
                        fields[i][j].setVisible(true);
                    }
                }
                if (j % 2 == sideLength % 2) {
                    if (i % 2 == 1) {
                        fields[i][j].setVisible(false);
                    }
                } else {
                    if (i % 2 == 0) {
                        fields[i][j].setVisible(false);
                    }
                }


        }
    }
}
