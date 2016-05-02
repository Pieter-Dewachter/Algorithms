package algorithms.HoehelKoarten;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class HoehelKoarten {
    private Graph g;

    public HoehelKoarten(String title) {
        g = new SingleGraph(title);
    }

    private void addHighway(String start, String stop, int weight) {
        if(g.getNode(start) == null) {
            Node firstNode =  g.addNode(start);
            firstNode.addAttribute("label", start);
        }

        if(g.getNode(stop) == null) {
            Node secondNode = g.addNode(stop);
            secondNode.addAttribute("label", stop);
        }

        Edge firstEdge = g.addEdge(start + "-" + stop, start, stop, true);
        firstEdge.addAttribute("weight", weight);
        firstEdge.addAttribute("label", ""+weight);
        firstEdge.addAttribute("vehicleAmount", 0);

        Edge secondEdge = g.addEdge(stop + "-" + start, stop, start, true);
        secondEdge.addAttribute("weight", weight);
        secondEdge.addAttribute("label", ""+weight);
        secondEdge.addAttribute("vehicleAmount", 0);
    }

    public Graph getGraph() {
        return g;
    }

    public void addCars(String from, String to) {

    }

    public static void main(String[] args) {
        HoehelKoarten kaart = new HoehelKoarten("Hoehel Koarten");

        kaart.addHighway("Brugge", "Gent", 50);
        kaart.addHighway("Brugge", "Antwerpen", 95);
        kaart.addHighway("Brugge", "Kortrijk", 56);
        kaart.addHighway("Gent", "Antwerpen", 60);
        kaart.addHighway("Gent", "Brussel", 50);
        kaart.addHighway("Brussel", "Antwerpen", 44);
        kaart.addHighway("Brussel", "Bergen", 78);
        kaart.addHighway("Brussel", "Waver", 30);
        kaart.addHighway("Brussel", "Leuven", 30);
        kaart.addHighway("Leuven", "Hasselt", 59);
        kaart.addHighway("Leuven", "Luik", 82);
        kaart.addHighway("Hasselt", "Luik", 53);
        kaart.addHighway("Kortrijk", "Bergen", 83);
        kaart.addHighway("Bergen", "Namen", 75);
        kaart.addHighway("Namen", "Waver", 40);
        kaart.addHighway("Namen", "Luik", 65);
        kaart.addHighway("Namen", "Neufchateau", 90);
        kaart.addHighway("Luik", "Neufchateau", 110);
        kaart.addHighway("Neufchateau", "Aarlen", 37);

        kaart.getGraph().display();
    }
}
