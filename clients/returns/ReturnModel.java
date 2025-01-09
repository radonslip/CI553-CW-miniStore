package clients.returns;

import catalogue.Basket;
import catalogue.Product;
import debug.DEBUG;
import middle.*;

import java.util.Observable;

/**
 * Implements the Model of the cashier client
 */
public class ReturnModel extends Observable
{
  private enum State { process, checked }

  private State       theState   = State.process;   // Current state
  private Product     theProduct = null;            // Current product
  private Basket      theBasket  = null;            // Bought items

  private String      pn = "";                      // Product being processed

  private StockReadWriter theStock     = null;
  private OrderProcessing theOrder     = null;

  /**
   * Construct the model of the Cashier
   * @param mf The factory to create the connection objects
   */

  public ReturnModel(MiddleFactory mf)
  {
    try                                           // 
    {      
      theStock = mf.makeStockReadWriter();        // Database access
      theOrder = mf.makeOrderProcessing();        // Process order
    } catch ( Exception e )
    {
      DEBUG.error("CashierModel.constructor\n%s", e.getMessage() );
    }
    theState   = State.process;                  // Current state
  }
  
  /**
   * Get the Basket of products
   * @return basket
   */
  public Basket getBasket()
  {
    return theBasket;
  }

  /**
   * Re stock 
   * @param productNum The product number of the item
   * @param quantity How many to be added
   */
  public void doReturn(String productNum)
  {
    String theAction = "";
    theBasket = makeBasket();
    pn  = productNum.trim();                    // Product no.
    String pn  = productNum.trim();             // Product no.
    int amount = 1;
    try
    {
  
      if ( theStock.exists( pn ) )              // Stock Exists?
      {                                         // T
        theStock.addStock(pn, amount);          //  Re stock
        Product pr = theStock.getDetails(pn);   //  Get details
        theBasket.add(pr);                      //
        theAction = "";                         // Display 
      } else {                                  // F
        theAction =                             //  Inform Unknown
          "Unknown product number " + pn;       //  product number
      } 
    } catch( StockException e )
    {
      theAction = e.getMessage();
    }
    setChanged(); notifyObservers(theAction);
  }
  


  /**
   * return an instance of a new Basket
   * @return an instance of a new Basket
   */
  protected Basket makeBasket()
  {
    return new Basket();
  }
}
  
