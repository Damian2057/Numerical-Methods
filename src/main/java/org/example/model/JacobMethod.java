package org.example.model;

public class JacobMethod {

    private final int numberOfEquations;
    private double[][] matrix; //nasza macierz wraz z wynikami
    private double[][] macierz_f; //uklad rownan bez wynikow
    private double[] b; //wektor, wyrazy wolne

    private double[] D; //D^-1, przekatna
    private double[][] M; //-D^-1 (L+U), gdzie L to wszystko ponizej przekatnej a U powyzej

    private double[] x1; //wynik 1
    private double[] x2; //wynik 2

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

        macierz_f = new double[numberOfEquations][numberOfEquations];
        b = new double[numberOfEquations];
        D = new double[numberOfEquations];
        M = new double[numberOfEquations][numberOfEquations];
        x1 = new double[numberOfEquations];
        x2 = new double[numberOfEquations];

        //wczytanie rownan do macierzy_f
        for (int i = 0; i < numberOfEquations; i++) {
            for (int j = 0; j < numberOfEquations; j++) {
                macierz_f[i][j] = matrix[i][j];
            }
        }

        //wczytanie wektora b
        for(int i=0; i<numberOfEquations; i++){
            b[i] = matrix[i][numberOfEquations];
        }

        //obliczenie odwrotnosci przekatnej
        for(int i=0; i<numberOfEquations; i++) {
            D[i] = (1.0/macierz_f[i][i]);
        }

        //obliczenie M
        for(int i=0; i<numberOfEquations; i++) {
            for(int j=0; j<numberOfEquations; j++) {
                if (i == j) M[i][j] = 0.0;
                else {
                    M[i][j] = -(macierz_f[i][j] * D[i]);
                    if(M[i][j] == -0.0) {
                        M[i][j] = 0.0;
                    }
                }
            }
        }

        for (int i=0; i<numberOfEquations; i++) {
            x1[i] = 0.0;
            x2[i] = 0.0;
        }

        showMatrix(M, numberOfEquations);
        showMatrix(macierz_f, numberOfEquations);
    }


    public double[] iterationSolver(int iterations) {
        for (int k=0; k<iterations; k++) {
            for (int i=0; i<numberOfEquations; i++) {
                x2[i] = D[i]*b[i];
                for (int j=0; j<numberOfEquations; j++)
                    x2[i] += M[j][i]*x1[j];
            }
            for (int i=0; i<numberOfEquations; i++)
                x1[i] = x2[i];
        }

//        for (int k=0; k<iterations; k++) {
//            for (int i=0; i<numberOfEquations; i++) {
//                x2[i] += b[i]*D[i];
//                for (int j=0; j<numberOfEquations; j++)
//                    x2[i] += M[i][j] * x1[i];
//            }
//            for (int i=0; i<numberOfEquations; i++)
//                x1[i] = x2[i];
//        }

        return x1;
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
