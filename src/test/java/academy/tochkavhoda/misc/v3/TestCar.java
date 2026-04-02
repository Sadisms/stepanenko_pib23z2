package academy.tochkavhoda.misc.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorErrorCode;
import academy.tochkavhoda.colors.v3.ColorException;
import academy.tochkavhoda.figures.v3.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCar {

    @Test
    public void testConstructorWithColor() throws ColorException {
        Car car = new Car(10, 20, "Toyota", Color.GREEN);
        assertAll(
                () -> assertEquals(10, car.getX()),
                () -> assertEquals(20, car.getY()),
                () -> assertEquals("Toyota", car.getModel()),
                () -> assertEquals(Color.GREEN, car.getColor())
        );
    }

    @Test
    public void testConstructorWithString() throws ColorException {
        Car car = new Car("BMW", "BLUE");
        assertAll(
                () -> assertEquals(0, car.getX()),
                () -> assertEquals(0, car.getY()),
                () -> assertEquals("BMW", car.getModel()),
                () -> assertEquals(Color.BLUE, car.getColor())
        );
    }

    @Test
    public void testDefaultConstructor() {
        Car car = new Car();
        assertAll(
                () -> assertEquals(0, car.getX()),
                () -> assertEquals(0, car.getY()),
                () -> assertEquals("Unknown", car.getModel()),
                () -> assertEquals(Color.RED, car.getColor())
        );
    }

    @Test
    public void testSettersAndMoves() throws ColorException {
        Car car = new Car();
        car.setX(1);
        car.setY(2);
        car.setModel("Audi");
        car.setColor("GREEN");
        car.moveRel(5, -1);
        car.moveTo(new Point(9, 8));
        assertAll(
                () -> assertEquals(9, car.getX()),
                () -> assertEquals(8, car.getY()),
                () -> assertEquals("Audi", car.getModel()),
                () -> assertEquals(Color.GREEN, car.getColor())
        );
    }

    @Test
    public void testWrongColor() {
        ColorException nullColor = assertThrows(ColorException.class, () -> new Car(1, 2, "Car", (Color) null));
        ColorException nullString = assertThrows(ColorException.class, () -> new Car(1, 2, "Car", (String) null));
        ColorException wrongString = assertThrows(ColorException.class, () -> new Car(1, 2, "Car", "YELLOW"));
        assertAll(
                () -> assertEquals(ColorErrorCode.NULL_COLOR, nullColor.getErrorCode()),
                () -> assertEquals(ColorErrorCode.NULL_COLOR, nullString.getErrorCode()),
                () -> assertEquals(ColorErrorCode.WRONG_COLOR_STRING, wrongString.getErrorCode())
        );
    }

    @Test
    public void testEquals() throws ColorException {
        Car first = new Car(1, 2, "Honda", Color.GREEN);
        Car second = new Car(1, 2, "Honda", Color.GREEN);
        Car third = new Car(1, 2, "Honda", Color.BLUE);
        assertAll(
                () -> assertEquals(first, second),
                () -> assertNotEquals(first, third)
        );
    }
}
