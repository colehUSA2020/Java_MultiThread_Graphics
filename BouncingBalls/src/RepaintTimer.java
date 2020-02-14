/*
RepainterTimer class
 */
import javax.swing.JFrame;

public class RepaintTimer implements Runnable
{
   private final JFrame repaintComponent;

   public RepaintTimer( JFrame frame )
   {
      // specify component to be repainted
      repaintComponent = frame;
   } // end RepaintTimer constructor
   
   public void run()
   {
      while ( true )
      {
         try
         {
            Thread.sleep( 20 );
         } // end try
         catch ( InterruptedException ex )
         {
            ex.printStackTrace();
         } // end catch
         
         repaintComponent.repaint(); // repaint the component
     } // end while
   } // end method run
} // end class RepaintTimer
