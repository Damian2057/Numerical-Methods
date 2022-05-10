package org.example.model;

public class Integral {
    private final double[][][] gaussNode = {
            {{-0.577350, 1}, {0.577350, 1}},
            {{-0.774597, 5.0 / 9.0},{0, 8.0 / 9.0},{0.774597, 5.0 / 9.0}},
            {{-0.861136, 0.347855},{-0.339981, 0.652145},{0.339981, 0.652145},{0.861136, 0.347855}},
            {{-0.906180, 0.236927},{-0.538469, 0.478629},{0, 0.568889},{0.538469, 0.478629},{0.906180, 0.236927}}
    };

    public Integral() {
    }

    public double[] coefficient(int countOfNodes, int n) {
        return gaussNode[countOfNodes - 2][n];
    }
}
