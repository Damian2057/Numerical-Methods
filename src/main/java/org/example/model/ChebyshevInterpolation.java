package org.example.model;

import java.util.ArrayList;

public class ChebyshevInterpolation {

    private FunctionContainer functionContainer;

    public ChebyshevInterpolation(FunctionContainer functionContainer) {
        this.functionContainer = functionContainer;
    }

    public void initializeNodes(double minRange, double maxRange
            , int numberOfNodes, ArrayList<Node> nodes) {
        for (int i = 1; i <= numberOfNodes; i++) {
            double x = 0.5*(minRange+maxRange) + 0.5*(maxRange-maxRange)*Math.cos(Math.PI*(2*i-1)/(2*numberOfNodes));
            Node node = new Node(x, functionContainer.function(x));
            nodes.add(node);
        }
    }

}
