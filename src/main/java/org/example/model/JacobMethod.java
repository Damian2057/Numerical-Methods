package org.example.model;

public class JacobMethod {

    private final int numberOfEquations;
    private int[][] matrix;

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
