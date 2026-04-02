package academy.tochkavhoda.misc.v2;

import academy.tochkavhoda.figures.v2.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestColoredPoint {

    @Test
    public void testConstructorFull() {
        ColoredPoint cp = new ColoredPoint(3, 4, 5);
        assertAll(
                () -> assertEquals(3, cp.getX()),
                () -> assertEquals(4, cp.getY()),
                () -> assertEquals(5, cp.getColor())
        );
    }

    @Test
    public void testConstructorXY() {
        ColoredPoint cp = new ColoredPoint(1, 2);
        assertAll(
                () -> assertEquals(1, cp.getX()),
                () -> assertEquals(2, cp.getY()),
                () -> assertEquals(0, cp.getColor())
        );
    }

    @Test
    public void testDefaultConstructor() {
        ColoredPoint cp = new ColoredPoint();
        assertAll(
                () -> assertEquals(0, cp.getX()),
                () -> assertEquals(0, cp.getY()),
                () -> assertEquals(0, cp.getColor())
        );
    }

    @Test
    public void testMoveTo() {
        ColoredPoint cp = new ColoredPoint(1, 2, 3);
        cp.moveTo(10, 20);
        assertAll(
                () -> assertEquals(10, cp.getX()),
                () -> assertEquals(20, cp.getY())
        );
    }

    @Test
    public void testMoveToPoint() {
        ColoredPoint cp = new ColoredPoint(1, 2, 3);
        cp.moveTo(new Point(5, 6));
        assertAll(
                () -> assertEquals(5, cp.getX()),
                () -> assertEquals(6, cp.getY())
        );
    }

    @Test
    public void testMoveRel() {
        ColoredPoint cp = new ColoredPoint(3, 4, 0);
        cp.moveRel(2, -1);
        assertAll(
                () -> assertEquals(5, cp.getX()),
                () -> assertEquals(3, cp.getY())
        );
    }

    @Test
    public void testSetColor() {
        ColoredPoint cp = new ColoredPoint(0, 0, 0);
        cp.setColor(7);
        assertEquals(7, cp.getColor());
    }

    @Test
    public void testEquals() {
        ColoredPoint a = new ColoredPoint(1, 2, 3);
        ColoredPoint b = new ColoredPoint(1, 2, 3);
        ColoredPoint c = new ColoredPoint(1, 2, 4);
        assertAll(
                () -> assertEquals(a, b),
                () -> assertNotEquals(a, c)
        );
    }

    @Test
    public void testHashCode() {
        ColoredPoint a = new ColoredPoint(1, 2, 3);
        ColoredPoint b = new ColoredPoint(1, 2, 3);
        assertEquals(a.hashCode(), b.hashCode());
    }
}
