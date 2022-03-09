package org.example.model;

import org.example.exception.NoZeroException;

public class Falsi {

    private functionInterpreter functionInterpreter;

    public Falsi(org.example.model.functionInterpreter functionInterpreter) {
        this.functionInterpreter = functionInterpreter;
    }

    public double f(double x) {
        return functionInterpreter.f(x);
    }

    public double falsiAlgorithmE(double lowerLimit, double upperLimit, double epsilon) throws Exception {

        if(f(lowerLimit) * f(upperLimit) >= 0) {
            throw new NoZeroException("Podany przedzial nie zawiera miejsca zerowego");
        }

        double x0 = upperLimit;
        double temp = lowerLimit;

        while (Math.abs(temp-x0) > epsilon) {
            temp = x0;
            x0 = (lowerLimit * f(upperLimit) - upperLimit * f(lowerLimit)) / (f(upperLimit) - f(lowerLimit));

            if(Math.abs(x0-temp) < epsilon) {
                return x0;
            }
            if(f(lowerLimit)*f(x0) < 0) {
                upperLimit = x0;
            } else {
                lowerLimit = x0;
            }

        }

        throw new NoZeroException("error");

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
