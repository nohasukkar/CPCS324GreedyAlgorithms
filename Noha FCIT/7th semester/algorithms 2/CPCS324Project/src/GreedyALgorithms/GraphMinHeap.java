/*
  CPCS-324 Algorithms 
  ** Ebtehaj Talal ALbadri - 1907182 **
  ** Noha Abbas Sukkar - 1906817 **
  ** Joud Ehsan Jabalawi - 1905881 **
 */
package GreedyALgorithms;

import java.util.Comparator;
import java.util.LinkedList;

public class GraphMinHeap {
public static class MinHeap {

    int capacity;
    int currentSize;
    HeapNode[] mH;
    int[] indexes; //will be used to decrease the key

    public MinHeap(int capacity) { // create Main2 for kwon capacity
        this.capacity = capacity;
        mH = new HeapNode[capacity + 1];
        indexes = new int[capacity];
        mH[0] = new HeapNode();
        mH[0].key = Integer.MIN_VALUE;
        mH[0].vertex = -1;
        currentSize = 0;
    }
    
    public static class HeapNode {  //  class for Heap Node  we save the vertex and key
            int vertex;
            int key;
        }

    public void insert(HeapNode x) {
        currentSize++;
        int idx = currentSize;
        mH[idx] = x;
        indexes[x.vertex] = idx;
        bubbleUp(idx);
    }

    public void bubbleUp(int pos) {
        int parentIdx = pos / 2;
        int currentIdx = pos;
        while (currentIdx > 0 && mH[parentIdx].key > mH[currentIdx].key) {
            HeapNode currentNode = mH[currentIdx];
            HeapNode parentNode = mH[parentIdx];
            //swap the positions
            indexes[currentNode.vertex] = parentIdx;
            indexes[parentNode.vertex] = currentIdx;
            swap(currentIdx, parentIdx);
            currentIdx = parentIdx;
            parentIdx = parentIdx / 2;
        }
    }

    public HeapNode extractMin() {
        HeapNode min = mH[1];
        HeapNode lastNode = mH[currentSize];
//            update the indexes[] and move the last node to the top
        indexes[lastNode.vertex] = 1;
        mH[1] = lastNode;
        mH[currentSize] = null;
        sinkDown(1);
        currentSize--;
        return min;
    }

    public void sinkDown(int k) {
        int smallest = k;
        int leftChildIdx = 2 * k;
        int rightChildIdx = 2 * k + 1;
        if (leftChildIdx < heapSize() && mH[smallest].key > mH[leftChildIdx].key) {
            smallest = leftChildIdx;
        }
        if (rightChildIdx < heapSize() && mH[smallest].key > mH[rightChildIdx].key) {
            smallest = rightChildIdx;
        }
        if (smallest != k) {
            HeapNode smallestNode = mH[smallest];
            HeapNode kNode = mH[k];
            //swap the positions
            indexes[smallestNode.vertex] = k;
            indexes[kNode.vertex] = smallest;
            swap(k, smallest);
            sinkDown(smallest);
        }
    }

    public void swap(int a, int b) {
        HeapNode temp = mH[a];
        mH[a] = mH[b];
        mH[b] = temp;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int heapSize() {
        return currentSize;
    }

};
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

    public static class GraphMH { // GraphMH class to create many GraphMH

        int vertices; // number of node in graph 
        LinkedList<Edge>[] adjacencylist; // LinkedList from Edge

        public GraphMH() {
        }

        public GraphMH(int vertices) { // GraphMH contractor
            this.vertices = vertices;
            adjacencylist = new LinkedList[vertices]; //initialize adjacency lists for all the vertices
            for (int i = 0; i < vertices; i++) {
                adjacencylist[i] = new LinkedList<>(); // create  adjacencylist for every vertices
            }
        }

        public void addEdge(int source, int destination, int weight) { // add Edge in adjacencylist  
            Edge edge = new Edge(source, destination, weight); // create the edge
            adjacencylist[source].addFirst(edge); //  add Edge in source's adjacencylist

            edge = new Edge(destination, source, weight); // create the edge
            adjacencylist[destination].addFirst(edge); //   add Edge in destination's adjacencylist 
            /*
            * because it for undirected graph we must add the edge 
             in source's adjacencylist and destination's adjacencylist
             */

        }

