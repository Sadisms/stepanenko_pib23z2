package academy.tochkavhoda.misc.v2;

import academy.tochkavhoda.iface.v2.Colored;
import academy.tochkavhoda.iface.v2.Movable;
import java.util.Objects;

public class Car implements Movable, Colored {
    private int x;
    private int y;
    private String model;
    private int color;

    public Car(int x, int y, String model, int color) {
        this.x = x;
        this.y = y;
        this.model = model;
        this.color = color;
    }

    public Car(String model, int color) {
        this(0, 0, model, color);
    }

    public Car() {
        this(0, 0, "Unknown", 0);
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return x == car.x && y == car.y && color == car.color && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, model, color);
    }
}
