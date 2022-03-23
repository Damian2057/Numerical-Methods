package org.example.model;

public class MatrixSort {
    private int[][] matrix;


    public static double[][] repairMatrix(double[][] matrix, int count) {
        double max = matrix[0][0];
        for (int i = 0; i < count; i++) { // wiersze
            max = matrix[i][i];
            for (int j = i; j < count; j++) { // schodek
                if(max < matrix[i][j]) {
                    for (int z = 0; z < count; z++) {
                        double temp = matrix[z][i];
                        matrix[z][i] = matrix[z][j];
                        matrix[z][j] = temp;
                    }
                }

            }
        }
        return matrix;
    }
}
