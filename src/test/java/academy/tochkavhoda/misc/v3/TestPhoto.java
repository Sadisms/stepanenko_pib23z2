package academy.tochkavhoda.misc.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorErrorCode;
import academy.tochkavhoda.colors.v3.ColorException;
import academy.tochkavhoda.figures.v3.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPhoto {

    @Test
    public void testConstructorWithColor() throws ColorException {
        Photo photo = new Photo(1, 2, 100, 200, Color.BLUE);
        assertAll(
                () -> assertEquals(1, photo.getX()),
                () -> assertEquals(2, photo.getY()),
                () -> assertEquals(100, photo.getWidth()),
                () -> assertEquals(200, photo.getHeight()),
                () -> assertEquals(Color.BLUE, photo.getColor())
        );
    }

    @Test
    public void testConstructorWithString() throws ColorException {
        Photo photo = new Photo(1, 2, 100, 200, "GREEN");
        assertEquals(Color.GREEN, photo.getColor());
    }

    @Test
    public void testConstructorsWithoutColor() {
        Photo photo = new Photo(800, 600);
        Photo defaultPhoto = new Photo();
        assertAll(
                () -> assertEquals(Color.RED, photo.getColor()),
                () -> assertEquals(Color.RED, defaultPhoto.getColor()),
                () -> assertEquals(1, defaultPhoto.getWidth()),
                () -> assertEquals(1, defaultPhoto.getHeight())
        );
    }

    @Test
    public void testSettersMovesResizeAndStretch() throws ColorException {
        Photo photo = new Photo();
        photo.setX(2);
        photo.setY(3);
        photo.setWidth(100);
        photo.setHeight(200);
        photo.setColor("GREEN");
        photo.moveRel(1, -1);
        photo.moveTo(new Point(5, 6));
        photo.resize(2.0);
        photo.stretch(0.5, 0.25);
        assertAll(
                () -> assertEquals(5, photo.getX()),
                () -> assertEquals(6, photo.getY()),
                () -> assertEquals(100, photo.getWidth()),
                () -> assertEquals(100, photo.getHeight()),
                () -> assertEquals(Color.GREEN, photo.getColor())
        );
    }

    @Test
    public void testWrongColor() {
        ColorException nullColor = assertThrows(ColorException.class, () -> new Photo(1, 2, 3, 4, (Color) null));
        ColorException nullString = assertThrows(ColorException.class, () -> new Photo(1, 2, 3, 4, (String) null));
        ColorException wrongString = assertThrows(ColorException.class, () -> new Photo(1, 2, 3, 4, "YELLOW"));
        assertAll(
                () -> assertEquals(ColorErrorCode.NULL_COLOR, nullColor.getErrorCode()),
                () -> assertEquals(ColorErrorCode.NULL_COLOR, nullString.getErrorCode()),
                () -> assertEquals(ColorErrorCode.WRONG_COLOR_STRING, wrongString.getErrorCode())
        );
    }

    @Test
    public void testEquals() throws ColorException {
        Photo first = new Photo(1, 2, 100, 200, Color.GREEN);
        Photo second = new Photo(1, 2, 100, 200, Color.GREEN);
        Photo third = new Photo(1, 2, 100, 200, Color.BLUE);
        assertAll(
                () -> assertEquals(first, second),
                () -> assertNotEquals(first, third)
        );
    }
}
