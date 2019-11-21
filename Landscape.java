/**
 * File: Landscape.java
 * Author: Domnika Popov
 * Date: 03/11/2019
 */

import java.util.ArrayList;
import java.awt.Graphics; 

public class Landscape
{
	
	private int w; 
	private int h; 
	private LinkedList<Agent> foregroundAgents;
	private LinkedList<Agent> backgroundAgents; 
	
	//a constructor that sets the width and height fields, and initializes the agent list.
	public Landscape(int w, int h)
	{
		foregroundAgents = new LinkedList<Agent>();
		backgroundAgents = new LinkedList<Agent>(); 
		this.w = w; 
		this.h = h; 	
	}
	//returns height
	public int getHeight()
	{
		return h; 
	}
	
	//returns the width
	public int getWidth()
	{
		return w; 
	}
	
	//inserts an agent to the background 
	public void addBackAgent( Agent b)
	{
		backgroundAgents.addFirst(b);
 	
	}
	//inserts an agent to the foreground 
	public void addForeAgent( Agent f)
	{
		foregroundAgents.addFirst(f);
 	
	}

	//returns the toString() method from the Agents class 
	public String toString()
	{
		String s = " "; 
		for (Agent b: backgroundAgents)
		{ 
			s += " " + b.toString();
			s += "\n";
		}
		for(Agent f: foregroundAgents)
		{
			s += " " + f.toString();
			s += "\n";
		}

		return s; 
	}
	

	//Calls the draw method of all the agents on the Landscape.
	//In the draw method, it should loop through the background agents first, then the foreground agents). 
	public void draw(Graphics g, int scale) 
	{
		for (Agent b: backgroundAgents) 
		{
			b.draw(g, scale); 
		}		
		for(Agent f: foregroundAgents)
		{
			f.draw(g, scale); 
		}
	}
	
	//main method
	public static void main(String[] args)
	{
	// Landscape landscape = new Landscape(500, 500); 
// 	System.out.println(landscape.getWidth()); 
// 	System.out.println(landscape.getHeight()); 
// 	SocialAgent agent1 = new SocialAgent(5, 5); 
// 	SocialAgent agent2 = new SocialAgent(10, 10); 
// 	SocialAgent agent3 = new SocialAgent(15, 15); 
// 	SocialAgent agent4 = new SocialAgent(20, 20); 
// 	
// 	landscape.addAgent(agent1); 
// 	landscape.addAgent(agent2); 
// 	landscape.addAgent(agent3); 
// 	landscape.addAgent(agent4); 
// 	System.out.println(landscape); 
// 	
	}

}