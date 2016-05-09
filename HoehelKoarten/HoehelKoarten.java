package algorithms.HoehelKoarten;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.algorithm.*;

import java.util.Scanner;

public class HoehelKoarten {
    private Graph g;

    /**
     * Constructor for our "Google Maps" class
     * @param title The title of this instance, will be used as Graph title
     */
    public HoehelKoarten(String title) {
        g = new SingleGraph(title);
        g.addAttribute("ui.stylesheet", "edge { shape: cubic-curve; }");
    }

    /**
     * Method which adds a highway to the maps system, with a certain "weight"
     * Both directions are automatically made, both with the same initial "weight"
     * Existing Nodes can be used, but new ones will be made when necessary
     * @param start The place (Node) where the highway starts
     * @param stop The place (Node) where the highway stops
     * @param weight The weight of this highway (~ travel time)
     */
    public void addHighway(String start, String stop, int weight) {
        // Checking if the first Node exists or needs to be added
        if(g.getNode(start) == null) {
            Node firstNode =  g.addNode(start);
            firstNode.addAttribute("label", start);
        }

        // Checking if the second Node exists or needs to be added
        if(g.getNode(stop) == null) {
            Node secondNode = g.addNode(stop);
            secondNode.addAttribute("label", stop);
        }

        // Adding the first directional Edge, and initialising it
        Edge firstEdge = g.addEdge(start + "-" + stop, start, stop, true);
        firstEdge.addAttribute("weight", weight);
        firstEdge.addAttribute("label", ""+weight);
        firstEdge.addAttribute("vehicles", 0);
        firstEdge.addAttribute("accidents", 0);

        // Adding the second directional Edge, and initialising it
        Edge secondEdge = g.addEdge(stop + "-" + start, stop, start, true);
        secondEdge.addAttribute("weight", weight);
        secondEdge.addAttribute("label", ""+weight);
        secondEdge.addAttribute("vehicles", 0);
        secondEdge.addAttribute("accidents", 0);
    }

    /**
     * Simple display method, the advanced GraphStream UI Renderer is used
     */
    public void display() {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        g.display();
    }

    /**
     * Method which adds 1k cars to a certain highway (directional!)
     * @param from The place (Node) where the highway starts
     * @param to The place (Node) where the highway stops
     */
    public void addCars(String from, String to) {
        Edge edge = g.getEdge(from + "-" + to);

        // Incrementing the weight by 1 for 1k extra cars
        int weight = edge.getAttribute("weight");
        edge.setAttribute("weight", ++weight);

        // Adding the actual 1k cars to the Edge
        int vehicles = edge.getAttribute("vehicles");
        edge.setAttribute("vehicles", vehicles+1000);

        // Getting the current amount of accidents
        int accidents = edge.getAttribute("accidents");

        for(int i = 0; i < 1000; i++) {
            // 0.1% probability of an accident per 1k cars
            if(Math.random()*1000 <= vehicles/1000) {
                edge.setAttribute("accidents", ++accidents);

                // Adding 30 weight for the first accidents, extra accidents add 50
                if (accidents == 1)
                    weight += 30;
                else
                    weight += 50;
            }
        }

        // Updating the weight and label attributes for correct displaying
        edge.setAttribute("weight", weight);
        edge.setAttribute("label", ""+weight);
    }

    /**
     * Methods which returns the shortest path from A to B, using Dijkstra's algorithm
     * @param from The place (Node) where you want to start your route
     * @param to The place (Node) where you want to navigate to
     * @return String containing the Nodes you should pass for the shortest route
     */
    public String calculateRoute(String from, String to) {
        Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "weight");

        dijkstra.init(g);
        dijkstra.setSource(g.getNode(from));
        dijkstra.compute();

        return dijkstra.getPath(g.getNode(to)).toString();
    }

    /**
     * Main function which adds the major highways of Belgium
     * You can add cars to highways and calculate shortest routes
     * @param args Needed for the user input
     */
    public static void main(String[] args) {
        // Creating an instance of this class and a Scanner instance
        HoehelKoarten kaart = new HoehelKoarten("Hoehel Koarten");
        Scanner scanner = new Scanner(System.in);

        // Adding all major highways of Belgium
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

        System.out.println("********************* Hoehel Maps *********************");
        System.out.println("Enter 'add' to add cars or 'route' to calculate a route");
        kaart.display();

        String from = "";
        String to;
        String method = "";
        int state = -1;

        while(true) {
            String next = scanner.next();
            if(next.equals("add") || next.equals("route")) {
                System.out.println("  Enter the starting point");
                method = next;
            }
            else {
                if (state == 0) {
                    from = next;
                    System.out.println("  Enter the stopping point");
                }
                else if (state == 1) {
                    to = next;
                    if(method.equals("add"))
                        kaart.addCars(from, to);
                    else
                        System.out.println(kaart.calculateRoute(from, to));
                    state = -2;
                    System.out.println("Your query is finished!\n");
                    System.out.println("Enter 'add' to add cars or 'route' to calculate a route");
                }
                else
                    continue;
            }
            state++;
        }
    }
}
