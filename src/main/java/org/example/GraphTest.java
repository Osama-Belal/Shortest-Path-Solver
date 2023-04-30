package org.example;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GraphTest
{

    @org.junit.Test
    public void getSize() {
    }

    @org.junit.Test
    public void dijkstra() {
        Graph g = new Graph(System.getProperty("user.dir")+"\\src\\main\\java\\org\\example\\"+ "dijkstra1.txt");
        int[] cost = new int[g.getSize()];
        int[]  parents = new int[g.getSize()];
        g.dijkstra(1, cost, parents);
        for(int i : cost)
            System.out.print(i + " ");
        System.out.println();

        for(int i : parents)
            System.out.print(i + " ");

        System.out.println();
    }

    @org.junit.Test
    public void bellmanFord() {
    }

    @org.junit.Test
    public void floydWarshall() {
    }

    @org.junit.Test
    public void hasNegativeCycle() {
    }
}