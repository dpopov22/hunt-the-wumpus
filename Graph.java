/**
 * File: Graph.java
 * Author: Domnika Popov
 * Date: 05/10/2019
 */
import java.util.ArrayList; 
import java.util.Comparator;

public class Graph
{
	private ArrayList<Vertex> vertices; 
	
	//constructor
	public Graph()
	{
		this.vertices = new ArrayList<Vertex>(); 
	}
	//returns all of the vertices
	public ArrayList<Vertex> getVertices()
	{
		return this.vertices; 
	}
	//returns the number of vertices
	public int vertexCount()
	{
		return vertices.size(); 
	}
	//connects two vertices in a specific direction
	public void addEdge(Vertex v1, Direction dir, Vertex v2)
	{
		v1.bothConnect(dir, v2); 
	}
	
	//finds the shortest path to get from one vertex to another
	public void shortestPath(Vertex v0)
	{
		for(Vertex v: this.vertices)
		{
			if (v!= null)
			{
				v.setCost(Integer.MAX_VALUE); 
				v.setMarked(false); 	
			}
		}
		PQHeap<Vertex> q = new PQHeap(new CostComparer());
		v0.setCost(0); 
		q.add(v0); 
		while (q.size() != 0)
		{
			Vertex v = q.remove();  
			v.setMarked(true); 
			for(Vertex w : v.getNeighbors())
			{
				if (w.getMarked() == false && (v.getCost() +1) < w.getCost())
				{
					w.setCost( v.getCost() +1); 
					q.add(w); 
				}
			}
		}
	}
	public static void main(String[] args ) {
		 Graph g = new Graph();
		 Vertex v1 = new Vertex(1, 0, 0);
		 Vertex v2 = new Vertex(1, 0, 1);
		 Vertex v3 = new Vertex(1, 1, 0);
		 Vertex v4 = new Vertex(1, 1, 1);
		 g.addEdge(v1, Direction.EAST, v2);
		 g.addEdge(v2, Direction.SOUTH, v3);
		 g.addEdge(v3, Direction.WEST, v4);
		 g.addEdge(v1, Direction.SOUTH, v4);
		 g.shortestPath(v1);
		 System.out.println(v1.getCost());
		 System.out.println(v2.getCost());
		 System.out.println(v3.getCost());
		 System.out.println(v4.getCost());	
	}
	
}