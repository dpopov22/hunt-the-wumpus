/**
 * File: Agent.java
 * Author: Domnika Popov
 * Date: 03/11/2019
 */
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random; 
import java.awt.Graphics; 

public class Agent
{
private int x0; 
private int y0; 
private int radius; 
private int minNeighbors; 
private int percent; 



	//a constructor that sets the position.
	public Agent(int x0, int y0)
	{  
		this.x0 = x0; 
		this.y0 = y0; 	
		this.radius = radius; 
		this.minNeighbors = minNeighbors; 
		this.percent = percent; 
	}
	//returns the x position
	public int getX()
	{
		return x0; 
	}
	//returns the y position
	public int getY()
	{
		return y0; 
	} 
	//sets the radius
	public void setRadius(int radius)
	{
		this.radius = radius; 
	}
	//sets the number of minimum neighbors
	public void setminNeighbors(int minNeighbors)
	{
		this.minNeighbors = minNeighbors; 
	}
	//sets percent change
	public void setPercent(int percent)
	{
		this.percent = percent; 
	}
	//sets the x position
	public void setX( int newX )
	{
		this.x0 = newX;			
	} 
	//sets the y position
	public void setY( int newY )
	{
		this.y0 = newY;
	}
	//returns x and y coordinates of agents in string form 
	public String toString()
	{
		String s = "";  
		s += "x coordinate:" + x0; 
		s+="\n"; 
		s += "y coordinate:" +  y0; 
	
		return s; 
	} 

	//sets the x and y coordinates for when they move
	public void move(int xd, int yd)
	{
		setX(x0 +xd); 
		setY(y0 +yd); 
	}

	public void draw(Graphics g, int scale)
	{
		;
	}

	//main method 
	public static void main(String[] args)
	{
		// Agent agent = new Agent(0, 0);
// 		agent.setY(3.0); 
// 		agent.setX(2.5); 
// 		System.out.println(agent.toString());   
// 		System.out.println(agent.getX()); 
// 		System.out.println(agent.getY()); 
	}

}
