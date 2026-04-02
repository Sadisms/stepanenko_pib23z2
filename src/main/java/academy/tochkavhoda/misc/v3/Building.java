package academy.tochkavhoda.misc.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorErrorCode;
import academy.tochkavhoda.colors.v3.ColorException;
import academy.tochkavhoda.iface.v3.Colored;
import academy.tochkavhoda.iface.v3.HasArea;
import academy.tochkavhoda.iface.v3.Movable;
import java.util.Objects;

public class Building implements Movable, HasArea, Colored {
    private int x;
    private int y;
    private int floorWidth;
    private int floorDepth;
    private Color color;

    public Building(int x, int y, int floorWidth, int floorDepth, Color color) throws ColorException {
        this.x = x;
        this.y = y;
        this.floorWidth = floorWidth;
        this.floorDepth = floorDepth;
        setColor(color);
    }

    public Building(int x, int y, int floorWidth, int floorDepth, String colorString) throws ColorException {
        this(x, y, floorWidth, floorDepth, Color.colorFromString(colorString));
    }

    public Building(int floorWidth, int floorDepth) {
        this.x = 0;
        this.y = 0;
        this.floorWidth = floorWidth;
        this.floorDepth = floorDepth;
        this.color = Color.RED;
    }

    public Building() {
        this(1, 1);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getFloorWidth() {
        return floorWidth;
    }

    public void setFloorWidth(int floorWidth) {
        this.floorWidth = floorWidth;
    }

    public int getFloorDepth() {
        return floorDepth;
    }

    public void setFloorDepth(int floorDepth) {
        this.floorDepth = floorDepth;
    }

    @Override
    public void setColor(Color color) throws ColorException {
        if (color == null) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }
        this.color = color;
    }

    @Override
    public void setColor(String colorString) throws ColorException {
        setColor(Color.colorFromString(colorString));
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void moveRel(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public double getArea() {
        return (double) floorWidth * floorDepth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return x == building.x && y == building.y
                && floorWidth == building.floorWidth
                && floorDepth == building.floorDepth
                && color == building.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, floorWidth, floorDepth, color);
    }
}
