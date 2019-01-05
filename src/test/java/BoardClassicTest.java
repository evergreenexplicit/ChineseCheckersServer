import junit.framework.TestCase;

public class BoardClassicTest extends TestCase {
    private Board board;
    private int sideLength;
    private int playersNumber;

    public void testGetField() {
        sideLength = 4;
        playersNumber = 2;
        board = new BoardClassic(sideLength,playersNumber);
        boolean allFieldsNotNull = true;
        for(int i = 0;i<6*sideLength+1;i++)
            for(int j =0;j<4*sideLength+1;j++)
                if(board.getField(i,j) ==null)
                    allFieldsNotNull = false;

        assertTrue(allFieldsNotNull);
    }

    public void testGetVertical() {
        sideLength = 4;
        playersNumber = 2;
        board = new BoardClassic(sideLength,playersNumber);
        assertEquals(17,board.getVertical());
    }

    public void testGetHorizontal() {
        sideLength = 4;
        playersNumber = 2;
        board = new BoardClassic(sideLength,playersNumber);
        assertEquals(25,board.getHorizontal());
    }

    public void testCreateBoard() {
        sideLength = 4;
        playersNumber = 2;
        board = new BoardClassic(sideLength,playersNumber);
        int counter = 0;
        for(int i = 0;i<6*sideLength+1;i++)
            for(int j =0;j<4*sideLength+1;j++)
                if(board.getField(i,j).getVisible())
                    counter++;

                assertEquals(121,counter);
    }

    public void testFillBoardForTwo() {
        sideLength = 4;
        playersNumber = 2;
        board = new BoardClassic(sideLength,playersNumber);
        int counter = 0;
        for(int i = 0;i<6*sideLength+1;i++)
            for(int j =0;j<4*sideLength+1;j++)
                if(board.getField(i,j).getTaken() != -1)
                    counter++;

               assertEquals(20,counter);
    }

    public void testFillBoardForThree() {
        sideLength = 4;
        playersNumber = 3;
        board = new BoardClassic(sideLength,playersNumber);
        int counter = 0;
        for(int i = 0;i<6*sideLength+1;i++)
            for(int j =0;j<4*sideLength+1;j++)
                if(board.getField(i,j).getTaken() != -1)
                    counter++;

        assertEquals(30,counter);
    }

    public void testFillBoardForFour() {
        sideLength = 4;
        playersNumber = 4;
        board = new BoardClassic(sideLength,playersNumber);
        int counter = 0;
        for(int i = 0;i<6*sideLength+1;i++)
            for(int j =0;j<4*sideLength+1;j++)
                if(board.getField(i,j).getTaken() != -1)
                    counter++;

        assertEquals(40,counter);
    }

    public void testFillBoardForSix() {
        sideLength = 4;
        playersNumber = 6;
        board = new BoardClassic(sideLength,playersNumber);
        int counter = 0;
        for(int i = 0;i<6*sideLength+1;i++)
            for(int j =0;j<4*sideLength+1;j++)
                if(board.getField(i,j).getTaken() != -1)
                    counter++;

        assertEquals(60,counter);
    }
}