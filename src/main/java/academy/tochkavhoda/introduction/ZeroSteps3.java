package academy.tochkavhoda.introduction;

public class ZeroSteps3 {
    public int sum(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int val : array) {
            sum += val;
        }
        return sum;
    }

    public int mul(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int prod = 1;
        for (int val : array) {
            prod *= val;
        }
        return prod;
    }

    public int min(int[] array) {
        if (array == null || array.length == 0) {
            return Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        for (int val : array) {
            if (val < min) {
                min = val;
            }
        }
        return min;
    }

    public int max(int[] array) {
        if (array == null || array.length == 0) {
            return Integer.MIN_VALUE;
        }
        int max = Integer.MIN_VALUE;
        for (int val : array) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }

    public double average(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        double sum = 0;
        for (int val : array) {
            sum += val;
        }
        return sum / array.length;
    }

    public boolean isSortedDescendant(int[] array) {
        if (array == null || array.length == 0) {
            return true;
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] <= array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public void cube(int[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] * array[i] * array[i];
        }
    }

    public boolean find(int[] array, int value) {
        if (array == null) {
            return false;
        }
        for (int val : array) {
            if (val == value) {
                return true;
            }
        }
        return false;
    }

    public void reverse(int[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    public boolean isPalindrome(int[] array) {
        if (array == null || array.length == 0) {
            return true;
        }
        for (int i = 0; i < array.length / 2; i++) {
            if (array[i] != array[array.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    public void replaceBySquare(int[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                break;
            }
            array[i] = array[i] * array[i];
        }
    }

    public int mulNonZero(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int prod = 1;
        boolean hasNonZero = false;
        for (int val : array) {
            if (val != 0) {
                prod *= val;
                hasNonZero = true;
            }
        }
        if (!hasNonZero) {
            return 0;
        }
        return prod;
    }

    public boolean allPositive(int[] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        for (int val : array) {
            if (val <= 0) {
                return false;
            }
        }
        return true;
    }

    public boolean allEqual(int[] array) {
        if (array == null || array.length == 0) {
            return true;
        }
        int first = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] != first) {
                return false;
            }
        }
        return true;
    }

    public int greaterThanNeighbours(int[] array) {
        if (array == null || array.length < 3) {
            return -1;
        }
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i] > array[i - 1] && array[i] > array[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    public boolean neighboursAverage(double[] array) {
        if (array == null || array.length < 3) {
            return false;
        }
        double eps = 1E-6;
        for (int i = 1; i < array.length - 1; i++) {
            double avg = (array[i - 1] + array[i + 1]) / 2.0;
            if (Math.abs(array[i] - avg) < eps) {
                return true;
            }
        }
        return false;
    }

    public int sumBetween2Zeros(int[] array) {
        if (array == null) {
            return 0;
        }
        int first = -1;
        int second = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                if (first == -1) {
                    first = i;
                } else {
                    second = i;
                    break;
                }
            }
        }
        if (first != -1 && second != -1) {
            int sum = 0;
            for (int i = first + 1; i < second; i++) {
                sum += array[i];
            }
            return sum;
        }
        return 0;
    }

    public int sameAtPosition(int[] array1, int[] array2) {
        if (array1 == null || array2 == null) {
            return 0;
        }
        int count = 0;
        int len = Math.min(array1.length, array2.length);
        for (int i = 0; i < len; i++) {
            if (array1[i] == array2[i]) {
                count++;
            }
        }
        return count;
    }

    public boolean bothZeroAtPosition(int[] array1, int[] array2) {
        if (array1 == null || array2 == null) {
            return false;
        }
        int len = Math.min(array1.length, array2.length);
        for (int i = 0; i < len; i++) {
            if (array1[i] == 0 && array2[i] == 0) {
                return true;
            }
        }
        return false;
    }

    public void accumulatedSum(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            array[i] = sum;
        }
    }

    public int[] mergeArrays(int[] array1, int[] array2) {
        int[] result = new int[array1.length + array2.length];
        int i = 0, j = 0, k = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] <= array2[j]) {
                result[k++] = array1[i++];
            } else {
                result[k++] = array2[j++];
            }
        }
        while (i < array1.length) {
            result[k++] = array1[i++];
        }
        while (j < array2.length) {
            result[k++] = array2[j++];
        }
        return result;
    }

    public int insideCircle(int[] x, int[] y, int radius) {
        if (x == null || y == null || x.length != y.length) {
            return 0;
        }
        int count = 0;
        long rSq = (long) radius * radius;
        for (int i = 0; i < x.length; i++) {
            long distSq = (long) x[i] * x[i] + (long) y[i] * y[i];
            if (distSq <= rSq) {
                count++;
            }
        }
        return count;
    }

    public double scalarProduct(double[] array1, double[] array2) {
        if (array1 == null || array2 == null || array1.length != array2.length) {
            return 0;
        }
        double sum = 0;
        for (int i = 0; i < array1.length; i++) {
            sum += array1[i] * array2[i];
        }
        return sum;
    }
}

