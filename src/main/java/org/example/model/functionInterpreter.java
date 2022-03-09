package org.example.model;

import org.example.model.functiontypes.Exponential;
import org.example.model.functiontypes.Polunomial;
import org.example.model.functiontypes.Trigonometry;

public class functionInterpreter {

    private String type;
    private Polunomial polunomial;
    private Exponential exponential;
    private Trigonometry trigonometry;

    public functionInterpreter(Polunomial polunomial) {
        this.polunomial = polunomial;
        this.type = "W";
    }

    public functionInterpreter(Exponential exponential) {
        this.exponential = exponential;
        this.type = "Z";
    }


    private double pot(double x, int pote) {
        double sum=1;
        for (int i = 0; i < pote; i++) {
            sum = sum*x;
        }
        return sum;
    }

    public double f(double x) {
        switch (type) {
            case "W" -> {
                double sum = 0;
                for (int i = 0; i < polunomial.getNumbers().size()-1    ; i++) {
                    sum = sum + polunomial.getNumbers().get(i) * pot(x,polunomial.getN()-i);
                }
                sum += polunomial.getNumbers().get(polunomial.getNumbers().size()-1);
                return sum;
            } case "T" -> {

            } case "Z" -> {
                return Math.pow(exponential.getBase(),x) - exponential.getFreeFactor();
            }
        }
    return 0;
    }
}
