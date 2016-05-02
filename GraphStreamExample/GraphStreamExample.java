package algorithms.GraphStreamExample;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class GraphStreamExample {
    public static void main(String[] args) {
        Graph g = new SingleGraph("Graph example");
        g.addNode("A");
        g.addNode("B");
        g.addNode("C");
        g.addEdge("AB", "A", "B");
        g.addEdge("AC", "A", "C");
        g.addEdge("BC", "B", "C");

        g.display();
    }
}
