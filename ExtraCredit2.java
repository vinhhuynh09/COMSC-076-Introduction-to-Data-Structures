/***********************************************************************************************************************
 * Name: Vinh Huynh
 * Assignment: Extra Credit #2
 * Course: COMSC 076. Summer 2023
 * Date: July 26, 2023
 ***********************************************************************************************************************
 * NOTE: PLEASE READ IT'S VERY IMPORTANT
 * The instruction said to use
 * https://liveexample.pearson.com/test/GraphSample1.txt
 * This site can’t be reached
 * Please use one of these two urls:
 *          https://liveexample.pearsoncmg.com/test/GraphSample1.txt
 *          https://liveexample.pearsoncmg.com/test/GraphSample2.txt
 * *********************************************************************************************************************
 * Description:
 Write a program that reads the data for a graph from  a file and determines whether the graph is connected.
 The first line in the file contains a number that indicates the number of vertices (n).
 The vertices are labeled as 0, 1, 2, . . ., n -1. Each subsequent line, with the format u v1 v2, . . .,
 describes edges (u, v1), (u,v2), (u,v3), and so on. The following gives examples of two files that serve as
 input to your program:

 First File

 6

 0 1 2

 1 0 3

 2 0 3 4

 3 1 2 4 5

 4 2 3 5

 5 3 4

 Second File:

 6

 0 1 2 3

 1 0 3

 2 0 3

 3 0 1 2

 4 5

 5 4

 Your program should prompt the user to enter a URL for the file, read the data from the file, create an instance g
 of UnweightedGraph, invoke g. print edges( ) to display the edges, and invoke dfs ( ) to obtain an instance tree of
 UnweightedGraph<V>.SearchTree. If tree.getNumberOfVerticesFound( ) is the same as the number of vertices in the graph,
 the graph is connected.



 Sample output:

 Enter a URL: https://liveexample.pearson.com/test/GraphSample1.txt [Enter]

 The number of vertices is 6

 Vertex 0:   (0, 1),  (0,2)

 Vertex 1:   (1, 0),  (1, 3)

 . . .

 Vertex 5:  (5, 3)  (5, 4)

 The graph is connected.

 See the textbook, Exercise 28.1, pages 1077-78 for more details.

 **********************************************************************************************************************/

import java.io.*;
import java.util.*;
import java.net.URL;

public class ExtraCredit2 {
    public static void main(String[] args) throws Exception {
        System.out.println("As of July 26, 2023, " + "https://liveexample.pearson.com/test/GraphSample1.txt" + " Can NOT be reach.");
        System.out.println("Please use one of these two urls: ");
        System.out.println("https://liveexample.pearsoncmg.com/test/GraphSample1.txt");
        System.out.println("https://liveexample.pearsoncmg.com/test/GraphSample2.txt");
        System.out.print("Enter a URL: ");
        Scanner urlPath = new Scanner(System.in);

        URL url = new URL(urlPath.nextLine());
        InputStream readUrl = url.openStream();

        byte[] read = readUrl.readAllBytes();
        int len = read.length;
        // By now, I have successfully opened and read data from URL
        // create a temporary file to write data from URL to the temporary file
        // I believe there must be a way to read data directly from URl without having to write to a temporary file
        String tempUrlStr = "tempUrl.txt";

        File tempFile = new File(tempUrlStr);
        tempFile.delete();          // Just in case "tempUrl.txt" is still there, delete it.
        tempFile.createNewFile();   // create temp file "tempUrl.txt". This fill will be deleted when this program exits.

        FileOutputStream tempFileOut = new FileOutputStream(tempFile);
        for (int i = 0; i < len; i++) { // write url data to tempFile
            tempFileOut.write(read[i]);
        }
        readUrl.close();
        tempFileOut.close();

        /**  Open temporary file. This will be the source file */
        File fileOfSource = new File(tempUrlStr);

        Scanner inputFile = new Scanner(fileOfSource);
        int NUMBER_OF_VERTICES = inputFile.nextInt();
        ArrayList<AbstractGraph.Edge> edgeList = new ArrayList<>(); // list of AbstractGraph.Edge objects
        String[] vertices = new String[NUMBER_OF_VERTICES]; // vertices array

        inputFile.nextLine();
        for (int j = 0; j < NUMBER_OF_VERTICES; j++) {
            String s = inputFile.nextLine();
            String[] line = s.split("[\\s+]");
            String u = line[0];
            vertices[j] = u; // Add vertex

            // Add edges for vertex u
            for (int i = 1; i < line.length; i++) {
                edgeList.add(new AbstractGraph.Edge(Integer.parseInt(u), Integer.parseInt(line[i])));
            }
        }

        Graph<String> graph = new UnweightedGraph<>( Arrays.asList(vertices), edgeList);    // Crate a graph
        System.out.println("\nThe number of vertices is " + graph.getSize()); // Print the number of vertices

        for (int u = 0; u < NUMBER_OF_VERTICES; u++) {  // Display edges
            System.out.print("Vertex " + graph.getVertex(u) + ":");
            for (Integer e : graph.getNeighbors(u)) {
                System.out.print(" (" + u + ", " + e + ")");
            }
            System.out.println();
        }

        AbstractGraph.Tree tree = graph.dfs(0); // Obtain an instance tree of AbstractGraph.Tree

        // Test if graph is connected
        System.out.println("The graph is " +
                (tree.getNumberOfVerticesFound() != graph.getSize() ?
                        "not " : "") + "connected");

        inputFile.close();      // Close the file
        fileOfSource.delete();  // delete temp file as if it was never there
    }   // end main
}   // end class

