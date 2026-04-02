package academy.tochkavhoda.misc.v2;

import academy.tochkavhoda.figures.v2.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBuilding {

    @Test
    public void testConstructorFull() {
        Building b = new Building(10, 20, 50, 30, 3);
        assertAll(
                () -> assertEquals(10, b.getX()),
                () -> assertEquals(20, b.getY()),
                () -> assertEquals(50, b.getFloorWidth()),
                () -> assertEquals(30, b.getFloorDepth()),
                () -> assertEquals(3, b.getColor())
        );
    }

    @Test
    public void testGetArea() {
        Building b = new Building(0, 0, 10, 5, 0);
        assertEquals(50.0, b.getArea(), 1E-9);
    }

    @Test
    public void testMoveTo() {
        Building b = new Building(0, 0, 10, 10, 0);
        b.moveTo(3, 7);
        assertAll(
                () -> assertEquals(3, b.getX()),
                () -> assertEquals(7, b.getY())
        );
    }

    @Test
    public void testMoveToPoint() {
        Building b = new Building(0, 0, 10, 10, 0);
        b.moveTo(new Point(5, 5));
        assertAll(
                () -> assertEquals(5, b.getX()),
                () -> assertEquals(5, b.getY())
        );
    }

    @Test
    public void testMoveRel() {
        Building b = new Building(2, 3, 10, 10, 0);
        b.moveRel(-1, 4);
        assertAll(
                () -> assertEquals(1, b.getX()),
                () -> assertEquals(7, b.getY())
        );
    }

    @Test
    public void testSetColor() {
        Building b = new Building();
        b.setColor(9);
        assertEquals(9, b.getColor());
    }

    @Test
    public void testEquals() {
        Building a = new Building(1, 2, 10, 20, 3);
        Building b = new Building(1, 2, 10, 20, 3);
        Building c = new Building(1, 2, 10, 20, 4);
        assertAll(
                () -> assertEquals(a, b),
                () -> assertNotEquals(a, c)
        );
    }

    @Test
    public void testHashCode() {
        Building a = new Building(1, 2, 10, 20, 3);
        Building b = new Building(1, 2, 10, 20, 3);
        assertEquals(a.hashCode(), b.hashCode());
    }
}
