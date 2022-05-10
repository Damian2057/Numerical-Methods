package org.example.model;

import java.util.ArrayList;

public class Approximation {

    private final FunctionContainer functionContainer;
    private final int countOfNodes;
    private Integral integral;

    public Approximation(FunctionContainer functionContainer, int countOfNodes) {
        this.functionContainer = functionContainer;
        this.countOfNodes = countOfNodes;
        integral = new Integral(functionContainer);
    }

    public double[] polynomialListCoefficients(int degree) {
        double[] polymial = new double[degree+1];
        long startTime  = System.nanoTime();
        for (int i = 0; i <= degree; i++) {
            polymial[i] = polynomiaCoefficient(i);
            System.out.println(polymial[i]);
//            if(Math.round((System.nanoTime() - startTime)/1000.0)/1000.0 > 30) {
//                System.out.println("The approximation seems unattainable");
//                throw new RuntimeException();
//            }
        }
        return polymial;
    }

    private double polynomiaCoefficient(int degree) {
        double we = (double) (2 * degree + 1) / 2 * gaussMethod(degree);
        return we;
    }

    private double baseFunction(int degree, double x) {
        ArrayList<Double> data = new ArrayList<>();
        data.add(1.0);
        data.add(x);
        for(int n = 2; n < degree+1; n++) {
            data.add(((2.0 * (n - 1.0) + 1.0) / n * x * data.get(n - 1) - (n - 1.0) / n * data.get(n - 2)));
        }
        return data.get(degree);
    }

    private double gaussMethod(int degree) {
        double integralSum = 0.0;

        for (int i = 0; i < countOfNodes; i++) {
            double x = integral.coefficient(countOfNodes, i)[0];
            double w = integral.coefficient(countOfNodes, i)[1];

            integralSum += w * functionContainer.function(x) * baseFunction(degree , x);
        }

        return integralSum;
    }

    public double gaussError(double[] tab) {
        double error = 0.0;
        for (int i = 0; i < countOfNodes; i++) {
            double x = integral.coefficient(countOfNodes, i)[0];
            double w = integral.coefficient(countOfNodes, i)[1];
            error += w * ((functionContainer.function(x) - polyValue(tab.length, x, tab)) * (functionContainer.function(x) -polyValue(tab.length, x, tab)));
        }
        return error;
    }

    private double polyValue(double degree, double x, double[] tab) {
        double sum = 0;
        for (int i = 0; i < degree; i++) {
            sum += tab[i] * baseFunction(i,x);
        }
        return sum;
    }

    public double horner(double[] poly, double x) {
        double sum = 0.0;
        for (int i = 0; i < poly.length; i++) {
            sum = sum * x + poly[i];
        }
        return sum;
    }

    public double valueTest(double[] poly, double x) {
        double sum = 0.0;
        for (int i = 0; i < poly.length; i++) {
            sum += poly[i] * silnia(x,i);
        }
        return sum;
    }
    private double silnia(double x, int i) {
        double sum = 1;
        for (int j = 0; j < i; j++) {
            sum = sum * x;
        }
        return sum;
    }
}
