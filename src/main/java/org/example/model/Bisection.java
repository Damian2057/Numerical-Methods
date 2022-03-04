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

    public double bisectionAlgorithm(double lowerLimit, double upperLimit, double epsilon) throws Exception {

        //Pierwszy krok sprawdzamy, czy srodek przedzial nie jest szukanym rozwiazaniem (lowerLimit+upperLimit)/2
        double x1 = (lowerLimit+upperLimit)/2.0;
        if(f(x1) == 0) {
            //jezeli tak zwracamy wartosc x1 czyli szukane miejsce zerowe na podanym przedziale.
            return x1;
        } else {
            int i  = 0;
            //Jezeli nie, Szukamy miejsca zerowego tak dlugo az osiagniemy zadana dokladnosc.
            while (Math.abs(upperLimit-lowerLimit) > epsilon) {
                // w kazdej iteracji biezemy srodek predzialu/kazdego kolejnego przedzialu
                //z kazdym obiegiem przeszukiwany przedzial sie zmniejsza
                x1 = (lowerLimit+upperLimit)/2.0;

                //jezeli znaki na krancach przedzialu sa rozne +-<0 oznacza to ze
                // w szukanym przedzialne napewno jest miejsce zerowe tzn. przecięcie z osią OX.
                if (f(lowerLimit) * f(x1) < 0) {
                    //jezeli lewy przedzial ma rozne znaki na krancach przedzialu wybieramy ten przedzial do dalszych badan
                    // srodek biezacego przedzialu przyjmujemy jako nasz nowy gorny limit
                    upperLimit = x1;
                } else if (f(upperLimit) * f(x1) < 0) {
                    //jezeli prawy przedzial ma rozne znaki na krancach przedzialu wybieramy ten przedzial do dalszych badan
                    // srodek biezacego przedzialu przyjmujemy jako nasz nowy gorny limit
                    lowerLimit = x1;
                } else {
                    //Funkcja za żadnym przedziale lewym i prawym nie zmienia znaku na krancach przedzialu,
                    //tzn. funkcja nie ma miejsca zerowego w zadanym przedziale.
                    //rzucamy wyjatek, ktory wylapiemy przy wywolaniu.
                    //throw new Exception();
                }
            }
        }
        return x1;
    }

    public double bisectionAlgorithm(double lowerLimit, double upperLimit, int iteration) {

        return 0;
    }


}
