package tsp_coursework1_cst3170_m00736217;

import java.util.ArrayList;
import java.util.Stack;

public class TSP {
	
	public static ArrayList<ArrayList<Integer>> solve(Stack<Integer> data_set){
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> ret_path = new ArrayList<Integer>();
		ArrayList<Integer> ret_distance = new ArrayList<Integer>();
		int distance_travelled = 0;

		//DFS tsp_dfs = new DFS(data_set);

		//distance_travelled = tsp_dfs.getShortestPathCost();
		ret_distance.add(distance_travelled);
		//ret_path = tsp_dfs.getShortestPath();

		ret.add(ret_distance);
		ret.add(ret_path);
		return ret;
	}
	
}
