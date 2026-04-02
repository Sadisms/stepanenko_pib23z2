package academy.tochkavhoda.misc.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorErrorCode;
import academy.tochkavhoda.colors.v3.ColorException;
import academy.tochkavhoda.figures.v3.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestColoredPoint {

    @Test
    public void testConstructorWithColor() throws ColorException {
        ColoredPoint point = new ColoredPoint(3, 4, Color.BLUE);
        assertAll(
                () -> assertEquals(3, point.getX()),
                () -> assertEquals(4, point.getY()),
                () -> assertEquals(Color.BLUE, point.getColor())
        );
    }

    @Test
    public void testConstructorWithString() throws ColorException {
        ColoredPoint point = new ColoredPoint(3, 4, "GREEN");
        assertEquals(Color.GREEN, point.getColor());
    }

    @Test
    public void testConstructorsWithoutColor() {
        ColoredPoint point = new ColoredPoint(1, 2);
        ColoredPoint defaultPoint = new ColoredPoint();
        assertAll(
                () -> assertEquals(Color.RED, point.getColor()),
                () -> assertEquals(Color.RED, defaultPoint.getColor())
        );
    }

    @Test
    public void testSettersAndMoves() throws ColorException {
        ColoredPoint point = new ColoredPoint();
        point.setX(7);
        point.setY(8);
        point.setColor(Color.GREEN);
        point.moveRel(3, 2);
        point.moveTo(new Point(1, 2));
        assertAll(
                () -> assertEquals(1, point.getX()),
                () -> assertEquals(2, point.getY()),
                () -> assertEquals(Color.GREEN, point.getColor())
        );
    }

    @Test
    public void testSetColorString() throws ColorException {
        ColoredPoint point = new ColoredPoint();
        point.setColor("BLUE");
        assertEquals(Color.BLUE, point.getColor());
    }

    @Test
    public void testWrongColor() {
        ColorException nullColor = assertThrows(ColorException.class, () -> new ColoredPoint(1, 2, (Color) null));
        ColorException nullString = assertThrows(ColorException.class, () -> new ColoredPoint(1, 2, (String) null));
        ColorException wrongString = assertThrows(ColorException.class, () -> new ColoredPoint(1, 2, "YELLOW"));
        assertAll(
                () -> assertEquals(ColorErrorCode.NULL_COLOR, nullColor.getErrorCode()),
                () -> assertEquals(ColorErrorCode.NULL_COLOR, nullString.getErrorCode()),
                () -> assertEquals(ColorErrorCode.WRONG_COLOR_STRING, wrongString.getErrorCode())
        );
    }

    @Test
    public void testEquals() throws ColorException {
        ColoredPoint first = new ColoredPoint(1, 2, Color.GREEN);
        ColoredPoint second = new ColoredPoint(1, 2, Color.GREEN);
        ColoredPoint third = new ColoredPoint(1, 2, Color.BLUE);
        assertAll(
                () -> assertEquals(first, second),
                () -> assertNotEquals(first, third)
        );
    }
}
