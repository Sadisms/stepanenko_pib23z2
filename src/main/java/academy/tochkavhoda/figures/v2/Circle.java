package academy.tochkavhoda.figures.v2;

import java.util.Objects;

public class Circle extends Figure {
    private Point center;
    private int radius;

    public Circle(Point center, int radius) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = radius;
    }

    public Circle(int xCenter, int yCenter, int radius) {
        this.center = new Point(xCenter, yCenter);
        this.radius = radius;
    }

    public Circle(int radius) {
        this.center = new Point(0, 0);
        this.radius = radius;
    }

    public Circle() {
        this.center = new Point(0, 0);
        this.radius = 1;
    }

    public Point getCenter() {
        return new Point(center.getX(), center.getY());
    }

    public int getRadius() {
        return radius;
    }

    public void setCenter(Point center) {
        this.center = new Point(center.getX(), center.getY());
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void moveTo(int x, int y) {
        center.moveTo(x, y);
    }

    @Override
    public void moveRel(int dx, int dy) {
        center.moveRel(dx, dy);
    }

    @Override
    public void resize(double ratio) {
        radius = (int) (radius * ratio);
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public boolean isInside(int x, int y) {
        double distSq = Math.pow(x - center.getX(), 2) + Math.pow(y - center.getY(), 2);
        return distSq <= (long) radius * radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return radius == circle.radius && Objects.equals(center, circle.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, radius);
    }
}

