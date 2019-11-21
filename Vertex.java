/**
 * File: Vertex.java
 * Author: Domnika Popov
 * Date: 05/10/2019
 */
import java.util.Collection; 
import java.util.Comparator;
import java.util.ArrayList; 
import java.awt.Color;
import java.awt.Graphics;

public class Vertex extends Agent
{
	private Hashmap<Direction, Vertex> vertices;
	private int cost; 
	private boolean marked; 
	private boolean visible;  
	private  Comparator<Vertex>comp; 
	private int x; 
	private int y; 
	
	//constructor
	public Vertex(int cost, int x, int y)
	{
		super(x, y); 
		this.comp = comp; 
		this.vertices = new Hashmap<Direction, Vertex>(200000, new DirectionComparer()); 
		this.cost = 0; 
		this.marked = false; 
		this.visible = false; 
		this.x = x; 
		this.y = y; 
	}
	//returns the cost
	public int getCost()
	{
		return cost; 
	}
	//gets the visible state of the vertex 
	public boolean getVisible()
	{
		return this.visible; 
	}
	//sets the visibility state of the vertex 
	public void setVisible(boolean visibility)
	{
		this.visible = visibility; 
	}
	//returns whether the vertex has been visited or not
	public boolean getMarked()
	{
		return this.marked; 
	}
	//sets the cost 
	public void setCost(int cost)
	{
		this.cost = cost; 
	}
	//sets the marked condition
	public void setMarked(boolean marked)
	{
		this.marked = marked; 
	}
	//returns the opposite direction for each direction
	public Direction opposite(Direction d)
	{
		if (d == Direction.NORTH)
		{
			return Direction.SOUTH; 
		}
		if (d == Direction.SOUTH)
		{
			return Direction.NORTH; 
		}
		if(d == Direction.EAST)
		{
			return Direction.WEST;  
		}
		if(d == Direction.WEST)
		{
			return Direction.EAST; 
		}
		else
		{
			return null; 
		}
	}
	//connects with the other Vertex 
	public void connect(Direction dir, Vertex other)
	{
			this.vertices.put(dir, other); 
	}
	//connects the vertices both ways
	public void bothConnect(Direction dir, Vertex other)
	{
		this.vertices.put(dir, other); 
		other.connect(opposite(dir), this); 
	}
	
	//returns the Vertex in the specified direction or null
	public Vertex getNeighbor(Direction dir)
	{
		return this.vertices.get(dir); 
	}
	
	//returns a Collection of all of the object's neighbors.
	public Collection<Vertex> getNeighbors()
	{
		return this.vertices.values(); 
	}
	//toString method
	public String toString()
	{
		String s = ""; 
		s += "num neighbors:" + vertices.size();
		s += "\n"; 
		s += "cost:" + this.cost; 
		s += "\n"; 
		s += "marked flag:" + this.marked; 
		s += "\n"; 
		return s; 
	}
	//draws the vertices
	public void draw(Graphics g, int scale) {
        if (!this.visible)
            return;
        int xpos = (int)this.getY()*scale;
        int ypos = (int)this.getX()*scale;
        int border = 2;
        int half = scale / 2;
        int eighth = scale / 8;
        int sixteenth = scale / 16;
        
        // draw rectangle for the walls of the rooms
        if (this.cost <= 2)
            // wumpus is nearby
            g.setColor(Color.red);
        else
            // wumpus is not nearby
            g.setColor(Color.white);
        
        g.drawRect(xpos + border, ypos + border, scale - 2*border, scale - 2 * border);
        
        // draw doorways as boxes
        g.setColor(Color.black);
        if (this.vertices.containsKey(Direction.NORTH))
            g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
        if (this.vertices.containsKey(Direction.SOUTH))
            g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth), 
                       eighth, eighth + sixteenth);
        if (this.vertices.containsKey(Direction.WEST))
            g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
        if (this.vertices.containsKey(Direction.EAST))
            g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth, 
                       eighth + sixteenth, eighth);
    }
    
	public static void main(String[] args ) 
	{
		// Vertex v = new Vertex(10); 
// 		Vertex w = new Vertex(20); 
// 		System.out.println(v.opposite(Direction.SOUTH)); 
// 		System.out.println(v.opposite(Direction.NORTH)); 
// 		System.out.println(v.opposite(Direction.EAST)); 
// 		System.out.println(v.opposite(Direction.WEST));
// 		w.bothConnect(Direction.SOUTH, v); 
// 	// 	v.connect(Direction.SOUTH, w); 
// // 		w.connect(Direction.SOUTH, v); 
// 
// 		System.out.println(w); 
// 		System.out.println(v); 
		;
		
		
	}
}
//stores the directions
enum Direction
{
	NORTH, SOUTH, EAST, WEST; 
}
//compares the cost 
class CostComparer implements Comparator<Vertex>
{
	public int compare(Vertex a, Vertex b)
	{
		int diff = a.getCost() - b.getCost(); 
		if (diff == 0)
			return 0; 
		if (diff > 0)
		{
			return -1; 
		}
		else
		{
			return 1; 
		}
		
	}
}
