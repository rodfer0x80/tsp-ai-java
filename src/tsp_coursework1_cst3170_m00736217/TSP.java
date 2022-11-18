package tsp_coursework1_cst3170_m00736217;

import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;

public class TSP {
	
	public static ArrayList<ArrayList<Integer>> solve(ArrayList<HashMap<Integer, ArrayList<Integer>>> data_set){
		ArrayList<Integer> distance_travelled = new ArrayList<Integer>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		
		
		ret.add(distance_travelled);
		ret.add(path);
		return ret;
	}
	
}
