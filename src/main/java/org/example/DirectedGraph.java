package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DirectedGraph extends Graph{
    public DirectedGraph(String path) {
        super();
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

                x--; y--;
                this.adjList[x].add(new Edge(y, weight));
            }
        }
        catch (Exception e)
        {
            System.out.println("No such File!");
        }
    }

    public DirectedGraph(int numofNodes, int numofEdges, List<Pair> edgeLists)
    {
        super();
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
        }
    }
}
