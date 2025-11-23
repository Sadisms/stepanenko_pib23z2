package academy.tochkavhoda.introduction;

public class ZeroSteps4 {

    public int min(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        if (matrix == null || matrix.length == 0) {
            return min;
        }
        for (int[] row : matrix) {
            for (int element : row) {
                if (element < min) {
                    min = element;
                }
            }
        }
        return min;
    }

    public int max(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        if (matrix == null || matrix.length == 0) {
            return max;
        }
        for (int[] row : matrix) {
            for (int element : row) {
                if (element > max) {
                    max = element;
                }
            }
        }
        return max;
    }

    public boolean find(int[][] matrix, int value) {
        if (matrix == null) {
            return false;
        }
        for (int[] row : matrix) {
            for (int element : row) {
                if (element == value) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSortedDescendant(int[][] matrix) {
        if (matrix == null) {
            return true;
        }
        for (int[] row : matrix) {
            for (int i = 0; i < row.length - 1; i++) {
                if (row[i] <= row[i + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hasNoZeroRows(int[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        int count = 0;
        for (int[] row : matrix) {
            boolean hasZero = false;
            for (int element : row) {
                if (element == 0) {
                    hasZero = true;
                    break;
                }
            }
            if (!hasZero) {
                count++;
            }
        }
        return count;
    }

    public boolean hasFullZeroRow(int[][] matrix) {
        if (matrix == null) {
            return false;
        }
        for (int[] row : matrix) {
            if (row.length == 0) {
                continue;
            }
            boolean isFullZero = true;
            for (int element : row) {
                if (element != 0) {
                    isFullZero = false;
                    break;
                }
            }
            if (isFullZero) {
                return true;
            }
        }
        return false;
    }

    public boolean isSymmetric(int[][] matrix) {
        if (matrix == null) {
            return false;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int mainDiagonalMax(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        if (matrix == null || matrix.length == 0) {
            return max;
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length > i) {
                if (matrix[i][i] > max) {
                    max = matrix[i][i];
                }
            }
        }
        return max;
    }

    public int mainDiagonalPositivesSum(int[][] matrix) {
        int sum = 0;
        if (matrix == null) {
            return 0;
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length > i) {
                if (matrix[i][i] > 0) {
                    sum += matrix[i][i];
                }
            }
        }
        return sum;
    }

    public int secondaryDiagonalMax(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        if (matrix == null || matrix.length == 0) {
            return max;
        }
        int len = matrix.length;
        for (int i = 0; i < len; i++) {
            int colIndex = len - 1 - i;
            if (matrix[i].length > colIndex) {
                if (matrix[i][colIndex] > max) {
                    max = matrix[i][colIndex];
                }
            }
        }
        return max;
    }

    public int secondaryDiagonalPositivesSum(int[][] matrix) {
        int sum = 0;
        if (matrix == null) {
            return 0;
        }
        int len = matrix.length;
        for (int i = 0; i < len; i++) {
            int colIndex = len - 1 - i;
            if (matrix[i].length > colIndex) {
                if (matrix[i][colIndex] > 0) {
                    sum += matrix[i][colIndex];
                }
            }
        }
        return sum;
    }

    public int[][] matrixSum(int[][] matrix1, int[][] matrix2) {
        int rows = matrix1.length;
        int[][] result = new int[rows][];
        for (int i = 0; i < rows; i++) {
            int length = matrix1[i].length;
            result[i] = new int[length];
            for (int j = 0; j < length; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    public void transpose(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public void interchange(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        for (int i = 0; i < matrix.length - 1; i += 2) {
            int[] temp = matrix[i];
            matrix[i] = matrix[i + 1];
            matrix[i + 1] = temp;
        }
    }

    public int[] toLinearByRow(int[][] matrix) {
        if (matrix == null) {
            return new int[0];
        }
        int size = 0;
        for (int[] row : matrix) {
            size += row.length;
        }
        int[] result = new int[size];
        int index = 0;
        for (int[] row : matrix) {
            for (int element : row) {
                result[index++] = element;
            }
        }
        return result;
    }

    public int[] toLinearByColumn(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int len = matrix.length;
        int[] result = new int[len * len];
        int index = 0;
        for (int j = 0; j < len; j++) {
            for (int i = 0; i < len; i++) {
                result[index++] = matrix[i][j];
            }
        }
        return result;
    }

    public int[] toLinearByRowUpperTriangle(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int len = matrix.length;
        int size = len * (len + 1) / 2;
        int[] result = new int[size];
        int index = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                result[index++] = matrix[i][j];
            }
        }
        return result;
    }

    public int sumUntilNotFoundInRow(int[][] matrix, int barrier) {
        int sum = 0;
        if (matrix == null) {
            return 0;
        }
        for (int[] row : matrix) {
            for (int element : row) {
                if (element == barrier) {
                    break;
                }
                sum += element;
            }
        }
        return sum;
    }

    public int sumUntilNotFound(int[][] matrix, int barrier) {
        int sum = 0;
        if (matrix == null) {
            return 0;
        }
        for (int[] row : matrix) {
            for (int element : row) {
                if (element == barrier) {
                    return sum;
                }
                sum += element;
            }
        }
        return sum;
    }
}
