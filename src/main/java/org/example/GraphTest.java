package org.example;

import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class GraphTest
{
    @org.junit.Test
    public void getSize()
    {
    }

    @org.junit.Test
    public void dijkstraWithUnconnectedGraph()
    {
        Graph d1 = new Graph(System.getProperty("user.dir") +
                "\\src\\main\\java\\org\\example\\" + "undirected_positive_islands.txt");
        int[] cost = new int[d1.getSize()];
        int[]  parents = new int[d1.getSize()];
        d1.dijkstra(0, cost, parents);

        int[] costSolution = new int[]{0, 100, 2, 104, 101, 1000000000, 1000000000,
            1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000};
        int[] parentSolution = new int[]{-1, 0, 0, 4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0};

        assertArrayEquals(costSolution, cost);
        assertArrayEquals(parentSolution, parents);
    }

    @org.junit.Test
    public void dijkstraSmallArray()
    {
        Graph d2 = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "dijkstra1.txt");
        int[] cost = new int[d2.getSize()];
        int[]  parents = new int[d2.getSize()];
        d2.dijkstra(0, cost, parents);

        int[] costSolution = new int[]{0, 2, 4, 1, 5};
        int[] parentSolution = new int[]{-1, 0, 3, 0, 2};

        assertArrayEquals(costSolution, cost);
        assertArrayEquals(parentSolution, parents);
    }
    @org.junit.Test
    public void dijkstraSmallArray2()
    {
        Graph d = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "dijkstra2.txt");
        int[] cost = new int[d.getSize()];
        int[]  parents = new int[d.getSize()];
        d.dijkstra(0, cost, parents);

        int[] costSolution = new int[]{0, 1000000000, 279, 200, 386, 248, 378, 389, 197, 524 };
        int[] parentSolution = new int[]{-1, 0, 3, 0, 8, 8, 5, 4, 0, 7};

        assertArrayEquals(costSolution, cost);
        assertArrayEquals(parentSolution, parents);
    }

    @org.junit.Test
    public void dijkstraSmallArray3()
    {
        Graph d = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "dijkstra5.txt");
        int[] cost = new int[d.getSize()];
        int[]  parents = new int[d.getSize()];
        d.dijkstra(0, cost, parents);

        int[] costSolution = new int[]{0, 1, 2};
        int[] parentSolution = new int[]{-1, 0, 1};

        assertArrayEquals(costSolution, cost);
        assertArrayEquals(parentSolution, parents);
    }
    @org.junit.Test
    public void dijkstra100Nodes()
    {
        Graph d = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "100NodesWithNoNegativeEdges.txt");
        int[] cost = new int[d.getSize()];
        int[]  parents = new int[d.getSize()];
        d.dijkstra(0, cost, parents);


        int[] costSolution = new int[]{0, 41, 820, 678, 532, 969, 675, 505, 731, 457, 332, 569, 865, 358, 318, 857, 491, 600, 447, 645, 933, 798, 910, 783, 153, 292, 633, 789, 485, 505, 452, 527, 596, 1016, 772, 869, 580, 507, 850, 718, 808, 651, 606, 767, 571, 259, 421, 291, 515, 443, 558, 672, 1086, 789, 734, 572, 589, 306, 641, 1005, 644, 1142, 580, 902, 542, 288, 817, 417, 411, 1126, 476, 650, 676, 936, 752, 554, 800, 452, 259, 551, 390, 391, 533, 263, 94, 892, 429, 460, 184, 616, 544, 508, 687, 477, 1059, 1179, 621, 387, 567, 426};
        int[] parentSolution = new int[]{-1,0,98,28,87,37,60,67,1,86,25,97,93,24,25,96,0,83,99,91,27,11,83,39,0,0,67,71,49,77,88,77,49,14,65,36,99,14,77,36,7,36,98,4,88,0,88,65,9,57,80,4,66,68,92,7,11,84,42,93,29,24,10,27,46,0,54,65,88,32,81,62,90,8,99,64,1,83,84,31,10,14,7,78,1,72,46,84,84,99,49,1,80,14,45,68,78,65,88,67};

        assertArrayEquals(costSolution, cost);
        assertArrayEquals(parentSolution, parents);
    }
    @org.junit.Test
    public void dijkstra1e6StressTest()
    {
        Graph d = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "dijkstra1e6Stress.txt");
        int[] cost = new int[d.getSize()];
        int[]  parents = new int[d.getSize()];

        long t1 = System.currentTimeMillis();
        d.dijkstra(0, cost, parents);
        long t2 = System.currentTimeMillis();

        System.out.println("Time taken for dijkstra 1e6 nodes: " + (t2-t1) + "ms");

    }

    @org.junit.Test
    public void bellman100NodesStress()
    {
        Graph d = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "100NodesWithNegativeEdges.txt");
        int[] cost = new int[d.getSize()];
        int[]  parents = new int[d.getSize()];

        long t1 = System.currentTimeMillis();
        d.bellmanFord(0, cost, parents);
        long t2 = System.currentTimeMillis();

        System.out.println("Time taken for bellman 100 nodes * 1000 edges: " + (t2-t1) + "ms");
    }
    @org.junit.Test
    public void bellman100NodesStressNoCycles()
    {
        Graph d = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "100NodesNegativeEdgesNoCycles.txt");
        int[] cost = new int[d.getSize()];
        int[]  parents = new int[d.getSize()];

        long t1 = System.currentTimeMillis();
        d.bellmanFord(0, cost, parents);
        long t2 = System.currentTimeMillis();

        System.out.println("Time taken for bellman 100 nodes * 1000 edges: " + (t2-t1) + "ms");
    }
    @org.junit.Test
    public void bellman1000NodesStress()
    {
        Graph d = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "1000NodesWithNegativeEdges.txt");
        int[] cost = new int[d.getSize()];
        int[]  parents = new int[d.getSize()];

        long t1 = System.currentTimeMillis();
        d.bellmanFord(0, cost, parents);
        long t2 = System.currentTimeMillis();

        System.out.println("Time taken for bellman 1000 nodes: " + (t2-t1) + "ms");
    }
   @org.junit.Test
    public void floyd100Nodes()
    {
        Graph d = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "100NodesWithNegativeEdges.txt");
        int[][] cost = new int[d.getSize()][d.getSize()];
        int[][]  parents = new int[d.getSize()][d.getSize()];

        long t1 = System.currentTimeMillis();
        d.floydWarshall(cost, parents);
        long t2 = System.currentTimeMillis();

        System.out.println("Time taken for floyd 100 nodes: " + (t2-t1) + "ms");
    }

    @org.junit.Test
    public void floyd100NodesStressNoCycles()
    {
        Graph d = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "100NodesNegativeEdgesNoCycles.txt");
        int[][] cost = new int[d.getSize()][d.getSize()];
        int[][]  parents = new int[d.getSize()][d.getSize()];

        long t1 = System.currentTimeMillis();
        d.floydWarshall(cost, parents);
        long t2 = System.currentTimeMillis();

        System.out.println("Time taken for floyd 100 nodes " + (t2-t1) + "ms");
    }

    @org.junit.Test
    public void bellmanWithUnconnectedGraph()
    {
        Graph d1 = new Graph(System.getProperty("user.dir") +
                "\\src\\main\\java\\org\\example\\" + "undirected_positive_islands.txt");
        int[] cost = new int[d1.getSize()];
        int[]  parents = new int[d1.getSize()];
        d1.bellmanFord(0, cost, parents);

        int[] costSolution = new int[]{0, 100, 2, 104, 101, 1000000000, 1000000000,
                1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000};
        int[] parentSolution = new int[]{-1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0};

        assertArrayEquals(costSolution, cost);
        assertArrayEquals(parentSolution, parents);
    }

    @org.junit.Test
    public void bellmanSmallArray()
    {
        Graph d2 = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "dijkstra1.txt");
        int[] cost = new int[d2.getSize()];
        int[]  parents = new int[d2.getSize()];
        d2.bellmanFord(0, cost, parents);

        int[] costSolution = new int[]{0, 2, 4, 1, 5};
        int[] parentSolution = new int[]{-1, 0, 3, 0, 2};

        assertArrayEquals(costSolution, cost);
        assertArrayEquals(parentSolution, parents);
    }
    @org.junit.Test
    public void bellmanSmallArray2()
    {
        Graph d = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "dijkstra2.txt");
        int[] cost = new int[d.getSize()];
        int[]  parents = new int[d.getSize()];
        d.bellmanFord(0, cost, parents);


        int[] costSolution = new int[]{0, 1000000000, 279, 200, 386, 248, 378, 389, 197, 524 };
        int[] parentSolution = new int[]{-1, 0, 3, 0, 8, 8, 5, 4, 0, 7};

        assertArrayEquals(costSolution, cost);
        assertArrayEquals(parentSolution, parents);
    }

    @org.junit.Test
    public void bellmanSmallArray3()
    {
        Graph d = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "dijkstra5.txt");
        int[] cost = new int[d.getSize()];
        int[]  parents = new int[d.getSize()];
        d.bellmanFord(0, cost, parents);


        int[] costSolution = new int[]{0, 1, 2};
        int[] parentSolution = new int[]{-1, 0, 1};

        assertArrayEquals(costSolution, cost);
        assertArrayEquals(parentSolution, parents);
    }

    @org.junit.Test
    public void floydWithUnconnectedGraph()
    {
        Graph d1 = new Graph(System.getProperty("user.dir") +
                "\\src\\main\\java\\org\\example\\" + "undirected_positive_islands.txt");
        int[][] cost = new int[d1.getSize()][d1.getSize()];
        int[][]  parents = new int[d1.getSize()][d1.getSize()];
        d1.floydWarshall(cost, parents);

//        int[] costSolution = new int[]{0, 100, 2, 104, 101, 1000000000, 1000000000,
//                1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000};
//        int[] parentSolution = new int[]{-1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0};
//
//        assertArrayEquals(costSolution, cost);
//        assertArrayEquals(parentSolution, parents);
    }

    @org.junit.Test
    public void floydSmallArray()
    {
        Graph d2 = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "dijkstra1.txt");
        int[][] cost = new int[d2.getSize()][d2.getSize()];
        int[][]  parents = new int[d2.getSize()][d2.getSize()];
        d2.floydWarshall(cost, parents);
