package org.example;


import java.util.List;

public class Graph {
    private int numNodes;
    private List<Edge>[] adjList;

    public Graph(String filePath) {
        // implementation of reading graph file and initializing graph
    }

    public int getSize() {
        return numNodes;
    }

    public void dijkstra(int source, int[] costs, int[] parents) {
        // implementation of Dijkstra's algorithm
    }

    public boolean bellmanFord(int source, int[] costs, int[] parents) {
        // implementation of Bellman-Ford algorithm
        return true;
    }

    public boolean floydWarshall(int[][] costs, int[][] predecessors) {
        // implementation of Floyd-Warshall algorithm
        return true;
    }

    public boolean hasNegativeCycle() {
        return false;
    }

    private static class Edge {
        int dest;
        int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
}
