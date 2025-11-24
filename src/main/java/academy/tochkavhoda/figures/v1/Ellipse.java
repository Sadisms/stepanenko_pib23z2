package academy.tochkavhoda.figures.v1;

import java.util.Objects;

public class Ellipse {
    private Point center;
    private int xAxis;
    private int yAxis;

    public Ellipse(Point center, int xAxis, int yAxis) {
        this.center = new Point(center.getX(), center.getY());
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public Ellipse(int xCenter, int yCenter, int xAxis, int yAxis) {
        this.center = new Point(xCenter, yCenter);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public Ellipse(int xAxis, int yAxis) {
        this.center = new Point(0, 0);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public Ellipse() {
        this.center = new Point(0, 0);
        this.xAxis = 1;
        this.yAxis = 1;
    }

    public Point getCenter() {
        return new Point(center.getX(), center.getY());
    }

    public int getXAxis() {
        return xAxis;
    }

    public int getYAxis() {
        return yAxis;
    }

    public void setXAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public void setYAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    public void setCenter(Point center) {
        this.center = new Point(center.getX(), center.getY());
    }

    public void moveTo(int x, int y) {
        center.moveTo(x, y);
    }

    public void moveTo(Point point) {
        moveTo(point.getX(), point.getY());
    }

    public void moveRel(int dx, int dy) {
        center.moveRel(dx, dy);
    }

    public void resize(double ratio) {
        xAxis = (int) (xAxis * ratio);
        yAxis = (int) (yAxis * ratio);
    }

    public void stretch(double xRatio, double yRatio) {
        xAxis = (int) (xAxis * xRatio);
        yAxis = (int) (yAxis * yRatio);
    }

    public double getArea() {
        return Math.PI * xAxis * yAxis / 4.0; // Axes are typically full lengths (diameters) or semi-axes (radii)?
        // Task says "размеры осей". Usually this means full length (diameter-like), but could mean semi-axis.
        // "Square with side 1, circle with radius 1".
        // "Ellipse with center at (0,0) and unit axes". If axes are 1, area should be like circle with d=1?
        // Let's assume axes are 2*a and 2*b (diameters).
        // Formula for area: PI * a * b. If xAxis is 2a, then a = xAxis/2.
        // Area = PI * (xAxis/2) * (yAxis/2) = PI * xAxis * yAxis / 4.
        // Re-reading: "Ellipse(int xAxis, int yAxis) ... center at (0,0) with specified axes sizes".
        // "Ellipse() ... center (0,0) and unit axes".
        // Let's assume xAxis and yAxis are the full lengths of the axes (like width and height of bounding box).
    }

    public double getPerimeter() {
        // P = 2 * PI * sqrt((a^2 + b^2) / 2) approximation?
        // Task: P= 2π * sqrt((XAxis2+YAxis2)/8)
        // If XAxis is 2a, XAxis^2 = 4a^2. (4a^2 + 4b^2)/8 = (a^2+b^2)/2.
        // So yes, XAxis and YAxis are full lengths.
        return 2 * Math.PI * Math.sqrt((Math.pow(xAxis, 2) + Math.pow(yAxis, 2)) / 8.0);
    }

    public boolean isInside(int x, int y) {
        // (x-x0)^2 / a^2 + (y-y0)^2 / b^2 <= 1
        // a = xAxis / 2.0, b = yAxis / 2.0
        double a = xAxis / 2.0;
        double b = yAxis / 2.0;
        return (Math.pow(x - center.getX(), 2) / (a * a) + Math.pow(y - center.getY(), 2) / (b * b)) <= 1.0;
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ellipse ellipse = (Ellipse) o;
        return xAxis == ellipse.xAxis && yAxis == ellipse.yAxis && Objects.equals(center, ellipse.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, xAxis, yAxis);
    }
}

