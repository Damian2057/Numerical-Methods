package org.example.model;

public class JacobMethod {

    private int numberOfEquations;
    private int numberOfCoefficients;
    private int[][] matrix;

    public JacobMethod(int numberOfEquations, int numberOfCoefficients, int[][] matrix) {
        this.numberOfEquations = numberOfEquations;
        this.numberOfCoefficients = numberOfCoefficients;
        this.matrix = matrix;
    }


    public int[] iterationSolver(int iterations) {
        return null;
    }

    public int[] accuracySolver(double epsilon) {
        return null;
    }
}
