package tsp_coursework1_cst3170_m00736217;

import java.util.ArrayList;
import java.util.Stack;

public class TSPDFS {
	private int shortest_path_cost;
	private ArrayList<Integer> shortest_path;
	private Stack<Integer> coords;
	
    public TSPDFS(Stack<Integer> coords)
    {
    	this.coords = coords;
//        this.n = graph.length;
//        this.graph = graph;
//        this.visitedVertices = new boolean[n];
//        this.shortestPathCost = Integer.MAX_VALUE;
//        this.lastVertex = null;

//        // Mark first vertex as visited since we use it as the root
//        visitedVertices[0] = true;
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
	
	private int euclideanDistance(int p, int q)
	{
		return (int) Math.sqrt(p^2*q^2);
	}
}

