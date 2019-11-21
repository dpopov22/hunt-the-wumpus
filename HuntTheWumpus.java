/**
 * File: HuntTheWumpus.java
 * Author: Domnika Popov
 * Date: 05/10/2019
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Point;

import javax.imageio.ImageIO;
import java.util.Scanner; 

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

import java.util.*;

/**
 * Creates a window with two text fields, one buttons, and a large
 * display area. The app then tracks the mouse over the display area.
 **/
public class HuntTheWumpus {

    // These four fields are as in the LandscapeDisplay class (though 
    // they are now all private)
    private JFrame win;
    private LandscapePanel canvas;    
    private Landscape scape; 
    private int scale;
    private Graph rooms; 
	private Hunter h; 
	private Wumpus w; 

    JLabel text; 

    // controls whether the game is playing or exiting
    private enum PlayState { PLAY, STOP, PAUSE }
    private PlayState state;

    /**
     * Creates the main window
     * @param scape the Landscape that will hold the objects we display
     * @param scale the size of each grid element
     **/	
     //constructor	 
    public HuntTheWumpus() {
        // The game should begin in the play state.
        this.state = PlayState.PLAY; 
        // Create the elements of the Landscape and the game.
        this.scale = 64; // determines the size of the grid
        this.scape = new Landscape(scale*10,scale*7);
        // This is test code that adds a few vertices.
        // You will need to update this to make a Graph first, then
        // add the vertices to the Landscape.
		rooms = new Graph(); 
		Scanner scn = new Scanner(System.in); 
		
		System.out.println("What width would you like the map to be?"); 
		String width = scn.nextLine(); 
		System.out.println("What height would you like the map to be?"); 
		String height = scn.nextLine(); 
		Vertex[][] tempMap = new Vertex[Integer.parseInt(height)][Integer.parseInt(width)]; 
		//creates the rooms
		for (int i = 0; i< Integer.parseInt(height); i++)
		{
			for (int j = 0; j< Integer.parseInt(width); j++)
			{
				Vertex room = new Vertex( 1, i, j); 
				tempMap[i][j] = room; 
				rooms.getVertices().add(room); 
				room.setVisible(false); 
				scape.addBackAgent(room); 
			}
		}
		//connects the rooms
		for (int i = 0; i< Integer.parseInt(height); i++)
		{
			for (int j = 0; j<Integer.parseInt(width); j++)
			{
				if(i != 0)
				{
					rooms.addEdge(tempMap[i][j], Direction.NORTH, tempMap[i-1][j]);
				}
				if (i < tempMap.length -1)
				{
					rooms.addEdge(tempMap[i][j], Direction.SOUTH, tempMap[i+1][j]); 
				}
				if (j < tempMap[0].length -1)
				{
					rooms.addEdge(tempMap[i][j], Direction.EAST, tempMap[i][j+1]); 
				}
				if(j!=0)
				{
					rooms.addEdge(tempMap[i][j], Direction.WEST, tempMap[i][j-1]); 
				}
				
			}
		}
		Random rand = new Random(); 
		int hx = rand.nextInt(Integer.parseInt(height)); 
		int hy = rand.nextInt(Integer.parseInt(width)); 
		int wx = rand.nextInt(Integer.parseInt(height)); 
		int wy = rand.nextInt(Integer.parseInt(width)); 
		h = new Hunter(hx, hy);
		h.updateLocation(tempMap[hx][hy]); 
		w = new Wumpus(wx, wy);
		w.updateLocation(tempMap[wx][wy]); 
		rooms.shortestPath(w.getLocation()); 
		scape.addForeAgent(h); 
		scape.addForeAgent(w); 
		
        // Make the main window
        this.win = new JFrame("Hunt the Wumpus");
        win.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

        // make the main drawing canvas (this is the usual
        // LandscapePanel we have been using). and put it in the window
        this.canvas = new LandscapePanel( this.scape.getWidth(),
					                                        this.scape.getHeight() );
        this.win.add( this.canvas, BorderLayout.CENTER );
        this.win.pack();
        this.text = new JLabel("Where is the wumpus??"); 
        
    
       
        JButton quit = new JButton("Quit");
        JPanel panel = new JPanel( new FlowLayout(FlowLayout.RIGHT));
        panel.add(this.text); 
        panel.add( quit );
        this.win.add( panel, BorderLayout.SOUTH);
        this.win.pack();

        // Add key and button controls.
        // We are binding the key control object to the entire window.
        // That means that if a key is pressed while the window is
        // in focus, then control.keyTyped will be executed.
        // Because we are binding quit (the button) to control, control.actionPerformed will
        // be called if the quit button is pressed. If you make more than one button,
        // then the same function will be called. Use an if-statement in the function
        // to determine which button was pressed (check out Control.actionPerformed and
        // this advice should make sense)
        Control control = new Control();
        this.win.addKeyListener(control);
        this.win.setFocusable(true);
        this.win.requestFocus();
        quit.addActionListener( control );

        // The last thing to do is make it all visible.
        this.win.setVisible( true );

    }

    private class LandscapePanel extends JPanel {
        /**
         * Creates the drawing canvas
         * @param height the height of the panel in pixels
         * @param width the width of the panel in pixels
         **/
        public LandscapePanel(int height, int width) {
            super();
            this.setPreferredSize( new Dimension( width, height ) );
            this.setBackground(Color.black);
        }

