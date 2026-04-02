package academy.tochkavhoda.misc.v2;

import academy.tochkavhoda.figures.v2.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPhoto {

    @Test
    public void testConstructorFull() {
        Photo p = new Photo(1, 2, 100, 200, 5);
        assertAll(
                () -> assertEquals(1, p.getX()),
                () -> assertEquals(2, p.getY()),
                () -> assertEquals(100, p.getWidth()),
                () -> assertEquals(200, p.getHeight()),
                () -> assertEquals(5, p.getColor())
        );
    }

    @Test
    public void testConstructorWidthHeight() {
        Photo p = new Photo(800, 600);
        assertAll(
                () -> assertEquals(0, p.getX()),
                () -> assertEquals(0, p.getY()),
                () -> assertEquals(800, p.getWidth()),
                () -> assertEquals(600, p.getHeight()),
                () -> assertEquals(0, p.getColor())
        );
    }

    @Test
    public void testMoveTo() {
        Photo p = new Photo(0, 0, 10, 10, 0);
        p.moveTo(5, 5);
        assertAll(
                () -> assertEquals(5, p.getX()),
                () -> assertEquals(5, p.getY())
        );
    }

    @Test
    public void testMoveToPoint() {
        Photo p = new Photo(0, 0, 10, 10, 0);
        p.moveTo(new Point(3, 7));
        assertAll(
                () -> assertEquals(3, p.getX()),
                () -> assertEquals(7, p.getY())
        );
    }

    @Test
    public void testMoveRel() {
        Photo p = new Photo(2, 3, 10, 10, 0);
        p.moveRel(1, -1);
        assertAll(
                () -> assertEquals(3, p.getX()),
                () -> assertEquals(2, p.getY())
        );
    }

    @Test
    public void testResize() {
        Photo p = new Photo(0, 0, 100, 200, 0);
        p.resize(2.0);
        assertAll(
                () -> assertEquals(200, p.getWidth()),
                () -> assertEquals(400, p.getHeight())
        );
    }

    @Test
    public void testStretch() {
        Photo p = new Photo(0, 0, 100, 200, 0);
        p.stretch(2.0, 0.5);
        assertAll(
                () -> assertEquals(200, p.getWidth()),
                () -> assertEquals(100, p.getHeight())
        );
    }

    @Test
    public void testEquals() {
        Photo a = new Photo(1, 2, 100, 200, 5);
        Photo b = new Photo(1, 2, 100, 200, 5);
        Photo c = new Photo(1, 2, 100, 200, 6);
        assertAll(
                () -> assertEquals(a, b),
                () -> assertNotEquals(a, c)
        );
    }

    @Test
    public void testHashCode() {
        Photo a = new Photo(1, 2, 100, 200, 5);
        Photo b = new Photo(1, 2, 100, 200, 5);
        assertEquals(a.hashCode(), b.hashCode());
    }
}
