package org.example.model;

public class Approximation {

    private final FunctionContainer functionContainer;
    private final int countOfNodes;
    private Laguerrea laguerrea;

    public Approximation(FunctionContainer functionContainer, int countOfNodes) {
        this.functionContainer = functionContainer;
        this.countOfNodes = countOfNodes;
        laguerrea = new Laguerrea(functionContainer);
    }

    public double[] polynomialListCoefficients(int degree) {
        double[] polymial = new double[degree+1];
        for (int i = 0; i <= degree; i++) {
            polymial[i] = polynomiaCoefficient(i);
        }
        return polymial;
    }

    private double polynomiaCoefficient(int degree) {

        return (double) (2 * degree + 1) / 2 * gaussMethod(degree);
    }

    private double gaussMethod(int degree) {
        double integral = 0.0;

        for (int i = 0; i <= degree; i++) {


            integral += laguerrea.calculateByNode(countOfNodes);
        }

        return integral;
    }

    public double gaussError() {
        double error = 0.0;

        return error;
    }

    public double horner(double[] poly, double x) {
        double sum = 0.0;
        for (int i = poly.length-1; i > 0; i--) {
            sum = sum * x + poly[i];
        }
        return sum;
    }
}
