import java.util.ArrayList;

/**
 * @author Nick Stolarow
 * @date 12/4/2020
 */
public class FloydWarshall {
    public static void main(String[] args) {
//      NOTE: THERE ARE PLACE HOLDER 0'S IN THE FIRST ROW AND COLUMN BECAUSE THIS ALGO STARTS INDEXING AT 1

        int inf = 999; // represents infinity

//      Problem set 8 #1:
        int[][] weightMatrix = {{0, 0, 0, 0, 0, 0},
                                {0, 0, inf, 3, inf, inf},
                                {0, -4, 0, 2, inf, inf},
                                {0, inf, -1, 0, 7, 5},
                                {0, inf, 1, inf, 0, 10},
                                {0, inf, inf, inf, -8, 0}};

//        In class example: (final table is on page 696 of textbook)
//        int[][] weightMatrix = {{0, 0, 0, 0, 0, 0},
//                                {0, 0, 3, 8, inf, -4},
//                                {0, inf, 0, inf, 1, 7},
//                                {0, inf, 4, 0, inf, inf},
//                                {0, 2, inf, -5, 0, inf},
//                                {0, inf, inf, inf, 6, 0}};

        fw(weightMatrix);
    }

    public static void fw(int[][] weight) {
        ArrayList<int[][]> d = new ArrayList<>();
        int n = weight.length;
        d.add(weight);

        // Prints the zeroth (input) matrix
        System.out.println("Matrix: 0");
        printMatrix(d.get(0));

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

//          Prints the k'th matrix
            System.out.println("Matrix: " + k);
            printMatrix(d.get(k));
        }
    }

    public static void printMatrix(int[][] matrix) {
        // Does not print placeholder 0's in row/column 0
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + "\t\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