/*********************************************************************
 *          interface Graph<V>
 *********************************************************************/
interface Graph<V> {
    /** Returns the number of vertices in the graph */
    public int getSize();

    /** Return the vertices in the graph */
    public java.util.List<V> getVertices();

    /** Return the object for the specified vertex index */
    public V getVertex(int index);

    /** Return the index for the specified vertex object */
    public int getIndex(V v);

    /** Return the neighbors of vertex with the specified index */
    public java.util.List<Integer> getNeighbors(int index);

    /** Return the degree for a specified vertex */
    public int getDegree(int v);

    /** Print the edges */
    public void printEdges();

    /** Clears the graph */
    public void clear();

    /** Add a vertex to the graph */
    public boolean addVertex(V vertex);

    /** Add an edge to the graph */
    public boolean addEdge(int u, int v);

    /** Obtains a depth-first search tree starting from v */
    public AbstractGraph<V>.Tree dfs(int v);

    /** Obtains a breadth-first search tree starting from v */
    public AbstractGraph<V>.Tree bfs(int v);
}   // end interface Graph<V>

/*********************************************************************
 *          abstract class AbstractGraph<V> implements Graph<V>
 *********************************************************************/
abstract class AbstractGraph<V> implements Graph<V> {
    protected List<V> vertices = new ArrayList<>(); // Store vertices
    protected List<List<Edge>> neighbors = new ArrayList<>(); // Adjacendy lists

    /** Construct an empty graph */
    protected AbstractGraph() {
    }

    /** Construct a graph from vertices and edges stored in arrays */
    protected AbstractGraph(V[] vertices, int[][] edges) {
        for (int i = 0; i < vertices.length; i++)
            addVertex(vertices[i]);

        createAdjacencyLists(edges, vertices.length);
    }

    /** Construct a graph from vertices and edges stored in List */
    protected AbstractGraph(List<V> vertices, List<Edge> edges) {
        for (int i = 0; i < vertices.size(); i++)
            addVertex(vertices.get(i));

        createAdjacencyLists(edges, vertices.size());
    }

