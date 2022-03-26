package org.example.model;

import java.util.Arrays;

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
        tryToFixRows();
        tryToFixColumns();

        checkJacobRequired(matrix,numberOfEquations);

    }


    public void  tryToFixRows() {
        boolean[] visited = new boolean[matrix.length];

        Arrays.fill(visited, false);

        for (int i = 0; i < numberOfEquations; i++) {
            double max = 0;
            int index  = -1;
            for (int j = 0; j < numberOfEquations; j++) {
                if(Math.abs(max) < Math.abs(matrix[j][i])) {
                    max = matrix[j][i];
                    index = j;
                }
            }
            if(index != -1 && !visited[index]) {
                for (int j = 0; j < numberOfEquations+1; j++) {
                    double temp = matrix[index][j];
                    matrix[index][j] = matrix[i][j];
                    matrix[i][j] = temp;
                }
                visited[index] = true;
            }
            index  = -1;
        }
        showMatrix(matrix,numberOfEquations);
    }

    public void  tryToFixColumns() {
        boolean[] visited = new boolean[matrix.length];

        Arrays.fill(visited, false);

        for (int i = 0; i < numberOfEquations; i++) {
            double max = 0;
            int index  = -1;
            for (int j = 0; j < numberOfEquations; j++) {
                if(Math.abs(max) < Math.abs(matrix[i][j])) {
                    max = matrix[i][j];
                    index = i;
                }
            }
            if(index != -1 && !visited[index]) {
                for (int j = 0; j < numberOfEquations; j++) {
                    double temp = matrix[j][index];
                    matrix[j][index] = matrix[j][i];
                    matrix[j][i] = temp;
                }
                visited[index] = true;
            }
            index  = -1;
        }
        showMatrix(matrix,numberOfEquations);
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
            if(Math.abs(matrix[i][i]) < sum - Math.abs(matrix[i][i])) { //konieczny
                System.out.println("Brak gwarancji poprawnego wyniku");
                return;
            }
        }
    }
}
