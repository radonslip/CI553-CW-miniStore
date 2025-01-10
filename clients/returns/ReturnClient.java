package clients.returns;

import catalogue.*;
import middle.MiddleFactory;
import middle.Names;
import middle.RemoteMiddleFactory;

import javax.swing.*;

/**
 * The Return Client.
 */


public class ReturnClient
{
   public static void main (String args[])
   {
     String stockURL = args.length < 1     // URL of stock RW
                     ? Names.STOCK_RW      //  default  location
                     : args[0];            //  supplied location
     String orderURL = args.length < 2     // URL of order
                     ? Names.ORDER         //  default  location
                     : args[1];            //  supplied location
     
    RemoteMiddleFactory mrf = new RemoteMiddleFactory();
    mrf.setStockRWInfo( stockURL );
    mrf.setOrderInfo  ( orderURL );        //
    displayGUI(mrf);                       // Create GUI
  }


  private static void displayGUI(MiddleFactory mf)
  {     
    JFrame  window = new JFrame();
     
    window.setTitle( "Return Client (MVC RMI)");
    window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    
    ReturnModel      model = new ReturnModel(mf);
    ReturnView       view  = new ReturnView( window, mf, 0, 0 );
    ReturnController cont  = new ReturnController( model, view );
    view.setController( cont );

    model.addObserver( view );       // Add observer to the model
    window.setVisible(true);         // Display Screen
  }
}
