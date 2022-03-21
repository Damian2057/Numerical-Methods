package org.example.model;

public class JacobMethod {

    private final int numberOfEquations;
    private double[][] matrix; //nasza macierz wraz z wynikami
    private double[][] M; //uklad rownan bez wynikow
    private double[] b; //wektor, wyrazy wolne

    private double[] currentX; //wynik 1
    private double[] prevX; //wynik 2

    double[][] copyMatrix() {
        double[][] temp = new double[numberOfEquations][numberOfEquations+1];
        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j < numberOfEquations+1; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        return temp;
    }

    public JacobMethod(int numberOfEquations, double[][] matrix) {
        this.numberOfEquations = numberOfEquations;
        this.matrix = matrix;

        b = new double[numberOfEquations];
        M = new double[numberOfEquations][numberOfEquations];
        currentX = new double[numberOfEquations];
        prevX = new double[numberOfEquations];

        //wczytanie rownan do macierzy_f
        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j < numberOfEquations; j++) {
                M[i][j] = matrix[i][j];
            }
        }

        //wczytanie wektora b
        for(int i=0; i<numberOfEquations; i++){
            b[i] = matrix[i][numberOfEquations];
        }


        //X init
        for (int i=0; i<numberOfEquations; i++) {
            currentX[i] = 0.0;
            prevX[i] = 0.0;
        }

    }


    public double[] iterationSolver(int iterations) {
        return null;
    }

    public double[] accuracySolver(double epsilon) {
        return null;
    }

    public static void showMatrix(double[][] matrix, int numberOfEquations) {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j < numberOfEquations; j++) {
                s.append(matrix[i][j]).append(" ");
            }
            s.append("\n");
        }
        System.out.println(s.toString());
    }
}
