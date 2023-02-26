package question1;

import java.util.ArrayList;
import java.util.List;

class ISPApplication {

//   graph with size v
    int V;
    // defining Graph
    int[][] adjMatrix;
    //Broken Node
    int brokenNode;


    ISPApplication(int V,int[][] edges, int brokenNode) {
        // vertices
        this.V = V;
        // size of array = number of vertices
        this.adjMatrix = new int[V][V];
        //looping the elements of grapht to populate it to Graph
        for (int[] edge : edges) {

            addEdge(edge[0], edge[1]);
        }
        this.brokenNode = brokenNode;
    }

    // Adding an edge undirected graph
    void addEdge(int src, int dest) {
        // Add an edge from src to dest.
        adjMatrix[src][dest] = 1;
        adjMatrix[dest][src] = 1;
    }

    // Removing a vertex with all edges connected to it
    void removeVertex() {
        // Remove the vertex from the matrix
        for (int i = 0; i < V; i++) {
            adjMatrix[i][brokenNode] = 0;
            adjMatrix[brokenNode][i] = 0;
        }
    }

    // Returning disconnected Node's list
    List<Integer> getDisconnectedSubgraphs() {
        removeVertex();
//        to check if the nodes are visited
        boolean[] visited = new boolean[V];
        //Defined a List of Integer as subgraphs that  returns disconnected node
        List<Integer> subgraphs = new ArrayList<>();

        // to find the subgraphs
        for (int v = 0; v < V; ++v) {
            //to check if its visited or not , if visited then it will be broken from the graph
            if (!visited[v] && v != brokenNode) {
                // Defined a subgraph for each as there might be multiple small disconnected due to one breakage point
                List<Integer> subgraph = new ArrayList<>();
                // all vertices from v wwill be printed
                DFSUtil(v, visited, subgraph);
                // to check if subgraph has source of network or breakpoint
                if (!subgraph.contains(brokenNode) && !subgraph.contains(0)) {
                    //if given conditions are satisfied it will be added to subgraph
                    subgraphs.addAll(subgraph);
                }
            }
        }
        //Finally returned subgraphs
        return subgraphs;
    }

    void DFSUtil(int v, boolean[] visited, List<Integer> subgraph) {
        // Mark the current node as visited and add it to the subgraph
        visited[v] = true;
        subgraph.add(v);
        // Recur for all the vertices adjacent to this vertex
        for (int i = 0; i < V; ++i) {
            if (adjMatrix[v][i] == 1 && !visited[i]) {
                DFSUtil(i, visited, subgraph);
            }
        }
    }
    // Driver code
    public static void main(String[] args) {
        //Edges OF the Graph
        int[][] edges = {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}};
        //broken Node
        int brokenNode = 4;
        // Create a graph
        ISPApplication g = new ISPApplication(8,edges,brokenNode);

        System.out.println("Interuppted Networks:");
        //Get Disconencted Networks
        List<Integer> subgraphs = g.getDisconnectedSubgraphs();
        System.out.println(subgraphs);

    }
}