package org.example.model;

public class Bisection {

    //private Expression expression;

    public Bisection(String eqaution) {
       // this.expression = new Expresion(eqaution);
        //x*x+x+1
    }


    private double f(double x) {
        //obliczamy wartosc funkcji dla zadanego x
        return x*x+x-1;
    }

    public double bisectionAlgorithm(double lowerLimit, double upperLimit, double epsilon) {

        //Pierwszy krok sprawdzamy, czy srodek przedzial nie jest szukanym rozwiazaniem (lowerLimit+upperLimit)/2
        double x1 = (lowerLimit+upperLimit)/2;
        if(f(x1) == 0) {
            return x1;
        } else {
            while (Math.abs(upperLimit-lowerLimit) > epsilon) {
                x1 = (lowerLimit+upperLimit)/2;
                //System.out.println(x1);
                if (f(lowerLimit) * f(x1) < 0) {
                    upperLimit = x1;
                } else if (f(upperLimit) * f(x1) < 0) {
                    lowerLimit = x1;
                }
            }
        }
        return x1;
    }

    public double bisectionAlgorithm(double lowerLimit, double upperLimit, int iteration) {

        return 0;
    }


}
