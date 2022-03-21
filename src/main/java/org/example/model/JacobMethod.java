package org.example.model;

import org.example.exception.JacobConditionException;

public class JacobMethod {

    private final int numberOfEquations;
    private double[][] matrix; //nasza macierz wraz z wynikami
    private double[][] M; //uklad rownan bez wynikow

    private double[] currentX; //wynik 1
    private double[] prevX; //wynik 2


    double[][] copyMatrix(int x, int y) {
        double[][] temp = new double[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        return temp;
    }

    public JacobMethod(int numberOfEquations, double[][] matrix) {
        this.numberOfEquations = numberOfEquations;
        this.matrix = matrix;

        M = new double[numberOfEquations][numberOfEquations];
        currentX = new double[numberOfEquations];
        prevX = new double[numberOfEquations];

        //wczytanie rownan do M
        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j < numberOfEquations; j++) {
                M[i][j] = matrix[i][j];
            }
        }

        //X init
        for (int i=0; i<numberOfEquations; i++) {
            currentX[i] = 0.0;
            prevX[i] = 0.0;
        }

        checkJacobRequired();
    }


    private void checkJacobRequired() {
        //sprawdzenie dla dominujacej przekatnej
        for (int i = 0; i < numberOfEquations; i++) {
            double sum = 0;
            for (int j = 0; j < numberOfEquations; j++) {
                sum += Math.abs(M[i][j]);
            }
            if(M[i][i] < sum - M[i][i]) {
                throw new JacobConditionException("Brak spelnienia warunku koniecznego.");
            }
        }

        //sprawdzenie czy dowolna z norm macierzy H jest mniejsza od jednosci
        for (int i = 0; i < numberOfEquations; i++) {
            double sum = 0;
            for (int j = 0; j < numberOfEquations; j++) {
                if(i != j) {
                    sum += Math.abs(M[i][j]/M[i][i]);
                }
            }
            if(sum > 1) {
                throw new JacobConditionException("Brak spelnienia warunku wystarczajacego.");
            }
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
