/*
  CPCS-324 Algorithms 
  ** Ebtehaj Talal ALbadri - 1907182 **
  ** Noha Abbas Sukkar - 1906817 **
  ** Joud Ehsan Jabalawi - 1905881 **
 */
package GreedyALgorithms;

import java.util.Scanner;

public class MainProgram {

    public static GraphPriorityQueue GP = new GraphPriorityQueue(); // GraphPriorityQueue for Priority Queue
    public static GraphKruskal GK = new GraphKruskal(); // GraphPriorityQueue for Kruksal
    public static GraphMinHeap.GraphMH MH = new GraphMinHeap.GraphMH(); // GraphPriorityQueue using Min-heap based Prim
    public static int CompareChoice; // User input choice for algorithm comparision

    public static void main(String[] args) {
        System.out.println("******************************************************************");
        System.out.println("  ---- CPCS-324 Algorithms and Data Structures (II) Project ---- ");
        System.out.println("******************************************************************");
        System.out.println("\nThese are the available algorithms you can compare between them: ");
        System.out.println("1- Kruskal and Priority-queue (pq) based Prim");
        System.out.println("2- Min-heap (mh) based Prim and Priority-queue (pq) based Prim");
        System.out.print("\nChoose the algorithms you want to compare between them: ");

        // reading from user the number 
        Scanner sc = new Scanner(System.in);

        CompareChoice = sc.nextInt();
        int userChoice ; // user case choice for graph from 1 to 10

        switch (CompareChoice) {
            case 1:
                PrintMenu(); // Display cases from 1 to 10
                userChoice = sc.nextInt(); // Read choice from user
                System.out.println(""); // Space
                CompareK_PQ(userChoice); //  Call for Kroksal algorithm and Priority-queue comparision function

                break;
            case 2:
                PrintMenu(); // Display cases from 1 to 10
                userChoice = sc.nextInt(); // Read choice from user
                System.out.println(""); // Space
                ComparePQ_MH(userChoice); //  Call for Priority-queue algorithm and Min-heap based Prim comparision function
                break;
            default:
                System.out.println("Wrong selection!"); // if user did not choose 1 or 2

        }

    }

    public static void PrintMenu() {
        System.out.println("\nThe Program offers 10 vary cases with vary Number of Vartices and Edges...");
        System.out.println("The available cases are: ");
        System.out.println("-------------------------------------------------------");
        System.out.println(" Case Number  |  Number of vertices  | Number of Edges ");
        System.out.println("-------------------------------------------------------");
        System.out.println("      1       |        1000          |      10000");
        System.out.println("      2       |        1000          |      15000");
        System.out.println("      3       |        1000          |      25000");
        System.out.println("      4       |        5000          |      15000");
        System.out.println("      5       |        5000          |      25000");
        System.out.println("      6       |        10000         |      15000");
        System.out.println("      7       |        10000         |      25000");
        System.out.println("      8       |        20000         |      200000");
        System.out.println("      9       |        20000         |      300000");
        System.out.println("     10       |        50000         |     1,000,000");
        System.out.println("-------------------------------------------------------");
        System.out.print("Choose the case number: ");

    }

    public static void Make_Graph(int n, int m) {
        if (CompareChoice == 1) {
            GP = new GraphPriorityQueue(n); // GraphPriorityQueue for PQ
            GK = new GraphKruskal(n); // GraphPriorityQueue for Kruksal
            for (int i = 0; i < m; i++) {

                int src = (int) (Math.random() * n); // Generate random number between 0 to n
                int dest = (int) (Math.random() * n); // Generate random number between 0 to n
                int weight = 1 + (int) (Math.random() * 21); // Generate random number between 0 to 10

                if (i < n) { // to be sure all node are connected 
                    if (i == dest)// to be sure no node have edge with himself   
                    {
                        dest++;
                    }
                    GP.addEdge(i, dest, weight);
                    GK.addEgde(i, dest, weight);

                } else {
                    if (src == dest)// to be sure no node have edge with himself   
                    {
                        dest++;
                    }
                    GP.addEdge(src, dest, weight);
                    GK.addEgde(src, dest, weight);
                }

            }
        } else {
            GP = new GraphPriorityQueue(n); // GraphPriorityQueue for PQ
            MH = new GraphMinHeap.GraphMH(n);

            for (int i = 0; i < m; i++) {

                int src = (int) (Math.random() * n); // Generate random number between 0 to 1000
                int dest = (int) (Math.random() * n); // Generate random number between 0 to 1000
                int weight = 1 + (int) (Math.random() * 21); // Generate random number between 0 to 10

                if (i < n) { // to be sure all node is concted 
                    if (i == dest)// to be sure no node have edge with himself   
                    {
                        dest++;
                    }

                    GP.addEdge(i, dest, weight);
                    MH.addEdge(i, dest, weight);
                    //     System.out.println("("+i+","+ dest+")"+" weight :"+weight);
                } else {
                    if (src == dest)// to be sure no node have edge with self  
                    {
                        dest++;
                    }
                    GP.addEdge(src, dest, weight);
                    MH.addEdge(src, dest, weight);
                    //  System.out.println("("+src+","+ dest+")"+" weight :"+weight);
                }
            }
        }
        

    }