        static class HeapNode2 {  //  class for Heap Node  we save the vertex and key

            int vertex;
            int key;
        }

        public static class ResultSet { // class for  Result Set we save the parent and weight

            int parent;
            int weight;
        }

        // class to represent a node in PriorityQueue
        // Stores a vertex and its corresponding
        // key value
        public class node {

            int vertex;
            int key;
        }

        // Comparator class created for PriorityQueue
        // returns 1 if node0.key > node1.key
        // returns 0 if node0.key < node1.key and
        // returns -1 otherwise
        public class comparator implements Comparator<node> {

            @Override
            public int compare(node node0, node node1) {
                return node0.key - node1.key;
            }
        }

        //********************* prim  min heap ********************* 
        public void primMST(GraphMH graph) {

            boolean[] inHeap = new boolean[graph.vertices]; // 
            ResultSet[] resultSet = new ResultSet[graph.vertices]; // create array to save for result Set
            //keys[] used to store the key to know whether min hea update is required
            int[] key = new int[graph.vertices];
            //create heapNode for all the vertices
            MinHeap.HeapNode[] heapNodes = new MinHeap.HeapNode[graph.vertices];
            for (int i = 0; i < graph.vertices; i++) {
                heapNodes[i] = new MinHeap.HeapNode();
                heapNodes[i].vertex = i;
                heapNodes[i].key = Integer.MAX_VALUE; // initial value 
                resultSet[i] = new ResultSet();// initial new result set for this vertices 
                resultSet[i].parent = -1; // initial value 
                inHeap[i] = true; // initial value 
                key[i] = Integer.MAX_VALUE; // initial value 
            }

            //decrease the key for the first index
            heapNodes[0].key = 0;

            //add all the vertices to the MinHeap
            MinHeap minHeap = new MinHeap(graph.vertices);
            //add all the vertices to priority queue
            for (int i = 0; i < graph.vertices; i++) {
                minHeap.insert(heapNodes[i]);
            }

            //while minHeap is not empty
            while (!minHeap.isEmpty()) {
                //extract the min
                MinHeap.HeapNode extractedNode = minHeap.extractMin();

                //extracted vertex
                int extractedVertex = extractedNode.vertex;
                inHeap[extractedVertex] = false;// deleted vertex   

                //iterate through all the adjacent vertices
                LinkedList<Edge> list = graph.adjacencylist[extractedVertex];
                for (int i = 0; i < list.size(); i++) {
                    Edge edge = list.get(i);
                    //only if edge destination is present in heap
                    if (inHeap[edge.destination]) {
                        int destination = edge.destination;
                        int newKey = edge.weight;
                        //check if updated key < existing key, if yes, update if
                        if (key[destination] > newKey) {
                            decreaseKey(minHeap, newKey, destination);
                            //update the parent node for destination
                            resultSet[destination].parent = extractedVertex;
                            resultSet[destination].weight = newKey;
                            key[destination] = newKey;
                        }
                    }
                }
            }
            //print mst
            printMST(graph, resultSet);
        }

        public void decreaseKey(MinHeap minHeap, int newKey, int vertex) { // function for updated key

            //get the index which key's needs a decrease;
            int index = minHeap.indexes[vertex];

            //get the node and update its value
            MinHeap.HeapNode node = minHeap.mH[index];
            node.key = newKey;
            minHeap.bubbleUp(index);
        }

        public void printMST(GraphMH graph, ResultSet[] resultSet) {
            int total_min_weight = 0; // initialize total_min_weight to count the weight 

            for (int i = 1; i < graph.vertices; i++) {// print resultSet of Minimum Spanning Tree

                total_min_weight += resultSet[i].weight; //  count the weight
            }
            System.out.println("\n-----------------------------------------");// print Total minimum key 
            System.out.println("     Min-Heap based Prim Algorithm  ");
            System.out.println("---------------------------------------");
            System.out.println("Total minimum key: " + total_min_weight); 
        }

      
    }
    
}
