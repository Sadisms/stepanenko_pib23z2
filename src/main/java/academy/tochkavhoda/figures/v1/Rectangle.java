package academy.tochkavhoda.figures.v1;

import org.w3c.dom.css.Rect;

import java.util.Objects;

public class Rectangle {
    private Point leftTop;
    private Point rightBottom;

    public Rectangle(Point leftTop, Point rightBottom) {
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom) {
        this.leftTop = new Point(xLeft, yTop);
        this.rightBottom = new Point(xRight, yBottom);
    }

    public Rectangle(int length, int width) {
        this.leftTop = new Point(0, -width);
        this.rightBottom = new Point(length, 0);
    }

    public Rectangle() {
        this.leftTop = new Point(0, -1);
        this.rightBottom = new Point(1, 0);
    }

    public Point getTopLeft() {
        return this.leftTop;
    }

    public Point getBottomRight() {
        return this.rightBottom;
    }

    public void setTopLeft(Point topLeft) {
        this.leftTop = topLeft;
    }

    public void setBottomRight(Point bottomRight) {
        this.rightBottom = bottomRight;
    }

    public int getLength() {
        return this.rightBottom.getX() - this.leftTop.getX();
    }

    public int getWidth() {
        return this.rightBottom.getY() - this.leftTop.getY();
    }

    public void moveTo(int x, int y) {
        int length = this.getLength();
        int width = this.getWidth();
        this.leftTop = new Point(x, y);
        this.rightBottom = new Point(x + length, y + width);
    }

    public void moveTo(Point point) {
        int length = this.getLength();
        int width = this.getWidth();
        this.leftTop = point;
        this.rightBottom = new Point(point.getX() + length, point.getY() + width);
    }

    public void moveRel(int dx, int dy) {
        this.leftTop = new Point(
                this.leftTop.getX() + dx,
                this.leftTop.getY() + dy
        );
        this.rightBottom = new Point(
                this.rightBottom.getX() + dx,
                this.rightBottom.getY() + dy
        );
    }

    public void resize(double ratio) {
        int newLength = (int)(this.getLength() * ratio);
        int newWidth = (int)(this.getWidth() * ratio);
        int xLeft = this.leftTop.getX();
        int yTop = this.leftTop.getY();

        this.rightBottom = new Point(xLeft + newLength, yTop + newWidth);
    }

    public void stretch(double xRatio, double yRatio) {
        int newLength = (int)(this.getLength() * xRatio);
        int newWidth = (int)(this.getWidth() * yRatio);

        int xLeft = this.leftTop.getX();
        int yTop = this.leftTop.getY();

        this.rightBottom = new Point(xLeft + newLength, yTop + newWidth);
    }

    public double getArea() {
        return this.getLength() * this.getWidth();
    }

    public double getPerimeter() {
        return 2 * (this.getLength() + this.getWidth());
    }

    public boolean isInside(int x, int y) {
        return x >= this.leftTop.getX() &&
                x <= this.rightBottom.getX() &&
                y >= this.leftTop.getY() &&
                y <= this.rightBottom.getY();
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    public boolean isInside(Rectangle rectangle) {
        return this.leftTop.getX() <= rectangle.leftTop.getX() &&
                this.leftTop.getY() <= rectangle.leftTop.getY() &&
                this.rightBottom.getX() >= rectangle.rightBottom.getX() &&
                this.rightBottom.getY() >= rectangle.rightBottom.getY();
    }

    public boolean isIntersects(Rectangle rectangle) {
        boolean noIntersectX = this.rightBottom.getX() < rectangle.leftTop.getX() ||
                this.leftTop.getX() > rectangle.rightBottom.getX();

        boolean noIntersectY = this.rightBottom.getY() < rectangle.leftTop.getY() ||
                this.leftTop.getY() > rectangle.rightBottom.getY();

        return !(noIntersectX || noIntersectY);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(leftTop, rectangle.leftTop) && Objects.equals(rightBottom, rectangle.rightBottom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftTop, rightBottom);
    }
}