import java.util.ArrayList;
import java.util.List;

// This code uses a depth-first search algorithm to 
// explore all possible paths through the cities and
// find the shortest one. It starts from city 0 and
// recursively visits each unvisited city,
// adding the distance between the current city and
// the next one to the total distance.
// When all cities have been visited,
// it calculates the distance to return to
// the starting city and compares it to the
// current minimum distance. If it is shorter,
// it updates the minimum distance and the minimum path.
// This approach is simple and straightforward,
// but it is not efficient for large numbers of
// cities because it generates and explores a large
// number of paths, many of which are not promising
// candidates for the optimal solution. As a result,
// this approach may not be practical for larger
// instances of the TSP. There are many other 
// algorithms and approaches that can be used to
// solve the TSP more efficiently, such as heuristics,
// approximations, and metaheuristics.
// These approaches trade off optimality for
// faster runtime, and may be more suitable for
// larger instances of the TSP.

public class TSP {
  public static List<Integer> solve(int[][] distances, int numCities) {
    // Initialize variables
    int minDistance = Integer.MAX_VALUE;
    List<Integer> minPath = null;

    // Initialize visited array and path list
    boolean[] visited = new boolean[numCities];
    List<Integer> path = new ArrayList<>();

    // Start DFS from city 0
    dfs(distances, numCities, 0, visited, path, 0, minDistance, minPath);

    return minPath;
  }

  private static void dfs(int[][] distances, int numCities, int city, boolean[] visited,
                         List<Integer> path, int distance, int minDistance, List<Integer> minPath) {
    // Add current city to path
    path.add(city);
    visited[city] = true;

    // Check if all cities have been visited
    if (path.size() == numCities) {
      // Calculate distance to return to starting city
      distance += distances[city][0];

      // Update minimum distance and path if necessary
      if (distance < minDistance) {
        minDistance = distance;
        minPath = new ArrayList<>(path);
      }
    } else {
      // Visit unvisited cities
      for (int i = 0; i < numCities; i++) {
        if (!visited[i]) {
          dfs(distances, numCities, i, visited, path, distance + distances[city][i], minDistance, minPath);
        }
      }
    }

    // Backtrack
    path.remove(path.size() - 1);
    visited[city] = false;
  }
}
