import junit.framework.TestCase;
import org.junit.Before;

public class FieldTest extends TestCase {
    Field field;
    @Before
    public void setUp(){
        field = new Field();
    }
    public void testVisible() {
        assertFalse(field.getVisible());
        field.setVisible(true);
        assertTrue(field.getVisible());
    }



    public void testTaken() {
        assertEquals(-1,field.getTaken());
        field.setTaken(0);
        assertEquals(0,field.getTaken());
    }


    public void testPlayerTarget() {
        assertEquals(-1,field.getPlayerTarget());
        field.setPlayerTarget(0);
        assertEquals(0,field.getPlayerTarget());
    }


}