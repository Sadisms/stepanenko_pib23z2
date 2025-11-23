package academy.tochkavhoda.introduction;

import java.util.Random;

public class ZeroSteps2 {
    public int sumSquares(int count) {
        int sum = 0;
        for (int i = 1; i <= count; i++) {
            sum += i * i;
        }
        return sum;
    }

    public int sumOdds(int count) {
        int sum = 0;
        for (int i = 1; i <= count; i++) {
            if (i % 2 != 0) {
                sum += i;
            }
        }
        return sum;
    }

    public double sumInverses(int count) {
        double sum = 0;
        for (int i = 1; i <= count; i++) {
            sum += 1.0 / i;
        }
        return sum;
    }

    public long factorial(int count) {
        long fact = 1;
        for (int i = 1; i <= count; i++) {
            fact *= i;
        }
        return fact;
    }

    public int prodDigits(int value) {
        int prod = 1;
        int temp = value;
        if (temp == 0) return 0;
        while (temp > 0) {
            prod *= temp % 10;
            temp /= 10;
        }
        return prod;
    }

    public int fibonacci(int number) {
        if (number == 0) return 0;
        if (number == 1) return 1;
        int a = 0;
        int b = 1;
        for (int i = 2; i <= number; i++) {
            int next = a + b;
            a = b;
            b = next;
        }
        return b;
    }

    public long sum2Powers(int max) {
        long sum = 0;
        long current = 1;
        for (int i = 0; i <= max; i++) {
            sum += current;
            current *= 2;
        }
        return sum;
    }

    public int sumSquaresWithBarrier(int count, int barrier) {
        int sum = 0;
        for (int i = 1; i <= count; i++) {
            sum += i * i;
            if (sum > barrier) {
                return sum;
            }
        }
        return sum;
    }

    public int sumPairProd(int count1, int count2) {
        int sum = 0;
        for (int i = 1; i <= count1; i++) {
            for (int j = 1; j <= count2; j++) {
                sum += i * j;
            }
        }
        return sum;
    }

    public int sumPairProdWithBarrier(int count1, int count2, int barrier) {
        int sum = 0;
        for (int i = 1; i <= count1; i++) {
            for (int j = 1; j <= count2; j++) {
                if (i * j < barrier) {
                    sum += i * j;
                }
            }
        }
        return sum;
    }

    public double sumInversePairProd(int count1, int count2) {
        double sum = 0;
        for (int i = 1; i <= count1; i++) {
            for (int j = 1; j <= count2; j++) {
                sum += 1.0 / (i * j);
            }
        }
        return sum;
    }

    public int sumTripleProd(int count1, int count2, int count3) {
        int sum = 0;
        for (int i = 1; i <= count1; i++) {
            for (int j = 1; j <= count2; j++) {
                for (int k = 1; k <= count3; k++) {
                    sum += i * j * k;
                }
            }
        }
        return sum;
    }

    public double calculateE() {
        double e = 1.0;
        double item = 1.0;
        int n = 1;
        while (true) {
            item /= n;
            if (item < 1E-6) {
                break;
            }
            e += item;
            n++;
        }
        return e;
    }

    public double calculatePi() {
        double pi = 0;
        double item = 1.0;
        int n = 1;
        int sign = 1;
        
        while (Math.abs(item) >= 1E-8) {
            pi += sign * item;
            n += 2;
            item = 1.0 / n;
            sign = -sign;
        }

        pi = 0;
        double denominator = 1;
        sign = 1;
        double term = 1.0 / denominator;
        
        while (Math.abs(term) >= 1E-8) {
            pi += sign * term;
            denominator += 2;
            term = 1.0 / denominator;
            sign = -sign;
        }
        
        return 4 * pi;
    }

    public double calculateCircleSquare(double length, int count) {
        double centerX = length / 2.0;
        double centerY = length / 2.0;
        double radius = length / 2.0;
        double radiusSq = radius * radius;
        int k = 0;
        Random random = new Random();
        
        for (int i = 0; i < count; i++) {
            double x = random.nextDouble() * length;
            double y = random.nextDouble() * length;
            
            double dx = x - centerX;
            double dy = y - centerY;
            
            if (dx * dx + dy * dy <= radiusSq) {
                k++;
            }
        }
        
        return length * length * k / count;
    }
}

