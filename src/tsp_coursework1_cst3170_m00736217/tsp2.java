import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class TSP {
  public static List<Integer> solve(int[][] distances, int numCities) {
    // Initialize variables
    List<Integer> path = new ArrayList<>();
    boolean[] visited = new boolean[numCities];

    // Find a minimum spanning tree (MST) of the cities
    List<int[]> mst = findMST(distances, numCities);

    // Find the odd-degree vertices in the MST
    List<Integer> oddVertices = findOddVertices(mst, numCities);

    // Find a minimum-weight perfect matching of the odd vertices
    List<int[]> matching = findMatching(distances, oddVertices);

    // Combine the MST and matching into an Eulerian circuit
    List<Integer> circuit = combineMSTAndMatching(mst, matching);

    // Find the Hamiltonian circuit by shortcutting the circuit
    path = findHamiltonianCircuit(circuit, numCities);

    return path;
  }

  private static List<int[]> findMST(int[][] distances, int numCities) {
    // Initialize MST as an empty list
    List<int[]> mst = new ArrayList<>();

    // Initialize priority queue with all edges
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
    for (int i = 0; i < numCities; i++) {
      for (int j = i + 1; j < numCities; j++) {
        pq.add(new int[] {i, j, distances[i][j]});
      }
    }

    // Use Kruskal's algorithm to find the MST
    int[] parent = new int[numCities];
    for (int i = 0; i < numCities; i++) {
      parent[i] = i;
    }
    while (!pq.isEmpty() && mst.size() < numCities - 1) {
      int[] edge = pq.poll();
      int u = edge[0];
      int v = edge[1];
      int w = edge[2];
      int rootU = find(parent, u);
      int rootV = find(parent, v);
      if (rootU != rootV) {
        mst.add(edge);
        parent[rootU] = rootV;
      }
    }

    return mst;
  }

  private static int find(int[] parent, int u) {
    if (parent[u] != u) {
      parent[u] = find(parent, parent[u]);
    }
    return parent[u];
  }
  private static List<Integer> findOddVertices(List<int[]> mst, int numCities) {
    // Initialize oddVertices list and degree array
    List<Integer> oddVertices = new ArrayList<>();
    int[] degree = new int[numCities];
    for (int[] edge : mst) {
      degree[edge[0]]++;
      degree[edge[1]]++;
    }
    for (int i = 0; i < numCities; i++) {
      if (degree[i] % 2 == 1) {
        oddVertices.add(i);
      }
    }
    return oddVertices;
  }

  private static List<int[]> findMatching(int[][] distances, List<Integer> oddVertices) {
    // Initialize matching as an empty list
    List<int[]> matching = new ArrayList<>();

    // Initialize priority queue with all edges between odd vertices
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
    for (int i = 0; i < oddVertices.size(); i++) {
      for (int j = i + 1; j < oddVertices.size(); j++) {
        int u = oddVertices.get(i);
        int v = oddVertices.get(j);
        pq.add(new int[] {u, v, distances[u][v]});
      }
    }

    // Use a greedy algorithm to find the matching
    while (!pq.isEmpty()) {
      int[] edge = pq.poll();
      int u = edge[0];
      int v = edge[1];
      int w = edge[2];
      if (!isMatched(matching, u) && !isMatched(matching, v)) {
        matching.add(edge);
      }
    }

    return matching;
  }

  private static boolean isMatched(List<int[]> matching, int u) {
    for (int[] edge : matching) {
      if (edge[0] == u || edge[1] == u) {
        return true;
      }
    }
    return false;
  }

  private static List<Integer> combineMSTAndMatching(List<int[]> mst, List<int[]> matching) {
    // Initialize circuit as a list of all vertices in the MST
    List<Integer> circuit = new ArrayList<>();
    for (int[] edge : mst) {
      circuit.add(edge[0]);
      circuit.add(edge[1]);
    }

    // Add the vertices in the matching to the circuit
    for (int[] edge : matching) {
      circuit.add(edge[0]);
      circuit.add(edge[1]);
    }

    return circuit;
  }

    private static List<Integer> findHamiltonianCircuit(List<Integer> circuit, int numCities) {
    // Initialize path as the circuit
    List<Integer> path = new ArrayList<>(circuit);

    // Find the first vertex in the circuit
    int start = circuit.get(0);

    // Try to shortcut the circuit by checking if each pair of consecutive vertices
    // is connected by an edge. If they are, remove the intermediate vertices.
    boolean changed = true;
    while (changed) {
      changed = false;
      for (int i = 0; i < path.size() - 2; i++) {
        int u = path.get(i);
        int v = path.get(i + 1);
        int w = path.get(i + 2);
        if (distances[u][v] + distances[v][w] == distances[u][w]) {
          path.remove(i + 1);
          changed = true;
          break;
        }
      }
    }

    // Add the start vertex to the end of the path
    path.add(start);

    return path;
  }
}
// This code assumes that you have a distances array
// that stores the distances between each pair of cities,
// and a numCities variable that stores the number
// of cities. To use this code, you can call the
// solve function and pass in the distances array
// and the numCities variable as arguments.
// The function will return a list of integers
// representing the order in which to visit the cities.