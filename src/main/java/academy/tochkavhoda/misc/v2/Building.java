package academy.tochkavhoda.misc.v2;

import academy.tochkavhoda.iface.v2.Colored;
import academy.tochkavhoda.iface.v2.HasArea;
import academy.tochkavhoda.iface.v2.Movable;
import java.util.Objects;

public class Building implements Movable, HasArea, Colored {
    private int x;
    private int y;
    private int floorWidth;
    private int floorDepth;
    private int color;

    public Building(int x, int y, int floorWidth, int floorDepth, int color) {
        this.x = x;
        this.y = y;
        this.floorWidth = floorWidth;
        this.floorDepth = floorDepth;
        this.color = color;
    }

    public Building(int floorWidth, int floorDepth) {
        this(0, 0, floorWidth, floorDepth, 0);
    }

    public Building() {
        this(0, 0, 1, 1, 0);
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
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
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
