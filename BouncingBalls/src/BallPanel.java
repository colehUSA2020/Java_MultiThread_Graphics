/*
BallPanel class
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.ArrayList;

public class BallPanel extends JPanel
{
   private ArrayList< Ball > balls = new ArrayList< Ball >( 20 ); 
   private ExecutorService threadExecutor; // for running Ball runnable
   private JFrame parentWindow; // parent window of JPanel
   private final int MAX_X = 200; // horizontal edge of JPanel
   private final int MAX_Y = 200; // vertical edge of JPanel

   public BallPanel( JFrame window )
   {
      parentWindow = window; // set parent window of JPanel

      // create ExecutorService for running ball runnable
      threadExecutor = Executors.newCachedThreadPool();

      // anonymous inner class handles mouse events
      addMouseListener( 
         new MouseAdapter() 
         {
            public void mousePressed( MouseEvent event ) 
            {
               createBall( event ); // delegate call to ball starter
               RepaintTimer timer = new RepaintTimer( parentWindow );
              threadExecutor.execute(timer);
            } // end method mousePressed
         } // end anonymous inner class
      ); // end call to addMouseListener
   } // end BallPanel constructor

   // create a ball and set it in motion
   private void createBall( MouseEvent event )
   {
      int x = event.getX(); // get x position of mouse press
      int y = event.getY(); // get y position of mouse press
      Ball ball = new Ball( x, y ); // create new ball
      threadExecutor.execute( ball ); // set ball in motion
      balls.add( ball ); // place new ball in the ArrayList
   } // end method createBall

   // return minimum size of animation
   public Dimension getMinimumSize()
   { 
      return getPreferredSize(); 
   } // end method getMinimumSize

   // return preferred size of animation
   public Dimension getPreferredSize()
   {
      return new Dimension( MAX_X, MAX_Y );
   } // end method getPreferredSize

   // draw balls at current positions
   public void paintComponent( Graphics g )
   {
      super.paintComponent( g );

      for ( Ball b : balls )
      {
         g.setColor( b.getColor() ); // set color 
         g.fillOval( b.getX(), b.getY(), 10, 10 ); // draw
      } // end for
   } // end method paintComponent
} // end class BallPanel