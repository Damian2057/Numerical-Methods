package org.example.model;

import org.example.model.functiontypes.Exponential;
import org.example.model.functiontypes.Polunomial;
import org.example.model.functiontypes.Trigonometry;

import java.util.Objects;

public class functionInterpreter {

    private String type;
    private Polunomial polunomial;
    private Exponential exponential;
    private Trigonometry trigonometry;

    public functionInterpreter(String type) {
        this.type = type;
        if(type.contains("T")) {
            trigonometry = new Trigonometry();
        }
        if(type.contains("Z")) {
            exponential = new Exponential();
        }
        if(type.contains("W")) {
            polunomial = new Polunomial();
        }
    }


    private double pot(double x, int pote) {
        double sum=1;
        for (int i = 0; i < pote; i++) {
            sum = sum*x;
        }
        return sum;
    }

    private double Horner(Polunomial polunomial,double x) {
        double sum = 0;
        for (int i = 0; i < polunomial.getNumbers().size()-1    ; i++) {
            sum = sum + polunomial.getNumbers().get(i) * pot(x,polunomial.getN()-i);
        }
        sum += polunomial.getNumbers().get(polunomial.getNumbers().size()-1);
        return sum;
    }

    private double TrigSum(Trigonometry trigonometry, double x) {
        if(Objects.equals(trigonometry.getFunkcja(), "sin")) {
            return Math.sin(x);
        } else if(Objects.equals(trigonometry.getFunkcja(), "cos")) {
            return Math.cos(x);
        } else if(Objects.equals(trigonometry.getFunkcja(), "tan")) {
            return Math.tan(x);
        } else {
            return 1.0/Math.tan(x);
        }
    }

    private double ExpSum(Exponential exponential, double x) {
        return Math.pow(exponential.getBase(),x) + exponential.getFreeFactor();
    }

    public double f(double x) {
        switch (type) {
            case "W" -> {
                return Horner(polunomial,x);
            } case "T" -> {
                return TrigSum(trigonometry,x);
            } case "Z" -> {
                return ExpSum(exponential,x);
            } case "TW" -> {
                return TrigSum(trigonometry,Horner(polunomial,x));
            }  case "TZ" -> {
                return TrigSum(trigonometry,ExpSum(exponential,x));
            } case "ZW" -> {
                return ExpSum(exponential,Horner(polunomial,x));
            } case "ZT" -> {
                return ExpSum(exponential,TrigSum(trigonometry,x));
            } case "WT" -> {
                return Horner(polunomial,TrigSum(trigonometry,x));
            } case "WZ" -> {
                return Horner(polunomial,ExpSum(exponential,x));
            } case "WTZ" -> {
                return Horner(polunomial, TrigSum(trigonometry, ExpSum(exponential, x)));
            } case "WZT" -> {
                return Horner(polunomial, ExpSum(exponential, TrigSum(trigonometry, x)));
            } case "TWZ" -> {
                return TrigSum(trigonometry,Horner(polunomial,ExpSum(exponential, x)));
            } case "TZW" -> {
                return TrigSum(trigonometry,ExpSum(exponential,Horner(polunomial, x)));
            } case "ZWT" -> {
                return ExpSum(exponential,Horner(polunomial,TrigSum(trigonometry, x)));
            } case "ZTW" -> {
                return ExpSum(exponential,TrigSum(trigonometry,Horner(polunomial, x)));
            }
        }
    return 0;
    }
}
