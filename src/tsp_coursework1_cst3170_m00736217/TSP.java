package tsp_coursework1_cst3170_m00736217;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

public class TSP {
	
	private final Map<String, Map<String, Integer>> distances;
	
    public TSP(Map<String, Map<String, Integer>> distances) {
        this.distances = distances;
    }
    
    public static <T> void swap(T[] array, int first, int second) {
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
    
	
	public static ArrayList<ArrayList<Integer>> solve(Stack<Integer> data_set){
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> ret_path = new ArrayList<Integer>();
		ArrayList<Integer> ret_distance = new ArrayList<Integer>();
		int distance_travelled = 0;

		TSPDFS tsp_dfs = new TSPDFS(data_set);
		tsp_dfs.runme();
		
		distance_travelled = tsp_dfs.getShortestPathCost();
		ret_distance.add(distance_travelled);
		ret_path = tsp_dfs.getShortestPath();

		ret.add(ret_distance);
		ret.add(ret_path);
		return ret;
	}
	
}
