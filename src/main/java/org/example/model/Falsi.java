package org.example.model;

import org.example.exception.NoZeroException;

public class Falsi {

    private String string;

    public Falsi(String string) {
        this.string = string;
    }

    public double f(double x) {
        return x*x+x-1;
    }

    public double falsiAlgorithm(double lowerLimit, double upperLimit, double epsilon) throws Exception {
        //Sprawdzamy czy zadany przedzial jest tym, ktory posiada rozne znaki na krancach przedzialu
        //tzn. czy znaduje sie tam miejsce zerowe prawdopodobne przeciecie z osia OX
        if(f(lowerLimit) * f(upperLimit) >= 0) {
            throw new NoZeroException("Podany przedzial nie zawiera miejsca zerowego");
        }
        //Ustawiamy pkt chwilowe.
        double x1 = lowerLimit;
        //zmienna do przechowywania xi-1
        double temp = x1;

        while (Math.abs(x1-temp) < epsilon) {
            //Ze wzoru obliczamy nasz x1, ktory jest brany jako pierwsze przyblizenie pierwiastka.
            x1 = (lowerLimit * f(upperLimit) - upperLimit * f(lowerLimit)) / (f(upperLimit) - f(lowerLimit));
            //Sprawdzamy czy prawy przedzial spelnia warunek znaku, tzn czy jest tam przeciecie z Osia OX
            if (f(x1) * f(lowerLimit) < 0) {
                // jezeli tak ograniczamy przedzial z gory, x1 staje sie nowym krancem gornym
                upperLimit = x1;
            } else {
                // jezeli nie tzn przeciecie znaduje sie w lewym przedziale
                //ograniczmy przedzial poszukiwan z dolu
                lowerLimit = x1;
            }
        }
        return x1;
    }

    public double falsiAlgorithm(double lowerLimit, double upperLimit, int iteration) {
//Sprawdzamy czy zadany przedzial jest tym, ktory posiada rozne znaki na krancach przedzialu
        //tzn. czy znaduje sie tam miejsce zerowe prawdopodobne przeciecie z osia OX
        if(f(lowerLimit) * f(upperLimit) >= 0) {
            throw new NoZeroException("Podany przedzial nie zawiera miejsca zerowego");
        }
        //Ustawiamy pkt chwilowe.
        double x1 = lowerLimit;
        //zmienna do przechowywania xi-1
        double temp = x1;

        for (int i = 0; i < iteration; i++) {
            //Ze wzoru obliczamy nasz x1, ktory jest brany jako pierwsze przyblizenie pierwiastka.
            x1 = (lowerLimit * f(upperLimit) - upperLimit * f(lowerLimit)) / (f(upperLimit) - f(lowerLimit));
            //Sprawdzamy czy prawy przedzial spelnia warunek znaku, tzn czy jest tam przeciecie z Osia OX
            if (f(x1) * f(lowerLimit) < 0) {
                // jezeli tak ograniczamy przedzial z gory, x1 staje sie nowym krancem gornym
                upperLimit = x1;
            } else {
                // jezeli nie tzn przeciecie znaduje sie w lewym przedziale
                //ograniczmy przedzial poszukiwan z dolu
                lowerLimit = x1;
            }
        }
        return x1;
    }
}