    /** Construct a graph for integer vertices 0, 1, 2 and edge list */
    protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++)
            addVertex((V)(new Integer(i))); // vertices is {0, 1, ...}

        createAdjacencyLists(edges, numberOfVertices);
    }

    /** Construct a graph from integer vertices 0, 1, and edge array */
    protected AbstractGraph(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++)
            addVertex((V)(new Integer(i))); // vertices is {0, 1, ...}

        createAdjacencyLists(edges, numberOfVertices);
    }

    /** Create adjacency lists for each vertex */
    private void createAdjacencyLists(
            int[][] edges, int numberOfVertices) {
        for (int i = 0; i < edges.length; i++) {
            addEdge(edges[i][0], edges[i][1]);
        }
    }

    /** Create adjacency lists for each vertex */
    private void createAdjacencyLists(
            List<Edge> edges, int numberOfVertices) {
        for (Edge edge : edges) {
            addEdge(edge.u, edge.v);
        }
    }

    @Override /** Return the number of vertices in the graph */
    public int getSize() {
        return vertices.size();
    }

    @Override /** Return the vertices in the graph */
    public List<V> getVertices() {
        return vertices;
    }

    @Override /** Return the object of the specified vertex */
    public V getVertex(int index) {
        return vertices.get(index);
    }

    @Override /** Return the index for the specified vertex object */
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    @Override /** Return the neighbors of the specified vertex */
    public List<Integer> getNeighbors(int index) {
        List<Integer> result = new ArrayList<>();
        for (Edge e : neighbors.get(index)) {
            result.add(e.v);
        }

        return result;
    }

    @Override /** Return the degree for a specified vertex */
    public int getDegree(int v) {
        return neighbors.get(v).size();
    }

    @Override /** Print the edges */
    public void printEdges() {
        for (int u = 0; u < neighbors.size(); u++) {
            System.out.print(getVertex(u) + " (" + u + "): ");
            for (Edge e : neighbors.get(u)) {
                System.out.print("(" + getVertex(e.u) + ", " +
                        getVertex(e.v) + ") ");
            }
            System.out.println();
        }
    }

    @Override /** Clear teh graph */
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    @Override /** Add a vertex to the graph */
    public boolean addVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            neighbors.add(new ArrayList<Edge>());
            return true;
        }
        else {
            return false;
        }
    }

    /** Add an edge to the graph */
    protected boolean addEdge(Edge e) {
        if (e.u < 0 || e.u > getSize() - 1)
            throw new IllegalArgumentException("No such index: " + e.u);

        if (e.u < 0 || e.v > getSize() - 1)
            throw new IllegalArgumentException("No such index: " + e.u);

        if (!neighbors.get(e.u).contains(e)) {
            neighbors.get(e.u).add(e);
            return true;
        }
        else {
            return false;
        }
    }

    @Override /** Add an edge to the graph */
    public boolean addEdge(int u, int v) {
        return addEdge(new Edge(u, v));
    }

    /** Edge inner class inside the AbstractGraph class */
    public static class Edge {
        public int u; // Starting vertex of the edge
        public int v; // Ending vertex of the edge

        /** Construct an edge for (u, v) */
        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

        public boolean equals(Object o) {
            return u == ((Edge)o).u && v == ((Edge)o).v;
        }
    }

    @Override /** Obtain a DFS tree starting from vertex v */
    public Tree dfs (int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
            parent[i] = -1; // Initialize parent[i] to -1

        // Mark visited vertices
        boolean[] isVisited = new boolean[vertices.size()];

        // Recursively search
        dfs(v, parent, searchOrder, isVisited);

        // Return a search tree
        return new Tree(v, parent, searchOrder);
    }

    /** Recursive method for DFS search */
    private void dfs(int u, int[] parent, List<Integer> searchOrder,
                     boolean[] isVisited) {
        // Store the visited vertex
        searchOrder.add(u);
        isVisited[u] = true; // Vertex v visited

        for (Edge e : neighbors.get(u)) {
            if (!isVisited[e.v]) {
                parent[e.v] = u; // The parent of vertex e.v is u
                dfs(e.v, parent, searchOrder, isVisited); // Recursive search
            }
        }
    }

    @Override /** Starting bfs search from vertex v */
    public Tree bfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
            parent[i] = -1; // Initialize parent[i] to -1

        java.util.LinkedList<Integer> queue =
                new java.util.LinkedList<>(); // list used as a queue
        boolean[] isVisited = new boolean[vertices.size()];
        queue.offer(v); // Enqueue v
        isVisited[v] = true; // Mark it visited

        while (!queue.isEmpty()) {
            int u = queue.poll(); // Dequeue to u
            searchOrder.add(u); // u searched
            for (Edge e : neighbors.get(u)) {
                if (!isVisited[e.u]) {
                    queue.offer(e.v); // Enqueue w
                    parent[e.v] = u; // The parent of w is u
                    isVisited[e.v] = true; // Mark it visited
                }
            }
        }

        return new Tree(v, parent, searchOrder);
    }
    /*********************************************************************
     *          public class Tree
     *   Tree inner class inside the AbstractGraph class
     *********************************************************************/
    public class Tree {
        private int root; // The root of the tree
        private int[] parent; // Store the parent of each vertex
        private List<Integer> searchOrder; // Store the search order

        /** Construct a tree with root, parent, and searchOrder */
        public Tree(int root, int[] parent, List<Integer> searchOrder) {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        /** Return the root of the tree */
        public int getRoot() {
            return root;
        }

        /** Return the parent vertex v */
        public int getParent(int v) {
            return parent[v];
        }

        /** Return an array representing search order */
        public List<Integer> getSearchOrder() {
            return searchOrder;
        }

        /** Return the number of vertices found */
        public int getNumberOfVerticesFound() {
            return searchOrder.size();
        }

        /** Return the path of vertices from a vertex to the root */
        public List<V> getPath(int index) {
            ArrayList<V> path = new ArrayList<>();

            do {
                path.add(vertices.get(index));
                index = parent[index];
            }
            while (index != -1);

            return path;
        }

        /** Print a path from the root to vertex v */
        public void printPath(int index) {
            List<V> path = getPath(index);
            System.out.print("A path form " + vertices.get(root) + " to " +
                    vertices.get(index) + ": ");
            for (int i = path.size() -1; i >= 0; i--)
                System.out.print(path.get(i) + " ");
        }

        /** Print the whole tree */
        public void printTree() {
            System.out.println("Root is: " + vertices.get(root));
            System.out.print("Edges: ");
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] != -1) {
                    // Display an edge
                    System.out.print("(" + vertices.get(parent[i]) + ", " +
                            vertices.get(i) + ") ");
                }
            }
            System.out.println();
        }
    }
}

/*********************************************************************
 *          class UnweightedGraph<V> extends AbstractGraph<V>
 *********************************************************************/
class UnweightedGraph<V> extends AbstractGraph<V> {
    /** Construct an empty graph */
    public UnweightedGraph() {
    }

    /** Construct a graph from vertices and edges stored in arrays */
    public UnweightedGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    /** Construct a graph from vertices and edges stored in List */
    public UnweightedGraph(List<V> vertices, List<Edge> edges) {
        super(vertices, edges);
    }

    /** Construct a graph for integer vertices 0, 1, 2 and edge list */
    public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    /** Construct a graph from integer vertices 0, 1, and edge array */
    public UnweightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }
}   // end class UnweightedGraph<V> extends AbstractGraph<V>
