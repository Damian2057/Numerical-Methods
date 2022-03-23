package org.example.model;

import org.example.exception.JacobConditionException;

public class JacobMethod {

    private final int numberOfEquations;
    private double[][] matrix; //nasza macierz wraz z wynikami

    private double[] currentX; //wynik 1
    private double[] prevX; //wynik 2


    public JacobMethod(int numberOfEquations, double[][] matrix) {
        this.numberOfEquations = numberOfEquations;
        this.matrix = matrix;

        currentX = new double[numberOfEquations];
        prevX = new double[numberOfEquations];

        //X init
        for (int i=0; i<numberOfEquations; i++) {
            currentX[i] = 0.0;
            prevX[i] = 0.0;
        }


        matrix = MatrixSort.repairMatrix(matrix,numberOfEquations);
        showMatrix(MatrixSort.repairMatrix(matrix,numberOfEquations), numberOfEquations);
        checkJacobRequired(matrix,numberOfEquations);

    }


    public double[] iterationSolver(int iterations) {
        for(int i = 0; i < iterations; i++) {
            for (int j = 0; j < numberOfEquations; j++) {
                double sum = matrix[j][numberOfEquations]; // vector b

                for (int z = 0; z < numberOfEquations; z++) {
                    if (j != z)
                        sum -= matrix[j][z] * prevX[z]; //b-a(ij)*x(k-1)-...
                }

                currentX[j] = 1/matrix[j][j] * sum; // b/a(ii)-a(ij)/a(ii)-...
            }

            prevX = currentX.clone(); //copy current X(k) to prev X(k-1)
        }
        return currentX;
    }

    public double[] accuracySolver(double epsilon) {
        while (true){
            for (int j = 0; j < numberOfEquations; j++) {
                double sum = matrix[j][numberOfEquations]; // vector b

                for (int z = 0; z < numberOfEquations; z++) {
                    if (j != z)
                        sum -= matrix[j][z] * prevX[z]; //b-a(ij)*x(k-1)-...
                }

                currentX[j] = 1/matrix[j][j] * sum; // b/a(ii)-a(ij)/a(ii)-...
            }
            int iter = 0;
            for (int i = 0; i < numberOfEquations; i++) {
                if(Math.abs(currentX[i] - prevX[i]) < epsilon) {
                    iter++;
                }
            }
            if(iter == numberOfEquations) {
                break;
            }
            iter = 0;


            prevX = currentX.clone(); //copy current X(k) to prev X(k-1)
        }
        return currentX;
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


    public static void checkJacobRequired(double matrix[][], int numberOfEquations) {
        //sprawdzenie dla dominujacej przekatnej
        for (int i = 0; i < numberOfEquations; i++) {
            double sum = 0;
            for (int j = 0; j < numberOfEquations; j++) {
                sum += Math.abs(matrix[i][j]);
            }
            if(matrix[i][i] < sum - matrix[i][i]) {
                throw new JacobConditionException("Brak spelnienia warunku koniecznego.");
            }
        }

        //sprawdzenie czy dowolna z norm macierzy H jest mniejsza od jednosci
        for (int i = 0; i < numberOfEquations; i++) {
            double sum = 0;
            for (int j = 0; j < numberOfEquations; j++) {
                if(i != j) {
                    sum += Math.abs(matrix[i][j]/matrix[i][i]);
                }
            }
            if(sum > 1) {
                throw new JacobConditionException("Brak spelnienia warunku wystarczajacego.");
            }
        }
    }
}
