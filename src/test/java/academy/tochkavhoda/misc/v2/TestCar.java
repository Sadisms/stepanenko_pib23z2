package academy.tochkavhoda.misc.v2;

import academy.tochkavhoda.figures.v2.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCar {

    @Test
    public void testConstructorFull() {
        Car car = new Car(10, 20, "Toyota", 0xFF0000);
        assertAll(
                () -> assertEquals(10, car.getX()),
                () -> assertEquals(20, car.getY()),
                () -> assertEquals("Toyota", car.getModel()),
                () -> assertEquals(0xFF0000, car.getColor())
        );
    }

    @Test
    public void testConstructorModelColor() {
        Car car = new Car("BMW", 0x0000FF);
        assertAll(
                () -> assertEquals(0, car.getX()),
                () -> assertEquals(0, car.getY()),
                () -> assertEquals("BMW", car.getModel()),
                () -> assertEquals(0x0000FF, car.getColor())
        );
    }

    @Test
    public void testDefaultConstructor() {
        Car car = new Car();
        assertAll(
                () -> assertEquals(0, car.getX()),
                () -> assertEquals(0, car.getY()),
                () -> assertEquals("Unknown", car.getModel()),
                () -> assertEquals(0, car.getColor())
        );
    }

    @Test
    public void testMoveTo() {
        Car car = new Car(0, 0, "Lada", 1);
        car.moveTo(100, 200);
        assertAll(
                () -> assertEquals(100, car.getX()),
                () -> assertEquals(200, car.getY())
        );
    }

    @Test
    public void testMoveToPoint() {
        Car car = new Car(0, 0, "Lada", 1);
        car.moveTo(new Point(50, 75));
        assertAll(
                () -> assertEquals(50, car.getX()),
                () -> assertEquals(75, car.getY())
        );
    }

    @Test
    public void testMoveRel() {
        Car car = new Car(10, 20, "Audi", 2);
        car.moveRel(5, -5);
        assertAll(
                () -> assertEquals(15, car.getX()),
                () -> assertEquals(15, car.getY())
        );
    }

    @Test
    public void testSetModel() {
        Car car = new Car();
        car.setModel("Mercedes");
        assertEquals("Mercedes", car.getModel());
    }

    @Test
    public void testSetColor() {
        Car car = new Car();
        car.setColor(0x00FF00);
        assertEquals(0x00FF00, car.getColor());
    }

    @Test
    public void testEquals() {
        Car a = new Car(1, 2, "Honda", 3);
        Car b = new Car(1, 2, "Honda", 3);
        Car c = new Car(1, 2, "Honda", 4);
        assertAll(
                () -> assertEquals(a, b),
                () -> assertNotEquals(a, c)
        );
    }

    @Test
    public void testHashCode() {
        Car a = new Car(1, 2, "Honda", 3);
        Car b = new Car(1, 2, "Honda", 3);
        assertEquals(a.hashCode(), b.hashCode());
    }
}
