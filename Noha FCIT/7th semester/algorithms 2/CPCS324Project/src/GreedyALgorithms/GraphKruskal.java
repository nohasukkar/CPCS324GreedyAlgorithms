/*
  CPCS-324 Algorithms 
  ** Ebtehaj Talal ALbadri - 1907182 **
  ** Noha Abbas Sukkar - 1906817 **
  ** Joud Ehsan Jabalawi - 1905881 **
 */

package GreedyALgorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class GraphKruskal {

    public static class HeapNode {

        int vertex;
        int key;
    }

    public static class Edge { // Edge class to create many edge 

        int source; // integer number represent the source
        int destination; // integer number represent the destination
        int weight; // the weight of edge

        public Edge(int source, int destination, int weight) { // Edge contractor to add new Edge
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    
    public int vertices; // initial vertices  
    public ArrayList<Edge> allEdges = new ArrayList<>(); // initial ArrayList from Edge class

    public GraphKruskal() {
    }

    public GraphKruskal(int vertices) { // contractor to initial new GraphKruskal
        this.vertices = vertices;
    }

    public void addEgde(int source, int destination, int weight) { // method to add addEgde for GraphK  
        Edge edge = new Edge(source, destination, weight);
        allEdges.add(edge); //add to total edges
    }

    public void kruskalMST() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.weight));

        //add all the edges to priority queue, //sort the edges on weights
        for (int i = 0; i < allEdges.size(); i++) {
            pq.add(allEdges.get(i));

        }

        //create a parent []
        int[] parent = new int[vertices];

        //makeset
        makeSet(parent);

        ArrayList<Edge> mst = new ArrayList<>();

        //process vertices - 1 edges
        int index = 0;
        while (index < vertices - 1) {
            Edge edge = pq.remove();
            //check if adding this edge creates a cycle
            int x_set = find(parent, edge.source);
            int y_set = find(parent, edge.destination);

            if (x_set == y_set) {
                //ignore, will create cycle
            } else {
                //add it to our final result
                mst.add(edge);
                index++;
                union(parent, x_set, y_set);
            }
        }
        //print MST
        printGraph(mst);
    }

    public void makeSet(int[] parent) {
        //Make set- creating a new element with a parent pointer to itself.
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }
    }

    public int find(int[] parent, int vertex) {
        //chain of parent pointers from x upwards through the tree
        // until an element is reached whose parent is itself
        if (parent[vertex] != vertex) {
            return find(parent, parent[vertex]);
        }
        return vertex;
    }

    public void union(int[] parent, int x, int y) {
        int x_set_parent = find(parent, x);
        int y_set_parent = find(parent, y);
        //make x as parent of y
        parent[y_set_parent] = x_set_parent;
    }

    public void printGraph(ArrayList<Edge> edgeList) {
        int total_min_weight = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);
            total_min_weight += edge.weight;
        }
        System.out.println("\n---------------------------------------");
        System.out.println("        Kruskal Algorithm ");
        System.out.println("---------------------------------------");
        System.out.println("Total Minimum Key: " + total_min_weight);
    }

}
