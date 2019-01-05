import junit.framework.TestCase;

public class PointTest extends TestCase {

    public void testGetSetX() {
        Point point = new Point(0,0);
        point.setX(1);
        assertEquals(1,point.getX());
    }

    public void testGetSetY() {
        Point point = new Point(0,0);
        point.setY(1);
        assertEquals(1,point.getY());
    }

}