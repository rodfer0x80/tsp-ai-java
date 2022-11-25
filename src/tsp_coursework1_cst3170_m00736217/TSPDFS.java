package tsp_coursework1_cst3170_m00736217;

import java.util.ArrayList;
import java.util.Stack;

public class TSPDFS {
	private int shortest_path_cost;
	private ArrayList<Integer> shortest_path;
	
	private Stack<Integer> coords;
	private int n;
	private boolean[] visited_vertices;
	private Object last_vertex;
	
    public TSPDFS(Stack<Integer> coords)
    {
    	this.coords = coords;
    	this.n = coords.size();
    	this.visited_vertices = new boolean[n];
    	this.shortest_path_cost = Integer.MAX_VALUE;
    	this.last_vertex = null;
    	this.visited_vertices[0] = true; // root vertix should be marked from start
    }
	
	public int getShortestPathCost() 
	{
		return this.shortest_path_cost;
	}
	
	public ArrayList<Integer> getShortestPath() 
	{
		return this.shortest_path;
	}
	
	public int runme()
	{
		return 0;
	}
	
}

