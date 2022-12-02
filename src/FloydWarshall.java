import java.util.ArrayList;

public class FloydWarshall {
    public static void main(String[] args) {
        int inf = 9999; // represents infinity

        // Problem set 8 #1:
        String[] matrixLabels = { "", "A", "B", "C", "D", "E" };
        int[][] weightMatrix = {
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, inf, 3, inf, inf },
                { 0, -4, 0, 2, inf, inf },
                { 0, inf, -1, 0, 7, 5 },
                { 0, inf, 1, inf, 0, 10 },
                { 0, inf, inf, inf, -8, 0 }
        };

        fw(weightMatrix, matrixLabels);
    }

    public static void fw(int[][] weight, String[] labels) {
        ArrayList<int[][]> d = new ArrayList<>();
        int n = weight.length;
        d.add(weight);

        // Prints the zeroth (input) matrix
        System.out.println("Matrix: 0");
        printMatrix(d.get(0), labels);

        int k;
        int i;
        int j;

        for (k = 1; k < n; k++) {
            d.add(k, new int[n][n]);
            for (i = 1; i < n; i++) {
                for (j = 1; j < n; j++) {
                    if (d.get(k - 1)[i][j] <= (d.get(k - 1)[i][k] + d.get(k - 1)[k][j])) {
                        d.get(k)[i][j] = d.get(k - 1)[i][j];
                    } else {
                        d.get(k)[i][j] = d.get(k - 1)[i][k] + d.get(k - 1)[k][j];
                    }
                }
            }

            // Prints the k'th matrix
            System.out.println("Matrix: " + k);
            printMatrix(d.get(k), labels);
        }
        if (containsNegativeWeightCycle(d.get(d.size() - 1))) {
            System.out.print("\n********************************************************");
            System.out.print("\n**********GRAPH CONTAINS NEGATIVE WEIGHT CYCLE**********");
            System.out.println("\n********************************************************");
        }
    }

    public static void printMatrix(int[][] matrix, String[] labels) {
        for (int i = 0; i < labels.length; i++) {
            System.out.print(labels[i] + "\t");
            if (i == labels.length - 1) {
                System.out.println();
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            System.out.print(labels[i] + "\t");
            for (int j = 1; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public static boolean containsNegativeWeightCycle(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][i] != 0) {
                return true;
            }
        }
        return false;
    }
}
