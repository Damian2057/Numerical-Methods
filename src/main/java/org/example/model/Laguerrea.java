package org.example.model;

import java.util.ArrayList;

public class Laguerrea {
    private final FunctionContainer container;
    private final ArrayList<Node> gaussNodeBy2 = new ArrayList<>();
    private final ArrayList<Node> gaussNodeBy3 = new ArrayList<>();
    private final ArrayList<Node> gaussNodeBy4 = new ArrayList<>();
    private final ArrayList<Node> gaussNodeBy5 = new ArrayList<>();

    public Laguerrea(FunctionContainer container) {
        this.container = container;
        initiateTheNodes();
    }

    public ArrayList<Double> calculateForEveryNode() {
        ArrayList<Double> resultArray = new ArrayList<>();
        resultArray.add(laguerrePolynomials(2, gaussNodeBy2));
        resultArray.add(laguerrePolynomials(3, gaussNodeBy3));
        resultArray.add(laguerrePolynomials(4, gaussNodeBy4));
        resultArray.add(laguerrePolynomials(5, gaussNodeBy5));
        return resultArray;
    }

    public double laguerrePolynomials(int nodesCount, ArrayList<Node> nodes) {
        double finalSum = 0;
        for (int i = 0; i < nodesCount; i++) {
            finalSum += nodes.get(i).getNode() * container.function(nodes.get(i).getWeight());
        }
        return finalSum;
    }

    public void initiateTheNodes() {
        //const
        gaussNodeBy2.add(new Node(0.853553,0.5857864376269050));
        gaussNodeBy2.add(new Node(0.146447,3.414213562373095));

        gaussNodeBy3.add(new Node(0.711093,0.4157745567834791));
        gaussNodeBy3.add(new Node(0.278518,2.294280360279042));
        gaussNodeBy3.add(new Node(0.010389, 6.289945082937479));

        gaussNodeBy4.add(new Node(0.603154, 0.3225476896193923));
        gaussNodeBy4.add(new Node(0.357419, 1.745761101158347));
        gaussNodeBy4.add(new Node(0.038888, 4.536620296921180));
        gaussNodeBy4.add(new Node(0.000539, 9.395070912301133));

        gaussNodeBy5.add(new Node(0.521756, 0.2635603197181409));
        gaussNodeBy5.add(new Node(0.398667, 1.413403059106517));
        gaussNodeBy5.add(new Node(0.075942,3.596425771040722));
        gaussNodeBy5.add(new Node(0.003612, 7.085810005858880));
        gaussNodeBy5.add(new Node(0.000032, 12.64080084427578));
    }
}
