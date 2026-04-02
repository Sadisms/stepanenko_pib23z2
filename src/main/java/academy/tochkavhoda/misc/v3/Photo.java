package academy.tochkavhoda.misc.v3;

import academy.tochkavhoda.colors.v3.Color;
import academy.tochkavhoda.colors.v3.ColorErrorCode;
import academy.tochkavhoda.colors.v3.ColorException;
import academy.tochkavhoda.iface.v3.Colored;
import academy.tochkavhoda.iface.v3.Movable;
import academy.tochkavhoda.iface.v3.Stretchable;
import java.util.Objects;

public class Photo implements Movable, Stretchable, Colored {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public Photo(int x, int y, int width, int height, Color color) throws ColorException {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setColor(color);
    }

    public Photo(int x, int y, int width, int height, String colorString) throws ColorException {
        this(x, y, width, height, Color.colorFromString(colorString));
    }

    public Photo(int width, int height) {
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
        this.color = Color.RED;
    }

    public Photo() {
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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
    public void resize(double ratio) {
        this.width = (int) (this.width * ratio);
        this.height = (int) (this.height * ratio);
    }

    @Override
    public void stretch(double xRatio, double yRatio) {
        this.width = (int) (this.width * xRatio);
        this.height = (int) (this.height * yRatio);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return x == photo.x && y == photo.y && width == photo.width
                && height == photo.height && color == photo.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height, color);
    }
}