    public static void CompareK_PQ(int choice) {

        switch (choice) {

            case 1: // •	n=1000 and m=10000

                //************************************************************** 
                Make_Graph(1000, 10000); // نضيف براميتر جديدة  - يوزر الخوارزم  او متغير جديد
                PrintEff(1000, 10000);
                break;
            //**************************************************************

            case 2: // •	n=1000 and m= 15000

                //************************************************************** 
                Make_Graph(1000, 15000);
                PrintEff(1000, 15000);
                break;
            //**************************************************************

            case 3:// •	n=1000 and m= 25000 

                //************************************************************** 
                Make_Graph(1000, 25000);
                PrintEff(1000, 25000);
                break;
            //**************************************************************

            case 4: //•	n=5000 and m=15000

                //************************************************************** 
                Make_Graph(5000, 15000);
                PrintEff(5000, 15000);
                break;
            //**************************************************************

            case 5:// •	n=5000 and m= 25000

                //************************************************************** 
                Make_Graph(5000, 25000);
                PrintEff(5000, 25000);
                break;
            //**************************************************************

            case 6:// •	n=10000 and m=15000

                //************************************************************** 
                Make_Graph(10000, 15000);
                PrintEff(10000, 15000);
                break;
            //**************************************************************

            case 7:// •	n=10000 and m= 25000

                //************************************************************** 
                Make_Graph(10000, 25000);
                PrintEff(10000, 25000);
                break;
            //**************************************************************

            case 8: //•	n=20000 and m=200000

                //************************************************************** 
                Make_Graph(20000, 200000);
                PrintEff(20000, 200000);
                break;
            //**************************************************************

            case 9:// •	n=20000 and m= 300000

                //************************************************************** 
                Make_Graph(20000, 300000);
                PrintEff(20000, 300000);
                break;
            //**************************************************************
            case 10: // •	n=50000 and m= 1000000
                Make_Graph(50000, 1000000);
                PrintEff(50000, 1000000);
        }

    }

    public static void ComparePQ_MH(int choice) {

        switch (choice) {

            case 1: // •	n=1000 and m=10000

                //************************************************************** 
                Make_Graph(1000, 10000);
                PrintEff(1000, 10000);
                break;
            //**************************************************************

            case 2: // •	n=1000 and m= 15000

                //************************************************************** 
                Make_Graph(1000, 15000);
                PrintEff(1000, 15000);
                break;
            //**************************************************************

            case 3:// •	n=1000 and m= 25000 

                //************************************************************** 
                Make_Graph(1000, 25000);
                PrintEff(1000, 25000);
                break;
            //**************************************************************

            case 4: //•	n=5000 and m=15000

                //************************************************************** 
                Make_Graph(5000, 15000);
                PrintEff(5000, 15000);
                break;
            //**************************************************************

            case 5:// •	n=5000 and m= 25000

                //************************************************************** 
                Make_Graph(5000, 25000);
                PrintEff(5000, 25000);
                break;
            //**************************************************************

            case 6:// •	n=10000 and m=15000

                //************************************************************** 
                Make_Graph(10000, 15000);
                PrintEff(10000, 15000);
                break;
            //**************************************************************

            case 7:// •	n=10000 and m= 25000

                //************************************************************** 
                Make_Graph(10000, 25000);
                PrintEff(10000, 25000);
                break;
            //**************************************************************

            case 8: //•	n=20000 and m=200000

                //************************************************************** 
                Make_Graph(20000, 200000);
                PrintEff(20000, 200000);
                break;
            //**************************************************************

            case 9:// •	n=20000 and m= 300000

                //************************************************************** 
                Make_Graph(20000, 300000);
                PrintEff(20000, 300000);
                break;
            //**************************************************************
            case 10: // •	n=50000 and m= 1000000
                Make_Graph(50000, 1000000);
                PrintEff(50000, 1000000);
        }

    }

    public static void PrintEff(int n, int m) {
        System.out.println("*****************************************************");
        System.out.println("     The Minimum Spanning Tree of two algorithms  ");
        System.out.println("*****************************************************");
        System.out.println("The Number of vertices = " + n + "  |  Number of Edges = " + m);
        if (CompareChoice == 1) {
            long PQT = CountTimePQ(GP);
            System.out.println("The Time in Priority-queue based Prim : " + PQT / 1000000
                    + " milliseconds");
            
            long KT = CountTimeK(GK);
            System.out.println("The Time in Kruskal : " + KT / 1000000 + " milliseconds ");
        } else {

            long PQT = CountTimePQ(GP);
            System.out.println("The Time in Priority-queue based Prim : " + PQT / 1000000
                    + " milliseconds"); 
            
            long MHT = CountTimeMH(MH);
            System.out.println("The Time in Min-heap based Prim : " + MHT / 1000000 + " milliseconds ");
        }
    }

    public static long CountTimePQ(GraphPriorityQueue GP) {
        long StartTimePQ = System.nanoTime();
        GP.prims_mst();
        long EndTimePQ = System.nanoTime();
        long totalTimePQ = EndTimePQ - StartTimePQ;

        return totalTimePQ;
    }

    public static long CountTimeK(GraphKruskal GK) {
        long StartTimeK = System.nanoTime();
        GK.kruskalMST(); // call kruskal ALgorithm
        long EndTimeK = System.nanoTime();
        long totalTimeK = EndTimeK - StartTimeK;

        return totalTimeK;
    }

    public static long CountTimeMH(GraphMinHeap.GraphMH mh) {
        long StartTimeMH = System.nanoTime();
        MH.primMST(mh);
        long EndTimeMH = System.nanoTime();
        long TotalTimeMH = EndTimeMH - StartTimeMH;

        return TotalTimeMH;
    }

}
