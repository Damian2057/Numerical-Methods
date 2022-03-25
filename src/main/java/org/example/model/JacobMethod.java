package org.example.model;

import org.example.exception.JacobConditionException;

import java.util.Arrays;

public class JacobMethod {

    private final int numberOfEquations;
    private double[][] matrix; //nasza macierz wraz z wynikami

    private double[] currentX; //wynik 1
    private double[] prevX; //wynik 2



    public boolean stworzDominante(int r, boolean[] V, int[] R)
    {
        int n = matrix.length;
        if (r == matrix.length) {
            double[][] T = new double[n][n+1];
            for (int i = 0; i < R.length; i++) {
                for (int j = 0; j < n + 1; j++)
                    T[i][j] = matrix[R[i]][j];
            }

            matrix = T;

            return true;
        }

        for (int i = 0; i < n; i++) {
            if (V[i]) continue;

            double sum = 0;

            for (int j = 0; j < n; j++)
                sum += Math.abs(matrix[i][j]);

            if (2 * Math.abs(matrix[i][r]) > sum) { // diagonally dominant?
                V[i] = true;
                R[r] = i;

                if (stworzDominante(r + 1, V, R))
                    return true;

                V[i] = false;
            }
        }

        return false;
    }

    public boolean sprawdzDominante()
    {
        boolean[] visited = new boolean[matrix.length];
        int[] rows = new int[matrix.length];

        Arrays.fill(visited, false);

        return stworzDominante(0, visited, rows);
    }
    
    

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

        if (!sprawdzDominante()) {
            System.out.println("Nie udalo sie spelnic warunku koniecznego");
        }

        showMatrix(matrix, numberOfEquations);
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


    public static void checkJacobRequired(double[][] matrix, int numberOfEquations) {
        //sprawdzenie dla dominujacej przekatnej
        for (int i = 0; i < numberOfEquations; i++) {
            double sum = 0;
            for (int j = 0; j < numberOfEquations; j++) {
                sum += Math.abs(matrix[i][j]);
            }
            if(matrix[i][i] < sum - matrix[i][i]) {
                System.out.println("Brak spelnienia warunku koniecznego.");
                //throw new JacobConditionException("Brak spelnienia warunku koniecznego.");
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
                System.out.println("Brak spelnienia warunku wystarczajacego.");
                //throw new JacobConditionException("Brak spelnienia warunku wystarczajacego.");
            }
        }
    }
}
