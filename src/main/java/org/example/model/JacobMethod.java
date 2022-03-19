package org.example.model;

public class JacobMethod {

    private final int numberOfEquations;
    private int[][] matrix;

    int[][] copyMatrix() {
        int[][] temp = new int[numberOfEquations][numberOfEquations+1];
        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j < numberOfEquations+1; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        return temp;
    }

    public JacobMethod(int numberOfEquations, int[][] matrix) {
        this.numberOfEquations = numberOfEquations;
        this.matrix = matrix;
    }


    public int[] iterationSolver(int iterations) {
        return null;
    }

    public int[] accuracySolver(double epsilon) {
        return null;
    }
}
