package org.example.model;

public class Laguerrea {
    private final FunctionContainer container;
    private final Node[][] gaussNode = new Node[6][6];
    private final Double epsilon;

    public Laguerrea(FunctionContainer container, double epsilon) {
        this.container = container;
        initiateTheNodes();
        this.epsilon = epsilon;
    }

    public void calculate() {
        int count = 2;
        double sum = laguerrePolynomials(gaussNode[2],2);
        double upperSum = laguerrePolynomials(gaussNode[3],3);
        int n = 4;
        while (Math.abs(upperSum-sum) > epsilon) {
            count++;
            sum = upperSum;
            if(n == 6) {
                System.out.println("Node: "+ count + " ,Result: " + sum);
                return;
            }
            upperSum = laguerrePolynomials(gaussNode[n],n);
            n++;
        }

        System.out.println("Node: "+ count + " ,Result: " + sum);
    }

    private double laguerrePolynomials(Node[] nodes, int nodesCount) {
        double finalSum = 0;
        for (int i = 0; i < nodesCount; i++) {
            finalSum += nodes[i].getNode() * container.function(nodes[i].getWeight());
        }
        return finalSum;
    }

    private void initiateTheNodes() {
        //const

        gaussNode[2][0] = new Node(0.853553,0.5857864376269050);
        gaussNode[2][1] = new Node(0.146447,3.414213562373095);

        gaussNode[3][0] = new Node(0.711093,0.4157745567834791);
        gaussNode[3][1] = new Node(0.278518,2.294280360279042);
        gaussNode[3][2] = new Node(0.010389, 6.289945082937479);

        gaussNode[4][0] = new Node(0.603154, 0.3225476896193923);
        gaussNode[4][1] = new Node(0.357419, 1.745761101158347);
        gaussNode[4][2] = new Node(0.038888, 4.536620296921180);
        gaussNode[4][3] = new Node(0.000539, 9.395070912301133);

        gaussNode[5][0] = new Node(0.521756, 0.2635603197181409);
        gaussNode[5][1] = new Node(0.398667, 1.413403059106517);
        gaussNode[5][2] = new Node(0.075942,3.596425771040722);
        gaussNode[5][3] = new Node(0.003612, 7.085810005858880);
        gaussNode[5][4] = new Node(0.000032, 12.64080084427578);
    }
}