        /**
         * Method overridden from JComponent that is responsible for
         * drawing components on the screen.  The supplied Graphics
         * object is used to draw.
         * 
         * @param g		the Graphics object used for drawing
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            scape.draw( g, scale );
        }
    } // end class LandscapePanel


    private class Control extends KeyAdapter implements ActionListener {

        public void keyTyped(KeyEvent e) {
            System.out.println( "Key Pressed: " + e.getKeyChar() );
            if( ("" + e.getKeyChar()).equalsIgnoreCase("q") ) {
                state = PlayState.STOP;
            }
            //allows to arm and disarm
            if( ("" + e.getKeyChar()).equalsIgnoreCase(" ") &&
            h.getAlive() == true && w.getAlive() == true)
            {
            	if(h.getArmed() == false)
            	{
            		h.setArmed(true); 
            		text.setText( "Hunter is Armed" );
            	}
            	else
            	{
            		h.setArmed(false); 
            		text.setText("Where is the wumpus??"); 
            	}
            }
            //allows for hunter to either move or shoot in the north direction 
            if( ("" + e.getKeyChar()).equalsIgnoreCase("w") && h.getLocation().getNeighbor(Direction.NORTH)!= null &&
            h.getAlive() == true && w.getAlive() == true){
            		if (h.getArmed() == true)
            		{
            			h.shoot(Direction.NORTH, w); 
            			if (h.shoot(Direction.NORTH, w) == true)
            			{
            				w.setAlive(false);
            				text.setText( "You win! The wumpus is dead" );
            			}
            			else
            			{
            				h.setAlive(false); 
            				text.setText( "You missed...the wumpus wins" );
            			}
            			w.setVisible(true); 
            		}
            		else if (h.getArmed() == false)
            		{
            			h.updateLocation(h.getLocation().getNeighbor(Direction.NORTH));
            		}
            }
             //allows for hunter to either move or shoot in the south direction 
             if( ("" + e.getKeyChar()).equalsIgnoreCase("s")&& h.getLocation().getNeighbor(Direction.SOUTH)!= null &&
            h.getAlive() == true && w.getAlive() == true ) {
             		if (h.getArmed() == true)
            		{
            			h.shoot(Direction.SOUTH, w); 
            			if (h.shoot(Direction.SOUTH, w) == true)
            			{
            				w.setAlive(false); 
            				text.setText( "You win! The wumpus is dead" );
            			}
            			else
            			{
            				h.setAlive(false); 
            				text.setText( "You missed...the wumpus wins" );
            			}
            			w.setVisible(true);
            		}
            		else
            		{
               			h.updateLocation(h.getLocation().getNeighbor(Direction.SOUTH));
               		}
            }
             //allows for hunter to either move or shoot in the east direction 
             if( ("" + e.getKeyChar()).equalsIgnoreCase("d")&& h.getLocation().getNeighbor(Direction.EAST)!= null &&
            h.getAlive() == true && w.getAlive() == true ) {
             		if (h.getArmed() == true)
            		{
            			h.shoot(Direction.EAST, w); 
            			if (h.shoot(Direction.EAST, w) == true)
            			{
            				w.setAlive(false); 
            				text.setText( "You win! The wumpus is dead" );
            			}
            			else
            			{
            				h.setAlive(false);
            				text.setText( "You missed...the wumpus wins" );
            			}
            			w.setVisible(true);  
            		}
            		else
            		{
               		 	h.updateLocation(h.getLocation().getNeighbor(Direction.EAST));
               		}
            }
             //allows for hunter to either move or shoot in the west direction 
             if( ("" + e.getKeyChar()).equalsIgnoreCase("a")&& h.getLocation().getNeighbor(Direction.WEST)!= null &&
            h.getAlive() == true && w.getAlive() == true ) {
            		if (h.getArmed() == true)
            		{
            			h.shoot(Direction.WEST, w); 
            			if (h.shoot(Direction.WEST, w) == true)
            			{
            				w.setAlive(false); 
							text.setText( "You win! The wumpus is dead" );
            			}
            			else
            			{
            				h.setAlive(false); 
            				text.setText( "You missed...the wumpus wins" );
            			}
            			w.setVisible(true);
            		}
            		else
            		{
               			h.updateLocation(h.getLocation().getNeighbor(Direction.WEST)); 
               		}
            }   
            //sets the hunter to dead          
            if(h.getLocation() == w.getLocation())
            {
            		h.setAlive(false); 
            		w.setVisible(true); 
            		text.setText( "You lose...the wumpus got you" );				
            }
            repaint();  
        }

        public void actionPerformed(ActionEvent event) {
            // If the Quit button was pressed
            if( event.getActionCommand().equalsIgnoreCase("Quit") ) {
		        System.out.println("Quit button clicked");
                state = PlayState.STOP;
            }
        }
    } // end class Control

    public void repaint() {
    	this.win.repaint();
    }

    public void dispose() {
	    this.win.dispose();
    } 

	//creates the game
    public static void main(String[] argv) throws InterruptedException {
       HuntTheWumpus w = new HuntTheWumpus();
        while (w.state == PlayState.PLAY){
            w.repaint();
            Thread.sleep(33);
        }
            System.out.println("Disposing window");
        	w.dispose();
    }
	
}
