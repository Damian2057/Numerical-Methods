package org.example.model;

public class Node {
    private final double node;
    private final double weight;

    public Node(double n, double weight) {
        this.node = n;
        this.weight = weight;
    }

    public double getNode() {
        return node;
    }

    public double getWeight() {
        return weight;
    }
}
