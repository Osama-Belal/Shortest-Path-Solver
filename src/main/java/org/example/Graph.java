package org.example;


import java.io.*;
import java.util.*;

public class Graph
{
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
            for(int i = 0; i < this.numofNodes; i++)
                this.adjList[i] = new ArrayList<>();


            for (int i = 0; i < this.numofEdges; i++)
            {
                int x = sc.nextInt(), y = sc.nextInt();
                int weight = sc.nextInt();
                System.out.println(x + " " + y + " " + weight);

                x--; y--;
                this.adjList[x].add(new Edge(y, weight));
                this.adjList[y].add(new Edge(x, weight));
            }
        }
        catch (Exception e)
        {
            System.out.println("No such File!");
        }
    }

    public Graph(int numofNodes, int numofEdges, List<Pair> edgeLists)
    {
        this.numofNodes = numofNodes;
        this.numofEdges = numofEdges;
        this.adjList = new List[this.numofNodes];

        for(int i = 0; i < this.numofNodes; i++)
            this.adjList[i] = new ArrayList<>();

        for (Pair pair : edgeLists)
        {
            int x = pair.nodeBefore, y = pair.currentNode, weight = pair.distance;
            x--; y--;
            this.adjList[x].add(new Edge(y, weight));
            this.adjList[y].add(new Edge(x, weight));
        }
    }

    public int getSize()
    {
        return numofNodes;
    }

    public void dijkstra(int source, int[] costs, int[] parents)
    {
        costs = new int[this.numofNodes];
        parents = new int[this.numofNodes];

        PriorityQueue<Pair> queue = new PriorityQueue<>();
        int inf = 1000000000;
        Arrays.fill(costs, inf);
        queue.add(new Pair(-1, source, 0));

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
                queue.add(new Pair(currentNode, edge.destination, distance + edge.weight));
        }
    }

    public boolean bellmanFord(int source, int[] costs, int[] parents)
    {
        costs = new int[this.numofNodes];
        parents = new int[this.numofNodes];

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
        costs = new int[this.numofNodes][this.numofNodes];
        predecessors = new int[this.numofNodes][this.numofNodes];

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

        public Pair(int nodeBefore, int currentNode, int distance)
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
