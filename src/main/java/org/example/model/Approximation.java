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

    public double[] wspolczynniki(int liczba_wezlow, int numer_wezla) {
        double[][][] dane = {
                {{-0.577350, 1}, {0.577350, 1}},
                {{-0.774597, 5 / 9},{0, 8 / 9},{0.774597, 5 / 9}},
        {{-0.861136, 0.347855},{-0.339981, 0.652145},{0.339981, 0.652145},{0.861136, 0.347855}},
            {{-0.906180, 0.236927},{-0.538469, 0.478629},{0, 0.568889},{0.538469, 0.478629},{0.906180, 0.236927}}
    };


        return dane[liczba_wezlow - 2][numer_wezla];
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

    private double funkcja_bazowa(int degree, double x) {
        double[] p = new double[10000];
        p[0] = 1;
        p[1] = x;
        for(int i = 2; i < degree+1; i++) {
            p[i] = (((2 * (i - 1) + 1) / i * x * p[i - 1] - (i - 1) / i * p[i - 2]));
        }
        return p[degree];
    }

    private double gaussMethod(int degree) {
        double integral = 0.0;

        for (int i = 0; i < countOfNodes; i++) {
            double x = wspolczynniki(countOfNodes , i)[0];
            double w = wspolczynniki(countOfNodes , i)[1];

            integral += w * functionContainer.function(x) * funkcja_bazowa(degree , x);
        }

        return integral;
    }

    public double gaussError(double[] tab) {
        double error = 0.0;
        for (int i = 0; i < countOfNodes; i++) {
            double x = wspolczynniki(countOfNodes , i)[0];
            double w = wspolczynniki(countOfNodes , i)[1];
            error += w * ((functionContainer.function(x) -polyValue(tab.length, x, tab)) * (functionContainer.function(x) -polyValue(tab.length, x, tab)));
        }
        return error;
    }

    private double polyValue(double degree, double x, double[] tab) {
        double sum = 0;
        for (int i = 0; i < degree; i++) {
            sum += tab[i] * funkcja_bazowa(i,x);
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
        for (int i = poly.length-1; i >= 0; i--) {
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
