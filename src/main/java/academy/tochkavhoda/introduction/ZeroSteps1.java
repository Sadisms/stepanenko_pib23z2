package academy.tochkavhoda.introduction;

public class ZeroSteps1 {
    public int sum(int x, int y) {
        return x + y;
    }

    public int mul(int x, int y) {
        return x * y;
    }

    public int div(int x, int y) {
        return x / y;
    }

    public int mod(int x, int y) {
        return x % y;
    }

    public boolean isEqual(int x, int y) {
        return x == y;
    }

    public boolean isGreater(int x, int y) {
        return x > y;
    }

    public double calculateTriangleSquare(int side1, int side2) {
        return 0.5 * side1 * side2;
    }

    public double calculateTrianglePerimeter(int side1, int side2) {
        return side1 + side2 + Math.sqrt(side1 * side1 + side2 * side2);
    }

    public int reverseNumber(int number) {
        int digit1 = number / 100;
        int digit2 = (number / 10) % 10;
        int digit3 = number % 10;
        return digit3 * 100 + digit2 * 10 + digit1;
    }

    public long calculate15Degree(int number) {
        long n = number;
        long n2 = n * n;
        long n3 = n2 * n;
        long n5 = n3 * n2;
        long n10 = n5 * n5;
        long n15 = n10 * n5;
        return n15;
    }

    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y) {
        return x >= xLeft && x <= xRight && y >= yTop && y <= yBottom;
    }

    public double sqrtAbs(int number) {
        return Math.sqrt(Math.abs(number));
    }

    public boolean isTriangleExist(int side1, int side2, int side3) {
        return (side1 + side2 > side3) && (side1 + side3 > side2) && (side2 + side3 > side1);
    }

    public int getDigitsProduction(int number) {
        int d1 = number / 1000;
        int d2 = (number / 100) % 10;
        int d3 = (number / 10) % 10;
        int d4 = number % 10;
        return d1 * d2 * d3 * d4;
    }

    public boolean isCircleInsideSquare(int radius, int side) {
        return (radius * 2) < side;
    }

    public char getCharByCode(short code) {
        return (char) code;
    }

    public short getCodeByChar(char character) {
        return (short) character;
    }

    public char getCharByCodeAndOffset(char ch, short offset) {
        return (char) (ch + offset);
    }

    public boolean isGreaterOrEqual(char ch1, char ch2) {
        return ch1 >= ch2;
    }

    public char getAverageChar(char ch1, char ch2) {
        return (char) ((ch1 + ch2) / 2);
    }
}
