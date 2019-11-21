/**
 * File: Wumpus.java
 * Author: Domnika Popov
 * Date: 05/20/2019
 */
import java.util.Collection; 
import java.util.Comparator;
import java.util.ArrayList; 
import java.awt.Color;
import java.awt.Graphics;


public class Wumpus extends Agent
{
	private Vertex curr_loc;
	private boolean visible; 
	private boolean alive; 
	
	//constructor
	public Wumpus(int x,int y)
	{
		super(x, y); 
		this.curr_loc = new Vertex(1, x, y); 
		this.visible = false;  
		this.alive = true; 
	}
	//gets the alive state of the wumpus
	public boolean getAlive()
	{
		return this.alive; 
	}
	//sets the alive state of the wumpus
	public void setAlive(boolean status )
	{
		this.alive = status; 
	}
	//returns whether the wumpus is visible
	public boolean getVisible()
	{
		return this.visible; 
	}
	//sets the visibility state of the wumpus
	public void setVisible(boolean visibility)
	{
		this.visible = visibility; 
	}
	
	//draws the wumpus at its location in different colors depending on the state
	public void draw(Graphics g, int scale)
	{
		if (!this.visible)
            return;
        int xpos = (int)this.curr_loc.getY()*scale;
        int ypos = (int)this.curr_loc.getX()*scale;
        int border = 2; 
        
        // draw rectangle for the walls of the room
        if (this.getAlive() == true)
        {
       		g.setColor(Color.red);
       	}
       	else
       	{
       		g.setColor(Color.white); 
       	}
        g.fillOval(xpos+(scale/4 - 2)+border, ypos+(scale/4-2)+border, scale/2, scale/2 );
	}
	
	//updates the wumpus location
	public void updateLocation(Vertex newlocation)
	{
		this.curr_loc = newlocation; 
	}
	//gets the location of the wumpus
	public Vertex getLocation()
	{
		return this.curr_loc; 
	}
	
}