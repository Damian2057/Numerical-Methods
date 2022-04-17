package org.example.model;

import java.util.ArrayList;

public class Hermite {
    private final FunctionContainer container;
    private final ArrayList<Node> Gauss2 = new ArrayList<>();
    private final ArrayList<Node> Gauss3 = new ArrayList<>();
    private final ArrayList<Node> Gauss4 = new ArrayList<>();
    private final ArrayList<Node> Gauss5 = new ArrayList<>();

    public Hermite(FunctionContainer container) {
        this.container = container;
        initiateTheNodes();
    }

    public double[] calculateForEveryNode() {
        double[] resultArray = new double[4];
        resultArray[0] = laguerrePolynomials(2,Gauss2);
        resultArray[1] = laguerrePolynomials(3,Gauss3);
        resultArray[2] = laguerrePolynomials(4,Gauss4);
        resultArray[3] = laguerrePolynomials(5,Gauss5);
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
        Gauss2.add(new Node(0.853553,0.5857864376269050));
        Gauss2.add(new Node(0.146447,3.414213562373095));

        Gauss3.add(new Node(0.711093,0.4157745567834791));
        Gauss3.add(new Node(0.278518,2.294280360279042));
        Gauss3.add(new Node(0.010389, 6.289945082937479));

        Gauss4.add(new Node(0.603154, 0.3225476896193923));
        Gauss4.add(new Node(0.357419, 1.745761101158347));
        Gauss4.add(new Node(0.038888, 4.536620296921180));
        Gauss4.add(new Node(0.000539, 9.395070912301133));

        Gauss5.add(new Node(0.521756, 0.2635603197181409));
        Gauss5.add(new Node(0.398667, 1.413403059106517));
        Gauss5.add(new Node(0.075942,3.596425771040722));
        Gauss5.add(new Node(0.003612, 7.085810005858880));
        Gauss5.add(new Node(0.000032, 12.64080084427578));
    }
}
