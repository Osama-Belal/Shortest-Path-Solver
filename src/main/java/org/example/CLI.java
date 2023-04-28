package org.example;


import java.util.Scanner;


public class CLI {
    private Scanner scanner = new Scanner(System.in);
    private static Graph graph;
    private static int[] costs;
    private static int[] parents;
    private static int[][] allPairsCosts;
    private static int[][] allPairsPredecessors;

    public void display() {

        System.out.print("Enter path to graph file: ");
        String filePath = scanner.nextLine();

        graph = new Graph(filePath);

        while (true) {
            System.out.println("Main menu:");
            System.out.println("1. Find shortest paths from source node");
            System.out.println("2. Find shortest paths between all pairs of nodes");
            System.out.println("3. Check if graph contains negative cycle");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleShortestPaths();
                    break;
                case 2:
                    handleAllPairsShortestPaths();
                    break;
                case 3:
                    handleNegativeCycle();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleShortestPaths() {
        System.out.print("Enter source node: ");
        int source = scanner.nextInt();

        System.out.println("Choose algorithm:");
        System.out.println("1. Dijkstra");
        System.out.println("2. Bellman-Ford");
        System.out.println("3. Floyd-Warshall");

        int algorithmChoice = scanner.nextInt();

        costs = new int[graph.getSize()];
        parents = new int[graph.getSize()];

        switch (algorithmChoice) {
            case 1:
                graph.dijkstra(source, costs, parents);
                break;
            case 2:
                boolean noNegativeCycle = graph.bellmanFord(source, costs, parents);
                if (!noNegativeCycle) {
                    System.out.println("Graph contains negative cycle.");
                    return;
                }
                break;
            case 3:
                noNegativeCycle = graph.floydWarshall(allPairsCosts, allPairsPredecessors);
                if (!noNegativeCycle) {
                    System.out.println("Graph contains negative cycle.");
                    return;
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        while (true) {
            System.out.println("Sub-menu:");
            System.out.println("1. Get cost of path to node");
            System.out.println("2. Get path to node");
            System.out.println("3. Return to main menu");
            System.out.print("Enter choice: ");
            int subChoice = scanner.nextInt();

            switch (subChoice) {
                case 1:
                    handleGetCost();
                    break;
                case 2:
                    handleGetPath(source);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleGetCost() {
        System.out.print("Enter destination node: ");
        int destination = scanner.nextInt();

        if (costs[destination] == Integer.MAX_VALUE) {
            System.out.println("There is no path from source to destination.");
        } else {
            System.out.printf("Cost of path from source to node %d: %d\n", destination, costs[destination]);
        }
    }

    private void handleGetPath(int source) {
        System.out.print("Enter destination node: ");
        int destination = scanner.nextInt();

        if (costs[destination] == Integer.MAX_VALUE) {
            System.out.println("There is no path from source to destination.");
        } else {
            System.out.printf("Path from source to node %d: ", destination);
            int current = destination;
            while (current != source) {
                System.out.print(current + " <- ");
                current = parents[current];
            }
            System.out.println(source);
        }
    }

    private void handleAllPairsShortestPaths() {
        System.out.println("Choose algorithm:");
        System.out.println("1. Floyd-Warshall");
        System.out.println("2. Dijkstra");
        System.out.println("3. Bellman-Ford");

        int algorithmChoice = this.scanner.nextInt();

        allPairsCosts = new int[graph.getSize()][graph.getSize()];
        allPairsPredecessors = new int[graph.getSize()][graph.getSize()];

        switch (algorithmChoice) {
            case 1:
                boolean noNegativeCycle = graph.floydWarshall(allPairsCosts, allPairsPredecessors);
                if (!noNegativeCycle) {
                    System.out.println("Graph contains negative cycle.");
                } else {
                    System.out.println("All pairs shortest paths have been calculated using Floyd-Warshall algorithm.");
                }
                break;
            case 2:
                for (int i = 0; i < graph.getSize(); i++) {
                    graph.dijkstra(i, allPairsCosts[i], allPairsPredecessors[i]);
                }
                System.out.printf("Shortest paths have been calculated using Dijkstra's algorithm starting from all nodes.\n");
                break;
            case 3:
                for (int i = 0; i < graph.getSize(); i++) {
                    boolean hasNegativeCycle = graph.bellmanFord(i, allPairsCosts[i], allPairsPredecessors[i]);
                    if (!hasNegativeCycle) {
                        System.out.println("Graph contains negative cycle.");
                        return;
                    }
                }
                System.out.printf("Shortest paths have been calculated using Bellman-Ford algorithm starting from all nodes.\n");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        // sub-menu for path queries
        while (true) {
            System.out.println("What do you want to do now?");
            System.out.println("1. Get the cost of a path between two nodes.");
            System.out.println("2. Print the path between two nodes.");
            System.out.println("3. Return to the main menu.");

            int subMenuChoice = this.scanner.nextInt();

            switch (subMenuChoice) {
                case 1:
                    System.out.print("Enter the source node: ");
                    int source = this.scanner.nextInt();
                    System.out.print("Enter the destination node: ");
                    int dest = this.scanner.nextInt();
                    System.out.printf("The cost of the shortest path from node %d to node %d is %d.\n", source, dest, allPairsCosts[source][dest]);
                    break;
                case 2:
                    System.out.print("Enter the source node: ");
                    source = this.scanner.nextInt();
                    System.out.print("Enter the destination node: ");
                    dest = this.scanner.nextInt();
                    System.out.print("The shortest path from node " + source + " to node " + dest + " is: ");
                    printPath(allPairsPredecessors, source, dest);
                    System.out.println();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }


    private void handleNegativeCycle() {
        System.out.println("Choose algorithm:");
        System.out.println("1. Bellman-Ford");
        System.out.println("2. Floyd-Warshall");

        int algorithmChoice = scanner.nextInt();

        boolean hasNegativeCycle = false;
        switch (algorithmChoice) {
            case 1:
                //////// do i need to scan source or loop over the whole graph
                System.out.print("Enter source node: ");
                int source = scanner.nextInt();
                costs = new int[graph.getSize()];
                parents = new int[graph.getSize()];
                hasNegativeCycle = graph.bellmanFord(source, costs, parents);
                break;
            case 2:
                allPairsCosts = new int[graph.getSize()][graph.getSize()];
                allPairsPredecessors = new int[graph.getSize()][graph.getSize()];
                hasNegativeCycle = graph.floydWarshall(allPairsCosts, allPairsPredecessors);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        if (hasNegativeCycle) {
            System.out.println("Graph contains negative cycle.");
        } else {
            System.out.println("Graph does not contain negative cycle.");
        }
    }


    private static void  printPath(int[][] predecessors, int i, int j) {
        if (i != j) {
            printPath(predecessors, i, predecessors[i][j]);
        }
        System.out.print(j + " <- ");
    }
}
