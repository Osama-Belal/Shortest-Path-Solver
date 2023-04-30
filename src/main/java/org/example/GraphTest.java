package org.example;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GraphTest
{
    Graph d1 = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "undirected_positive_islands.txt");
    Graph d2 = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "dijkstra2.txt");
    Graph d3 = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "dijkstra3.txt");
    Graph d4 = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "dijkstra4.txt");
    Graph d5 = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "dijkstra5.txt");
    Graph negative1 = new Graph(System.getProperty("user.dir") +  "\\src\\main\\java\\org\\example\\" + "Directed1.txt");

    @org.junit.Test
    public void getSize()
    {
    }

    @org.junit.Test
    public void dijkstra()
    {
        int[] cost = new int[d1.getSize()];
        int[]  parents = new int[d1.getSize()];
        d1.dijkstra(0, cost, parents);

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