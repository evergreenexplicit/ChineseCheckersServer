public class BoardClassic implements Board {
    private Field[][] fields;
    private int sideLength;
    private int vertical;
    private int horizontal;
    BoardClassic(int sideLength, int playersNumber){
        this.sideLength = sideLength;
        this.vertical = 4*sideLength+1;
        this.horizontal = 6*sideLength+1;
        fields = new Field[6*sideLength+1][4*sideLength+1];
        for(int i = 0; i < 6*sideLength+1 ; i++)
            for(int j = 0; j < 4*sideLength+1 ; j++)
                    fields[i][j] = new Field();

            createBoard();
            if(playersNumber ==2)
                fillBoardForTwo();
            else if(playersNumber ==3)
                fillBoardForThree();
            else if(playersNumber ==4)
                fillBoardForFour();
            else if(playersNumber ==6)
                fillBoardForSix();


    }
    public Field getField(int x,int y){
        return fields[x][y];
    }
    public int getVertical(){return vertical;}
    public int getHorizontal(){return horizontal;}
    public void createBoard(){
        for (int j = 0; j < vertical; j++) {
            for (int i = 0; i < horizontal; i++) {

                fields[i][j] = new Field();

                fields[i][j].setVisible(false);
                if (j < sideLength){
                    if ((i >= horizontal/2 - j) && (i <= horizontal/2 + j)){
                        fields[i][j].setVisible(true);
                    }
                } else if (j >= vertical - sideLength){
                    if ((i >= (horizontal / 2) - (vertical - j - 1)) && (i <= horizontal / 2 + (vertical - j - 1))){
                        fields[i][j].setVisible(true);
                    }
                } else {
                    if((i >= (horizontal / 2) - (2 * sideLength + Math.abs(2 * sideLength - j))) && (i <= (horizontal / 2) + (2 * sideLength + Math.abs(2 * sideLength - j)))){
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
    public void fillBoardForTwo(){
        fillTriangleWithIdx(3 * sideLength,0,+1,0,0);
        fillTriangleWithTarget(3 * sideLength, 4 * sideLength,-1,0,0);

        fillTriangleWithIdx(3 * sideLength, 4 * sideLength,-1,1,0);
        fillTriangleWithTarget(3 * sideLength,0,+1,1,0);
    }
    public void fillBoardForThree(){
        fillTriangleWithIdx(3 * sideLength, 0,1,0,0);
        fillTriangleWithTarget(3*sideLength,4 * sideLength,-1,0,0);


        fillTriangleWithIdx(5* sideLength+1,2 * sideLength+1, 1,1,0);
        fillTriangleWithTarget(sideLength-1,2*sideLength-1,-1,1,0);

        fillTriangleWithIdx(sideLength-1,2 * sideLength+1,1,2,0);
        fillTriangleWithTarget(5*sideLength+1,2*sideLength-1,-1,2,0);

    }
    void fillBoardForFour(){




        fillTriangleWithIdx(5*sideLength+1,2 * sideLength-1,-1,0,0);
        fillTriangleWithTarget(sideLength-1,2*sideLength+1,+1,0,0);

        fillTriangleWithIdx(5*sideLength+1,2 * sideLength+1,1,1,0);
        fillTriangleWithTarget(sideLength-1,2*sideLength-1,-1,1,0);

        fillTriangleWithIdx(sideLength-1,2 * sideLength+1,1,2,0);
        fillTriangleWithTarget(5*sideLength+1,2*sideLength-1,-1,2,0);

        fillTriangleWithIdx(sideLength-1,2 * sideLength-1, -1,3,0);
        fillTriangleWithTarget(5*sideLength+1,2*sideLength+1,1,3,0);
    }
    void fillBoardForSix(){
        fillTriangleWithIdx(3 * sideLength,0,+1,0,0);
        fillTriangleWithTarget(3 * sideLength, 4 * sideLength,-1,0,0);

        fillTriangleWithIdx(5*sideLength+1,2 * sideLength-1,-1,1,0);
        fillTriangleWithTarget(sideLength-1,2*sideLength+1,+1,1,0);

        fillTriangleWithIdx(5*sideLength+1,2 * sideLength+1,1,2,0);
        fillTriangleWithTarget(sideLength-1,2*sideLength-1,-1,2,0);

        fillTriangleWithIdx(3 * sideLength, 4 * sideLength,-1,3,0);
        fillTriangleWithTarget(3 * sideLength,0,+1,3,0);

        fillTriangleWithIdx(sideLength-1,2 * sideLength+1,1,4,0);
        fillTriangleWithTarget(5*sideLength+1,2*sideLength-1,-1,4,0);

        fillTriangleWithIdx(sideLength-1,2 * sideLength-1, -1,5,0);
        fillTriangleWithTarget(5*sideLength+1,2*sideLength+1,1,5,0);

    }
    private void fillLineWithTarget(int x, int y, int direction, int playerIdx, int counter){
        this.getField(x,y).setPlayerTarget(playerIdx);
        counter++;
        if(counter<sideLength)
            fillLineWithTarget(x+1,y+direction,direction,playerIdx,counter);
    }
    private void fillTriangleWithTarget(int x, int y, int direction, int playerIdx, int counter){
        this.getField(x,y).setPlayerTarget(playerIdx);
        counter++;
        if(counter<sideLength) {
            fillLineWithTarget(x + 1, y + direction, direction, playerIdx, counter);
            fillTriangleWithTarget(x - 1, y + direction, direction, playerIdx, counter);
        }
    }
    private void fillLineWithIdx(int x, int y, int direction, int playerIdx, int counter){
        this.getField(x,y).setTaken(playerIdx);
        counter++;
        if(counter<sideLength)
            fillLineWithIdx(x+1,y+direction,direction,playerIdx,counter);
    }
    private void fillTriangleWithIdx(int x, int y, int direction, int playerIdx, int counter){
        this.getField(x,y).setTaken(playerIdx);
        counter++;
        if(counter<sideLength) {
            fillLineWithIdx(x + 1, y + direction, direction, playerIdx, counter);
            fillTriangleWithIdx(x - 1, y + direction, direction, playerIdx, counter);
        }
    }
}
