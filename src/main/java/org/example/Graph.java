package org.example;


import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Graph {
    private int numofNodes;
    private int numofEdges;
    private List<Edge>[] adjList;
    private NegativeCycleState negativeCycleState = NegativeCycleState.notCalculatedYet;

    public Graph(String path)
    {
        path = path.replace("\"","");
        File file = new File(path);

        try
        {
            Scanner sc = new Scanner(file);

            this.numofNodes = sc.nextInt();
            this.numofEdges = sc.nextInt();
            this.adjList = new List[this.numofNodes];

            for (int i = 0; i < this.numofEdges; i++)
            {
                int x = sc.nextInt(), y = sc.nextInt();
                int weight = sc.nextInt();

                x--; y--;
                this.adjList[x].add(new Edge(y, weight));
                this.adjList[y].add(new Edge(x, weight));
            }
        }
        catch (Exception e)
        {
        }
    }

    public int getSize() {
        return numofNodes;
    }

    public void dijkstra(int source, int[] costs, int[] parents)
    {
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        int inf = 1000000000;
        Arrays.fill(costs, inf);
        queue.add(new Pair(source, 0, -1));

        while (!queue.isEmpty())
        {
            int currentNode = queue.peek().currentNode;
            int nodeBefore = queue.peek().nodeBefore;
            int distance = queue.peek().distance;
            queue.poll();

            if (distance >= costs[currentNode]) continue;
            costs[currentNode] = distance;
            parents[currentNode] = nodeBefore;

            for (Edge edge : adjList[currentNode])
                queue.add(new Pair(edge.destination, distance + edge.weight, currentNode));
        }
    }

    public boolean bellmanFord(int source, int[] costs, int[] parents)
    {
        int inf = 1000000000;
        Arrays.fill(costs, inf);
        costs[source] = 0;

        for(int i = 1; i <= numofNodes; i++)
        {
            for (int node = 0; node < numofNodes; node++)
            {
                for (Edge edge : adjList[node])
                {
                    if (costs[node] + edge.weight < costs[edge.destination])
                    {
                        if (i == numofNodes)
                        {
                            this.negativeCycleState = NegativeCycleState.Cycle;
                            return false;
                        }
                        costs[edge.destination] = costs[node] + edge.weight;
                    }
                }
            }
        }
        this.negativeCycleState = NegativeCycleState.NoCycle;
        return true;
    }

    public boolean floydWarshall(int[][] costs, int[][] predecessors)
    {
        int inf = 1000000000;
        for (int i = 0; i < costs.length; i++)
        {
            Arrays.fill(costs[i], inf);
            costs[i][i] = 0;
        }

        for(int i = 0; i < numofNodes; i++)
            for(int j = 0; j < numofNodes; j++)
                for(int k = 0; k < numofNodes; k++)
                    if (costs[j][i] + costs[i][k] < costs[j][k])
                    {
                        costs[j][k] = costs[j][i] + costs[i][k];
                        predecessors[j][k] = i;
                    }
        for (int i = 0; i < numofNodes; i++)
            if (costs[i][i] < 0)
            {
                this.negativeCycleState = NegativeCycleState.Cycle;
                return false;
            }
        this.negativeCycleState = NegativeCycleState.NoCycle;
        return true;
    }

    public boolean hasNegativeCycle()
    {
        if (this.negativeCycleState == NegativeCycleState.notCalculatedYet)
        {
            int[] costs = new int[numofNodes];
            int[] parents = new int[numofNodes];
            this.bellmanFord(0, costs, parents);
        }
        return (this.negativeCycleState == NegativeCycleState.Cycle);
    }

    private static class Edge
    {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    private class Pair implements Comparable
    {
        int distance;
        int currentNode;
        int nodeBefore;

        public Pair(int currentNode, int distance, int nodeBefore)
        {
            this.distance = distance;
            this.currentNode = currentNode;
            this.nodeBefore = nodeBefore;
        }

        @Override
        public int compareTo(Object o)
        {
            Pair otherPair = (Pair)o;
            return this.distance - otherPair.distance;
        }
    }

    private enum NegativeCycleState
    {
        notCalculatedYet,
        Cycle,
        NoCycle
    }
}