//
//        int[] costSolution = new int[]{0, 2, 4, 1, 5};
//        int[] parentSolution = new int[]{-1, 0, 3, 0, 2};
//
//        assertArrayEquals(costSolution, cost);
//        assertArrayEquals(parentSolution, parents);
    }
    @org.junit.Test
    public void floydSmallArray2()
    {
        Graph d1 = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "dijkstra2.txt");
        int[][] cost = new int[d1.getSize()][d1.getSize()];
        int[][]  parents = new int[d1.getSize()][d1.getSize()];
        d1.floydWarshall(cost, parents);



//        int[] costSolution = new int[]{0, 1000000000, 279, 200, 386, 248, 378, 389, 197, 524 };
//        int[] parentSolution = new int[]{-1, 0, 3, 0, 8, 8, 5, 4, 0, 7};
//
//        assertArrayEquals(costSolution, cost);
//        assertArrayEquals(parentSolution, parents);
    }

    @org.junit.Test
    public void floydSmallArray3()
    {
        Graph d1 = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "dijkstra5.txt");
        int[][] cost = new int[d1.getSize()][d1.getSize()];
        int[][]  parents = new int[d1.getSize()][d1.getSize()];
        d1.floydWarshall(cost, parents);
        int[][] costSolution = new int[][]{{0, 1, 2}, {1, 0, 1}, {2, 1, 0}};
        int[][] parentSolution = new int[][]{{0, 0, 1}, {1, 0, 1}, {1, 2, 0}};
        assertTrue(Arrays.deepEquals(costSolution, cost));
        assertTrue(Arrays.deepEquals(parentSolution, parents));
    }

    @org.junit.Test
    public void hasNegativeCycleBellman() {
        Graph d = new DirectedGraph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "100NodesWithNegativeEdges.txt");
        int[] cost = new int[d.getSize()];
        int[]  parents = new int[d.getSize()];
        d.bellmanFord(0, cost, parents);
        assertTrue(d.hasNegativeCycle());
    }

    @org.junit.Test
    public void hasNegativeCycleBellman2()
    {
        Graph d = new DirectedGraph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "100NodesNegativeEdgesNoCycles.txt");
        int[] cost = new int[d.getSize()];
        int[]  parents = new int[d.getSize()];

        d.bellmanFord(0, cost, parents);

        assertFalse(d.hasNegativeCycle());
    }

    @org.junit.Test
    public void hasNegativeCycleFloyd() {
        Graph d = new DirectedGraph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "100NodesWithNegativeEdges.txt");
        int[][] cost = new int[d.getSize()][d.getSize()];
        int[][]  parents = new int[d.getSize()][d.getSize()];

        d.floydWarshall(cost, parents);

        assertTrue(d.hasNegativeCycle());
    }

    @org.junit.Test
    public void hasNegativeCycleFloyd2()
    {
        Graph d = new DirectedGraph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "100NodesNegativeEdgesNoCycles.txt");
        int[][] cost = new int[d.getSize()][d.getSize()];
        int[][]  parents = new int[d.getSize()][d.getSize()];

        d.floydWarshall(cost, parents);
        assertFalse(d.hasNegativeCycle());
    }
}