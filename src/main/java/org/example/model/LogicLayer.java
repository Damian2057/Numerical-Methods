package org.example.model;

import org.example.exception.NoZeroException;

public class LogicLayer {

    protected functionInterpreter functionInterpreter;

    public LogicLayer(org.example.model.functionInterpreter functionInterpreter) {
        this.functionInterpreter = functionInterpreter;
    }

    void checkValid(double a, double b) {
        if(functionInterpreter.function(a) * functionInterpreter.function(b) >= 0) {
            throw new NoZeroException("Podany przedzial nie zawiera miejsca zerowego");
        }
    }
}
