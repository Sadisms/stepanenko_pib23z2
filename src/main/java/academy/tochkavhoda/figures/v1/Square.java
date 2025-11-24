package academy.tochkavhoda.figures.v1;

import java.util.Objects;

public class Square {
    private Point topLeft;
    private int size;

    public Square(Point leftTop, int size) {
        this.topLeft = new Point(leftTop.getX(), leftTop.getY());
        this.size = size;
    }

    public Square(int xLeft, int yTop, int size) {
        this.topLeft = new Point(xLeft, yTop);
        this.size = size;
    }

    public Square(int size) {
        this.topLeft = new Point(0, -size);
        this.size = size;
    }

    public Square() {
        this.topLeft = new Point(0, -1);
        this.size = 1;
    }

    public Point getTopLeft() {
        return new Point(topLeft.getX(), topLeft.getY());
    }

    public Point getBottomRight() {
        return new Point(topLeft.getX() + size, topLeft.getY() + size);
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = new Point(topLeft.getX(), topLeft.getY());
    }

    public int getLength() {
        return size;
    }

    public void moveTo(int x, int y) {
        topLeft.moveTo(x, y);
    }

    public void moveTo(Point point) {
        moveTo(point.getX(), point.getY());
    }

    public void moveRel(int dx, int dy) {
        topLeft.moveRel(dx, dy);
    }

    public void resize(double ratio) {
        size = (int) (size * ratio);
    }

    public double getArea() {
        return size * size;
    }

    public double getPerimeter() {
        return 4 * size;
    }

    public boolean isInside(int x, int y) {
        return x >= topLeft.getX() && x <= topLeft.getX() + size &&
               y >= topLeft.getY() && y <= topLeft.getY() + size;
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    public boolean isIntersects(Square square) {
        int thisRight = topLeft.getX() + size;
        int thisBottom = topLeft.getY() + size;
        int otherRight = square.topLeft.getX() + square.size;
        int otherBottom = square.topLeft.getY() + square.size;

        return this.topLeft.getX() <= otherRight &&
               thisRight >= square.topLeft.getX() &&
               this.topLeft.getY() <= otherBottom &&
               thisBottom >= square.topLeft.getY();
    }

    public boolean isInside(Square square) {
        int thisRight = topLeft.getX() + size;
        int thisBottom = topLeft.getY() + size;
        int otherRight = square.topLeft.getX() + square.size;
        int otherBottom = square.topLeft.getY() + square.size;

        return this.topLeft.getX() <= square.topLeft.getX() &&
               thisRight >= otherRight &&
               this.topLeft.getY() <= square.topLeft.getY() &&
               thisBottom >= otherBottom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return size == square.size && Objects.equals(topLeft, square.topLeft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeft, size);
    }
}
