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


    void createBoard(){ // parzystosc???
        for(int i = 0; i < boardWidth ; i++) {

            if (i <= sideLength) {
                int bias = boardWidth / 2 + 1 - i;
                for (int j = 0; j <=i; j++) {
                 fields[bias + 2 * j][i].setVisible(true);

                }
            } else if (i >= boardHeight - sideLength) {
                int bias = boardWidth / 2 + 1 - (boardHeight - 1 - i);
                for (int j = 0; j <= (boardHeight - 1 - i); j++) {
                    fields[bias + 2 * j][i].setVisible(true);

                }
            } else if ( i > sideLength &&  i <= 2 * sideLength){
                int bias =  (i-1 - sideLength);
                for (int j = 0; j <= 3 * sideLength + 1 -  2 * (i-1 - sideLength); j++) {
                    fields[bias + 2 * j][i].setVisible(true);

                }


            }else {
                int bias = 3 * sideLength - ( i - 1 ) ;
                for (int j = 0; j <= 2 * sideLength + 1 - 2 * ((i-1) - 2 * sideLength); j++) { //TODO
                    fields[bias + 2 * j][i].setVisible(true);

                }


            }


        }



    }
}
