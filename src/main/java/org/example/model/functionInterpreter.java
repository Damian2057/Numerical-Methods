package org.example.model;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

public class functionInterpreter {

    private String equation;

    public functionInterpreter(String equation) {
        this.equation = equation;
    }

    public double f(double x) {
        Function f = new Function("f(x) = "+equation);
        Argument valueOfX = new Argument("x = "+String.valueOf(x));
        Expression expression = new Expression("f(x)",f,valueOfX);
        return expression.calculate();
    }
}
