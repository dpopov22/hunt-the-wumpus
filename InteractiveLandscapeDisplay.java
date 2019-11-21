/*
  Template created by Bruce A. Maxwell and Stephanie R Taylor

  Your name and header goes here
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
public class InteractiveLandscapeDisplay {

    // These four fields are as in the LandscapeDisplay class (though 
    // they are now all private)
    private JFrame win;
    private LandscapePanel canvas;    
    private Landscape scape; 
    private int scale;

    /** fields related to demo of mouse interaction **/
    // Unless you have a good reason to report the mouse position in
    // HuntTheWumpus, you should remove these fields and all the
    // code that affects them.
    // There here to demonstrate how you would add a message to the bottom
    // of the window. For HuntTheWumpus, you may want to use it to indicate
    // that the Hunter is armed or close to the Wumpus, or dead.
    JLabel fieldX; // Label field 1, displays the X location of the mouse 
    JLabel fieldY; // Label field 2, displays the Y location of the mouse 

    // controls whether the game is playing or exiting
    private enum PlayState { PLAY, STOP }
    private PlayState state;

    /**
     * Creates the main window
     * @param scape the Landscape that will hold the objects we display
     * @param scale the size of each grid element
     **/		 
    public InteractiveLandscapeDisplay() {
        // The game should begin in the play state.
        this.state = PlayState.PLAY; 
        // Create the elements of the Landscape and the game.
        this.scale = 64; // determines the size of the grid
        this.scape = new Landscape(scale*10,scale*7);
        // This is test code that adds a few vertices.
        // You will need to update this to make a Graph first, then
        // add the vertices to the Landscape.
        Graph g = new Graph(); 
        Vertex v1 = new Vertex(1, 0, 0 );
        v1.setVisible( true );
        v1.setCost( 0 );
        Vertex v2 = new Vertex(1, 0, 1 );
        v2.setVisible( true );
        v2.setCost( 1 );
        Vertex v3 = new Vertex(1, 0, 2 );
        v3.setVisible( true );
        v3.setCost( 2 );
        Vertex v4 = new Vertex(1, 0, 3 );
        v4.setVisible( true );
        v4.setCost( 3 );
        v1.connect(Direction.EAST, v2);
        v2.connect(Direction.WEST, v1);
        v2.connect(Direction.EAST, v3);
        v3.connect(Direction.WEST, v2);
        v3.connect(Direction.EAST, v4);
        v4.connect(Direction.WEST, v3 );
        this.scape.addBackAgent( v1 );
        this.scape.addBackAgent( v2 );
        this.scape.addBackAgent( v3 );
        this.scape.addBackAgent( v4 );        
        
        // Make the main window
        this.win = new JFrame("Basic Interactive Display");
        win.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

        // make the main drawing canvas (this is the usual
        // LandscapePanel we have been using). and put it in the window
        this.canvas = new LandscapePanel( this.scape.getWidth(),
					                                        this.scape.getHeight() );
        this.win.add( this.canvas, BorderLayout.CENTER );
        this.win.pack();

        // make the labels and a button and put them into the frame
        // below the canvas.
        this.fieldX = new JLabel("X");
        this.fieldY = new JLabel("Y");
        JButton quit = new JButton("Quit");
        JPanel panel = new JPanel( new FlowLayout(FlowLayout.RIGHT));
        panel.add( this.fieldX );
        panel.add( this.fieldY );
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

        // for mouse control
        // Make a MouseControl object and then bind it to the canvas
        // (the part that displays the Landscape). When the mouse
        // enters, exits, moves, or clicks in the canvas, the appropriate
        // method will be called.
        MouseControl mc = new MouseControl();
        this.canvas.addMouseListener( mc );
        this.canvas.addMouseMotionListener( mc );

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
            this.setBackground(Color.white);
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

    // This is the class where you define functions that are 
    // executed when certain mouse events take place.
    private class MouseControl extends MouseInputAdapter {
        public void mouseMoved(MouseEvent e) {
            fieldX.setText( "" + e.getPoint().x );
            fieldY.setText( "" + e.getPoint().y );
        }

        public void mouseDragged(MouseEvent e) {
            fieldX.setText( "" + e.getPoint().x );
            fieldY.setText( "" + e.getPoint().y );
        }
        
        public void mousePressed(MouseEvent e) {
            System.out.println( "Pressed: " + e.getClickCount() );
        }

        public void mouseReleased(MouseEvent e) {
            System.out.println( "Released: " + e.getClickCount());
        }

        public void mouseEntered(MouseEvent e) {
            System.out.println( "Entered: " + e.getPoint() );
        }

        public void mouseExited(MouseEvent e) {
            System.out.println( "Exited: " + e.getPoint() );
        }

        public void mouseClicked(MouseEvent e) {
    	    System.out.println( "Clicked: " + e.getClickCount() );
        }
    } // end class MouseControl

    private class Control extends KeyAdapter implements ActionListener {

        public void keyTyped(KeyEvent e) {
            System.out.println( "Key Pressed: " + e.getKeyChar() );
            if( ("" + e.getKeyChar()).equalsIgnoreCase("q") ) {
                state = PlayState.STOP;
            }
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


    public static void main(String[] argv) throws InterruptedException {
        InteractiveLandscapeDisplay w = new InteractiveLandscapeDisplay();
        while (w.state == PlayState.PLAY) {
            w.repaint();
            Thread.sleep(33);
        }
        System.out.println("Disposing window");
        w.dispose();
    }
	
} // end class InteractiveLandscapeDisplay
