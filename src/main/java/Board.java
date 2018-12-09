public class Board {
    private Field[][] fields;
    private int sideLength;
    private int boardHeight;
    private int boardWidth;
    public Board(int sideLength){
        this.sideLength = sideLength;
        this.boardHeight = 4*sideLength+1;
        this.boardWidth = 6*sideLength+1;
        fields = new Field[6*sideLength+1][3*sideLength+1];
        for(int i = 0; i < 6*sideLength+1 ; i++)
            for(int j = 0; j < 3*sideLength+1 ; j++)
                    fields[i][j] = new Field();



    }
    Field getField(int x,int y){
        return fields[x][y];
    }

    void createBoard(){ // parzystosc???
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
    //void matchTargets();
}
