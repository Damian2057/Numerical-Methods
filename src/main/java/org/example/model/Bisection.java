package org.example.model;

import org.example.exception.NoZeroException;

public class Bisection {

    private String eqaution;

    public Bisection(String eqaution) {
        this.eqaution = eqaution;
    }

    public double f(double x) {
        //obliczamy wartosc funkcji dla zadanego x
        return x*x+x-1;
    }

    public double bisectionAlgorithm(double lowerLimit, double upperLimit, double epsilon) throws Exception {
        if(f(lowerLimit) * f(upperLimit) >= 0) {
            throw new NoZeroException("Podany przedzial nie zawiera miejsca zerowego");
        }
        //Pierwszy krok sprawdzamy, czy srodek przedzial nie jest szukanym rozwiazaniem (lowerLimit+upperLimit)/2
        double x1 = (lowerLimit+upperLimit)/2.0;
        if(f(x1) == 0) {
            //jezeli tak zwracamy wartosc x1 czyli szukane miejsce zerowe na podanym przedziale.
            return x1;
        } else {
            //Jezeli nie, Szukamy miejsca zerowego tak dlugo az osiagniemy zadana dokladnosc.
            double temp = x1;
            while (Math.abs(x1-temp) < epsilon) {
                // w kazdej iteracji biezemy srodek predzialu/kazdego kolejnego przedzialu
                //z kazdym obiegiem przeszukiwany przedzial sie zmniejsza
                temp = x1;
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
                    throw new NoZeroException("Brak miejsca zerowego");
                }
            }
        }
        return x1;
    }

    public double bisectionAlgorithm(double lowerLimit, double upperLimit, int iteration) {
        double x1 = (lowerLimit+upperLimit)/2.0;
        if(f(x1) == 0) {
            return x1;
        } else {

            //flaga zabezpieczenie, czy nasza podana funkcja ma w ogole jakies miejsce zerowe w zadanym przedziale
            //jezeli zostanie wykonany krok tzn zmniejszony przedzial algorytm wykonuje sie dalej, jezeli nie rzucany jest wyjatek
            boolean flag = false;
            //algorytm wykonuje sie tak dlugo az nie zostanie wykonana zadana liczba iteracji.
            for (int i = 0; i < iteration; i++){
                x1 = (lowerLimit+upperLimit)/2.0;
                if (f(lowerLimit) * f(x1) < 0) {
                    upperLimit = x1;
                    flag = true;
                } else if (f(upperLimit) * f(x1) < 0) {
                    lowerLimit = x1;
                    flag = true;
                }
                //Zabezpieczenie w przypadku braku miejsca zerowego w zadanym przedziale.
                if(!flag) throw new NoZeroException("Brak miejsca zerowego");
            }
        }
        return x1;
    }
}
