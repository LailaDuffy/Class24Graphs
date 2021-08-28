package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Graph myGraph = createGraph();

        // Print all the vertices
        System.out.println("All the vertices of myGraph: ");
        for (Vertex myVertex : myGraph.getAdjVertices().keySet()) {
            System.out.println(myVertex.label);
        }
        System.out.println();

        // Create graph of airports and routes
        Graph flightsGraph = airportsGraph();

        // Print out all the elements from the new graph
        System.out.println("All the elements from flightsGraph: ");
        for (Vertex eachVertex : flightsGraph.getAdjVertices().keySet()) {
            System.out.println(eachVertex.label);
        }
        System.out.println();

        // Find out how many direct flights there are in total (one edge = 2 flights (to and from))
        int directFlights = 0;
        for (Vertex eachVertex : flightsGraph.getAdjVertices().keySet()) {
            for (Vertex eachFlight : flightsGraph.getAdjVertices(eachVertex.label)) {
                directFlights++;
            }
        }
        System.out.println("Direct flights: " + directFlights);
        System.out.println();

        // Print out all airports which are connected to Cape Town with direct flight
        System.out.println("All the airports that have a direct flight to Cape Town: ");
        for (Vertex eachVertex : flightsGraph.getAdjVertices("Cape Town")) {
            System.out.println(eachVertex.label);
        }
        System.out.println();

        // How many direct flights are there from Jo'burg airport
        directFlights = 0;
        for (Vertex eachVertex : flightsGraph.getAdjVertices("Jo'burg (OR Tambo)")) {
            directFlights++;
        }
        System.out.println("Direct flights: " + directFlights);


    }

    static Graph airportsGraph() {
        Graph graph = new Graph();
        graph.addVertex("Livingstone");
        graph.addVertex("Victoria Falls");
        graph.addVertex("Harare");
        graph.addVertex("Windhoek");
        graph.addVertex("Jo'burg (OR Tambo)");
        graph.addVertex("Mauritius");
        graph.addVertex("Cape Town");
        graph.addVertex("PE");
        graph.addVertex("East London");
        graph.addVertex("Durbs");
        graph.addEdge("Livingstone", "Jo'burg (OR Tambo)");
        graph.addEdge("Victoria Falls", "Jo'burg (OR Tambo)");
        graph.addEdge("Harare", "Jo'burg (OR Tambo)");
        graph.addEdge("Windhoek", "Jo'burg (OR Tambo)");
        graph.addEdge("Mauritius", "Jo'burg (OR Tambo)");
        graph.addEdge("Cape Town", "Jo'burg (OR Tambo)");
        graph.addEdge("Cape Town", "Durbs");
        graph.addEdge("Cape Town", "East London");
        graph.addEdge("Cape Town", "PE");
        graph.addEdge("PE", "Jo'burg (OR Tambo)");
        graph.addEdge("PE", "Durbs");
        graph.addEdge("East London", "Jo'burg (OR Tambo)");
        graph.addEdge("Durbs", "Jo'burg (OR Tambo)");
        return graph;
    }

    static Graph createGraph() {
        Graph graph = new Graph();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addEdge("Bob", "Alice");
        graph.addEdge("Bob", "Rob");
        graph.addEdge("Alice", "Mark");
        graph.addEdge("Rob", "Mark");
        graph.addEdge("Alice", "Maria");
        graph.addEdge("Rob", "Maria");
        return graph;
    }

    // explores vertices as deeper as possible along each branch before exploring vertices at the same level
    Set<String> depthFirstTraversal(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<String>();
        Stack<String> stack = new Stack<String>();
        stack.push(root);
        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (Vertex v : graph.getAdjVertices(vertex)) {
                    stack.push(v.label);
                }
            }
        }
        return visited;
    }

    // explores all neighboring vertices at the same level before going deeper in the graph
    Set<String> breadthFirstTraversal(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            for (Vertex v : graph.getAdjVertices(vertex)) {
                if (!visited.contains(v.label)) {
                    visited.add(v.label);
                    queue.add(v.label);
                }
            }
        }
        return visited;
    }
}
