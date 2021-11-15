/*
  CPCS-324 Algorithms 
  ** Ebtehaj Talal ALbadri - 1907182 **
  ** Noha Abbas Sukkar - 1906817 **
  ** Joud Ehsan Jabalawi - 1905881 **
 */
package GreedyALgorithms;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import javafx.util.Pair;

public class GraphPriorityQueue {// GraphPriorityQueue class to create many GraphPriorityQueue

    static class HeapNode {

        int vertex;
        int key;
    }

    static class Edge { // Edge class to create many edge 

        int source; // integer number represent the source
        int destination; // integer number represent the destination
        int weight; // the weight of edge

        public Edge(int source, int destination, int weight) { // Edge contractor to add new Edge
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    static class ResultSet {

        int parent;
        int weight;
    }

    int vertices; // number of node in graph 
    LinkedList<Edge>[] adjacencylist; // LinkedList from Edge 

    public GraphPriorityQueue() {
    }

    GraphPriorityQueue(int vertices) { // GraphPriorityQueue contractor
        this.vertices = vertices;
        adjacencylist = new LinkedList[vertices];  //initialize adjacency lists for all the vertices

        for (int i = 0; i < vertices; i++) {
            adjacencylist[i] = new LinkedList<>(); // create  adjacencylist for every vertices
        }
    }

    public void addEdge(int source, int destination, int weight) { // add Edge in adjacencylist  
        Edge edge = new Edge(source, destination, weight); // create the edge
        adjacencylist[source].addFirst(edge); //  add Edge in source's adjacencylist

        edge = new Edge(destination, source, weight); // create the edge
        adjacencylist[destination].addFirst(edge); //   add Edge in destination's adjacencylist 
       
    }

    // class to represent a node in PriorityQueue
    // Stores a vertex and its corresponding
    // key value
    class node {

        int vertex;
        int key;
    }

    class comparator implements Comparator<node> {

        @Override
        public int compare(node node0, node node1) {
            return node0.key - node1.key;
        }
    }

    //********************* prime priority queue ********************* 
    public void prims_mst() {

        boolean[] mst = new boolean[vertices];
        GraphPriorityQueue.ResultSet[] resultSet = new GraphPriorityQueue.ResultSet[vertices]; // create array to save for result Set
        int[] key = new int[vertices];  //keys used to store the key to know whether priority queue update is required

        //Initialize all the keys to infinity and
        //initialize resultSet for all the vertices
        for (int i = 0; i < vertices; i++) {
            key[i] = Integer.MAX_VALUE;
            resultSet[i] = new GraphPriorityQueue.ResultSet();
        }

        //Initialize priority queue
        //override the comparator to do the sorting based keys
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertices, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                //sort using key values
                int key1 = p1.getKey();
                int key2 = p2.getKey();
                return key1 - key2;
            }
        });

        //create the pair for for the first index, 0 key 0 index
        key[0] = 0;
        Pair<Integer, Integer> p0 = new Pair<>(key[0], 0);
        //add it to pq
        pq.offer(p0);

        resultSet[0] = new GraphPriorityQueue.ResultSet();
        resultSet[0].parent = -1;

        //while priority queue is not empty
        while (!pq.isEmpty()) {
            //extract the min
            Pair<Integer, Integer> extractedPair = pq.poll();

            //extracted vertex
            int extractedVertex = extractedPair.getValue();
            mst[extractedVertex] = true;

            //iterate through all the adjacent vertices and update the keys
            LinkedList<Edge> list = adjacencylist[extractedVertex];
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                //only if edge destination is not present in mst
                if (mst[edge.destination] == false) {
                    int destination = edge.destination;
                    int newKey = edge.weight;
                    //check if updated key < existing key, if yes, update if
                    if (key[destination] > newKey) {
                        //add it to the priority queue
                        Pair<Integer, Integer> p = new Pair<>(newKey, destination);
                        pq.offer(p);
                        //update the resultSet for destination vertex
                        resultSet[destination].parent = extractedVertex;
                        resultSet[destination].weight = newKey;
                        //update the key[]
                        key[destination] = newKey;
                    }
                }
            }
        }
        //print mst
        print_MST(resultSet);
    }

    public void print_MST(ResultSet[] resultSet) {
        int total_min_weight = 0;
        
        for (int i = 1; i < vertices; i++) {

            // #### if you want to see the vertices of the Minimum Spanning Tree###  
            // System.out.println("Edge: " + i + " - " + resultSet[i].parent +
            //       " key: " + resultSet[i].weight);
            total_min_weight += resultSet[i].weight;
        }
        System.out.println("\n---------------------------------------");
        System.out.println("  Priority-queue based Prim Algorithm  ");
        System.out.println("---------------------------------------");
        System.out.println("Total Minimum Key: "+total_min_weight );
    }

}
