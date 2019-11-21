/**
 * File: Hunter.java
 * Author: Domnika Popov
 * Date: 05/10/2019
 */
import java.util.Collection; 
import java.util.Comparator;
import java.util.ArrayList; 
import java.awt.Color;
import java.awt.Graphics;


public class Hunter extends Agent
{
	private Vertex curr_loc;
	private boolean armed; 
	private boolean alive; 
	
	//constructor 
	public Hunter(int x,int y)
	{
		super(x, y); 
		curr_loc = new Vertex(1, x, y); 
		this.armed = false;
		this.alive = true; 
		 
	}
	//draws the hunter at its current position
	public void draw(Graphics g, int scale)
	{
        int xpos = (int)this.curr_loc.getY()*scale;
        int ypos = (int)this.curr_loc.getX()*scale;
        int border = 2; 
        
        //change color depending on condition
        if (this.getAlive() == true)
        {
       		if (this.getArmed() == true)
       		{
       			g.setColor(Color.blue); 
       		}
       		if (this.getArmed() == false)
       		{
       			g.setColor(Color.green);
       		}
       	}
    	else
       	{
       		g.setColor(Color.white);
       	}
        g.fillOval(xpos+(scale/4 - 2)+border, ypos+(scale/4-2)+border, scale/2, scale/2 );
	}
	
	//sets the alive state of the hunter
	public void setAlive(boolean alive)
	{
		this.alive = alive; 
	}
	//gets the alive state of the hunter
	public boolean getAlive()
	{
		return this.alive; 
	}
	
	//allows for the hunter to shoot and kill the wumpus
	public boolean shoot(Direction d, Wumpus w)
	{
		Vertex z = this.curr_loc.getNeighbor(d); 
		Vertex y = z.getNeighbor(d); 
		if (z == w.getLocation() || y == w.getLocation())
		{
			return true; //wumpus shot
		}
		else return false; 
	}
	//allows for the hunter to move 
	public void updateLocation(Vertex newlocation)
	{
		Vertex oldlocation = curr_loc; 
		oldlocation.setVisible(false); 
		curr_loc = newlocation;
		curr_loc.setVisible(true); 
		
	}
	//returns the current location of the hunter
	public Vertex getLocation()
	{
		return curr_loc; 
	}
	//returns the armed state of the hunter
	public boolean getArmed()
	{
		return armed; 
	}
	//allows for the hunter to be either armed or disarmed 
	public void setArmed(boolean armed)
	{
		this.armed = armed; 
	}
	
}