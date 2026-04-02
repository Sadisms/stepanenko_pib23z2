package academy.tochkavhoda.misc.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorErrorCode;
import academy.tochkavhoda.colors.v3.ColorException;
import academy.tochkavhoda.figures.v3.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBuilding {

    @Test
    public void testConstructorWithColor() throws ColorException {
        Building building = new Building(10, 20, 50, 30, Color.GREEN);
        assertAll(
                () -> assertEquals(10, building.getX()),
                () -> assertEquals(20, building.getY()),
                () -> assertEquals(50, building.getFloorWidth()),
                () -> assertEquals(30, building.getFloorDepth()),
                () -> assertEquals(Color.GREEN, building.getColor())
        );
    }

    @Test
    public void testConstructorWithString() throws ColorException {
        Building building = new Building(10, 20, 50, 30, "GREEN");
        assertEquals(Color.GREEN, building.getColor());
    }

    @Test
    public void testConstructorsWithoutColor() {
        Building sized = new Building(50, 30);
        Building defaultBuilding = new Building();
        assertAll(
                () -> assertEquals(Color.RED, sized.getColor()),
                () -> assertEquals(50.0 * 30, sized.getArea()),
                () -> assertEquals(Color.RED, defaultBuilding.getColor()),
                () -> assertEquals(1, defaultBuilding.getFloorWidth()),
                () -> assertEquals(1, defaultBuilding.getFloorDepth())
        );
    }

    @Test
    public void testSettersAndMoves() throws ColorException {
        Building building = new Building();
        building.setX(7);
        building.setY(8);
        building.setFloorWidth(20);
        building.setFloorDepth(10);
        building.setColor(Color.BLUE);
        building.moveRel(3, 2);
        building.moveTo(new Point(1, 2));
        assertAll(
                () -> assertEquals(1, building.getX()),
                () -> assertEquals(2, building.getY()),
                () -> assertEquals(20, building.getFloorWidth()),
                () -> assertEquals(10, building.getFloorDepth()),
                () -> assertEquals(200.0, building.getArea()),
                () -> assertEquals(Color.BLUE, building.getColor())
        );
    }

    @Test
    public void testSetColorString() throws ColorException {
        Building building = new Building();
        building.setColor("GREEN");
        assertEquals(Color.GREEN, building.getColor());
    }

    @Test
    public void testWrongColor() {
        ColorException nullColor = assertThrows(ColorException.class, () -> new Building(1, 2, 3, 4, (Color) null));
        ColorException nullString = assertThrows(ColorException.class, () -> new Building(1, 2, 3, 4, (String) null));
        ColorException wrongString = assertThrows(ColorException.class, () -> new Building(1, 2, 3, 4, "YELLOW"));
        assertAll(
                () -> assertEquals(ColorErrorCode.NULL_COLOR, nullColor.getErrorCode()),
                () -> assertEquals(ColorErrorCode.NULL_COLOR, nullString.getErrorCode()),
                () -> assertEquals(ColorErrorCode.WRONG_COLOR_STRING, wrongString.getErrorCode())
        );
    }

    @Test
    public void testEquals() throws ColorException {
        Building first = new Building(1, 2, 10, 20, Color.GREEN);
        Building second = new Building(1, 2, 10, 20, Color.GREEN);
        Building third = new Building(1, 2, 10, 20, Color.BLUE);
        assertAll(
                () -> assertEquals(first, second),
                () -> assertNotEquals(first, third)
        );
    }
}
