package academy.tochkavhoda.matrix;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class MatrixNonSimilarRows {

    private final int[][] matrix;

    public MatrixNonSimilarRows(int[][] matrix) {
        this.matrix = matrix;
    }

    public Set<int[]> getNonSimilarRows() {
        Map<Set<Integer>, int[]> firstOccurrence = new LinkedHashMap<>();
        for (int[] row : matrix) {
            Set<Integer> rowSet = new HashSet<>();
            for (int val : row) {
                rowSet.add(val);
            }
            if (!firstOccurrence.containsKey(rowSet)) {
                firstOccurrence.put(rowSet, row);
            }
        }
        return new LinkedHashSet<>(firstOccurrence.values());
    }
}
