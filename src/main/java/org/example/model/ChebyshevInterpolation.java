package org.example.model;

import java.util.ArrayList;

public class ChebyshevInterpolation {

    private final FunctionContainer functionContainer;
    private ArrayList<Node> nodes = new ArrayList<>();

    public ChebyshevInterpolation(FunctionContainer functionContainer, double minRange
            , double maxRange, int numberOfNodes) {

        this.functionContainer = functionContainer;
        initializeNodes(minRange,maxRange,numberOfNodes);

    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void initializeNodes(double minRange, double maxRange
            , int numberOfNodes) {
        for (int i = 1; i <= numberOfNodes; i++) {
            double x = 0.5 * (minRange + maxRange) + 0.5 * (maxRange - minRange) * Math.cos(Math.PI * (2 * i - 1) / (2 * numberOfNodes));
            Node node = new Node(x, functionContainer.function(x));
            nodes.add(node);
        }
    }

    public double getInterpolatedValue(double x) {
        int size = nodes.size();
        double sum = 0.0;
        for (int i = 0; i < size; i++) {
            double finalResult = 1.0;
            for (int j = 0; j < size; j++) {
                if(i != j) {
                    finalResult *= ((x - nodes.get(j).getX()) / (nodes.get(i).getX() - nodes.get(j).getX()));
                }
            }
            sum += nodes.get(i).getY()*finalResult;
        }
        return  sum;
    }
}
