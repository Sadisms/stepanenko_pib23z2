package academy.tochkavhoda.colors.v3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestColor {

    @Test
    public void testColorFromString() throws ColorException {
        assertAll(
                () -> assertEquals(Color.RED, Color.colorFromString("RED")),
                () -> assertEquals(Color.GREEN, Color.colorFromString("GREEN")),
                () -> assertEquals(Color.BLUE, Color.colorFromString("BLUE"))
        );
    }

    @Test
    public void testColorFromStringErrors() {
        ColorException nullException = assertThrows(ColorException.class, () -> Color.colorFromString(null));
        ColorException wrongException = assertThrows(ColorException.class, () -> Color.colorFromString("YELLOW"));
        assertAll(
                () -> assertEquals(ColorErrorCode.NULL_COLOR, nullException.getErrorCode()),
                () -> assertEquals(ColorErrorCode.WRONG_COLOR_STRING, wrongException.getErrorCode())
        );
    }

    @Test
    public void testErrorStringsAndExceptionMessage() {
        ColorException exception = new ColorException(ColorErrorCode.NULL_COLOR);
        assertAll(
                () -> assertEquals("Wrong color string", ColorErrorCode.WRONG_COLOR_STRING.getErrorString()),
                () -> assertEquals("Color must not be null", ColorErrorCode.NULL_COLOR.getErrorString()),
                () -> assertEquals(ColorErrorCode.NULL_COLOR, exception.getErrorCode()),
                () -> assertEquals("Color must not be null", exception.getMessage())
        );
    }
}
